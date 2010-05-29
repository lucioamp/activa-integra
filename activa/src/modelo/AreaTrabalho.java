package modelo;

import interfaces.AreaTrabalhoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AreaTrabalhoDAO;

public class AreaTrabalho {

	private long pkAreaTrabalho;
	private String nome;
	private String descricao;
	private Membro membro;
		
	private static AreaTrabalhoI dao;
	
	public AreaTrabalho(){
		this.membro = new Membro();
	}
	
	public long getPkAreaTrabalho() {
		return pkAreaTrabalho;
	}

	public void setPkAreaTrabalho(long pkAreaTrabalho) {
		this.pkAreaTrabalho = pkAreaTrabalho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private static AreaTrabalhoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AreaTrabalhoDAO();
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
	
	public static Collection<AreaTrabalho> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public AreaTrabalho consultar() throws CadastroException{
		return getDAO().consultar(this);
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Membro getMembro() {
		return membro;
	}
	
	public static Collection<AreaTrabalho> consultarPorMembro(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembro(membro);
	}
}
