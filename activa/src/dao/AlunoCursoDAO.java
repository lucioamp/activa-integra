package dao;

import interfaces.AlunoCursoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.AlunoCurso;
import modelo.Curso;
import util.CadastroException;
import util.ConnectionFactory;

public class AlunoCursoDAO implements AlunoCursoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (AlunoCurso alunoCurso) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into alunocurso (fk_aluno, fk_curso) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,alunoCurso.getAluno().getPkUsuario());
				stmt.setLong(2,alunoCurso.getCurso().getPkServico());
								
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
	
	/*public int alterar(AlunoCurso membroCurso) throws CadastroException{
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
	
	public int excluir (AlunoCurso alunoCurso) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from alunocurso where fk_aluno=? and fk_curso = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, alunoCurso.getAluno().getPkUsuario());
				stmt.setLong(2, alunoCurso.getCurso().getPkServico());
				
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
	
	public int excluirPorCurso (AlunoCurso alunoCurso) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from alunocurso where fk_curso = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, alunoCurso.getCurso().getPkServico());
				
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
	
	public AlunoCurso consultar(AlunoCurso alunoCurso) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from alunocurso, membro, usuario, bairro, municipio, uf, formacaoacademica, instituicao, servico, curso, membro " +
					" where fk_aluno=? and fk_curso=? and fk_aluno=pk_aluno and fk_curso=pk_curso " +
					" and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and " +
					" pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao" +
					" and pk_curso=pk_servico and pk_aluno=pk_membro";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, alunoCurso.getAluno().getPkUsuario());
			stmt.setLong(2, alunoCurso.getCurso().getPkServico());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, alunoCurso);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return alunoCurso;
	}
	
	public Collection<AlunoCurso> consultarPorAluno(Membro aluno) throws CadastroException{
		Collection<AlunoCurso> col = new ArrayList<AlunoCurso>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from alunocurso, membro, usuario, bairro, municipio, uf, formacaoacademica, instituicao, servico, curso, membro " +
						 " where fk_aluno=? and fk_aluno=pk_aluno and fk_curso=pk_curso " +
						 " and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and " +
						 " pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao" +
						 " and pk_curso=pk_servico and pk_aluno=pk_membro";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, aluno.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				AlunoCurso membroCurso = new AlunoCurso();
				carregar(rs, membroCurso);
				col.add(membroCurso);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	public Collection<AlunoCurso> consultarPorCurso(Curso curso) throws CadastroException{
		Collection<AlunoCurso> col = new ArrayList<AlunoCurso>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from alunocurso " +
						 " where fk_curso=? ";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, curso.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				AlunoCurso membroCurso = new AlunoCurso();
				carregar(rs, membroCurso);
				col.add(membroCurso);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,AlunoCurso alunoCurso) throws SQLException{
		if(rs.getLong("fk_aluno") > 0 && rs.getLong("fk_curso") > 0)
		{
			alunoCurso.getAluno().setPkUsuario(rs.getLong("fk_aluno"));
			alunoCurso.getCurso().setPkServico(rs.getLong("fk_curso"));
			try {
				alunoCurso.getAluno().consultar();
				alunoCurso.getCurso().consultar();
			} catch (Exception e) {
				// TODO: handle exception
			}		
		}
	}
}
