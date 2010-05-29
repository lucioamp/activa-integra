package dao;

import interfaces.MetaI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Ambiente;
import modelo.Meta;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class MetaDAO implements MetaI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Meta meta) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into meta (pk_meta,ds_meta,ds_duracao,fk_ambiente) values (?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "meta", "pk_meta");
				
				stmt.setLong(1,pk);
				stmt.setString(2,meta.getDescricao());
				stmt.setString(3,meta.getDuracao());
				stmt.setLong(4,meta.getAmbiente().getPkAmbiente());
				
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
	
	public int alterar(Meta meta) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update meta set ds_meta=?,ds_duracao=?,fk_ambiente=? where pk_meta=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, meta.getDescricao());
			stmt.setString(2, meta.getDuracao());
			stmt.setLong(3, meta.getAmbiente().getPkAmbiente());
			stmt.setLong(4, meta.getPkMeta());			
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Meta meta) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from meta where pk_meta = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, meta.getPkMeta());
				r = stmt.executeUpdate();
				
				System.out.println("MetaDAO excluir :" +stmt );
			
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}
			finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
		
		return r;
	}
	
	public Collection<Meta> consultarTodos() throws CadastroException{
		Collection<Meta> col = new ArrayList<Meta>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from meta, ambiente where fk_ambiente=pk_ambiente";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Meta meta = new Meta();
					carregar(rs, meta);
					col.add(meta);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Meta> consultarPorAmbiente(Ambiente ambiente) throws CadastroException{
		Collection<Meta> col = new ArrayList<Meta>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from meta, ambiente where pk_ambiente=? and fk_ambiente=pk_ambiente";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, ambiente.getPkAmbiente());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Meta meta = new Meta();
					carregar(rs, meta);
					col.add(meta);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Meta consultar(Meta meta) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from meta, ambiente where pk_meta=? and fk_ambiente=pk_ambiente";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, meta.getPkMeta());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, meta);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return meta;
	}
	
	private void carregar(ResultSet rs ,Meta meta) throws SQLException{
		//carrega ambiente
		meta.getAmbiente().setPkAmbiente(rs.getLong("pk_ambiente"));
		meta.getAmbiente().setNome(rs.getString("no_ambiente"));
		meta.getAmbiente().setData(rs.getString("dt_ambiente"));
		meta.getAmbiente().setDescricao(rs.getString("ds_ambiente"));
		meta.getAmbiente().setImagem(rs.getString("no_imagem"));
		
		//carrega meta
		meta.setPkMeta(rs.getLong("pk_meta"));
		meta.setDescricao(rs.getString("ds_meta"));
		meta.setDuracao(rs.getString("ds_duracao")); 	
	}
}
