package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Visao;
import modelo.Meta;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;
import interfaces.VisaoI;

public class VisaoDAO implements VisaoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Visao visao) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into visao (pk_visao,no_visao,ds_visao,dt_visao,fk_meta) values (?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "visao", "pk_visao");
				
				stmt.setLong(1,pk);
				stmt.setString(2,visao.getNome());
				stmt.setString(3,visao.getDescricao());
				stmt.setString(4,visao.getData());
				stmt.setLong(5,visao.getMeta().getPkMeta());
				
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
	
	public int alterar(Visao visao) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update visao set no_visao=?,ds_visao=?,dt_visao=?,fk_meta=? where pk_visao=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, visao.getNome());
			stmt.setString(2, visao.getDescricao());
			stmt.setString(3, visao.getData());
			stmt.setLong(4, visao.getMeta().getPkMeta());
			stmt.setLong(5, visao.getPkVisao());			
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Visao visao) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from visao where pk_visao = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, visao.getPkVisao());
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
	
	public Collection<Visao> consultarTodos() throws CadastroException{
		Collection<Visao> col = new ArrayList<Visao>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from visao,meta, ambiente where fk_ambiente=pk_ambiente and fk_meta=pk_meta";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Visao visao = new Visao();
					carregar(rs, visao);
					col.add(visao);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Visao> consultarPorMeta(Meta meta) throws CadastroException{
		Collection<Visao> col = new ArrayList<Visao>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from visao,meta, ambiente where pk_meta=? and fk_ambiente=pk_ambiente and fk_meta=pk_meta";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, meta.getPkMeta());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Visao visao = new Visao();
					carregar(rs, visao);
					col.add(visao);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Visao consultar(Visao visao) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from visao,meta, ambiente where pk_visao=? and fk_ambiente=pk_ambiente and fk_meta=pk_meta";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, visao.getPkVisao());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, visao);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return visao;
	}
	
	private void carregar(ResultSet rs ,Visao visao) throws SQLException{
		//carrega ambiente
		visao.getMeta().getAmbiente().setPkAmbiente(rs.getLong("pk_ambiente"));
		visao.getMeta().getAmbiente().setNome(rs.getString("no_ambiente"));
		visao.getMeta().getAmbiente().setData(rs.getString("dt_ambiente"));
		visao.getMeta().getAmbiente().setDescricao(rs.getString("ds_ambiente"));
		visao.getMeta().getAmbiente().setImagem(rs.getString("no_imagem"));
		
		//carrega meta
		visao.getMeta().setPkMeta(rs.getLong("pk_meta"));
		visao.getMeta().setDescricao(rs.getString("ds_meta"));
		visao.getMeta().setDuracao(rs.getString("ds_duracao"));
		
		//carrega visao
		visao.setPkVisao(rs.getLong("pk_visao"));
		visao.setNome(rs.getString("no_visao"));
		visao.setDescricao(rs.getString("ds_visao"));
		visao.setData(rs.getString("dt_visao"));
	}
}
