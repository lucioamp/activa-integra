<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			$this.find('#tabs').TabsGenerate();
		};
	});
</script>

<div id="tabs">
	<ul>
		<li><a href="../../MembroMensagensServlet?opcao=P">Principal</a></li>
		<li><a href="../../MembroMensagensServlet?opcao=E">Enviadas</a></li>
		<li><a href="../../MembroMensagensServlet?opcao=R">Recebidas</a></li>
		<li><a href="servicos/perfil/mensagens/enviar.jsp">Enviar Mensagem</a></li>
	</ul>
</div>