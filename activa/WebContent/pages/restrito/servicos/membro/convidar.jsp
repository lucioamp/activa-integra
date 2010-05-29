<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			
			var object = $this.getObject();
			
		};
	});
</script>
<div id="page_1">
<div id="title">Convidar Membro</div>
<table>
	<tbody>
		<tr>
			<td colspan="2">Para colocar mais de um email, basta digitar no
			final de cada email ponto e virgula(;).</td>
		</tr>
		<tr>
			<td style="width: 10px;">Email(s):</td>
			<td><textarea name="email" id="email" cols="60" rows="7"></textarea></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<button type="button" name="convidar" id="convidar"
				class="ui-state-default ui-corner-all" style="text-align: right;">Convidar</button>
			</td>
		</tr>
	</tfoot>
</table>
</div>