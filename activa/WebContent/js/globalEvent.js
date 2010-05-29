
	var $_SESSION = new Array();
		
	var PROTOCOLO = null;
	
	var desktop = null;
	var menuOption = null;
	var menuOptionConteudo = null;
	
	var showMenuOption = function() { if(menuOption != null) menuOption.fadeIn(500); };
	var hideMenuOption = function() { if(menuOption != null) menuOption.fadeOut(500); };
	
	var menuIsOpen = false;
	
	var hidedServices = false;
	
	var clearFocus = function()
	{
		if(menuIsOpen == true)
			return true;
		
		var minimizedWindows = $('#menuBar table#minimizedWindows tr');
		
		minimizedWindows.find('[class$="-focused"]').each(function(i, o) {
			var $this = $(o); $this.attr('class', $this.attr('class').replace('-focused', ''));
		});
		
		var dialogs = $('.ui-dialog');
		dialogs.changeClass('ui-widget-content', 'ui-widget-content-disabled').find('.conteudo').css('opacity', 0.43);
		dialogs.find('.ui-dialog-titlebar').changeClass('ui-widget-header', 'ui-widget-header-disabled');
	};
	
	var showTelaAmbiente = function(param)
	{
		desktop.hide();
		$('#menuBar').hide();
		request.html($('body'), null, 'servicos/ambiente/selecionar.jsp', false, function() {
			$('#selecionar_ambiente').showDialog({
				minimize: false,
				maximize: false,
				modal: true,
				showCloseButton: false,
				title: 'Ambiente',
				width: '600',
				maxHeight: '450',				
				draggable: true,
				reload: false,
				position: ["center", 60]
			});
		});
	};
	
	var verificarSession = function()
	{
		if($_SESSION['id'] == 0)
		{
			$_SESSION['id'] = setInterval(function() {
				//if($_SESSION['verificandoSession'] == false)
				{
					//$_SESSION['verificandoSession'] = true;
					$.post('../../LoginServlet?opcao=1', function(data) {
						//$_SESSION['verificandoSession'] = false;
						var result = trim(data);
						if(result != 'true' && result != 'processando')
						{
							clearInterval($_SESSION['id']);
							hideMenuOption();
							$(this).alert('Sessão expirada.', null, {clickOK: function() { location.href = 'index.jsp'; }});
						}
					});
				}
			}, 1200);
		}
	};
	
	var showTelaNovoAmbiente = function()
	{
		desktop.hide();
		$('#menuBar').hide();
		request.html($('body'), null, 'servicos/ambiente/incluir.jsp', false, function() {
			$('#novo_ambiente').showDialog({
				minimize: false,
				maximize: false,
				modal: true,
				showCloseButton: false,
				title: 'Gerenciador de Ambiente',
				width: '380',
				draggable: true,
				position: 'center',
				reload: false,
				position: ["center", 60]
			});
		});
	};

	function globalEvent(modulo)
	{
		desktop = $('#desktop');
		menuOption = $('#menuOption');
		menuOptionConteudo = menuOption.find('#conteudo');
		
		PROTOCOLO = modulo;
		$_SESSION['id'] = 0;
		$_SESSION['verificandoSession'] = false;
				
		var mensagem="";
		function clickIE() {if (document.all) {(mensagem);return false;}}
		function clickNS(e) {if 
		(document.layers ||(document.getElementById&&!document.all)) {
		if (e.which==2||e.which==3) {(mensagem);return false;}}}
		if (document.layers) 
		{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
		else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}
		document.oncontextmenu=new Function("return false");
		
		switch(modulo)
		{
			case "adm":
				menuOption.css('width', '157px').css('height', '267px').find('div#servicos').css('top', '2px').css('width', '150px').css('height', '230px');				
					
				desktopEvent.click();
				menuEvent.click().options();
				
				desktopEvent.loadIcon();
				
				verificarSession();
				
				break;
				
			case "restrita":
				switch(request.get('page'))
				{
					case 'TROCAR_AMBIENTE':
						showTelaAmbiente();
						break;
						
					case 'NOVO_AMBIENTE':
						showTelaNovoAmbiente();
						break;
						
					default:
						//loginEvent.showDialog();
				}
				
				desktop.contextMenu({
					buttons: {
						Meus_Dados: {
							onClick: function() {
								menuOptionConteudo.find('img.membroFoto').click();
							}
						},
						Perfil_do_Ambiente: {
							onClick: function() {
								menuOptionConteudo.find('a[href="#PERFIL_AMBIENTE"]').click();
							}
						},
						Mostrar_Área_de_Trabalho: {
							onClick: function() {
								/*if(hidedServices == false)
								{
									$('.ui-dialog').fadeIn(200);
									hidedServices = true;
								}
								else
								{
									$('.ui-dialog').fadeOut(200);
									hidedServices = false;
								}*/
								$('.ui-dialog').fadeOut(200);
							}
						},
						Sair: {
							icon: "quit",
							onClick: function() {
								hideMenuOption();
								desktop.alert('Tem certeza que deseja sair?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$.post('../../LoginServlet?opcao=2', null,	function(data) {
											location.href = 'index.jsp';
										});
									}
								});
							}
						}
					}
				});

				menuEvent.click().options().barClick();
				
				desktopEvent.loadIcon();
				
				verificarSession();
				
				break;
				
			case "principal":
				/*$('.box').corner();
				$('.cadastrar').corner("Round bl 10px");
				$(".column").disableSelection().sortable({connectWith: '.column'});

				$(".portlet").addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
					.find(".portlet-header")
						.addClass("ui-widget-header ui-corner-all")
						.prepend('<span class="ui-icon ui-icon-plusthick"></span>')
						.end()
					.find(".portlet-content");

				$(".portlet-header .ui-icon").click(function() {
					$(this).toggleClass("ui-icon-minusthick");
					$(this).parents(".portlet:first").find(".portlet-content").slideToggle();
				});
				
				$('div#detalhe').hide();
				
				$('a[href="#DETALHE"]').click(function(e) {
					e.preventDefault();
					$('div#detalhe').showDialog({
						minimize: false,
						maximize: false,
						show: 'fold',
						draggable: false,
						title: 'Sobre o sistema - detalhes',
						width: 1000,
						modal: true
					});
				});*/
				
				break;
		}
	}
	