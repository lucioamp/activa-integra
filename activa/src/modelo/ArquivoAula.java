package modelo;

import interfaces.ArquivoAulaI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ArquivoAulaDAO;

public class ArquivoAula {

	private long pkArquivoAula;
	private String nome;
	private Aula aula;
	
	private static ArquivoAulaI dao;

	public ArquivoAula(){
		this.aula = new Aula();
	}
	
	public long getPkArquivoAula() {
		return pkArquivoAula;
	}

	public void setPkArquivoAula(long pkArquivoAula) {
		this.pkArquivoAula = pkArquivoAula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	private static ArquivoAulaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ArquivoAulaDAO();
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
	
	public static Collection<ArquivoAula> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<ArquivoAula> consultarPorAula(Aula aula) throws CadastroException{
		return getDAO().consultarPorAula(aula);
	}
	
	public ArquivoAula consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
