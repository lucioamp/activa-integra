package dao.integra;

import interfaces.integra.UsuarioAplicacaoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

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
			String sql = "select a.id_aplicacao, a.nome, r.id_recurso, r.nome";
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
				obj.setIdAplicacao(rs.getLong(1));
				obj.setNomeAplicacao(rs.getString(2));
				obj.setIdRecurso(rs.getLong(3));
				obj.setNomeRecurso(rs.getString(4));
				
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
				String sql ="delete from ae_usuario_aplicacao where pk_usuario = ? and id_aplicacao = ? and id_recurso = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, usuarioAplicacao.getPkUsuario());
				stmt.setLong(2, usuarioAplicacao.getIdAplicacao());
				stmt.setLong(3, usuarioAplicacao.getIdRecurso());
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
							
			String sql = "insert into ae_usuario_aplicacao (pk_usuario,id_aplicacao,id_recurso,permissao,mostrar_janela,atualizacao_automatica,tempo_valor)";
			sql += " values (?,?,?,?,?,?,?)";
							
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
			sql += " permissao = ?,mostrar_janela = ?,atualizacao_automatica = ?,tempo_valor = ?";
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

			stmt.setLong(5, usuarioAplicacao.getIdUsuarioAplicacao());
			
			System.out.println(stmt);
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	}
	
	public Long consultarUsuarioAplicacao(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException{
		Long retValue = null;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select id_usuario_aplicacao";
			sql += " from ae_usuario_aplicacao";
			sql += " where pk_usuario = ? and id_aplicacao = ? and id_recurso = ?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuarioAplicacao.getPkUsuario());
			stmt.setLong(2, usuarioAplicacao.getIdAplicacao());
			stmt.setLong(3, usuarioAplicacao.getIdRecurso());
			
			rs = stmt.executeQuery();
			
			while (rs.next()){
				retValue = rs.getLong(1);
			}
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return retValue;
	}
}
