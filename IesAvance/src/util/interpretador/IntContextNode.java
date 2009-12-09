package util.interpretador;

public class IntContextNode extends ContextNode 
{
	protected int  valInt;

	public IntContextNode(String nome, int valInt) {
		super(nome);
		this.valInt = valInt;
	}

	public int getValInt() {
		return valInt;
	}
	public void setValInt(int valInt) {
		this.valInt = valInt;
	}
}
