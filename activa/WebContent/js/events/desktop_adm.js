
var desktopEvent = function()
{
	return {
		click:function()
		{
	
			desktop.bind('mousedown', function() { hideMenuOption(); clearFocus(); });
			
			return this;
		},
		serviceIsOpen:function(service)
		{
			var service = $('div.ui-dialog').find(service);
			return (service.html() != null)
		},
		loadIcon:function() {
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
			
			desktop.createIcon('Aplicações Externas', 'puzzle.png').dblclick(function(e){
				getServico("Aplicações Externas").click(); e.preventDefault();
			});	
		}
	}
}();