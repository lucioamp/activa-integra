<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var url = $this.find('input#url');
			var iframe = $this.find('iframe');

			$this.find('button#pesquisar').click(function(e) {
				e.preventDefault();
				iframe.attr('src', url.val());
			});
		};
	});
</script>
<form name="form" onsubmit="return false;">
	<input type="text" size="60" name="url" id="url" value="http://" style="display: inline;"/>
	<button type="submit" id="pesquisar" name="pesquisar" class="ui-state-default ui-corner-all">Pesquisar</button>
	<iframe src ="" width="100%" height="98%" style="border: 0px;">
		<p>Seu Browser não suporta iframe.</p>
	</iframe>
</form>