
var desktopEvent = function()
{
	return {
		click:function()
		{
	
			/*desktop.bind('mousedown', function() { hideMenuOption(); clearFocus(); });*/
			
			return this;
		},
		loadIcon:function()
		{
			var servicos = menuOptionConteudo.find('div#servicos ul');
			
			var getServico = function(nome)
			{
				var servico = null;
				servicos.find('a').each(function(i) {
					if($(this).html() == nome)
					{
						servico = $(this);
						return false;
					}
				});
				
				return servico;
			};

			desktop.createIcon('Mensagens', 'email.png').dblclick(function(e){
				getServico("Mensagens").click(); e.preventDefault();
			});	
			
			desktop.createIcon('Membros', 'membros.gif').dblclick(function(e){
				getServico("Membros").click(); e.preventDefault();
			});
			
			desktop.createIcon('Comunidades', 'comunidade.gif').dblclick(function(e){
				getServico("Comunidades").click(); e.preventDefault();
			});
			
			desktop.createIcon('Artefatos', 'artefato.gif').dblclick(function(e){
				getServico("Artefatos").click(); e.preventDefault();
			});
			
			desktop.createIcon('Blog', 'blog.gif').dblclick(function(e){
				getServico("Blog").click(); e.preventDefault();
			});
			
			desktop.createIcon('Fórum', 'forum.gif').dblclick(function(e){
				getServico("Fórum").click(); e.preventDefault();
			});
			
			desktop.createIcon('EAD', 'curso.gif').dblclick(function(e){
				getServico("EAD").click(); e.preventDefault();
			});
			
			desktop.createIcon('Favoritos', 'favoritos.png', {position: 'absolute', top: '0px', left: '100px'}).dblclick(function(e){
				getServico("Favoritos").click(); e.preventDefault();
			});
			
			desktop.createIcon('RSS', 'rss.png', {position: 'absolute', top: '73px', left: '100px'}).dblclick(function(e){
				e.preventDefault();
			});
			
			desktop.createIcon('Chat', 'chat.png', {position: 'absolute', top: '146px', left: '100px'}).dblclick(function(e){
				e.preventDefault();
			});
			
			desktop.createIcon('Conhecimento Colaborativo', 'solucoes.gif', {position: 'absolute', top: '222px', left: '100px'}).dblclick(function(e){
				getServico("Conhecimento Colaborativo").click(); e.preventDefault();
			});
			
			desktop.createIcon('Browser', 'browser.png', {position: 'absolute', top: '312px', left: '100px'}).dblclick(function(e){
				getServico("Browser").click(); e.preventDefault();
			});
			
			desktop.createIcon('Aplicações Externas', 'puzzle.png', {position: 'absolute', top: '392px', left: '100px'}).dblclick(function(e){
				getServico("Aplicações Externas").click(); e.preventDefault();
			});
			
			return this;
		},
		serviceIsOpen:function(service)
		{
			var service = $('div.ui-dialog').find(service);
			return (service.html() != null)
		}
	}
}();