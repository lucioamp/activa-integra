package dao;

import interfaces.CategoriaArtefatoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.CategoriaArtefato;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class CategoriaArtefatoDAO implements CategoriaArtefatoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (CategoriaArtefato categoriaArtefato) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into categoriaartefato (pk_catArtefato,no_catArtefato,ds_catArtefato) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "categoriaArtefato", "pk_catArtefato");
				
				stmt.setLong(1,pk);
				stmt.setString(2,categoriaArtefato.getNome());
				stmt.setString(3, categoriaArtefato.getDescricao());
				
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
	
	public int alterar(CategoriaArtefato categoriaArtefato) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update categoriaartefato set no_catArtefato=?, ds_catArtefato=? where pk_catArtefato=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, categoriaArtefato.getNome());
			stmt.setString(2, categoriaArtefato.getDescricao());
			stmt.setLong(3, categoriaArtefato.getPkCatArtefato());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (CategoriaArtefato categoriaArtefato) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from categoriaartefato where pk_catArtefato = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, categoriaArtefato.getPkCatArtefato());
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
	
	public Collection<CategoriaArtefato> consultarTodos() throws CadastroException{
		Collection<CategoriaArtefato> col = new ArrayList<CategoriaArtefato>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from categoriaartefato";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					CategoriaArtefato categoriaArtefato = new CategoriaArtefato();
					carregar(rs, categoriaArtefato);
					col.add(categoriaArtefato);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public CategoriaArtefato consultar(CategoriaArtefato categoriaArtefato) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from categoriaartefato where pk_catArtefato=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, categoriaArtefato.getPkCatArtefato());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, categoriaArtefato);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return categoriaArtefato;
	}
	
	private void carregar(ResultSet rs ,CategoriaArtefato categoriaArtefato) throws SQLException{
		categoriaArtefato.setPkCatArtefato(rs.getLong("pk_catArtefato"));
		categoriaArtefato.setNome(rs.getString("no_catArtefato"));
		categoriaArtefato.setDescricao(rs.getString("ds_catArtefato"));
	}
}
