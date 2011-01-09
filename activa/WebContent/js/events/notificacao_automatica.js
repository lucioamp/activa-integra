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
				title: 'Avisos Automáticos',
				width: '450',
				position: 'right',
				resizable: true,
				draggable: true,
				reload: false,
				posTop: 0,
				height: 500,
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
	notificacaoDialog.showDialog();
	
	var request =  new XMLHttpRequest();
	request.open("POST", "../../AplicacaoExternaCometServlet", true);
	request.setRequestHeader("Content-Type",
	                         "application/x-javascript;");

	request.onreadystatechange = function() {
		if (request.readyState == 4) {
            if (request.status == 200){
            	var lastTag = request.responseText.substring(request.responseText.length - 7).trim();
            	
			    if (request.responseText && request.responseText != null && lastTag != '') {
			    	notificacaoDialog.showDialog();
			    	
			    	var div = $('#notificacaoConteudo');
			    	div.html(request.responseText);
			    }
            }
            
            executaReverseAjax();
	  	}
	};
	
	request.send(null);
};
