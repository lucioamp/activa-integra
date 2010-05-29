package modelo;

import interfaces.MensagemI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MensagemDAO;

public class Mensagem {

	private long pkMensagem;
	private String assunto;
	private String mensagem;
	private Membro membroOrigem;
	private Membro membroDestino;
		
	private static MensagemI dao;
	
	public Mensagem(){
		this.membroOrigem = new Membro();
		this.membroDestino = new Membro();
	}
	
	public long getPkMensagem() {
		return pkMensagem;
	}

	public void setPkMensagem(long pkMensagem) {
		this.pkMensagem = pkMensagem;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Membro getMembroOrigem() {
		return membroOrigem;
	}

	public void setMembroOrigem(Membro membroOrigem) {
		this.membroOrigem = membroOrigem;
	}

	public Membro getMembroDestino() {
		return membroDestino;
	}

	public void setMembroDestino(Membro membroDestino) {
		this.membroDestino = membroDestino;
	}

	private static MensagemI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MensagemDAO();
				break;

			default:
				break;
			}
		}
		return dao; 
	}
	
	public int incluir() throws CadastroException{
		return getDAO().incluir(this);
	}

	public int alterar() throws CadastroException{
		return getDAO().alterar(this);
	}
	
	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	public static Collection<Mensagem> consultarEnviadas(Membro membro) throws CadastroException{
		return getDAO().consultarEnviadas(membro);
	}
	
	public static Collection<Mensagem> consultarRecebidas(Membro membro) throws CadastroException{
		return getDAO().consultarRecebidas(membro);
	}
	
	public Mensagem consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
