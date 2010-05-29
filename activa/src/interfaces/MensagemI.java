package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.Mensagem;
import util.CadastroException;

public interface MensagemI {

	public int incluir (Mensagem mensagem) throws CadastroException;
	public int alterar (Mensagem mensagem) throws CadastroException;
	public int excluir (Mensagem mensagem) throws CadastroException;
	public Collection<Mensagem> consultarEnviadas(Membro membro) throws CadastroException;
	public Collection<Mensagem> consultarRecebidas(Membro membro) throws CadastroException;
	public Mensagem consultar(Mensagem mensagem) throws CadastroException;
}
