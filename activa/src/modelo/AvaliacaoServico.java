package modelo;

import interfaces.AvaliacaoServicoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AvaliacaoServicoDAO;

public class AvaliacaoServico {

	private Membro membro;
	private Servico servico;
	private long avaliacao;
		
	private static AvaliacaoServicoI dao;
	
	public AvaliacaoServico(){
		this.membro = new Membro();
		this.servico = new Servico();
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public long getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(long avaliacao) {
		this.avaliacao = avaliacao;
	}

	private static AvaliacaoServicoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AvaliacaoServicoDAO();
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
	
	public static Collection<AvaliacaoServico> consultarPorServico(Servico servico) throws CadastroException{
		return getDAO().consultarPorServico(servico);
	}
	
	public AvaliacaoServico consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
