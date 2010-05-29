package dao;

import interfaces.TagI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Tag;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class TagDAO implements TagI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Tag tag) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into tag (pk_tag,no_tag,ds_tag) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "tag", "pk_tag");
				
				stmt.setLong(1,pk);
				stmt.setString(2,tag.getNome());
				stmt.setString(3, tag.getDescricao());
				
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
	
	public int alterar(Tag tag) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update tag set no_tag=?, ds_tag=? where pk_tag=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag.getNome());
			stmt.setString(2, tag.getDescricao());
			stmt.setLong(3, tag.getPkTag());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Tag tag) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from tag where pk_tag = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, tag.getPkTag());
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
	
	public Collection<Tag> consultarTodos() throws CadastroException{
		Collection<Tag> col = new ArrayList<Tag>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from tag";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Tag tag = new Tag();
					carregar(rs, tag);
					col.add(tag);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Tag consultar(Tag tag) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from tag where pk_tag=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, tag.getPkTag());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, tag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return tag;
	}
	
	private void carregar(ResultSet rs ,Tag tag) throws SQLException{
		tag.setPkTag(rs.getLong("pk_tag"));
		tag.setNome(rs.getString("no_tag"));
		tag.setDescricao(rs.getString("ds_tag"));
	}
}
