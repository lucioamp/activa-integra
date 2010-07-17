package dao.integra;

import interfaces.integra.UsuarioAplicacaoI;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import modelo.integra.UsuarioAplicacao;
import util.AplicacaoExternaException;
import util.ConnectionFactory;

public class UsuarioAplicacaoDAO implements UsuarioAplicacaoI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public Collection<UsuarioAplicacao> consultarPorUsuario(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException{
		Collection<UsuarioAplicacao> col = new ArrayList<UsuarioAplicacao>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select u.id_usuario_aplicacao, a.id_aplicacao, a.nome, r.id_recurso, r.nome";
			sql += " from ae_usuario_aplicacao u, ae_aplicacao a, ae_recurso r";
			sql += " where u.id_aplicacao = a.id_aplicacao and u.id_recurso = r.id_recurso";
			sql += " and pk_usuario = ?";
			sql += " order by a.nome, r.nome";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuarioAplicacao.getPkUsuario());
			
			System.out.println("UsuarioAplicacaoDAO Consultar consultarPorUsuario :" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				UsuarioAplicacao obj = new UsuarioAplicacao();
				obj.setIdUsuarioAplicacao(rs.getLong(1));
				obj.setIdAplicacao(rs.getLong(2));
				obj.setNomeAplicacao(rs.getString(3));
				obj.setIdRecurso(rs.getLong(4));
				obj.setNomeRecurso(rs.getString(5));
				
				col.add(obj);
			}
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	public int excluir (UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from ae_usuario_aplicacao where id_usuario_aplicacao = ?";
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, usuarioAplicacao.getIdUsuarioAplicacao());

				r = stmt.executeUpdate();
			
			}catch (Exception e) {
				throw new AplicacaoExternaException(e);
			}
			finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
		
		return r;
	}
	
	public long incluir (UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
							
			String sql = "insert into ae_usuario_aplicacao (pk_usuario,id_aplicacao,id_recurso,permissao,mostrar_janela,atualizacao_automatica,tempo_valor,usuario,senha)";
			sql += " values (?,?,?,?,?,?,?,?,?)";
							
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuarioAplicacao.getPkUsuario());
			stmt.setLong(2, usuarioAplicacao.getIdAplicacao());
			stmt.setLong(3, usuarioAplicacao.getIdRecurso());
			stmt.setInt(4, usuarioAplicacao.getPermissao());
			stmt.setString(5, usuarioAplicacao.getMostrarJanela());
			
			if (usuarioAplicacao.getAtualizacaoAutomatica() != null) {
				stmt.setInt(6, usuarioAplicacao.getAtualizacaoAutomatica());
			}
			else {
				stmt.setNull(6, Types.INTEGER);
			}
			
			if (usuarioAplicacao.getTempoValor() != null) {
				stmt.setInt(7, usuarioAplicacao.getTempoValor());
			}
			else {
				stmt.setNull(7, Types.INTEGER);
			}
			stmt.setString(8, usuarioAplicacao.getUsuario());
			stmt.setString(9, usuarioAplicacao.getSenha());
			
			System.out.println(stmt);
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return getIDUsuarioAplicacao();
	}
	
	public long getIDUsuarioAplicacao() throws AplicacaoExternaException{
		long retValue = 0;
		
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select max(id_usuario_aplicacao) from ae_usuario_aplicacao";
		
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
	
	public void atualizar (UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
							
			String sql = "update ae_usuario_aplicacao set";
			sql += " permissao = ?,mostrar_janela = ?,atualizacao_automatica = ?,tempo_valor = ?,usuario = ?,senha = ?";
			sql += " where id_usuario_aplicacao = ?";
							
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, usuarioAplicacao.getPermissao());
			stmt.setString(2, usuarioAplicacao.getMostrarJanela());
			
			if (usuarioAplicacao.getAtualizacaoAutomatica() != null) {
				stmt.setInt(3, usuarioAplicacao.getAtualizacaoAutomatica());
			}
			else {
				stmt.setNull(3, Types.INTEGER);
			}
			
			if (usuarioAplicacao.getTempoValor() != null) {
				stmt.setInt(4, usuarioAplicacao.getTempoValor());
			}
			else {
				stmt.setNull(4, Types.INTEGER);
			}
			
			stmt.setString(5, usuarioAplicacao.getUsuario());
			stmt.setString(6, usuarioAplicacao.getSenha());

			stmt.setLong(7, usuarioAplicacao.getIdUsuarioAplicacao());
			
			System.out.println(stmt);
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	}
	
	public void consultarUsuarioAplicacao(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select *";
			sql += " from ae_usuario_aplicacao";
			sql += " where pk_usuario = ? and id_aplicacao = ? and id_recurso = ?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuarioAplicacao.getPkUsuario());
			stmt.setLong(2, usuarioAplicacao.getIdAplicacao());
			stmt.setLong(3, usuarioAplicacao.getIdRecurso());
			
			rs = stmt.executeQuery();
			
			while (rs.next()){
				carregar(rs, usuarioAplicacao);
			}
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	}
	
	public void consultarPorId(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select *";
			sql += " from ae_usuario_aplicacao";
			sql += " where id_usuario_aplicacao = ?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuarioAplicacao.getIdUsuarioAplicacao());
			
			rs = stmt.executeQuery();
			
			while (rs.next()){
				carregar(rs, usuarioAplicacao);
			}
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	}
	
	public List<UsuarioAplicacao> consultarNotificacaoAutomatica(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException{
		List<UsuarioAplicacao> col = new ArrayList<UsuarioAplicacao>();
		
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select *";
			sql += " from ae_usuario_aplicacao";
			sql += " where ((permissao = 1 and pk_usuario = ?) or (permissao = 2)) and atualizacao_automatica = 2";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuarioAplicacao.getPkUsuario());
			
			rs = stmt.executeQuery();
			
			while (rs.next()){
				UsuarioAplicacao obj = new UsuarioAplicacao();
				carregar(rs, obj);
				col.add(obj);
			}
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	public String consultaCache(Long idUsuarioAplicacao) throws AplicacaoExternaException{
		Blob retorno = null;
		
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select conteudo";
			sql += " from ae_usuario_aplicacao_cache";
			sql += " where id_usuario_aplicacao = ?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, idUsuarioAplicacao);
			
			rs = stmt.executeQuery();
			
			while (rs.next()){
				retorno = rs.getBlob("conteudo");
			}
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		if (retorno != null) {
			byte[] bdata;
			try {
				bdata = retorno.getBytes(1, (int) retorno.length());
				return new String(bdata);
			} catch (SQLException e) {
			}
		}
		
		return "";
	}
	
	public void atualizaCache(Long idUsuarioAplicacao, String conteudo) throws AplicacaoExternaException{
		
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select *";
			sql += " from ae_usuario_aplicacao_cache";
			sql += " where id_usuario_aplicacao = ?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, idUsuarioAplicacao);
			
			rs = stmt.executeQuery();
			
			if (rs.next()){
				sql = "update ae_usuario_aplicacao_cache set conteudo = ? where id_usuario_aplicacao = ?";
			} else {
				sql = "insert into ae_usuario_aplicacao_cache(conteudo, id_usuario_aplicacao) values(?, ?)";
			}
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,  conteudo);
			stmt.setLong(2, idUsuarioAplicacao);
			
			stmt.executeUpdate();
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	}
	
	private void carregar(ResultSet rs, UsuarioAplicacao usuarioAplicacao) throws SQLException {
		usuarioAplicacao.setIdUsuarioAplicacao(rs.getLong("id_usuario_aplicacao"));
		usuarioAplicacao.setPkUsuario(rs.getLong("pk_usuario"));
		usuarioAplicacao.setIdAplicacao(rs.getLong("id_aplicacao"));
		usuarioAplicacao.setIdRecurso(rs.getLong("id_recurso"));
		usuarioAplicacao.setPermissao(rs.getInt("permissao"));
		usuarioAplicacao.setMostrarJanela(rs.getString("mostrar_janela"));
		if (rs.getInt("atualizacao_automatica") > 0) {
			usuarioAplicacao.setAtualizacaoAutomatica(rs.getInt("atualizacao_automatica"));
			usuarioAplicacao.setTempoValor(rs.getInt("tempo_valor"));
		}
		usuarioAplicacao.setUsuario(rs.getString("usuario"));
		usuarioAplicacao.setSenha(rs.getString("senha"));
	}
}
