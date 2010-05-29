package dao;

import interfaces.FavoritoI;

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

public class FavoritoDAO implements FavoritoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Favorito favorito) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into favorito (pk_favorito,no_link,ds_link,no_url,fk_membro, fk_pasta) values (?,?,?,?,?,"+(favorito.getPasta() != null ? favorito.getPasta().getPkPasta() : "null")+")";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "favorito", "pk_favorito");
				
				stmt.setLong(1,pk);
				stmt.setString(2,favorito.getNomeLink());
				stmt.setString(3,favorito.getDescricaoLink());
				stmt.setString(4,favorito.getUrl());
				stmt.setLong(5,favorito.getMembro().getPkUsuario());
				
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
	
	public int alterar(Favorito favorito) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update favorito set no_link=?,ds_link=?,no_url=?,fk_membro=?, fk_pasta = "+(favorito.getPasta() != null ? favorito.getPasta().getPkPasta() : "null")+" where pk_favorito=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, favorito.getNomeLink());
			stmt.setString(2, favorito.getDescricaoLink());
			stmt.setString(3, favorito.getUrl());
			stmt.setLong(4, favorito.getMembro().getPkUsuario());
			stmt.setLong(5, favorito.getPkFavorito());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Favorito favorito) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from favorito where pk_favorito = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, favorito.getPkFavorito());
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
	
	public int excluirPorPasta(PastasFavorito pasta) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from favorito where fk_pasta = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, pasta.getPkPasta());
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
	
	public Collection<Favorito> consultarTodos() throws CadastroException{
		Collection<Favorito> col = new ArrayList<Favorito>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from favorito";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Favorito favorito = new Favorito();
					carregar(rs, favorito);
					col.add(favorito);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Favorito> consultarPorMembro(Usuario usuario) throws CadastroException{
		Collection<Favorito> col = new ArrayList<Favorito>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from favorito where fk_membro=?";
			
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, usuario.getPkUsuario());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Favorito favorito = new Favorito();
					carregar(rs, favorito);
					col.add(favorito);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Favorito consultar(Favorito favorito) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from favorito where pk_favorito=?";
			
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, favorito.getPkFavorito());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, favorito);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return favorito;
	}
	
	private void carregar(ResultSet rs ,Favorito favorito) throws SQLException{
		//carrega microblog
		favorito.setPkFavorito(rs.getLong("pk_favorito"));
		favorito.setNomeLink(rs.getString("no_link"));
		favorito.setDescricaoLink(rs.getString("ds_link"));
		favorito.setUrl(rs.getString("no_url"));
		
		if(rs.getLong("fk_membro") > 0)
		{
			favorito.getMembro().setPkUsuario(rs.getLong("fk_membro"));
			try {
				favorito.getMembro().consultar();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		favorito.setPasta(new PastasFavorito());
		if(rs.getLong("fk_pasta") > 0)
		{
			favorito.getPasta().setPkPasta(rs.getLong("fk_pasta"));
			try {
				favorito.getPasta().consultar();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
