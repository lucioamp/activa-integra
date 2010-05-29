
var menuEvent = function()
{
	return {
		click:function()
		{
			$('a[id="botaoIniciar"]').click(function(e) { e.preventDefault(); }).bind('mousedown', function(e){
				clearFocus();
				if(menuOption.css('display') == 'none')
					showMenuOption();				
				else
					hideMenuOption();
					
				return false;
			}).bind('mouseup', function(e){			
				$('#menuBar').unbind('mousedown', hideMenuOption).one('mousedown', hideMenuOption);
			}).disableSelection();
			
			return this;
		},
		barClick:function()
		{			
			return this;
		},
		options:function(modulo)
		{
			menuOptionConteudo.find('img.membroFoto').click(function(e) {
				e.preventDefault();
				hideMenuOption();
				$(this).openWindow('PERFIL_USUARIO', {title: 'Dados do Usuário', width: '700', height: '350', file: '../../MembroServlet?opcao=C'}); e.preventDefault();
			});
			
			menuOptionConteudo.find('input#microBlog').enableSelection().blur(function() {
				$.post('../../MembroMicroBlogServlet?opcao=I', {descricao: $(this).val()}, function(data){});
			});			
			menuOptionConteudo.find('a[href="#PERFIL_AMBIENTE"]').click(function(e) {
				$(this).openWindow('PERFIL_AMBIENTE', {title: 'Perfil do Ambiente', width: '730', height: '350', file: '../../MembroAmbienteServlet?opcao=D'}); e.preventDefault();
			});
			
			menuOptionConteudo.find('a[href="#PAINEL_CONTROLE"]').click(function(e) {
				$(this).openWindow('PAINEL_CONTROLE', {title: 'Painel de Controle', width: '700', height: '350', file: 'servicos/painel_controle.jsp'}); e.preventDefault();
			});			
			
			menuOptionConteudo.find('a[href="#TROCAR_AMBIENTE"]').click(function(e) {
				e.preventDefault();
				hideMenuOption();
				$(this).alert('Tem certeza que deseja trocar de ambiente?', null, {
					dialog: DIALOG_CONFIRM,
					clickOK: function() {
						$.post('../../LoginServlet?opcao=6', null,	function(data) {
							location.href = 'index.jsp';
						});
					}
				});
			});
			
			menuOptionConteudo.find('a[href="#NOVO_AMBIENTE"]').click(function(e) {
				e.preventDefault();
				hideMenuOption();
				$('#menuBar').hide();
				request.html($('body'), null, 'servicos/ambiente/novo.jsp', false, function() {
					$('#novo_ambiente').showDialog({
						minimize: false,
						maximize: false,
						modal: true,
						showCloseButton: false,
						title: 'Novo Ambiente',
						width: '380',
						draggable: false,
						position: 'center',
						reload: false
					});
				});
			});
			
			menuOptionConteudo.find('div#perfil a[href="#SAIR"]').click(function(e) {
				e.preventDefault();
				hideMenuOption();
				$(this).alert('Tem certeza que deseja sair?', null, {
					dialog: DIALOG_CONFIRM,
					clickOK: function() {
						$.post('../../LoginServlet?opcao=2', null,	function(data) {
							location.href = 'index.jsp';
						});
					}
				});
			});
		
			var servicos = menuOptionConteudo.find('div#servicos ul').html('')/* Correção para o IE */;

			servicos.createOption('Membros').click(function(e) {
				$(this).openWindow('MEMBRO', {title: 'Membros', width: '700', height: '350', file: 'servicos/membro/index.jsp'}); e.preventDefault();
			});
			
			servicos.createOption('Comunidades').click(function(e) {
				$(this).openWindow('COMUNIDADE', {title: 'Comunidades', width: '700', height: '350', file: 'servicos/comunidade/index.jsp'}); e.preventDefault();
			});
			
			servicos.createOption('Artefatos').click(function(e) {
				$(this).openWindow('ARTEFATO', {title: 'Artefatos', width: '700', height: '350', file: 'servicos/artefato/index.jsp'}); e.preventDefault();
			});
			
			servicos.createOption('Blog').click(function(e) {
				$(this).openWindow('BLOG', {title: 'Blog', width: '700', height: '350', file: 'servicos/blog/index.jsp'}); e.preventDefault();
			});
			
			servicos.createOption('Favoritos').click(function(e) {
				$(this).openWindow('FAVORITO', {title: 'Favoritos', width: '700', height: '350', file: '../../MembroFavoritoServlet?opcao=C'}); e.preventDefault();
			});
			
			servicos.createOption('Mensagens').click(function(e) {
				$(this).openWindow('MENSAGEM', {title: 'Mensagens', width: '700', height: '350', file: 'servicos/perfil/mensagens/index.jsp'}); e.preventDefault();
			});
			
			servicos.createOption('Fórum').click(function(e) {
				$(this).openWindow('FORUM', {title: 'Fórum', width: '700', height: '350', file: 'servicos/forum/index.jsp'}); e.preventDefault();
			});
			
			servicos.createOption('EAD').click(function(e) {
				$(this).openWindow('CURSO', {title: 'EAD', width: '700', height: '350', file: 'servicos/curso/index.jsp'}); e.preventDefault();
			});
			
			servicos.createOption('Conhecimento Colaborativo').click(function(e) {
				$(this).openWindow('SOLUCAO', {title: 'Conhecimento Colaborativo', width: '700', height: '350', file: 'servicos/solucoes/index.jsp'}); e.preventDefault();
			});
			
			servicos.createOption('Browser').click(function(e) {
				$(this).openWindow('BROWSER', {title: 'Browser', width: '700', height: '350', file: 'servicos/browser/index.jsp'}); e.preventDefault();
			});
			
			servicos.createOption('Aplicações Externas').click(function(e) {
				$(this).openWindow('APLICACOES_EXTERNAS', {title: 'Aplicações Externas', width: '700', height: '350', file: '../../MembroAplicacaoServlet?opcao=C'}); e.preventDefault();
			});
				
			var showSubMenuEventOver = null;
			var showSubMenuEventOut = null;
			
			var menuOptionConteudo_subServicos = menuOptionConteudo.find('#sub_servicos');
			var menuOptionConteudo_perfilMenu = menuOptionConteudo_subServicos.find('#perfil_menu');
			
			var menuOptionConteudo_perfil_mouseover = function()
			{
				clearTimeout(showSubMenuEventOut);
					
				if(menuOptionConteudo_subServicos.css('display') == 'none')
				{
					showSubMenuEventOver = setTimeout(function(){
						menuOptionConteudo_subServicos.css('left', '289px');
						menuOptionConteudo_subServicos.show('slide');
						menuOptionConteudo_perfilMenu.show();
					}, 650);
				}
			};
			
			var menuOptionConteudo_perfil_mouseout = function()
			{
				clearTimeout(showSubMenuEventOver);
			
				if(menuOptionConteudo_subServicos.css('display') != 'none')
				{
					showSubMenuEventOut = setTimeout(function(){
						menuOptionConteudo_subServicos.hide('slide', 150);
						menuOptionConteudo_perfilMenu.hide();
					}, 200);
				}
			};
			
			menuOptionConteudo.find('a[href="#PERFIL_USUARIO"]').hover(menuOptionConteudo_perfil_mouseover, menuOptionConteudo_perfil_mouseout).
			click(function(e) { e.preventDefault(); return false; });			
			menuOptionConteudo_subServicos.hover(menuOptionConteudo_perfil_mouseover, menuOptionConteudo_perfil_mouseout);
			
			var subServicos = menuOptionConteudo_perfilMenu.find('ul').html('')/* Correção para o IE */;
			
			subServicos.createOption('Idioma').click(function(e) {
				$(this).openWindow('IDIOMA', {title: 'Idioma', width: '400', height: '350', file: '../../MembroIdiomaServlet?opcao=C'}); e.preventDefault();
			});
			
			subServicos.createOption('Áreas de Interesse').click(function(e) {
				$(this).openWindow('AREA_INTERESSE', {title: 'Áreas de Interesse', width: '400', height: '350', file: '../../MembroAreaInteresseServlet?opcao=C'}); e.preventDefault();
			});
			
			subServicos.createOption('Contatos').click(function(e) {
				$(this).openWindow('CONTATO', {title: 'Contatos', width: '700', height: '350', file: '../../MembroContatoServlet?opcao=C'}); e.preventDefault();
			});
			
			subServicos.createOption('Cursos de Extensão').click(function(e) {
				$(this).openWindow('CURSO_EXTENSAO', {title: 'Cursos de Extensão', width: '700', height: '350', file: '../../CursoExtensaoServlet?opcao=C'}); e.preventDefault();
			});
			
			subServicos.createOption('Áreas de Trabalho').click(function(e) {
				$(this).openWindow('AREA_TRABALHO', {title: 'Áreas de Trabalho', width: '700', height: '350', file: '../../AreaTrabalhoServlet?opcao=C'}); e.preventDefault();
			});
	
			subServicos.createOption('Microblogs').click(function(e) {
				$(this).openWindow('MICROBLOG', {title: 'Microblogs', width: '700', height: '350', file: '../../MembroMicroBlogServlet?opcao=C'}); e.preventDefault();
			});
			
			return this;
		}
	}
}();