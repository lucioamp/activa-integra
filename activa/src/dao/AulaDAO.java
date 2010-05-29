package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Aula;
import modelo.Curso;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;
import interfaces.AulaI;

public class AulaDAO implements AulaI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Aula aula) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into aula (pk_aula,ds_assunto,ds_aula,nu_peso,fk_curso,dt_aula) values (?,?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "aula", "pk_aula");
				
				stmt.setLong(1,pk);
				stmt.setString(2,aula.getAssunto());
				stmt.setString(3,aula.getAula());	
				stmt.setLong(4,aula.getPeso());	
				stmt.setLong(5,aula.getCurso().getPkServico());	
				stmt.setString(6, aula.getData());
								
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
	
	public int alterar(Aula aula) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update aula set ds_assunto=?,ds_aula=?,nu_peso=?,fk_curso=?,dt_aula=? where pk_aula=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,aula.getAssunto());
			stmt.setString(2,aula.getAula());	
			stmt.setLong(3,aula.getPeso());	
			stmt.setLong(4,aula.getCurso().getPkServico());
			stmt.setString(5,aula.getData());
			stmt.setLong(6, aula.getPkAula());
			System.out.println(stmt);		
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Aula aula) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from aula where pk_aula = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, aula.getPkAula());
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
	
	public int excluirPorCurso(Aula aula) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from aula where fk_curso = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, aula.getCurso().getPkServico());
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
	
	public Collection<Aula> consultarTodos() throws CadastroException{
		Collection<Aula> col = new ArrayList<Aula>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from curso a,ambiente b,servico c,usuario d, aula e" +
						" where c.fk_ambiente=b.pk_ambiente and a.pk_curso=c.pk_servico and c.fk_membro=d.pk_usuario and e.fk_curso=a.pk_curso";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Aula aula = new Aula();
					carregar(rs, aula);
					col.add(aula);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Aula> consultarPorCurso(Curso curso) throws CadastroException{
		Collection<Aula> col = new ArrayList<Aula>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from curso a,ambiente b,servico c,usuario d, aula e" +
						" where a.pk_curso=? and c.fk_ambiente=b.pk_ambiente and a.pk_curso=c.pk_servico and c.fk_membro=d.pk_usuario and e.fk_curso=a.pk_curso ORDER BY e.dt_aula";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, curso.getPkServico());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Aula aula = new Aula();
					carregar(rs, aula);
					col.add(aula);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}	
	
	public Aula consultar(Aula aula) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from curso a,ambiente b,servico c,usuario d, aula e" +
					" where e.pk_aula=? and c.fk_ambiente=b.pk_ambiente and a.pk_curso=a.pk_servico and c.fk_membro=d.pk_usuario and e.fk_curso=a.pk_curso";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, aula.getPkAula());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, aula);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return aula;
	}
	
	private void carregar(ResultSet rs ,Aula aula) throws SQLException{
		//carrega ambiente
		aula.getCurso().getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		aula.getCurso().getAmbiente().setNome(rs.getString("b.no_ambiente"));
		aula.getCurso().getAmbiente().setData(rs.getString("b.dt_ambiente"));
		aula.getCurso().getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		aula.getCurso().getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		aula.getCurso().setPkServico(rs.getLong("c.pk_servico"));
		aula.getCurso().setNome(rs.getString("c.no_servico"));
		aula.getCurso().setData(rs.getString("c.dt_servico"));
		aula.getCurso().setDescricao(rs.getString("c.ds_servico"));
		aula.getCurso().setImagem(rs.getString("c.no_imagem"));
		aula.getCurso().setStatus(rs.getString("c.st_servico"));
		aula.getCurso().setAutomatico(rs.getString("c.fl_automatico"));
		aula.getCurso().getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		aula.getCurso().getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		aula.getCurso().getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		aula.getCurso().getMembro().setCpf(rs.getLong("d.nu_cpf"));
		aula.getCurso().getMembro().setNome(rs.getString("d.no_usuario"));
		aula.getCurso().getMembro().setApelido(rs.getString("d.no_apelido"));
		aula.getCurso().getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		aula.getCurso().getMembro().setEmail(rs.getString("d.no_email"));
		aula.getCurso().getMembro().setSenha(rs.getString("d.pw_senha"));
		aula.getCurso().getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		aula.getCurso().getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		aula.getCurso().getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		aula.getCurso().getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		aula.getCurso().getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		aula.getCurso().getMembro().setComplemento(rs.getString("d.ds_complemento"));
		aula.getCurso().getMembro().setCep(rs.getString("d.nu_cep"));
		aula.getCurso().getMembro().setStatus(rs.getString("d.st_usuario"));
		aula.getCurso().getMembro().setImagem(rs.getString("d.img_usuario"));
		
		//carrega curso
		aula.getCurso().setDataLiberacao(rs.getString("a.dt_liberacao"));
		
		//carrega aula
		aula.setPkAula(rs.getLong("e.pk_aula"));
		aula.setAssunto(rs.getString("e.ds_assunto"));
		aula.setAula(rs.getString("e.ds_aula"));
		aula.setPeso(rs.getLong("e.nu_peso"));
		aula.setData(rs.getString("e.dt_aula"));
	}
}
