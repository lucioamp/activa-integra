package dao;

import interfaces.CategoriaComunidadeI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.CategoriaComunidade;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class CategoriaComunidadeDAO implements CategoriaComunidadeI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (CategoriaComunidade categoriaComunidade) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into categoriacomunidade (pk_catComunidade,no_catComunidade,ds_catComunidade) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "categoriacomunidade", "pk_catComunidade");
				
				stmt.setLong(1,pk);
				stmt.setString(2,categoriaComunidade.getNome());
				stmt.setString(3, categoriaComunidade.getDescricao());
				
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
	
	public int alterar(CategoriaComunidade categoriaComunidade) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update categoriacomunidade set no_catComunidade=?, ds_catComunidade=? where pk_catComunidade=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, categoriaComunidade.getNome());
			stmt.setString(2, categoriaComunidade.getDescricao());
			stmt.setLong(3, categoriaComunidade.getPkCatComunidade());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (CategoriaComunidade categoriaComunidade) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from categoriacomunidade where pk_catComunidade = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, categoriaComunidade.getPkCatComunidade());
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
	
	public Collection<CategoriaComunidade> consultarTodos() throws CadastroException{
		Collection<CategoriaComunidade> col = new ArrayList<CategoriaComunidade>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from categoriacomunidade";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					CategoriaComunidade categoriaComunidade = new CategoriaComunidade();
					carregar(rs, categoriaComunidade);
					col.add(categoriaComunidade);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public CategoriaComunidade consultar(CategoriaComunidade categoriaComunidade) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from categoriacomunidade where pk_catComunidade=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, categoriaComunidade.getPkCatComunidade());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, categoriaComunidade);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return categoriaComunidade;
	}
	
	private void carregar(ResultSet rs ,CategoriaComunidade categoriaComunidade) throws SQLException{
		categoriaComunidade.setPkCatComunidade(rs.getLong("pk_catComunidade"));
		categoriaComunidade.setNome(rs.getString("no_catComunidade"));
		categoriaComunidade.setDescricao(rs.getString("ds_catComunidade"));
	}
}
