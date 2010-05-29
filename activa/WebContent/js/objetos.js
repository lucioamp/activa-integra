/*
	Objeto para Desktop Restrito
*/

function Meta(){
	var id = null;
	var descricao = null;
	var duracao = null;
	var tags = new Array();
	var etapas = new Array();
	
	var virtual = false;	
	var deletado = false;
	var alterado = false;
	var trReferencia = null;	
	
	this.adicionarEtapa = function(etapa)
	{
		etapas.push(etapa);
	};
	
	this.adicionarTag = function(tag)
	{
		tags.push(tag);
	};
	
	this.setId = function(_id)
	{
		id = _id;
	};
	
	this.setDescricao = function(_descricao)
	{
		descricao = _descricao;
	};
	
	this.setDuracao = function(_duracao)
	{
		duracao = _duracao;
	};
	
	this.getId = function()
	{
		return id;
	};
	
	this.getEtapas = function()
	{
		return etapas;
	};
	
	this.getTags = function()
	{
		return tags;
	};
	
	this.getDescricao = function()
	{
		return descricao;
	};
	
	this.getDuracao = function()
	{
		return duracao;
	};	
	
	this.getVirtual = function()
	{
		return virtual;
	};
	
	this.getDeletado = function()
	{
		return deletado;
	};
	
	this.getAlterado = function()
	{
		return alterado;
	};
	
	this.getTrReferencia = function()
	{
		return trReferencia;
	};
	
	this.setVirtual = function(_virtual)
	{
		virtual = _virtual;
	};
	
	this.setDeletado = function(_deletado)
	{
		deletado = _deletado;
	};
	
	this.setAlterado = function(_alterado)
	{
		alterado = _alterado;
	};
	
	this.setTrReferencia = function(_trReferencia)
	{
		trReferencia = _trReferencia;
	};
}