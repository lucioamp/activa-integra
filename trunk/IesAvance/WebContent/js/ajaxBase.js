/*
 *  Monta a string com os par�metros
 */
function montaURL(formData)
{
	var hoje = new Date();
	var url = formData.action + "?time=" + hoje.getTime();

	if ( formData.elements.length > 0 )
	{
		for ( i = 0; i < formData.elements.length; i++) 
		{
			elem = formData.elements[i];
			
			// Elimina os fieldsets e os bot�es
			if ( (elem.name != undefined) && (elem.name != "") )
			{
				// Elimina os radio n�o marcados
				if ( (elem.type == 'radio') && !elem.checked )
					continue;

				url += "&" + elem.name + "=";
						
				// Determina o valor dos checkbox
				if ( elem.type == 'checkbox' )
					url += (elem.checked ? 'on' : 'off');
				else // Campo comum nome=valor
					url += escape(elem.value);
			}
		}
	}
	
	return url;
}

function getCookie(nome)
{
    var cookieVet = document.cookie.split("; ");
    for (c = 0; c < cookieVet.length; c++)
    {
	cv = cookieVet[c].split("=");
	if ( cv[0] == nome)
	    return unescape(cv[1]);
    }

    return "";
}

function obtemSaidaPagina( obj )
{
	with ( obj )
	{
		if ( responseXML ) 
		{
			if ( responseXML.documentElement ) 
			{
				if ( responseXML.documentElement.tagName == 'parsererror' ) // Firefox
				{
					return 'Erro no parsing do XML (1):<br /> ' + 
						   responseXML.documentElement.firstChild.nodeValue;
				}
				else if ( responseXML.documentElement.tagName == 'error' ) 
				{
					return 'Error ao recuperar XML:<br /> ' + 
						   responseXML.documentElement.firstChild.nodeValue;
				}
			}
			else if ( responseXML.parseError ) // Internet Explorer
			{
				return 'Erro no parsing do XML (2):<br /> ' + 
					   'Code: ' + responseXML.parseError.errorCode + '<br />' +
					   'Reason: ' + responseXML.parseError.reason + '<br />' +
					   'Line: ' + responseXML.parseError.line + '<br />' +
				       '<textarea rows=10 cols=50>' + 
				       responseText + '</textarea>';
			}
			else 
			{
				return 'Nenhum XML v�lido respondido';
			}
		}
		else if ( responseText )
		{
   	    	return responseText;   
		}
		else
		{
			return 'Os objetos responseXML e responseText estavam vazios';
		}
	}
	
	return null;
}

function pedeContaSenha( url, texto, fml )
{
    var j;
    var titulo;
    
    j = open("", "ContaSenha", "height=120,width=600");
    
    titulo = texto.split("=");
    j.document.write("Autentica��o Requisitada por " + titulo[1] + "<br/>");
    j.document.write("<form method='POST' action='" + url + "' ");
    j.document.write("onsubmit='opener.abreFormulario(this, true); self.close(); return true;'>");
    j.document.write("Usu�rio: <input type='text' name='usuAuth'><br/>");
    j.document.write("Senha: <input type='password' name='senAuth'><br/>");

	if  ( fml != null )
	{
		for ( var i = 0; i < fml.elements.length; i++ )
			with ( fml.elements[i] )
			{
				if ( type == "text" )
				{
				    j.document.write("<input type='hidden' name='" + name + 
				    				   "' value='" + value + "'>");
				}
			}
	}

    j.document.write("<input type='submit' value='Envia'>");
    j.document.write("</form>");
    
    j.document.close();
}

