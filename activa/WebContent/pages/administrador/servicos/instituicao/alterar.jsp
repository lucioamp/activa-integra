<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<% Instituicao instituicao = (Instituicao)request.getAttribute("instituicao"); %>
<%@page import="modelo.Instituicao"%>
<%@page import="modelo.ContatoInstituicao"%>
<%@page import="modelo.Uf"%>
<%@page import="modelo.Municipio"%>
<%@page import="modelo.Bairro"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			object['contatos'] = new Array();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../InstituicaoServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});

			var id_instituicao = <%= instituicao.getPkInstituicao() %>;

			var nome = $this.find('input#nome');
			var tp_logradouro = $this.find('select#tp_logradouro');
			var nu_logradouro = $this.find('input#nu_logradouro');
			var no_logradouro = $this.find('input#no_logradouro');
			var ds_complemento = $this.find('textarea#ds_complemento');
			var nu_cep = $this.find('input#nu_cep').setMask('99999-999');
			var uf = $this.find('select#uf');
			var municipio = $this.find('select#municipio');
			var bairro = $this.find('select#bairro');			
			$this.find('button#alterar').click(function() {
				if(nome.val() == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(uf.val() == 0)
				{
					$this.alert('Por favor, selecione uma uf.', null, {clickOK: function() { uf.focus(); }});
					return false;
				}

				if(municipio.val() == 0)
				{
					$this.alert('Por favor, selecione um município.', null, {clickOK: function() { municipio.focus(); }});
					return false;
				}

				if(bairro.val() == 0)
				{
					$this.alert('Por favor, selecione um bairro.', null, {clickOK: function() { bairro.focus(); }});
					return false;
				}

				var contatos = "";
				var contato = null;
				for (var i in object['contatos'])
				{
					contato = object['contatos'][i];

					contatos += 'id: '+contato.id+'# tipo:'+contato.tipo+' # desc:'+contato.desc+'# ';
					if(contato.virtual == true)
						contatos += 'virtual: true&';
					else if(contato.deletado == true)
						contatos += 'deletar: true&';
					else if(contato.alterado == true)
						contatos += 'alterar: true&';
				}
				
				$this.sendRequest('../../InstituicaoServlet?opcao=A',
					{
						pkInstituicao: id_instituicao, nome: nome.val(), tp_logradouro: tp_logradouro.val(), no_logradouro: no_logradouro.val(), nu_logradouro: nu_logradouro.val(),
						ds_complemento: ds_complemento.val(), nu_cep: nu_cep.val(), bairro: bairro.val(), contatos: contatos
					},
					function() { botaoVoltar.click(); }
				);
			});
		
			$this.find('#incluirContato').click(function() {
				$mainPage.newPage('servicos/contato/incluir.jsp', null);
			});
			
			uf.change(function() {
				var pkUf = $(this).val();

				municipio.empty().addOption("0", "").selectOptions("0");
				bairro.empty().addOption("0", "").selectOptions("0");
				$this.find('tr#tr_bairro').hide();
				if(pkUf > 0)
				{
					$this.find('tr#tr_municipio').show();
					$this.sendRequest('../../BairroServlet?opcao=M', {pkEstado: pkUf},	function(data) {
						municipio.append(data);
					});
				}else
					$this.find('tr#tr_municipio').hide();
			});
			
			municipio.change(function() {
				var pkMunicipio = $(this).val();
				bairro.empty().addOption("0", "");
				if(pkMunicipio > 0)
				{
					$this.find('tr#tr_bairro').show();
					$this.sendRequest('../../BairroServlet?opcao=B', {pkMunicipio: pkMunicipio}, function(data) {
						bairro.append(data);
					});
				}else
					$this.find('tr#tr_bairro').hide();
			});

			<%
			Collection<Uf> colUf = Uf.consultarTodos();
			for(Uf uf:colUf)
				out.print("uf.addOption('"+uf.getPkEstado()+"'.clearNull(), '"+uf.getEstado()+"'.clearNull());");
			
			Collection<Municipio> colMun = Municipio.consultarPorUf(instituicao.getBairro().getMunicipio().getUf());
			for(Municipio mun:colMun)
				out.print("municipio.addOption('"+mun.getPkMunicipio()+"'.clearNull(), '"+mun.getMunicipio()+"'.clearNull());");
			
			Collection<Bairro> colBar = Bairro.consultarPorMunicipio(instituicao.getBairro().getMunicipio());
			for(Bairro bar:colBar)
				out.print("bairro.addOption('"+bar.getPkBairro()+"'.clearNull(), '"+bar.getBairro()+"'.clearNull());");
			%>

			var adicionarContato = function(id, tipo, tipo_nome, desc) {
				var tr = $('<tr></tr>');
				var tdTipo = $('<td></td>').html(tipo_nome);
				
				var tdDesc = $('<td></td>').html(desc);

				tr.contextMenu({
					buttons: {
						Editar: {
							icon: "edit",
							onClick: function() {
								$mainPage.newPage('../../ContatoServlet?opcao=D', {id: id, tipo_contato: tipo, desc: desc, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										for (var i in object['contatos'])
										{
											if(object['contatos'][i].id == id)
												object['contatos'][i].deletado = true;
										}
										tr.clear();
									}
								});
							}
						}
					}
				});
				
				object['contatos'].push({id: id, tipo: tipo, desc: desc, virtual: false, deletado: false, alterado: false, trReferencia: tr});

			};

			nome.val("<%= instituicao.getNome() %>".clearNull());
			tp_logradouro.selectOptions("<%= instituicao.getTipoLogradouro() %>".clearNull());
			no_logradouro.val("<%= instituicao.getLogradouro()%>".clearNull());
			nu_logradouro.val("<%= instituicao.getNumero()%>".clearNull());
			nu_cep.val("<%= instituicao.getCep()%>".clearNull());
			uf.selectOptions("<%= instituicao.getBairro().getMunicipio().getUf().getPkEstado() %>".clearNull());
			municipio.selectOptions("<%= instituicao.getBairro().getMunicipio().getPkMunicipio() %>".clearNull());
			bairro.selectOptions("<%= instituicao.getBairro().getPkBairro() %>".clearNull());

			<%
			@SuppressWarnings ("unchecked")
			Collection<ContatoInstituicao> contatos = (Collection<ContatoInstituicao>)request.getAttribute("contatos");
			for(ContatoInstituicao contato:contatos)
				out.print("adicionarContato("+contato.getPkContatoInstituicao()+", '"+contato.getTipoContato().getPkTipoContato()+"'.clearNull(), '"+contato.getTipoContato().getTipoContato()+"'.clearNull(), '"+contato.getContato()+"'.clearNull());");
			%>
		}
	});
