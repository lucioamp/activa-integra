package dao.integra;

import interfaces.integra.RecursoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.integra.AplicacaoExterna;
import modelo.integra.Recurso;
import util.AplicacaoExternaException;
import util.ConnectionFactory;

public class RecursoDAO implements RecursoI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public void consultarPorAplicacao(AplicacaoExterna aplicacao) throws AplicacaoExternaException{
		Collection<Recurso> col = new ArrayList<Recurso>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from ae_recurso where id_aplicacao=? order by nome";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, aplicacao.getIdAplicacao());
				
				System.out.println("UfDAO Consultar consultarPorAplicacao :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Recurso recurso = new Recurso();
					carregar(rs, recurso);
					col.add(recurso);
				}
			}catch (Exception e) {
				throw new AplicacaoExternaException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		aplicacao.setRecursoList(col);
	}
	
	public void consultar(Recurso recurso) throws AplicacaoExternaException{
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from ae_recurso where id_recurso=?";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, recurso.getIdRecurso());
				
				rs = stmt.executeQuery();
				
				while (rs.next()){
					carregar(rs, recurso);
				}
			}catch (Exception e) {
				throw new AplicacaoExternaException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
	}
	
	private void carregar(ResultSet rs, Recurso recurso) throws SQLException {
		recurso.setIdRecurso(rs.getLong("id_recurso"));
		recurso.setIdAplicacao(rs.getLong("id_aplicacao"));
		recurso.setNome(rs.getString("nome"));
		recurso.setBase(rs.getString("base"));
		recurso.setPath(rs.getString("path"));
		recurso.setMetodo(rs.getString("metodo"));
	}

	public int incluir (Recurso recurso) throws AplicacaoExternaException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
							
			String sql = "insert into ae_recurso (id_aplicacao,nome,base,path,metodo)";
			sql += " values (?,?,?,?,?)";
							
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1,recurso.getIdAplicacao());
			stmt.setString(2,recurso.getNome());
			stmt.setString(3, recurso.getBase());
			stmt.setString(4, recurso.getPath());
			stmt.setString(5, recurso.getMetodo());
			
			System.out.println(stmt);
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return getIDRecurso();
	}
	
	public int getIDRecurso() throws AplicacaoExternaException{
		int retValue = 0;
		
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select max(id_recurso) from ae_recurso";
		
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if (rs.next()){
				retValue = rs.getInt(1);
			}
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return retValue;
	}
	
}
