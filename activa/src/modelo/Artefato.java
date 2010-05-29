package modelo;

import interfaces.ArtefatoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ArtefatoDAO;

public class Artefato extends Servico{

	private String autor;
	private long anoPublicacao;
	private CategoriaArtefato categoriaArtefato;
	
	private static ArtefatoI dao;

	public Artefato(){
		this.categoriaArtefato = new CategoriaArtefato();
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public long getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(long anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public CategoriaArtefato getCategoriaArtefato() {
		return categoriaArtefato;
	}

	public void setCategoriaArtefato(CategoriaArtefato categoriaArtefato) {
		this.categoriaArtefato = categoriaArtefato;
	}

	private static ArtefatoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ArtefatoDAO();
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
	
	public static Collection<Artefato> consultarTodosArtefatos() throws CadastroException{
		return getDAO().consultarTodosArtefatos();
	}
	
	public static Collection<Artefato> consultarArtefatosPorAmbiente(Ambiente ambiente) throws CadastroException{
		return getDAO().consultarArtefatosPorAmbiente(ambiente);
	}
	
	public Artefato consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
