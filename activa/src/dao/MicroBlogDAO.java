package dao;

import interfaces.MicroBlogI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.MicroBlog;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class MicroBlogDAO implements MicroBlogI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (MicroBlog microBlog) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into microblog (pk_microBlog,ds_microBlog,fk_membro, st_atual) values (?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "microBlog", "pk_microBlog");
				
				stmt.setLong(1,pk);
				stmt.setString(2,microBlog.getDescricao());
				stmt.setLong(3,microBlog.getMembro().getPkUsuario());
				stmt.setLong(4,microBlog.getSt_atual());
				
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
	
	public int alterar(MicroBlog microBlog) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update microblog set ds_microBlog=?, fk_membro=?, st_atual=? where pk_microBlog=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, microBlog.getDescricao());
			stmt.setLong(2, microBlog.getMembro().getPkUsuario());
			stmt.setInt(3, microBlog.getSt_atual());
			stmt.setLong(4, microBlog.getPkMicroBlog());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (MicroBlog microBlog) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from microblog where pk_microBlog = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, microBlog.getPkMicroBlog());
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
	
	public Collection<MicroBlog> consultarTodos() throws CadastroException{
		Collection<MicroBlog> col = new ArrayList<MicroBlog>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from microblog";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					MicroBlog microBlog = new MicroBlog();
					carregar(rs, microBlog);
					col.add(microBlog);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<MicroBlog> consultarPorMembro(Membro membro) throws CadastroException{
		Collection<MicroBlog> col = new ArrayList<MicroBlog>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from microBlog where fk_membro=?";
			
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, membro.getPkUsuario());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					MicroBlog microBlog = new MicroBlog();
					carregar(rs, microBlog);
					col.add(microBlog);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public MicroBlog consultar(MicroBlog microBlog) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from microBlog where pk_microBlog=?";
			
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, microBlog.getPkMicroBlog());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, microBlog);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return microBlog;
	}
	
	public void limparStatusPorMembro(Membro membro) throws CadastroException
	{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update microblog set st_atual=0 where fk_membro=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, membro.getPkUsuario());
						
			stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	}
	
	private void carregar(ResultSet rs ,MicroBlog microBlog) throws SQLException{
		
		//carrega microblog
		microBlog.setPkMicroBlog(rs.getLong("pk_microBlog"));
		microBlog.setDescricao(rs.getString("ds_microBlog"));
		microBlog.setSt_atual(rs.getInt("st_atual"));
	}
}
