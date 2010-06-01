package dao.integra;

import interfaces.integra.UsuarioAplicacaoParametroI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.integra.UsuarioAplicacaoParametro;
import util.AplicacaoExternaException;
import util.ConnectionFactory;

public class UsuarioAplicacaoParametroDAO implements UsuarioAplicacaoParametroI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public void excluirPorUsuarioAplicacao (long idUsuarioAplicacao) throws AplicacaoExternaException{
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from ae_usuario_aplicacao_parametro where id_usuario_aplicacao = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, idUsuarioAplicacao);
				stmt.executeUpdate();
			
			}catch (Exception e) {
				throw new AplicacaoExternaException(e);
			}
			finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
	}
	
	public void incluir (UsuarioAplicacaoParametro usuarioAplicacaoParametro) throws AplicacaoExternaException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
							
			String sql = "insert into ae_usuario_aplicacao_parametro (id_usuario_aplicacao,id_parametro,valor_padrao,bloquear_valor)";
			sql += " values (?,?,?,?)";
							
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuarioAplicacaoParametro.getIdUsuarioAplicacao());
			stmt.setLong(2, usuarioAplicacaoParametro.getIdParametro());
			stmt.setString(3, usuarioAplicacaoParametro.getValorPadrao());
			stmt.setInt(4, usuarioAplicacaoParametro.isBloquearValor() ? 1 : 0);
			
			System.out.println(stmt);
			stmt.executeUpdate();
			
		}catch (Exception e) {
			throw new AplicacaoExternaException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	}
	
}