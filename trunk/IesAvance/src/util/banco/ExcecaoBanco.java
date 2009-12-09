package util.banco;

public class ExcecaoBanco extends Exception {
	
	static final long serialVersionUID = 1;
	
	public ExcecaoBanco() {
		super();
	}
	
	public ExcecaoBanco(String msg) {
		super(msg);
	}
}
