<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			$this.find('iframe').fadeIn(500).load(function() {
				//
			});
		};
	});
</script>
<iframe src ="<%= request.getParameter("url") %>" width="100%" height="98%" style="display: none; border: 0px;">
  <p>Seu Browser não suporta iframe.</p>
</iframe>