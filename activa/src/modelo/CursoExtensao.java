package modelo;

import interfaces.CursoExtensaoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.CursoExtensaoDAO;

public class CursoExtensao {

	private long pkCursoExtensao;
	private String nome;
	private String descricao;
	private Membro membro;
		
	private static CursoExtensaoI dao;
	
	public CursoExtensao(){
		this.membro = new Membro();
	}
	
	public long getPkCursoExtensao() {
		return pkCursoExtensao;
	}

	public void setPkCursoExtensao(long pkCursoExtensao) {
		this.pkCursoExtensao = pkCursoExtensao;
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
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	private static CursoExtensaoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new CursoExtensaoDAO();
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
	
	public static Collection<CursoExtensao> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public CursoExtensao consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	public static Collection<CursoExtensao> consultarPorMembro(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembro(membro);
	}
}
