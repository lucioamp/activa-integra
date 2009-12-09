package util.interpretador;

public class ContextNode 
{
	protected String nome;
	
	public ContextNode(String nome) 
	{
		super();
		this.nome = nome;
	}
	
	public String getNome()          { return nome;	     }
	public void setNome(String nome) { this.nome = nome; }
	public String getValStr()		 { return null;		 }
	public int    getValInt()		 { return 0;		 }
}
