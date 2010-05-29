package dao;

import interfaces.PastasFavoritoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Favorito;
import modelo.PastasFavorito;
import modelo.Usuario;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class PastasFavoritoDAO implements PastasFavoritoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (PastasFavorito pasta) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into pastasfavorito (pk_pasta, no_pasta, fk_pasta, fk_membro) values (?,?, "+(pasta.getPasta() != null ? pasta.getPasta().getPkPasta() : "null")+", ?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "pastasfavorito", "pk_pasta");
				
				stmt.setLong(1, pk);
				stmt.setString(2,pasta.getNome());
				stmt.setLong(3,pasta.getUsuario().getPkUsuario());
				
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
	
	public int alterar(PastasFavorito pasta) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update pastasfavorito set no_pasta=?, fk_pasta="+(pasta.getPasta() != null ? pasta.getPasta().getPkPasta() : "null")+", fk_membro = ? where pk_pasta=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pasta.getNome());
			stmt.setLong(2, pasta.getUsuario().getPkUsuario());
			stmt.setLong(3, pasta.getPkPasta());
						
			System.out.println(stmt);
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (PastasFavorito pasta) throws CadastroException{
		int r = 0;
			try{
				// Excluir favoritos
				Favorito.excluirPorPasta(pasta);
				
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from pastasfavorito where pk_pasta = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, pasta.getPkPasta());
				r = stmt.executeUpdate();
			}catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
				throw new CadastroException(e);
			}
			finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
		
		return r;
	}
	
	public PastasFavorito consultar(PastasFavorito pasta) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from pastasfavorito where pk_pasta = ?";
			
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, pasta.getPkPasta());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, pasta);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return pasta;
	}
	
	public Collection<PastasFavorito> consultarPorUsuario(Usuario usuario) throws CadastroException{
		Collection<PastasFavorito> col = new ArrayList<PastasFavorito>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from pastasfavorito where fk_membro = ? order by fk_pasta, pk_pasta";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, usuario.getPkUsuario());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				while (rs.next()){
					PastasFavorito pasta = new PastasFavorito();
					carregar(rs, pasta);
					col.add(pasta);
				}
			}catch (Exception e) {
				System.out.println(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
		return col;
	}
	
	public Collection<PastasFavorito> consultarPorPasta(PastasFavorito pasta) throws CadastroException{
		Collection<PastasFavorito> col = new ArrayList<PastasFavorito>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from pastasfavorito where fk_pasta = ?";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, pasta.getPkPasta());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				while (rs.next()){
					PastasFavorito _pasta = new PastasFavorito();
					carregar(rs, _pasta);
					col.add(_pasta);
				}
			}catch (Exception e) {
				System.out.println(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
		return col;
	}
	
	public Collection<PastasFavorito> consultarTodos() throws CadastroException{
		Collection<PastasFavorito> col = new ArrayList<PastasFavorito>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from pastasfavorito";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					PastasFavorito pasta = new PastasFavorito();
					carregar(rs, pasta);
					col.add(pasta);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	private void carregar(ResultSet rs , PastasFavorito pasta) throws SQLException{
		
		pasta.setPkPasta(rs.getLong("pk_pasta"));
		pasta.setNome(rs.getString("no_pasta"));
		pasta.getUsuario().setPkUsuario(rs.getLong("fk_membro"));
		
		pasta.setPasta(new PastasFavorito());
		if(rs.getLong("fk_pasta") > 0 && pasta.getPkPasta() != rs.getLong("fk_pasta"))
		{
			pasta.getPasta().setPkPasta(rs.getLong("fk_pasta"));
			/*try {
				pasta.getPasta().consultar();
			} catch (Exception e) {
				// TODO: handle exception
			}*/
		}
	}
}