function abrePaginaSimples( urlPag, destino )
{
	var divDoc = null;

	divDoc = document.getElementById(destino);
	
urlPag = "http://localhost:8080" + urlPag;

// alert (urlPag);

	var callback = {   
	    success : function(o) {   
			var msg = null;
			
			msg = obtemSaidaPagina( o );
// alert(o.responseText);
			
			if ( msg == null ) 
			{
				var root = o.responseXML.getElementsByTagName('channel');

			    divDoc.innerHTML = null; 
			    
			    // � um arquivo RSS
			    if ( root.length > 0 )
			    {
					parseRSS(o.responseXML, divDoc);
				}
				else
				{
					root = o.responseXML.getElementsByTagName('xforms');

				    // � um arquivo XForms
				    if ( root.length > 0 )
				    {
				    	parseXForms(root[0], divDoc);
				    }
				    else
				    {
				    	divDoc.innerHTML = "<h1>Tipo desconhecido de XML</h1>";
				    }
				}
			}
			else
			    divDoc.innerHTML = msg; 
			    
	    	return false;
    	},   
	    failure : function(o) {   
	    	if ( o.status == "401" )
	    	{
	    		if ( o.statusText.substr(0, 6) == "AvaNCE" )
	    		{
	        		divDoc.innerHTML = "Sua conex�o expirou!<br/>Fa�a login novamente...";
	    		}
	    		else
		    		pedeContaSenha( urlPag, o.statusText, null );
	    	}
	    	else 
        		divDoc.innerHTML = "Erro " + o.status + ":<br/>" +
        		                   o.statusText + "<hr/> CONNECTION FAILED!";
    		return false;
    	}   
	}   

	if ( getCookie("JSESSIONID") != "" )
	{
		// Se URL j� tem par�metros, separador tem que ser '&'
		var sep = (urlPag.indexOf("?") == -1) ? "?" : "&";
		
		urlPag += sep + "sesplat=" + getCookie("JSESSIONID");
	}

	// Connect to our data source and load the data   
	var conn = YAHOO.util.Connect.asyncRequest("GET", urlPag, callback);   

	return false;
}

/*
 * S� pode ser usada para formul�rios
 */
function abreFormulario( formData, fazCritica )
{
	var divDoc = null;
	var urlPag = null;
	
    if ( fazCritica )
    {
		// Faz a cr�tica apropriada do formul�rio
//		if ( !eval( "critica" + formData.name + "(formData)" ) )
		if ( !chamaCritica( formData.name, formData ) ) 			    
			return false;
	}
	
	divDoc = document.getElementById("docDiv");
	
	var callback = {   
	    success : function(o) {   

			var msg = null;
			
			msg = obtemSaidaPagina( o );

			if ( msg == null ) 
			{
			    divDoc.innerHTML = null; 
				parseRSS(o.responseXML, divDoc);
			}
			else
			    divDoc.innerHTML = msg; 
			    
	    	return false;
    	},   
	    failure : function(o) {  

	    	if ( o.status == "401" )
	    	{
	    		if ( o.statusText.substr(0, 6) == "AvaNCE" )
	    		{
	        		divDoc.innerHTML = "Sua conex�o expirou!<br/>Fa�a login novamente...";
	    		}
	    		else
		    		pedeContaSenha( urlPag, o.statusText, formData );
	    	}
	    	else 
        		divDoc.innerHTML = "Erro " + o.status + ":<br/>" +
        		                   o.statusText + "<hr/> CONNECTION FAILED!";
    		return false;
    	}   
	}   
	
	var tamanho;
    var campoHidden;
    
	// Codifica cada um dos par�metros do formul�rio para resolver
	// problema da acentua��o.
	tamanho = formData.elements.length;
	for ( i = 0; i < tamanho; i++ )
		with ( formData.elements[i] )
		{
			if ( name.substring(0, 2) == "f_" )
			{
				var valor = value;
				
//				if ( type == 'checkbox' || type == 'radio' )
// se bot�o de radio, n�o pode fazer valor = "off" pois um bot�o anterior com o mesmo nome
// pode j� ter sido marcado anteriormente. Mauricio. 28/11/2008
				if ( type == 'checkbox' )
					if ( !checked )
						valor = "off";
				
// a passagem de campos hidden com bot�es de radio n�o est� funcionando.
			    campoHidden = document.createElement("input");

			    campoHidden.type = "hidden";
			    campoHidden.name = name.substring(2);
			    campoHidden.value = escape( valor );
			    formData.appendChild(campoHidden);
			}
		}

	var hoje = new Date();
    
    campoHidden = document.createElement("input");
    
    campoHidden.type = "hidden";
    campoHidden.name = "time";
    campoHidden.value = hoje.getTime();
    formData.appendChild(campoHidden);
	
	YAHOO.util.Connect.setForm( formData );
	
	urlPag = formData.action;

	// Connect to our data source and load the data   
	
    // alert ("request= " + urlPag);

	var conn = YAHOO.util.Connect.asyncRequest("POST", urlPag, callback);   

	return false;
}
