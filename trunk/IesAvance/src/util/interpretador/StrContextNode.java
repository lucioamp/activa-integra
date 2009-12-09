package util.interpretador;

public class StrContextNode extends ContextNode {
	String valStr;

	public StrContextNode(String nome, String valStr) 
	{
		super(nome);
		this.valStr = valStr;
	}
	
	public String getValStr() {
		return valStr;
	}
	public void setValStr(String valStr) {
		this.valStr = valStr;
	}
}
