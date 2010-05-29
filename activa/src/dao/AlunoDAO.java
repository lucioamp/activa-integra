package dao;

import interfaces.AlunoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Aluno;
import util.CadastroException;
import util.ConnectionFactory;

public class AlunoDAO implements AlunoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Aluno aluno) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into aluno (pk_aluno) values (?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,aluno.getPkUsuario());
								
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
	
	public int excluir (Aluno aluno) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from aluno where pk_aluno = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, aluno.getPkUsuario());
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
	
	public Collection<Aluno> consultarTodosAlunos() throws CadastroException{
		Collection<Aluno> col = new ArrayList<Aluno>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from aluno, usuario, bairro, municipio, uf, formacaoacademica, instituicao where " +
						"fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and pk_aluno=pk_usuario " +
						"and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Aluno aluno = new Aluno();
					carregar(rs, aluno);
					col.add(aluno);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Aluno consultar(Aluno aluno) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from aluno, usuario, bairro, municipio, uf, formacaoacademica, instituicao where " +
					"pk_usuario=? and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado " +
					"and pk_aluno=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, aluno.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, aluno);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return aluno;
	}
	
	public boolean isAluno(Aluno aluno) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select pk_aluno from aluno where pk_aluno=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, aluno.getPkUsuario());
						
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
	
	private void carregar(ResultSet rs ,Aluno aluno) throws SQLException{
		//carrega uf
		aluno.getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		aluno.getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		aluno.getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		aluno.getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		aluno.getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		aluno.getBairro().setPkBairro(rs.getLong("pk_bairro"));
		aluno.getBairro().setBairro(rs.getString("no_bairro"));
		
		//formacaoAcademica
		aluno.getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		aluno.getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		aluno.getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		aluno.getInstituicao().setNome(rs.getString("no_instituicao"));
		aluno.getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		aluno.getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		aluno.getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		aluno.getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		aluno.getInstituicao().setCep(rs.getString("nu_cepInst"));
		
		//carrega usuario
		aluno.setPkUsuario(rs.getLong("pk_usuario"));
		aluno.setCpf(rs.getLong("nu_cpf"));
		aluno.setNome(rs.getString("no_usuario"));
		aluno.setApelido(rs.getString("no_apelido"));
		aluno.setDataNasc(rs.getString("dt_nasc"));
		aluno.setEmail(rs.getString("no_email"));
		aluno.setSenha(rs.getString("pw_senha"));
		aluno.setPerguntaChave(rs.getString("ds_perguntaChave"));
		aluno.setRespostaChave(rs.getString("ds_respostaChave"));
		aluno.setTipoLogradouro(rs.getString("tp_logradouro"));
		aluno.setLogradouro(rs.getString("no_logradouro"));
		aluno.setNumero(rs.getLong("nu_logradouro"));
		aluno.setComplemento(rs.getString("ds_complemento"));
		aluno.setCep(rs.getString("nu_cep"));
		aluno.setStatus(rs.getString("st_usuario"));
		aluno.setImagem(rs.getString("img_usuario"));
		
	}
}
