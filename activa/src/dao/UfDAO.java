package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Uf;

import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

import interfaces.UfI;

public class UfDAO implements UfI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Uf uf) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into uf (pk_estado,no_estado,sg_estado) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "uf", "pk_estado");
				
				stmt.setLong(1,pk);
				stmt.setString(2,uf.getEstado());
				stmt.setString(3,uf.getSigla());
				
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
	
	public int alterar(Uf uf) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update uf set no_estado=?, sg_estado=? where pk_estado=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uf.getEstado());
			stmt.setString(2, uf.getSigla());
			stmt.setLong(3, uf.getPkEstado());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Uf uf) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from uf where pk_estado = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, uf.getPkEstado());
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
	
	public Collection<Uf> consultarTodos() throws CadastroException{
		Collection<Uf> col = new ArrayList<Uf>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from uf order by no_estado";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Uf uf = new Uf();
					carregar(rs, uf);
					col.add(uf);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Uf consultar(Uf uf) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from uf where pk_estado=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, uf.getPkEstado());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, uf);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return uf;
	}
	
	private void carregar(ResultSet rs ,Uf uf) throws SQLException{
		uf.setPkEstado(rs.getLong("pk_estado"));
		uf.setEstado(rs.getString("no_estado"));
		uf.setSigla(rs.getString("sg_estado")); 	
	}

}