</script>

<div id="title">Alterar Institui&ccedil;&atilde;o</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td>Tipo de Logradouro:</td>
			<td><select name="tp_logradouro" id="tp_logradouro">
				<option value=""></option>
				<option value="Av">Avenida</option>
				<option value="Rua">Rua</option>
				<option value="Est">Estrada</option>
				<option value="Prç">Praça</option>
			</select></td>
		</tr>
		<tr>
			<td>Logradouro:</td>
			<td><input type="text" name="no_logradouro" id="no_logradouro" /></td>
		</tr>
		<tr>
			<td>N&uacute;mero:</td>
			<td><input type="text" name="nu_logradouro" id="nu_logradouro" /></td>
		</tr>
		<tr>
			<td>Complemento:</td>
			<td><textarea id="ds_complemento" name="ds_complemento"><%= instituicao.getComplemento()%></textarea></td>
		</tr>
		<tr>
			<td>Cep:</td>
			<td><input type="text" name="nu_cep" id="nu_cep" /></td>
		</tr>
		<tr>
			<td>UF:</td>
			<td><select name="uf" id="uf">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td>Munic&iacute;pio:</td>
			<td><select name="municipio" id="municipio">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td>Bairro:</td>
			<td><select name="bairro" id="bairro">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_contatos" class="tabelaResult">
				<caption>contatos<a href="#" id="incluirContato"
					title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
				<thead>
					<tr>
						<th>Tipo</th>
						<th>Descrição</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>