
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
		options:function()
		{
			var menuOptionConteudo_mouseover = function(modulo)
			{
				menuOptionConteudo_subServicos.show().find('div').hide();
				modulo.show();
			};
			
			var menuOptionConteudo_mouseout = function(modulo)
			{
				menuOptionConteudo_subServicos.hide();
				modulo.hide();
			};


			var menuOptionConteudo_subServicos = menuOptionConteudo.find('#sub_servicos').css('left', '157px');
			
			var menuOptionConteudo_subServicos_enderecos = menuOptionConteudo_subServicos.find('#enderecos');
			{
				var subService_endereco = menuOptionConteudo_subServicos_enderecos.find('ul').html(''); /* Corre��o para o IE */;
				{	
					subService_endereco.createOption('Unidades de Fed.').click(function(e) {
						e.preventDefault();
						$(this).openWindow('UNIDADE_FEDERACAO', {title: 'Unidades de Fed.', width: '600', height: '350', file: '../../UfServlet?opcao=C'});
					});
					subService_endereco.createOption('Munic�pios').click(function(e) {
						e.preventDefault();
						$(this).openWindow('MUNICIPIO', {title: 'Munic�pios', width: '600', height: '350', file: '../../MunicipioServlet?opcao=C'});
					});
					subService_endereco.createOption('Bairros').click(function(e) {
						e.preventDefault();
						$(this).openWindow('BAIRRO', {title: 'Bairros', width: '600', height: '350', file: '../../BairroServlet?opcao=C'});
					});
				}
			}
				
			var menuOptionConteudo_subServicos_perfis = menuOptionConteudo_subServicos.find('#perfis');
			{
				var subService_perfil = menuOptionConteudo_subServicos_perfis.find('ul').html(''); /* Corre��o para o IE */;
				{
					subService_perfil.createOption('Forma��es Acad�mica').click(function(e) {
						e.preventDefault();
						$(this).openWindow('FORMACAO_ACADEMICA', {title: 'Forma��es Acad�mica', width: '600', height: '350', file: '../../FormacaoAcademicaServlet?opcao=C'});
					});
					subService_perfil.createOption('Idiomas').click(function(e) {
						e.preventDefault();
						$(this).openWindow('IDIOMA', {title: 'Idiomas', width: '600', height: '350', file: '../../IdiomaServlet?opcao=C'});
					});
					subService_perfil.createOption('�reas de Interesse').click(function(e) {
						e.preventDefault();
						$(this).openWindow('AREA_INTERESSE', {title: '�reas de Interesse', width: '600', height: '350', file: '../../AreaInteresseServlet?opcao=C'});
					});
				}
			}
			
			var servicos = menuOptionConteudo.find('div#servicos ul').html('')/* Corre��o para o IE */;
			servicos.createOption('Endere�os', true).hover(
				function() {
					menuOptionConteudo_mouseover(menuOptionConteudo_subServicos_enderecos);
				}
			);
			
			servicos.createOption('Tipos de Contato').click(function(e) {
				e.preventDefault();
				$(this).openWindow('TIPO_CONTATO', {title: 'Tipos de Contato', width: '600', height: '350', file: '../../TipoContatoServlet?opcao=C'});
			});
			
			servicos.createOption('Institui��es').click(function(e) {
				e.preventDefault();
				$(this).openWindow('INSTITUICAO', {title: 'Institui��es', width: '600', height: '350', file: '../../InstituicaoServlet?opcao=C'});
			});
			
			servicos.createOption('Perfis', true).hover(
				function() {
					menuOptionConteudo_mouseover(menuOptionConteudo_subServicos_perfis);
				}
			);
			
			servicos.createOption('Categorias de Artefatos').click(function(e) {
				e.preventDefault();
				$(this).openWindow('CATEGORIA_ARTEFATO', {title: 'Categorias de Artefatos', width: '600', height: '350', file: '../../CategoriaArtefatoServlet?opcao=C'});
			});
			
				servicos.createOption('Categorias de Comunidades').click(function(e) {
					e.preventDefault();
					$(this).openWindow('CATEGORIA_COMUNIDADE', {title: 'Categorias de Comunidades', width: '600', height: '350', file: '../../CategoriaComunidadeServlet?opcao=C'});
				});
			
			servicos.createOption('Usu�rios').click(function(e) {
				e.preventDefault();
				$(this).openWindow('USUARIO', {title: 'Usu�rios', width: '600', height: '350', file: '../../UsuarioServlet?opcao=C'});
			});
			
			servicos.createOption('Aplica��es Externas').click(function(e) {
				e.preventDefault();
				$(this).openWindow('APLICACOES_EXTERNAS', {title: 'Aplica��es Externas', width: '700', height: '350', file: '../../AplicacaoExternaServlet?opcao=C'});
			});
			
			servicos.find('a').each(function() {
				if($(this).find('span').html() == null)
					$(this).mouseover(function() { menuOptionConteudo_subServicos.hide().find('div').hide(); });
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
			
			return this;
		}
	}
}();