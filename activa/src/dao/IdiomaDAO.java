package dao;

import interfaces.IdiomaI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Idioma;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class IdiomaDAO implements IdiomaI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Idioma idioma) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into idioma (pk_idioma,no_idioma) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "idioma", "pk_idioma");
				
				stmt.setLong(1,pk);
				stmt.setString(2,idioma.getNome());
				
				System.out.println(stmt);
				stmt.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return pk;
	}
	
	public int alterar(Idioma idioma) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update idioma set no_idioma=? where pk_idioma=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, idioma.getNome());
			stmt.setLong(2, idioma.getPkIdioma());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Idioma idioma) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from idioma where pk_idioma = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, idioma.getPkIdioma());
				r = stmt.executeUpdate();
			
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}
			finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
		
		return r;
	}
	
	public Collection<Idioma> consultarTodos() throws CadastroException{
		Collection<Idioma> col = new ArrayList<Idioma>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from idioma";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Idioma idioma = new Idioma();
					carregar(rs, idioma);
					col.add(idioma);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Idioma consultar(Idioma idioma) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from idioma where pk_idioma=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, idioma.getPkIdioma());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, idioma);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return idioma;
	}
	
	private void carregar(ResultSet rs ,Idioma idioma) throws SQLException{
		idioma.setPkIdioma(rs.getLong("pk_idioma"));
		idioma.setNome(rs.getString("no_idioma"));	
	}
}
