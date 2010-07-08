var notificacaoDialog = function()
{
	return {
		showDialog:function()
		{
			var notificacao = $('#notificacao');
			notificacao.showDialog({
				minimize: false,
				maximize: false,
				modal: false,
				showCloseButton: true,
				show: 'fold',
				title: 'Notificação Automática',
				width: '400',
				position: 'right',
				resizable: false,
				draggable: false,
				reload: false
			});				
			
			return this;
		}
	}
}();

var executaReverseAjax = function()
{
	var request =  new XMLHttpRequest();
	request.open("POST", "../../AplicacaoExternaCometServlet", true);
	request.setRequestHeader("Content-Type",
	                         "application/x-javascript;");

	request.onreadystatechange = function() {
		if (request.readyState == 4) {
            if (request.status == 200){
			    if (request.responseText && request.responseText != '') {
			    	var div = $(this).find('#notificacao');
			    	div.html(request.responseText);
			    	
			    	notificacaoDialog.showDialog();
			    	
			    	$(this).fadeOut(3000, function() {
			    		notificacaoDialog.remove();
					});
			    }
            }

            executaReverseAjax();
	  	}
	};
	
	request.send(null);
};
