package dao;

import interfaces.AdministradorI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Administrador;
import modelo.Usuario;
import util.CadastroException;
import util.ConnectionFactory;

public class AdministradorDAO implements AdministradorI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Administrador administrador) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into administrador (pk_administrador) values (?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, administrador.getPkUsuario());
								
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
	
	public int excluir (Administrador administrador) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from administrador where pk_administrador = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, administrador.getPkUsuario());
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
	
	public Collection<Administrador> consultarTodosAdministradores() throws CadastroException{
		Collection<Administrador> col = new ArrayList<Administrador>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from administrador, usuario, bairro, municipio, uf, instituicao where " +
						"fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and pk_administrador=pk_usuario and fk_instituicao=pk_instituicao";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Administrador administrador = new Administrador();
					carregar(rs, administrador);
					col.add(administrador);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Administrador consultar(Administrador administrador) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from administrador, usuario, bairro, municipio, uf, instituicao where " +
					"pk_usuario=? and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and pk_administrador=pk_usuario and fk_instituicao=pk_instituicao";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, administrador.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, administrador);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return administrador;
	}
	
	public boolean isAdministrador(Usuario usuario) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select pk_administrador from administrador where pk_administrador=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuario.getPkUsuario());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
				
		return false;
	}
	
	private void carregar(ResultSet rs ,Administrador administrador) throws SQLException{
		//carrega uf
		administrador.getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		administrador.getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		administrador.getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		administrador.getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		administrador.getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		administrador.getBairro().setPkBairro(rs.getLong("pk_bairro"));
		administrador.getBairro().setBairro(rs.getString("no_bairro"));
		
		//carrega instituicao
		administrador.getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		administrador.getInstituicao().setNome(rs.getString("no_instituicao"));
		administrador.getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		administrador.getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		administrador.getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		administrador.getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		administrador.getInstituicao().setCep(rs.getString("nu_cepInst"));
		
		//carrega usuario
		administrador.setPkUsuario(rs.getLong("pk_usuario"));
		administrador.setCpf(rs.getLong("nu_cpf"));
		administrador.setNome(rs.getString("no_usuario"));
		administrador.setApelido(rs.getString("no_apelido"));
		administrador.setDataNasc(rs.getString("dt_nasc"));
		administrador.setEmail(rs.getString("no_email"));
		administrador.setSenha(rs.getString("pw_senha"));
		administrador.setPerguntaChave(rs.getString("ds_perguntaChave"));
		administrador.setRespostaChave(rs.getString("ds_respostaChave"));
		administrador.setTipoLogradouro(rs.getString("tp_logradouro"));
		administrador.setLogradouro(rs.getString("no_logradouro"));
		administrador.setNumero(rs.getLong("nu_logradouro"));
		administrador.setComplemento(rs.getString("ds_complemento"));
		administrador.setCep(rs.getString("nu_cep"));
		administrador.setStatus(rs.getString("st_usuario"));
		administrador.setImagem(rs.getString("img_usuario"));
		
	}
}
