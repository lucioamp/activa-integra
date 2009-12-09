package util.arquivo;

public class ExcecaoArquivo extends Exception 
{
	static final long serialVersionUID = 1;
	
	public ExcecaoArquivo() 
	{
		super();
	}
	
	public ExcecaoArquivo(String msg) 
	{
		super(msg);
	}
}
