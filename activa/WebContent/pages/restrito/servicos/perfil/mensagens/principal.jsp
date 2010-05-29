<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $mainPage) {
			var object = $this.getObject();
			object['cache'] = false;

			$this.find('label#enviadas').html('<%= request.getAttribute("quantidadeEnviadas") %>'.clearNull());	
			$this.find('label#recebidas').html('<%= request.getAttribute("quantidadeRecebidas") %>'.clearNull());
		};
	});
</script>

Enviadas (<label id="enviadas">0</label>)<br />
Recebidas (<label id="recebidas">0</label>)