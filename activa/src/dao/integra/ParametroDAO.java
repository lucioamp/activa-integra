package dao.integra;

import interfaces.integra.ParametroI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.integra.Parametro;
import modelo.integra.Recurso;
import util.AplicacaoExternaException;
import util.ConnectionFactory;

public class ParametroDAO implements ParametroI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public long incluir (Parametro parametro) throws AplicacaoExternaException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
							
			String sql = "insert into ae_parametro (id_recurso,nome,titulo,obrigatorio)";
			sql += " values (?,?,?,?)";
							
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1,parametro.getIdRecurso());
			stmt.setString(2,parametro.getName());
			stmt.setString(3, parametro.getTitle());
			stmt.setInt(4, parametro.isRequired() ? 1 : 0);
			
			System.out.println(stmt);
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return 0;
	}
	
	public void consultarPorRecurso(Recurso recurso) throws AplicacaoExternaException{
		List<Parametro> col = new ArrayList<Parametro>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from ae_parametro where id_recurso=? order by obrigatorio desc";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, recurso.getIdRecurso());
				
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Parametro parametro = new Parametro();
					carregar(rs, parametro);
					col.add(parametro);
				}
			}catch (Exception e) {
				throw new AplicacaoExternaException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
			recurso.setParametros(col);
	}
	
	private void carregar(ResultSet rs, Parametro parametro) throws SQLException {
		parametro.setIdParametro(rs.getLong("id_parametro"));
		parametro.setIdRecurso(rs.getInt("id_recurso"));
		parametro.setName(rs.getString("nome"));
		parametro.setTitle(rs.getString("titulo"));
		parametro.setRequired(rs.getInt("obrigatorio") == 1 ? true : false);
	}
}
