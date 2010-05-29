package dao;

import interfaces.AreaTrabalhoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.AreaTrabalho;
import modelo.Membro;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class AreaTrabalhoDAO implements AreaTrabalhoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (AreaTrabalho areaTrabalho) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into areatrabalho (pk_areaTrabalho,no_areaTrabalho,ds_areaTrabalho, fk_membro) values (?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "areaTrabalho", "pk_areaTrabalho");
				
				stmt.setLong(1,pk);
				stmt.setString(2,areaTrabalho.getNome());
				stmt.setString(3, areaTrabalho.getDescricao());
				stmt.setLong(4, areaTrabalho.getMembro().getPkUsuario());
				
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
	
	public int alterar(AreaTrabalho areaTrabalho) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update areatrabalho set no_areaTrabalho=?, ds_areaTrabalho=?, fk_membro = ? where pk_areaTrabalho=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, areaTrabalho.getNome());
			stmt.setString(2, areaTrabalho.getDescricao());
			stmt.setLong(3, areaTrabalho.getMembro().getPkUsuario());
			stmt.setLong(4, areaTrabalho.getPkAreaTrabalho());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (AreaTrabalho areaTrabalho) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from areatrabalho where pk_areaTrabalho = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, areaTrabalho.getPkAreaTrabalho());
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
	
	public Collection<AreaTrabalho> consultarTodos() throws CadastroException{
		Collection<AreaTrabalho> col = new ArrayList<AreaTrabalho>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from areatrabalho";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					AreaTrabalho areaTrabalho = new AreaTrabalho();
					carregar(rs, areaTrabalho);
					col.add(areaTrabalho);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public AreaTrabalho consultar(AreaTrabalho areaTrabalho) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from areatrabalho where pk_areaTrabalho=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, areaTrabalho.getPkAreaTrabalho());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, areaTrabalho);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return areaTrabalho;
	}
	
	public Collection<AreaTrabalho> consultarPorMembro(Membro membro) throws CadastroException{
		Collection<AreaTrabalho> col = new ArrayList<AreaTrabalho>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from areatrabalho where fk_membro=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				AreaTrabalho areaTrabalho = new AreaTrabalho();
				carregar(rs, areaTrabalho);
				col.add(areaTrabalho);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,AreaTrabalho areaTrabalho) throws SQLException{
		areaTrabalho.setPkAreaTrabalho(rs.getLong("pk_areaTrabalho"));
		areaTrabalho.setNome(rs.getString("no_areaTrabalho"));
		areaTrabalho.setDescricao(rs.getString("ds_areaTrabalho"));
		areaTrabalho.getMembro().setPkUsuario(rs.getLong("fk_membro"));
	}
}
