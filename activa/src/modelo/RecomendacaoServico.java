package modelo;

import interfaces.RecomendacaoServicoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.RecomendacaoServicoDAO;

public class RecomendacaoServico {

	private Membro membroRecomendador;
	private Membro membroReceptor;
	private Servico servico;
		
	private static RecomendacaoServicoI dao;
	
	public RecomendacaoServico(){
		this.membroRecomendador = new Membro();
		this.membroReceptor = new Membro();
		this.servico = new Servico();
	}
	
	public Membro getMembroRecomendador() {
		return membroRecomendador;
	}

	public void setMembroRecomendador(Membro membroRecomendador) {
		this.membroRecomendador = membroRecomendador;
	}

	public Membro getMembroReceptor() {
		return membroReceptor;
	}

	public void setMembroReceptor(Membro membroReceptor) {
		this.membroReceptor = membroReceptor;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	private static RecomendacaoServicoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new RecomendacaoServicoDAO();
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

	/*public int alterar() throws CadastroException{
		return getDAO().alterar(this);
	}*/
	
	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	public static Collection<RecomendacaoServico> consultarPorMembroReceptor(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembroReceptor(membro);
	}
	
	public RecomendacaoServico consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
