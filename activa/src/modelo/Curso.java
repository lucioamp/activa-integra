package modelo;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.CursoDAO;
import interfaces.CursoI;

public class Curso extends Servico{

	private String dataLiberacao;
	
	private static CursoI dao;

	public String getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(String dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}
	
	private static CursoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new CursoDAO();
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
	
	public static Collection<Curso> consultarTodosCursos() throws CadastroException{
		return getDAO().consultarTodosCursos();
	}
	
	public static Collection<Curso> consultarCursosPorAmbiente(Ambiente ambiente) throws CadastroException{
		return getDAO().consultarCursosPorAmbiente(ambiente);
	}
	
	public Curso consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
