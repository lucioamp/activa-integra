<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Curso"%>
<%@page import="modelo.Aula"%>
<%@page import="modelo.ArquivoAula"%>
<%@page import="modelo.Usuario"%>
<%
	Curso curso = (Curso)request.getAttribute("curso");
	Usuario usuario = (Usuario)session.getAttribute("membro");
%>

<%@page import="modelo.AlunoAula"%>
<%@page import="modelo.AlunoCurso"%>
<%@page import="util.Ferramenta"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this, tab) {
			var object = $this.getObject();
			object['aulas'] = new Array();

			var id = '<%= curso.getPkServico() %>';

			var nome = $this.find('label#no_curso').html('<%= curso.getNome() %>'.clearNull());
			var desc = $this.find('textarea#ds_servico');

			$this.find('div#title').html('Detalhe de <%= curso.getNome() %>');

			$this.find('button#ingressar').click(function(e) {
				$this.sendRequest('../../MembroCursoServlet?opcao=1',
					{codigoCurso: id, codigoAluno: <%= usuario.getPkUsuario() %>},
					function(data) {
						var result = trim(data);

						if(result == "true")
							$this.alert('Inscrito com sucesso no curso.', null, {clickOK: function(e) {
								tab.tabs('load', object['id']);
							}});
						else
							$this.alert('Erro ao tentar se inscrever no curso.');
					}
				);
			});

			var tabelaAulas = $this.find('table#aulas');
			var adicionarAula = function(id, assunto, desc, peso, data)
			{
				var tr = $('<tr></tr>');

				var aula = {id: id, assunto: assunto, desc: desc, arquivos: new Array(), peso: peso, data: data};

				var tdAssunto = $('<td></td>').html($('<a href="#"></a>').append(assunto).click(function(e) {
					e.preventDefault();
					object['_aula'] = aula;
					$this.newPage('servicos/curso/aula/detalhe.jsp', null);
				}));

				var tdData = $('<td></td>').html(data);

				object['aulas'].push(aula);

				tabelaAulas.find('tbody').append(tr.append(tdAssunto).append(tdData));

				return aula;
			};

			var adicionarArquivo = function(id, nome, aula)
			{
				aula.arquivos.push({id: id, nome: nome});
			};

			var aula = null;
			<%			
			Boolean inscrito = false;
			
			if(curso.getMembro().getPkUsuario() == usuario.getPkUsuario())
				inscrito = true;
			else
			{
				Collection<AlunoCurso> alunos = AlunoCurso.consultarPorCurso(curso);
				for(AlunoCurso aluno:alunos)
				{
					if(aluno.getAluno().getPkUsuario() == usuario.getPkUsuario())
						inscrito = true;		
				}
			}
			
			if(inscrito == true)
			{
				out.print("tabelaAulas.show(); $this.find('tr#ingressar').hide();");
				
				@SuppressWarnings ("unchecked")
				Collection<Aula> aulas = (Collection<Aula>)request.getAttribute("aulas");
				for(Aula aula:aulas)
				{
					/*Collection<AlunoAula> alunos = AlunoAula.consultarPorAula(aula);
					for(AlunoAula aluno:alunos)
					{
						if(aluno.getAluno().getPkUsuario() == usuario.getPkUsuario())
						{*/
							out.print("tabelaAulas.show(); aula = adicionarAula("+aula.getPkAula()+", '"+aula.getAssunto()+"'.clearNull(), '"+aula.getAula()+"'.clearNull(), "+aula.getPeso()+", '"+Ferramenta.formatarData(aula.getData(), true)+"');");
							
							Collection<ArquivoAula> arquivosAula = ArquivoAula.consultarPorAula(aula);
							for(ArquivoAula arquivoAula:arquivosAula)
							{
								out.print("adicionarArquivo("+arquivoAula.getPkArquivoAula()+", '"+arquivoAula.getNome()+"'.clearNull(), aula);");
							}
						//}
					//}
				}
			}
			%>
		};
	});
</script>

<div id="title">Detalho de Curso</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 30%;">Nome:</td>
			<td><label id="no_curso"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o da Ementa:</td>
			<td><textarea name="ds_servico" id="ds_servico" readonly="readonly"><%= curso.getDescricao() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="aulas" class="tabelaResult" border="1" style="display: none;">
				<caption>Aulas</caption>
				<thead>
					<tr>
						<th>Assunto</th>
						<th>Data</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
	<tfoot style="text-align: center;">
		<tr id="ingressar">
			<td colspan="3">
			<button type="button" class="ui-state-default ui-corner-all"
				name="ingressar" id="ingressar">Inscrever-se</button>
			</td>
		</tr>
	</tfoot>
</table>