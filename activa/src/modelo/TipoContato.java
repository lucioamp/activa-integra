package modelo;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.TipoContatoDAO;
import interfaces.TipoContatoI;

public class TipoContato {

	private long pkTipoContato;
	private String tipoContato;
		
	private static TipoContatoI dao;
	
	public long getPkTipoContato() {
		return pkTipoContato;
	}
	public void setPkTipoContato(long pkTipoContato) {
		this.pkTipoContato = pkTipoContato;
	}
	public String getTipoContato() {
		return tipoContato;
	}
	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}
		
	private static TipoContatoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new TipoContatoDAO();
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
	
	public static Collection<TipoContato> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public TipoContato consultar() throws CadastroException{
		return getDAO().consultar(this);
	}

}
