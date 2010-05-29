package dao.integra;

import interfaces.integra.AplicacaoExternaI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

import modelo.integra.AplicacaoExterna;
import util.AplicacaoExternaException;
import util.ConnectionFactory;

public class AplicacaoExternaDAO implements AplicacaoExternaI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public long incluir (AplicacaoExterna aplicacao) throws AplicacaoExternaException{
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql = "insert into ae_aplicacao (nome,url,id_processamento,data_cadastro,auth_basica,auth_openid,auth_gauth)";
				sql += " values (?,?,?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1,aplicacao.getNome());
				stmt.setString(2,aplicacao.getUrl());
				stmt.setInt(3, aplicacao.getIdProcessamento());
				stmt.setDate(4, new Date(new java.util.Date().getTime()));
				stmt.setInt(5, aplicacao.isAuthBasica() ? 1 : 0);
				stmt.setInt(6, aplicacao.isAuthOpenId() ? 1 : 0);
				stmt.setInt(7, aplicacao.isAuthGAuth() ? 1 : 0);
				
				System.out.println(stmt);
				stmt.executeUpdate();
				
			}catch (Exception e) {
				throw new AplicacaoExternaException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return getIDAplicacao();
	}
	
	public int excluir (AplicacaoExterna aplicacao) throws AplicacaoExternaException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from ae_aplicacao where id_aplicacao = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, aplicacao.getIdAplicacao());
				r = stmt.executeUpdate();
			
			}catch (Exception e) {
				throw new AplicacaoExternaException(e);
			}
			finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
		
		return r;
	}
	
	public Collection<AplicacaoExterna> consultarTodos() throws AplicacaoExternaException{
		Collection<AplicacaoExterna> col = new ArrayList<AplicacaoExterna>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from ae_aplicacao order by nome";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					AplicacaoExterna aplicacao = new AplicacaoExterna();
					carregar(rs, aplicacao);
					col.add(aplicacao);
				}
			}catch (Exception e) {
				throw new AplicacaoExternaException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public void consultar(AplicacaoExterna aplicacao) throws AplicacaoExternaException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from ae_aplicacao where id_aplicacao=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, aplicacao.getIdAplicacao());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, aplicacao);
			}
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	}
	
	public long getIDAplicacao() throws AplicacaoExternaException{
		long retValue = 0;
		
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select max(id_aplicacao) from ae_aplicacao";
		
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if (rs.next()){
				retValue = rs.getLong(1);
			}
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return retValue;
	}
	
	private void carregar(ResultSet rs, AplicacaoExterna aplicacao) throws SQLException {
		aplicacao.setIdAplicacao(rs.getLong("id_aplicacao"));
		aplicacao.setNome(rs.getString("nome"));
		aplicacao.setUrl(rs.getString("url"));
	}

}
