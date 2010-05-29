package dao;

import interfaces.ProfessorI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Professor;
import util.CadastroException;
import util.ConnectionFactory;

public class ProfessorDAO implements ProfessorI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Professor professor) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into professor (pk_professor) values (?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,professor.getPkUsuario());
								
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
	
	public int excluir (Professor professor) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from professor where pk_professor = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, professor.getPkUsuario());
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
	
	public Collection<Professor> consultarTodosProfessores() throws CadastroException{
		Collection<Professor> col = new ArrayList<Professor>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from professor, usuario, bairro, municipio, uf, formacaoacademica, instituicao where " +
						" fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado " +
						" and pk_professor=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Professor professor = new Professor();
					carregar(rs, professor);
					col.add(professor);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Professor consultar(Professor professor) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from professor, usuario, bairro, municipio, uf, formacaoacademica, instituicao where " +
					"pk_usuario=? and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado " +
					"and pk_professor=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, professor.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, professor);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return professor;
	}
	
	public boolean isProfessor(Professor professor) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select pk_professor from professor where pk_professor=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, professor.getPkUsuario());
									
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
	
	private void carregar(ResultSet rs ,Professor professor) throws SQLException{
		//carrega uf
		professor.getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		professor.getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		professor.getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		professor.getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		professor.getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		professor.getBairro().setPkBairro(rs.getLong("pk_bairro"));
		professor.getBairro().setBairro(rs.getString("no_bairro"));
		
		//formacaoAcademica
		professor.getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		professor.getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		professor.getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		professor.getInstituicao().setNome(rs.getString("no_instituicao"));
		professor.getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		professor.getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		professor.getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		professor.getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		professor.getInstituicao().setCep(rs.getString("nu_cepInst"));
		
		//carrega usuario
		professor.setPkUsuario(rs.getLong("pk_usuario"));
		professor.setCpf(rs.getLong("nu_cpf"));
		professor.setNome(rs.getString("no_usuario"));
		professor.setApelido(rs.getString("no_apelido"));
		professor.setDataNasc(rs.getString("dt_nasc"));
		professor.setEmail(rs.getString("no_email"));
		professor.setSenha(rs.getString("pw_senha"));
		professor.setPerguntaChave(rs.getString("ds_perguntaChave"));
		professor.setRespostaChave(rs.getString("ds_respostaChave"));
		professor.setTipoLogradouro(rs.getString("tp_logradouro"));
		professor.setLogradouro(rs.getString("no_logradouro"));
		professor.setNumero(rs.getLong("nu_logradouro"));
		professor.setComplemento(rs.getString("ds_complemento"));
		professor.setCep(rs.getString("nu_cep"));
		professor.setStatus(rs.getString("st_usuario"));
		professor.setImagem(rs.getString("img_usuario"));
		
	}
}
