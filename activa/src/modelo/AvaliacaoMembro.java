package modelo;

import interfaces.AvaliacaoMembroI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AvaliacaoMembroDAO;

public class AvaliacaoMembro {

	private Membro membroAvaliador;
	private Membro membro;
	private long avaliacao;
		
	private static AvaliacaoMembroI dao;
	
	public AvaliacaoMembro(){
		this.membroAvaliador = new Membro();
		this.membro = new Membro();
	}
	
	public Membro getMembroAvaliador() {
		return membroAvaliador;
	}

	public void setMembroAvaliador(Membro membroAvaliador) {
		this.membroAvaliador = membroAvaliador;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public long getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(long avaliacao) {
		this.avaliacao = avaliacao;
	}

	private static AvaliacaoMembroI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AvaliacaoMembroDAO();
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
	
	public static Collection<AvaliacaoMembro> consultarPorMembro(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembro(membro);
	}
	
	public AvaliacaoMembro consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
