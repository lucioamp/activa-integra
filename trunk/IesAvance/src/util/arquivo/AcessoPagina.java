package util.arquivo;

public class AcessoPagina {
	
	private String pagina;
	private String diretorio;
	private int nivelAcesso;

	public AcessoPagina()
	{
		pagina = "";
		diretorio = "";
		nivelAcesso = 0;
	}
	
	public AcessoPagina(String pag, String dir) 
	{
		pagina = pag;
		diretorio = dir;
		nivelAcesso = 0;
	}
	
	public String caminho()
	{
		if ( diretorio != null )
			return diretorio + "\\" + pagina;
		
		return pagina;
	}

	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
}
