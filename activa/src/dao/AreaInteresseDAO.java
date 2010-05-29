package dao;

import interfaces.AreaInteresseI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.AreaInteresse;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class AreaInteresseDAO implements AreaInteresseI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (AreaInteresse areaInteresse) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into areainteresse (pk_areaInteresse,no_areaInteresse,ds_areaInteresse) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "areaInteresse", "pk_areaInteresse");
				
				stmt.setLong(1,pk);
				stmt.setString(2,areaInteresse.getNome());
				stmt.setString(3, areaInteresse.getDescricao());
				
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
	
	public int alterar(AreaInteresse areaInteresse) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update areainteresse set no_areaInteresse=?, ds_areaInteresse=? where pk_areaInteresse=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, areaInteresse.getNome());
			stmt.setString(2, areaInteresse.getDescricao());
			stmt.setLong(3, areaInteresse.getPkAreaInteresse());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (AreaInteresse areaInteresse) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from areainteresse where pk_areaInteresse = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, areaInteresse.getPkAreaInteresse());
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
	
	public Collection<AreaInteresse> consultarTodos() throws CadastroException{
		Collection<AreaInteresse> col = new ArrayList<AreaInteresse>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from areaInteresse";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					AreaInteresse areaInteresse = new AreaInteresse();
					carregar(rs, areaInteresse);
					col.add(areaInteresse);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public AreaInteresse consultar(AreaInteresse areaInteresse) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from areainteresse where pk_areaInteresse=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, areaInteresse.getPkAreaInteresse());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, areaInteresse);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return areaInteresse;
	}
	
	private void carregar(ResultSet rs ,AreaInteresse areaInteresse) throws SQLException{
		areaInteresse.setPkAreaInteresse(rs.getLong("pk_areaInteresse"));
		areaInteresse.setNome(rs.getString("no_areaInteresse"));
		areaInteresse.setDescricao(rs.getString("ds_areaInteresse"));
	}
}
