var notificacaoDialog = function()
{
	return {
		showDialog:function()
		{
			var notificacao = $('#notificacao');
			notificacao.show();
			
			var notificacaoConteudo = $('#notificacaoConteudo');
			notificacaoConteudo.show();
			
			notificacao.showDialog({
				minimize: false,
				maximize: true,
				modal: false,
				showCloseButton: false,
				show: 'fold',
				title: 'Notificação Automática',
				width: '420',
				position: 'right',
				resizable: true,
				draggable: true,
				reload: false,
				posTop: 0,
				height: 300,
				maxHeight: true,
				minHeight: 100
			});
			
			return this;
		},
		fadeOut:function(duration)
		{
			var notificacao = $('#notificacaoConteudo');
			notificacao.fadeOut(duration);
		}
	}
}();

var executaReverseAjax = function()
{
	var request =  new XMLHttpRequest();
	request.open("GET", "../../AplicacaoExternaCometServlet", true);
	request.setRequestHeader("Content-Type",
	                         "application/x-javascript;");

	request.onreadystatechange = function() {
		if (request.readyState == 4) {
            if (request.status == 200){
            	if (request.responseText && request.responseText != null) {
			    	notificacaoDialog.showDialog();
			    	
			    	var div = $('#notificacaoConteudo');
			    	div.html(request.responseText);
			    }
            }
	  	}	
	};
	
	request.send(null);
	
	window.setTimeout('executaReverseAjax()', 60000);
};
