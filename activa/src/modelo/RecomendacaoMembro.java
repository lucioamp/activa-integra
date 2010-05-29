package modelo;

import interfaces.RecomendacaoMembroI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.RecomendacaoMembroDAO;

public class RecomendacaoMembro {

	private Membro membroRecomendador;
	private Membro membroReceptor;
	private Membro membroRecomendado;
		
	private static RecomendacaoMembroI dao;
	
	public RecomendacaoMembro(){
		this.membroRecomendador = new Membro();
		this.membroReceptor = new Membro();
		this.membroRecomendado = new Membro();
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

	public Membro getMembroRecomendado() {
		return membroRecomendado;
	}

	public void setMembroRecomendado(Membro membroRecomendado) {
		this.membroRecomendado = membroRecomendado;
	}

	private static RecomendacaoMembroI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new RecomendacaoMembroDAO();
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
	
	public static Collection<RecomendacaoMembro> consultarPorMembroReceptor(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembroReceptor(membro);
	}
	
	public RecomendacaoMembro consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
