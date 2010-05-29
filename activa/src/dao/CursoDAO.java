package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Ambiente;
import modelo.Curso;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.CursoI;

public class CursoDAO implements CursoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Curso curso) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into curso (pk_curso,dt_liberacao) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = (int) curso.getPkServico();
				
				stmt.setLong(1,pk);
				stmt.setString(2,curso.getDataLiberacao());			
				
				System.out.println(stmt);
				stmt.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return pk;
	}
	
	public int alterar(Curso curso) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update curso set dt_liberacao=? where pk_curso=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,curso.getDataLiberacao());
			stmt.setLong(2, curso.getPkServico());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Curso curso) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from curso where pk_curso = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, curso.getPkServico());
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
	
	public Collection<Curso> consultarTodosCursos() throws CadastroException{
		Collection<Curso> col = new ArrayList<Curso>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from curso a,ambiente b,servico c,usuario d" +
						" where c.fk_ambiente=b.pk_ambiente and a.pk_curso=c.pk_servico and c.fk_membro=d.pk_usuario";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Curso curso = new Curso();
					carregar(rs, curso);
					col.add(curso);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Curso> consultarCursosPorAmbiente(Ambiente ambiente) throws CadastroException{
		Collection<Curso> col = new ArrayList<Curso>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from curso a,ambiente b,servico c,usuario d" +
						" where b.pk_ambiente=? and c.fk_ambiente=b.pk_ambiente and a.pk_curso=c.pk_servico and c.fk_membro=d.pk_usuario ORDER BY dt_servico DESC";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, ambiente.getPkAmbiente());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Curso curso = new Curso();
					carregar(rs, curso);
					col.add(curso);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}	
	
	public Curso consultar(Curso curso) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from curso a,ambiente b,servico c,usuario d" +
					" where a.pk_curso=? and c.fk_ambiente=b.pk_ambiente and c.pk_servico = a.pk_curso and c.fk_membro=d.pk_usuario";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, curso.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, curso);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return curso;
	}
	
	private void carregar(ResultSet rs ,Curso curso) throws SQLException{
		//carrega ambiente
		curso.getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		curso.getAmbiente().setNome(rs.getString("b.no_ambiente"));
		curso.getAmbiente().setData(rs.getString("b.dt_ambiente"));
		curso.getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		curso.getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		curso.setPkServico(rs.getLong("c.pk_servico"));
		curso.setNome(rs.getString("c.no_servico"));
		curso.setData(rs.getString("c.dt_servico"));
		curso.setDescricao(rs.getString("c.ds_servico"));
		curso.setImagem(rs.getString("c.no_imagem"));
		curso.setStatus(rs.getString("c.st_servico"));
		curso.setAutomatico(rs.getString("c.fl_automatico"));
		curso.getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		curso.getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		curso.getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		curso.getMembro().setCpf(rs.getLong("d.nu_cpf"));
		curso.getMembro().setNome(rs.getString("d.no_usuario"));
		curso.getMembro().setApelido(rs.getString("d.no_apelido"));
		curso.getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		curso.getMembro().setEmail(rs.getString("d.no_email"));
		curso.getMembro().setSenha(rs.getString("d.pw_senha"));
		curso.getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		curso.getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		curso.getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		curso.getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		curso.getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		curso.getMembro().setComplemento(rs.getString("d.ds_complemento"));
		curso.getMembro().setCep(rs.getString("d.nu_cep"));
		curso.getMembro().setStatus(rs.getString("d.st_usuario"));
		curso.getMembro().setImagem(rs.getString("d.img_usuario"));
		
		//carrega curso
		curso.setDataLiberacao(rs.getString("a.dt_liberacao"));
	}
}
