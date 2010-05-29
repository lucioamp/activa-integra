
var loginEvent = function()
{
	return {
		showDialog:function()
		{
			desktop.hide();
			$('#menuBar').hide();
			request.html($('body'), null, '../login.jsp', false, function() {
				var login = $('#login');
				login.showDialog({
					minimize: false,
					maximize: false,
					modal: true,
					showCloseButton: false,
					show: 'fold',
					title: 'Login',
					width: '255',
					position: 'center',
					//resizable: false,
					draggable: false,
					reload: false
				});				
			});
			
			return this;
		}
	}
}();