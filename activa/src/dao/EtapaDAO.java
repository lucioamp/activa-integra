package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Etapa;
import modelo.Meta;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;
import interfaces.EtapaI;

public class EtapaDAO implements EtapaI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Etapa etapa) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into etapa (pk_etapa,no_etapa,ds_etapa,dt_etapa,fk_meta) values (?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "etapa", "pk_etapa");
				
				stmt.setLong(1,pk);
				stmt.setString(2,etapa.getNome());
				stmt.setString(3,etapa.getDescricao());
				stmt.setString(4,etapa.getData());
				stmt.setLong(5,etapa.getMeta().getPkMeta());
				
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
	
	public int alterar(Etapa etapa) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update etapa set no_etapa=?,ds_etapa=?,dt_etapa=?,fk_meta=? where pk_etapa=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, etapa.getNome());
			stmt.setString(2, etapa.getDescricao());
			stmt.setString(3, etapa.getData());
			stmt.setLong(4, etapa.getMeta().getPkMeta());
			stmt.setLong(5, etapa.getPkEtapa());			
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Etapa etapa) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from etapa where pk_etapa = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, etapa.getPkEtapa());
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
	
	public Collection<Etapa> consultarTodos() throws CadastroException{
		Collection<Etapa> col = new ArrayList<Etapa>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from etapa,meta, ambiente where fk_ambiente=pk_ambiente and fk_meta=pk_meta";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Etapa etapa = new Etapa();
					carregar(rs, etapa);
					col.add(etapa);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Etapa> consultarPorMeta(Meta meta) throws CadastroException{
		Collection<Etapa> col = new ArrayList<Etapa>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from etapa,meta, ambiente where pk_meta=? and fk_ambiente=pk_ambiente and fk_meta=pk_meta";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, meta.getPkMeta());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Etapa etapa = new Etapa();
					carregar(rs, etapa);
					col.add(etapa);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Etapa consultar(Etapa etapa) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from etapa,meta, ambiente where pk_etapa=? and fk_ambiente=pk_ambiente and fk_meta=pk_meta";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, etapa.getPkEtapa());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, etapa);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return etapa;
	}
	
	private void carregar(ResultSet rs ,Etapa etapa) throws SQLException{
		//carrega ambiente
		etapa.getMeta().getAmbiente().setPkAmbiente(rs.getLong("pk_ambiente"));
		etapa.getMeta().getAmbiente().setNome(rs.getString("no_ambiente"));
		etapa.getMeta().getAmbiente().setData(rs.getString("dt_ambiente"));
		etapa.getMeta().getAmbiente().setDescricao(rs.getString("ds_ambiente"));
		etapa.getMeta().getAmbiente().setImagem(rs.getString("no_imagem"));
		
		//carrega meta
		etapa.getMeta().setPkMeta(rs.getLong("pk_meta"));
		etapa.getMeta().setDescricao(rs.getString("ds_meta"));
		etapa.getMeta().setDuracao(rs.getString("ds_duracao"));
		
		//carrega etapa
		etapa.setPkEtapa(rs.getLong("pk_etapa"));
		etapa.setNome(rs.getString("no_etapa"));
		etapa.setDescricao(rs.getString("ds_etapa"));
		etapa.setData(rs.getString("dt_etapa"));
	}
}
