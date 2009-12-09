
function obtemFilho(filhos, nome)
{
	for ( var j = 0; j < filhos.length; j++ )
	{
		if ( filhos[j].nodeName == nome )
			return filhos[j];
	}
	
	return null;
}

function obtemTexto(filhos, nome)
{
	var f = obtemFilho(filhos, nome);
	
	if ( (f != null) && (f.firstChild != null) && ("nodeValue" in f.firstChild) )
		return f.firstChild.nodeValue;
	     
	return "";
}

function parseXForms(raiz, goal) 
{
	var formulario = document.createElement('form');
	
	var submission = raiz.getElementsByTagName('submission');
	if ( submission.length < 1 )
	{
		alert("XML sem submission");
		return;
	}

    var url = submission[0].getAttribute('action');
    		
	formulario.setAttribute('name', submission[0].getAttribute('id'));	
	formulario.setAttribute('action', url);	
	formulario.setAttribute('method', 'POST');	
	formulario.setAttribute('onsubmit', 'return abreFormulario(this, true);');	
	
	var p = url.lastIndexOf("/");
	url = url.substring(0, p);

	var tabela = document.createElement('table');
	tabela.setAttribute('style', 'border-collapse: separate; border-spacing: 10px');

	for ( var i = 0; i < raiz.childNodes.length; i++ )
	{
		with ( raiz.childNodes[i] )
		{
			var campo = null;
	
			if ( nodeName == 'input' ) 
			{
				campo = document.createElement('input');
				campo.setAttribute('type', 'text');
				campo.setAttribute('name', getAttribute('ref'));
				campo.setAttribute('size', '50');
			}
			else if ( nodeName == 'textarea' )
			{
				campo = document.createElement('textarea');
				campo.setAttribute('name', getAttribute('ref'));
				campo.setAttribute('cols', '45');
				campo.setAttribute('rows', '6');
			}
			else if ( nodeName == 'secret' )
			{
				campo = document.createElement('input');
				campo.setAttribute('type', 'password');
				campo.setAttribute('name', getAttribute('ref'));
				campo.setAttribute('size', '50');
			}
			else if ( nodeName == 'select' )
			{
				if ( getAttribute('appearance') == 'full' )
				{
					campo = document.createElement('input');
					campo.setAttribute('type', 'checkbox');
					campo.setAttribute('name', getAttribute('ref'));
				}
			}
			else if ( nodeName == 'select1' )
			{
				if ( getAttribute('appearance') == 'full' )
				{
					campo = document.createElement('input');
					campo.setAttribute('type', 'radio');
					campo.setAttribute('name', getAttribute('ref'));
				}
				else
				{
					campo = document.createElement('select');
					campo.setAttribute('name', getAttribute('ref'));
				}
			}
			else if ( nodeName == 'submit' )
			{
				campo = document.createElement('input');
				campo.setAttribute('type', 'submit');
			}
			
			if ( campo != null )
			{
				var texto;
				var tr = document.createElement('tr');
				tabela.appendChild(tr);

				var tdLabel = document.createElement('td');
				var tdCampo = document.createElement('td');
				
				texto = obtemTexto(childNodes, 'label');
				
				if ( texto != "" )
				{
					if ( nodeName != 'submit' )
					{
						var nomeLabel = document.createTextNode(texto);
						tdLabel.appendChild(nomeLabel);
					}
					else
					{
						var botao = document.createElement('input');

						botao.setAttribute('type', 'button');
						botao.setAttribute('value', 'Voltar');
						botao.setAttribute('onclick', 'abrePaginaSimples("' + url +
																   '", "docDiv");');	

						tdCampo.setAttribute('align', 'right');
						tdCampo.appendChild(botao);
						
						campo.setAttribute('value', texto);
					}
				}

				tdCampo.appendChild(campo);

				if ( nodeName == 'select' )
				{
					var item = obtemFilho(childNodes, 'item');
					
					if ( (item != null) && (item.childNodes != null) )
					{
						texto = obtemTexto(item.childNodes, 'label');
						if ( texto != "" )
						{
							texto = " " + texto;
							var nomeLabel = document.createTextNode(texto);
							tdCampo.appendChild(nomeLabel);
						}
						
						texto = obtemTexto(item.childNodes, 'value');
						if ( texto != "" )
							campo.setAttribute('value', texto);
					}
				}
				else if ( nodeName == 'select1' )
				{
					var item;
					
					// Radio Button
					if ( getAttribute('appearance') == 'full' )
					{
						item = obtemFilho(childNodes, 'item');
						
						if ( (item != null) && (item.childNodes != null) )
						{
							texto = obtemTexto(item.childNodes, 'label');
							if ( texto != "" )
							{
								texto = " " + texto;
								var nomeLabel = document.createTextNode(texto);
								tdCampo.appendChild(nomeLabel);
							}
							
							texto = obtemTexto(item.childNodes, 'value');
							if ( texto != "" )
								campo.setAttribute('value', texto);
						}
					}
					else // Select
					{
						var opt;

						for ( var j = 0; j < childNodes.length; j++ )
						{
							if ( childNodes[j].nodeName == 'item' )
							{
								item = childNodes[j];
								
								if ( (item != null) && (item.childNodes != null) )
								{
									opt = document.createElement('option');
									
									texto = obtemTexto(item.childNodes, 'label');
									if ( texto != "" )
									{
										texto = " " + texto;
										var nomeLabel = document.createTextNode(texto);
										opt.appendChild(nomeLabel);
									}
									
									texto = obtemTexto(item.childNodes, 'value');
									if ( texto != "" )
										opt.setAttribute('value', texto);
	
									campo.appendChild(opt);
								}
							}
						}
					}
				}

				tr.appendChild(tdLabel);
				tr.appendChild(tdCampo);
			}
		}
	}	

	formulario.appendChild(tabela);

	goal.appendChild(formulario);
}
