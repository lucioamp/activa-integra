package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Tarefa;
import modelo.Etapa;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;
import interfaces.TarefaI;

public class TarefaDAO implements TarefaI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Tarefa tarefa) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into tarefa (pk_tarefa,no_tarefa,ds_tarefa,dt_tarefa,fk_etapa) values (?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "tarefa", "pk_tarefa");
				
				stmt.setLong(1,pk);
				stmt.setString(2,tarefa.getNome());
				stmt.setString(3,tarefa.getDescricao());
				stmt.setString(4,tarefa.getData());
				stmt.setLong(5,tarefa.getVisao().getPkEtapa());
				
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
	
	public int alterar(Tarefa tarefa) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update tarefa set no_tarefa=?,ds_tarefa=?,dt_tarefa=?,fk_etapa=? where pk_tarefa=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tarefa.getNome());
			stmt.setString(2, tarefa.getDescricao());
			stmt.setString(3, tarefa.getData());
			stmt.setLong(4, tarefa.getVisao().getPkEtapa());
			stmt.setLong(5, tarefa.getPkTarefa());			
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Tarefa tarefa) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from tarefa where pk_tarefa = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, tarefa.getPkTarefa());
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
	
	public int excluirPorEtapa (Etapa etapa) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from tarefa where 	fk_etapa = ?";
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
	
	public Collection<Tarefa> consultarTodos() throws CadastroException{
		Collection<Tarefa> col = new ArrayList<Tarefa>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from tarefa,etapa,meta, ambiente where " +
						" fk_ambiente=pk_ambiente and fk_meta=pk_meta and pk_etapa=fk_etapa";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Tarefa tarefa = new Tarefa();
					carregar(rs, tarefa);
					col.add(tarefa);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Tarefa> consultarPorVisao(Etapa visao) throws CadastroException{
		Collection<Tarefa> col = new ArrayList<Tarefa>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from tarefa,etapa,meta, ambiente where " +
						" pk_etapa=? and fk_ambiente=pk_ambiente and fk_meta=pk_meta and pk_etapa=fk_etapa";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, visao.getPkEtapa());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Tarefa tarefa = new Tarefa();
					carregar(rs, tarefa);
					col.add(tarefa);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Tarefa consultar(Tarefa tarefa) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from tarefa,etapa,meta, ambiente where pk_tarefa=? " +
					" and fk_ambiente=pk_ambiente and fk_meta=pk_meta and pk_etapa=fk_etapa";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, tarefa.getPkTarefa());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, tarefa);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return tarefa;
	}
	
	private void carregar(ResultSet rs ,Tarefa tarefa) throws SQLException{
		//carrega ambiente
		tarefa.getVisao().getMeta().getAmbiente().setPkAmbiente(rs.getLong("pk_ambiente"));
		tarefa.getVisao().getMeta().getAmbiente().setNome(rs.getString("no_ambiente"));
		tarefa.getVisao().getMeta().getAmbiente().setData(rs.getString("dt_ambiente"));
		tarefa.getVisao().getMeta().getAmbiente().setDescricao(rs.getString("ds_ambiente"));
		tarefa.getVisao().getMeta().getAmbiente().setImagem(rs.getString("no_imagem"));
		
		//carrega meta
		tarefa.getVisao().getMeta().setPkMeta(rs.getLong("pk_meta"));
		tarefa.getVisao().getMeta().setDescricao(rs.getString("ds_meta"));
		tarefa.getVisao().getMeta().setDuracao(rs.getString("ds_duracao"));
		
		//carrega visao
		tarefa.getVisao().setPkEtapa(rs.getLong("pk_etapa"));
		tarefa.getVisao().setNome(rs.getString("no_etapa"));
		tarefa.getVisao().setDescricao(rs.getString("ds_etapa"));
		tarefa.getVisao().setData(rs.getString("dt_etapa"));
		
		//carrega tarefa
		tarefa.setPkTarefa(rs.getLong("pk_tarefa"));
		tarefa.setNome(rs.getString("no_tarefa"));
		tarefa.setDescricao(rs.getString("ds_tarefa"));
		tarefa.setData(rs.getString("dt_tarefa"));
	}
}
