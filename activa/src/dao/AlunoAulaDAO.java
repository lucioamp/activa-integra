package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.AlunoAula;
import modelo.Aula;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.AlunoAulaI;

public class AlunoAulaDAO implements AlunoAulaI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (AlunoAula alunoAula) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into alunoaula (fk_aluno, fk_aula) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,alunoAula.getAluno().getPkUsuario());
				stmt.setLong(2,alunoAula.getAula().getPkAula());
								
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
	
	/*public int alterar(AlunoAula membroAula) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update contatoInstituicao set ds_contato=? where fk_tipoContato=? and fk_instituicao=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, contatoInstituicao.getContato());
			stmt.setLong(2, contatoInstituicao.getTipoContato().getPkTipoContato());
			stmt.setLong(3, contatoInstituicao.getInstituicao().getPkInstituicao());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}*/
	
	public int excluir (AlunoAula alunoAula) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from alunoaula where fk_aluno=? and fk_aula = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, alunoAula.getAluno().getPkUsuario());
				stmt.setLong(2, alunoAula.getAula().getPkAula());
				
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
	
	public int excluirPorAula (AlunoAula alunoAula) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from alunoaula where fk_aula = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, alunoAula.getAula().getPkAula());
				
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
	
	/*public Collection<ContatoInstituicao> consultarTodos() throws CadastroException{
		Collection col = new ArrayList();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from contatoInstituicao,tipoContato,instituicao where fk_tipoContato=pk_tipoContato and fk_instituicao=pk_instituicao";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					ContatoInstituicao contatoInstituicao = new ContatoInstituicao();
					carregar(rs, contatoInstituicao);
					col.add(contatoInstituicao);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}*/
	
	public AlunoAula consultar(AlunoAula alunoAula) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from alunoaula, aluno, usuario, bairro, municipio, uf, formacaoacademica, instituicao, servico, curso, membro, aula " +
					" where fk_aluno=? and fk_aula=? and fk_aluno=pk_aluno and fk_aula=pk_aula " +
					" and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and " +
					" pk_aluno=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao" +
					" and pk_curso=pk_servico and pk_aluno=pk_membro and fk_curso=pk_curso";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, alunoAula.getAluno().getPkUsuario());
			stmt.setLong(2, alunoAula.getAula().getPkAula());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, alunoAula);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return alunoAula;
	}
	
	public Collection<AlunoAula> consultarPorAluno(Membro aluno) throws CadastroException{
		Collection<AlunoAula> col = new ArrayList<AlunoAula>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from alunoaula, aluno, usuario, bairro, municipio, uf, formacaoacademica, instituicao, servico, curso, membro, aula " +
						 " where fk_aluno=? and fk_aluno=pk_aluno and fk_aula=pk_aula " +
						 " and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and " +
						 " pk_aluno=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao" +
						 " and pk_curso=pk_servico and pk_aluno=pk_membro and fk_curso=pk_curso";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, aluno.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				AlunoAula membroAula = new AlunoAula();
				carregar(rs, membroAula);
				col.add(membroAula);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	public Collection<AlunoAula> consultarPorAula(Aula curso) throws CadastroException{
		Collection<AlunoAula> col = new ArrayList<AlunoAula>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from alunoaula, aluno, usuario, bairro, municipio, uf, formacaoacademica, instituicao, servico, curso, membro, aula " +
						 " where fk_aula=? and fk_aluno=pk_aluno and fk_aula=pk_aula " +
						 " and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and " +
						 " pk_aluno=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao" +
						 " and pk_curso=pk_servico and pk_aluno=pk_membro and fk_curso=pk_curso";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, curso.getPkAula());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				AlunoAula membroAula = new AlunoAula();
				carregar(rs, membroAula);
				col.add(membroAula);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,AlunoAula alunoAula) throws SQLException{
		//carrega uf
		alunoAula.getAluno().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		alunoAula.getAluno().getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		alunoAula.getAluno().getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		alunoAula.getAluno().getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		alunoAula.getAluno().getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		alunoAula.getAluno().getBairro().setPkBairro(rs.getLong("pk_bairro"));
		alunoAula.getAluno().getBairro().setBairro(rs.getString("no_bairro"));
		
		//formacaoAcademica
		alunoAula.getAluno().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		alunoAula.getAluno().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		alunoAula.getAluno().getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		alunoAula.getAluno().getInstituicao().setNome(rs.getString("no_instituicao"));
		alunoAula.getAluno().getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		alunoAula.getAluno().getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		alunoAula.getAluno().getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		alunoAula.getAluno().getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		alunoAula.getAluno().getInstituicao().setCep(rs.getString("nu_cepInst"));
		
		//carrega usuario
		alunoAula.getAluno().setPkUsuario(rs.getLong("pk_usuario"));
		alunoAula.getAluno().setCpf(rs.getLong("nu_cpf"));
		alunoAula.getAluno().setNome(rs.getString("no_usuario"));
		alunoAula.getAluno().setApelido(rs.getString("no_apelido"));
		alunoAula.getAluno().setDataNasc(rs.getString("dt_nasc"));
		alunoAula.getAluno().setEmail(rs.getString("no_email"));
		alunoAula.getAluno().setSenha(rs.getString("pw_senha"));
		alunoAula.getAluno().setPerguntaChave(rs.getString("ds_perguntaChave"));
		alunoAula.getAluno().setRespostaChave(rs.getString("ds_respostaChave"));
		alunoAula.getAluno().setTipoLogradouro(rs.getString("tp_logradouro"));
		alunoAula.getAluno().setLogradouro(rs.getString("no_logradouro"));
		alunoAula.getAluno().setNumero(rs.getLong("nu_logradouro"));
		alunoAula.getAluno().setComplemento(rs.getString("ds_complemento"));
		alunoAula.getAluno().setCep(rs.getString("nu_cep"));
		alunoAula.getAluno().setStatus(rs.getString("st_usuario"));
		alunoAula.getAluno().setImagem(rs.getString("img_usuario"));
		
		//carrega servico
		alunoAula.getAula().getCurso().setPkServico(rs.getLong("pk_servico"));
		alunoAula.getAula().getCurso().setNome(rs.getString("no_servico"));
		alunoAula.getAula().getCurso().setData(rs.getString("dt_servico"));
		alunoAula.getAula().getCurso().setDescricao(rs.getString("ds_servico"));
		alunoAula.getAula().getCurso().setImagem(rs.getString("no_imagem"));
		alunoAula.getAula().getCurso().setStatus(rs.getString("st_servico"));
		alunoAula.getAula().getCurso().setAutomatico(rs.getString("fl_automatico"));
		alunoAula.getAula().getCurso().getMembro().setPkUsuario(rs.getLong("fk_membro"));
		alunoAula.getAula().getCurso().getAmbiente().setPkAmbiente(rs.getLong("fk_ambiente"));
		
		//carrega curso
		alunoAula.getAula().getCurso().setDataLiberacao(rs.getString("dt_liberacao"));
		
		//carrega aula
		alunoAula.getAula().setPkAula(rs.getLong("pk_aula"));
		alunoAula.getAula().setAssunto(rs.getString("ds_assunto"));
		alunoAula.getAula().setAula(rs.getString("ds_aula"));
		alunoAula.getAula().setPeso(rs.getLong("nu_peso"));
	}
}
