package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.TipoContato;

import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

import interfaces.TipoContatoI;

public class TipoContatoDAO implements TipoContatoI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (TipoContato tipoContato) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into tipocontato (pk_tipoContato,ds_tipoContato) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "tipocontato", "pk_tipoContato");
				
				stmt.setLong(1,pk);
				stmt.setString(2,tipoContato.getTipoContato());
				
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
	
	public int alterar(TipoContato tipoContato) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update tipocontato set ds_tipoContato=? where pk_tipoContato=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tipoContato.getTipoContato());
			stmt.setLong(2, tipoContato.getPkTipoContato());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (TipoContato tipoContato) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from tipocontato where pk_tipoContato = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, tipoContato.getPkTipoContato());
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
	
	public Collection<TipoContato> consultarTodos() throws CadastroException{
		Collection<TipoContato> col = new ArrayList<TipoContato>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from tipocontato";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					TipoContato tipoContato = new TipoContato();
					carregar(rs, tipoContato);
					col.add(tipoContato);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public TipoContato consultar(TipoContato tipoContato) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from tipocontato where pk_tipoContato=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, tipoContato.getPkTipoContato());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, tipoContato);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return tipoContato;
	}
	
	private void carregar(ResultSet rs ,TipoContato tipoContato) throws SQLException{
		tipoContato.setPkTipoContato(rs.getLong("pk_tipoContato"));
		tipoContato.setTipoContato(rs.getString("ds_tipoContato"));	
	}

}
