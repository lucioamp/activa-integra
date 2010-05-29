package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Ambiente;
import modelo.Comunidade;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.ComunidadeI;

public class ComunidadeDAO implements ComunidadeI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Comunidade comunidade) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into comunidade (pk_comunidade,fk_catComunidade) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = (int) comunidade.getPkServico();
				
				stmt.setLong(1,pk);
				stmt.setLong(2,comunidade.getCategoriaComunidade().getPkCatComunidade());			
				
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
	
	public int alterar(Comunidade comunidade) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update comunidade set fk_catComunidade=? where pk_comunidade=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1,comunidade.getCategoriaComunidade().getPkCatComunidade());
			stmt.setLong(2, comunidade.getPkServico());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Comunidade comunidade) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from comunidade where pk_comunidade = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, comunidade.getPkServico());
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
	
	public Collection<Comunidade> consultarTodosComunidades() throws CadastroException{
		Collection<Comunidade> col = new ArrayList<Comunidade>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from comunidade a,ambiente b,servico c,usuario d, categoriacomunidade e" +
						" where c.fk_ambiente=b.pk_ambiente and a.pk_comunidade=c.pk_servico and " +
						" c.fk_membro=d.pk_usuario and a.fk_catComunidade=e.pk_catComunidade";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Comunidade comunidade = new Comunidade();
					carregar(rs, comunidade);
					col.add(comunidade);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Comunidade> consultarComunidadesPorAmbiente(Ambiente ambiente) throws CadastroException{
		Collection<Comunidade> col = new ArrayList<Comunidade>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from comunidade a,ambiente b,servico c,usuario d, categoriacomunidade e" +
						" where b.pk_ambiente=? and c.fk_ambiente=b.pk_ambiente and a.pk_comunidade=c.pk_servico " +
						" and c.fk_membro=d.pk_usuario and a.fk_catComunidade=e.pk_catComunidade";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, ambiente.getPkAmbiente());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Comunidade comunidade = new Comunidade();
					carregar(rs, comunidade);
					col.add(comunidade);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}	
	
	public Comunidade consultar(Comunidade comunidade) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from comunidade a,ambiente b,servico c,usuario d, categoriacomunidade e" +
					" where a.pk_comunidade=? and c.fk_ambiente=b.pk_ambiente and a.pk_comunidade=a.pk_servico " +
					" and c.fk_membro=d.pk_usuario and a.fk_catComunidade=e.pk_catComunidade";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, comunidade.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, comunidade);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return comunidade;
	}
	
	private void carregar(ResultSet rs ,Comunidade comunidade) throws SQLException{
		//carrega ambiente
		comunidade.getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		comunidade.getAmbiente().setNome(rs.getString("b.no_ambiente"));
		comunidade.getAmbiente().setData(rs.getString("b.dt_ambiente"));
		comunidade.getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		comunidade.getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		comunidade.setPkServico(rs.getLong("c.pk_servico"));
		comunidade.setNome(rs.getString("c.no_servico"));
		comunidade.setData(rs.getString("c.dt_servico"));
		comunidade.setDescricao(rs.getString("c.ds_servico"));
		comunidade.setImagem(rs.getString("c.no_imagem"));
		comunidade.setStatus(rs.getString("c.st_servico"));
		comunidade.setAutomatico(rs.getString("c.fl_automatico"));
		comunidade.getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		comunidade.getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		comunidade.getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		comunidade.getMembro().setCpf(rs.getLong("d.nu_cpf"));
		comunidade.getMembro().setNome(rs.getString("d.no_usuario"));
		comunidade.getMembro().setApelido(rs.getString("d.no_apelido"));
		comunidade.getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		comunidade.getMembro().setEmail(rs.getString("d.no_email"));
		comunidade.getMembro().setSenha(rs.getString("d.pw_senha"));
		comunidade.getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		comunidade.getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		comunidade.getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		comunidade.getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		comunidade.getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		comunidade.getMembro().setComplemento(rs.getString("d.ds_complemento"));
		comunidade.getMembro().setCep(rs.getString("d.nu_cep"));
		comunidade.getMembro().setStatus(rs.getString("d.st_usuario"));
		comunidade.getMembro().setImagem(rs.getString("d.img_usuario"));
		
		//carrega categoriaComunidade
		comunidade.getCategoriaComunidade().setPkCatComunidade(rs.getLong("e.pk_catComunidade"));
		comunidade.getCategoriaComunidade().setNome(rs.getString("e.no_catComunidade"));
		comunidade.getCategoriaComunidade().setDescricao(rs.getString("e.ds_catComunidade"));
	}
}
