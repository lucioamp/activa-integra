package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.ContatoUsuario;
import modelo.Usuario;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;
import interfaces.ContatoUsuarioI;

public class ContatoUsuarioDAO implements ContatoUsuarioI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (ContatoUsuario contatoUsuario) throws CadastroException{
		int r =0;
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into contatousuario (pk_contatoUsuario, fk_tipoContato,fk_usuario,ds_contato) values (?, ?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "contatousuario", "pk_contatoUsuario");
				
				stmt.setLong(1,pk);
				stmt.setLong(2,contatoUsuario.getTipoContato().getPkTipoContato());
				stmt.setLong(3,contatoUsuario.getUsuario().getPkUsuario());
				stmt.setString(4, contatoUsuario.getContato());
				
				System.out.println(stmt);
				r=stmt.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return r;
	}
	
	public int alterar(ContatoUsuario contatoUsuario) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update contatousuario set ds_contato=?, fk_tipoContato=?, fk_usuario=? where pk_contatoUsuario=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, contatoUsuario.getContato());
			stmt.setLong(2, contatoUsuario.getTipoContato().getPkTipoContato());
			stmt.setLong(3, contatoUsuario.getUsuario().getPkUsuario());
			stmt.setLong(4, contatoUsuario.getPkContatoUsuario());
			
			System.out.println(stmt);
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (ContatoUsuario contatoUsuario) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from contatousuario where pk_contatoUsuario=?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, contatoUsuario.getPkContatoUsuario());
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
	
	public Collection<ContatoUsuario> consultarTodos() throws CadastroException{
		Collection<ContatoUsuario> col = new ArrayList<ContatoUsuario>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from contatousuario,tipocontato,usuario where fk_tipoContato=pk_tipoContato and fk_usuario=pk_usuario";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					ContatoUsuario contatoUsuario = new ContatoUsuario();
					carregar(rs, contatoUsuario);
					col.add(contatoUsuario);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public ContatoUsuario consultar(ContatoUsuario contatoUsuario) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from contatousuario, tipocontato, usuario where fk_tipoContato=? and fk_usuario=? and fk_tipoContato=pk_tipoContato and fk_usuario=pk_usuario";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, contatoUsuario.getTipoContato().getPkTipoContato());
			stmt.setLong(2, contatoUsuario.getUsuario().getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, contatoUsuario);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return contatoUsuario;
	}
	
	public Collection<ContatoUsuario> consultarPorUsuario(Usuario usuario) throws CadastroException{
		Collection<ContatoUsuario> col = new ArrayList<ContatoUsuario>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from contatousuario, tipocontato, usuario where fk_usuario=? and fk_tipoContato=pk_tipoContato and fk_usuario=pk_usuario";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuario.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				ContatoUsuario contatoUsuario = new ContatoUsuario();
				carregar(rs, contatoUsuario);
				col.add(contatoUsuario);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		return col;
	}
	
	private void carregar(ResultSet rs ,ContatoUsuario contatoUsuario) throws SQLException{
		//carrega tipoContato
		contatoUsuario.getTipoContato().setPkTipoContato(rs.getLong("pk_tipoContato"));
		contatoUsuario.getTipoContato().setTipoContato(rs.getString("ds_tipoContato"));	

		//carrega usuario
		contatoUsuario.getUsuario().setPkUsuario(rs.getLong("pk_usuario"));
		contatoUsuario.getUsuario().setNome(rs.getString("no_usuario"));
		contatoUsuario.getUsuario().setTipoLogradouro(rs.getString("tp_logradouro"));
		contatoUsuario.getUsuario().setLogradouro(rs.getString("no_logradouro"));
		contatoUsuario.getUsuario().setNumero(rs.getLong("nu_logradouro"));
		contatoUsuario.getUsuario().setComplemento(rs.getString("ds_complemento"));
		contatoUsuario.getUsuario().setCep(rs.getString("nu_cep"));

		//carrega contatoUsuario
		contatoUsuario.setPkContatoUsuario(rs.getLong("pk_contatoUsuario"));
		contatoUsuario.setContato(rs.getString("ds_contato"));
	}
}
