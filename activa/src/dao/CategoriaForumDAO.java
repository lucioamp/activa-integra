package dao;

import interfaces.CategoriaForumI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Ambiente;
import modelo.CategoriaForum;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class CategoriaForumDAO implements CategoriaForumI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (CategoriaForum categoriaForum) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into categoriaforum (pk_catForum,no_catForum,ds_catForum, fk_etapa) values (?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "categoriaForum", "pk_catForum");
				
				stmt.setLong(1,pk);
				stmt.setString(2,categoriaForum.getNome());
				stmt.setString(3, categoriaForum.getDescricao());
				stmt.setLong(4,categoriaForum.getEtapa().getPkEtapa());				
				
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
	
	public int alterar(CategoriaForum categoriaForum) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update categoriaforum set no_catForum=?, ds_catForum=?,fk_etapa=? where pk_catForum=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, categoriaForum.getNome());
			stmt.setString(2, categoriaForum.getDescricao());
			stmt.setLong(3,categoriaForum.getEtapa().getPkEtapa());	
			stmt.setLong(4, categoriaForum.getPkCatForum());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (CategoriaForum categoriaForum) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from categoriaforum where pk_catForum = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, categoriaForum.getPkCatForum());
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
	
	public Collection<CategoriaForum> consultarTodos() throws CadastroException{
		Collection<CategoriaForum> col = new ArrayList<CategoriaForum>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from categoriaforum,etapa,meta,ambiente " +
						" where fk_ambiente=pk_ambiente and fk_meta=pk_meta and fk_etapa=pk_etapa";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					CategoriaForum categoriaForum = new CategoriaForum();
					carregar(rs, categoriaForum);
					col.add(categoriaForum);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<CategoriaForum> consultarPorAmbiente(Ambiente ambiente) throws CadastroException{
		Collection<CategoriaForum> col = new ArrayList<CategoriaForum>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from categoriaforum,etapa,meta,ambiente " +
						" where pk_ambiente=? and fk_ambiente=pk_ambiente and fk_meta=pk_meta and fk_etapa=pk_etapa";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, ambiente.getPkAmbiente());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					CategoriaForum categoriaForum = new CategoriaForum();
					carregar(rs, categoriaForum);
					col.add(categoriaForum);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public CategoriaForum consultar(CategoriaForum categoriaForum) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from categoriaforum,etapa,meta,ambiente " +
			             " where pk_catForum=? and fk_ambiente=pk_ambiente and fk_meta=pk_meta and fk_etapa=pk_etapa";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, categoriaForum.getPkCatForum());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, categoriaForum);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return categoriaForum;
	}
	
	private void carregar(ResultSet rs ,CategoriaForum categoriaForum) throws SQLException{
		//carrega ambiente
		categoriaForum.getEtapa().getMeta().getAmbiente().setPkAmbiente(rs.getLong("pk_ambiente"));
		categoriaForum.getEtapa().getMeta().getAmbiente().setNome(rs.getString("no_ambiente"));
		categoriaForum.getEtapa().getMeta().getAmbiente().setData(rs.getString("dt_ambiente"));
		categoriaForum.getEtapa().getMeta().getAmbiente().setDescricao(rs.getString("ds_ambiente"));
		categoriaForum.getEtapa().getMeta().getAmbiente().setImagem(rs.getString("no_imagem"));
		
		//carrega meta
		categoriaForum.getEtapa().getMeta().setPkMeta(rs.getLong("pk_meta"));
		categoriaForum.getEtapa().getMeta().setDescricao(rs.getString("ds_meta"));
		categoriaForum.getEtapa().getMeta().setDuracao(rs.getString("ds_duracao"));
		
		//carrega etapa
		categoriaForum.getEtapa().setPkEtapa(rs.getLong("pk_etapa"));
		categoriaForum.getEtapa().setNome(rs.getString("no_etapa"));
		categoriaForum.getEtapa().setDescricao(rs.getString("ds_etapa"));
		categoriaForum.getEtapa().setData(rs.getString("dt_etapa"));
		
		//carrega categoriaForum		
		categoriaForum.setPkCatForum(rs.getLong("pk_catForum"));
		categoriaForum.setNome(rs.getString("no_catForum"));
		categoriaForum.setDescricao(rs.getString("ds_catForum"));
	}
}
