package dao;

import interfaces.ServicoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Ambiente;
import modelo.Servico;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class ServicoDAO implements ServicoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Servico servico) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into servico (pk_servico,no_servico,dt_servico,ds_servico,no_imagem,st_servico,fl_automatico,fk_ambiente,fk_membro) values (?,?,?,?,?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "servico", "pk_servico");
				
				stmt.setLong(1,pk);
				stmt.setString(2,servico.getNome());
				stmt.setString(3,servico.getData());
				stmt.setString(4, servico.getDescricao());
				stmt.setString(5,servico.getImagem());
				stmt.setString(6,servico.getStatus());
				stmt.setString(7,servico.getAutomatico());
				stmt.setLong(8,servico.getAmbiente().getPkAmbiente());
				stmt.setLong(9,servico.getMembro().getPkUsuario());
				
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
	
	public int alterar(Servico servico) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update servico set no_servico=?,dt_servico=?,ds_servico=?,no_imagem=?,st_servico=?,fl_automatico=?,fk_ambiente=?,fk_membro=? where pk_servico=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,servico.getNome());
			stmt.setString(2,servico.getData());
			stmt.setString(3, servico.getDescricao());
			stmt.setString(4,servico.getImagem());
			stmt.setString(5,servico.getStatus());
			stmt.setString(6,servico.getAutomatico());
			stmt.setLong(7,servico.getAmbiente().getPkAmbiente());
			stmt.setLong(8,servico.getMembro().getPkUsuario());
			stmt.setLong(9, servico.getPkServico());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Servico servico) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from servico where pk_servico = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, servico.getPkServico());
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
	
	public Collection<Servico> consultarTodos() throws CadastroException{
		Collection<Servico> col = new ArrayList<Servico>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from servico,ambiente,membro,usuario,bairro,municipio,uf,formacaoacademica,instituicao where " +
						" fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado " +
						" and pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica " +
						" and fk_instituicao=pk_instituicao and fk_membro=pk_membro and fk_ambiente=pk_ambiente";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Servico servico = new Servico();
					carregar(rs, servico);
					col.add(servico);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Servico> consultarPorAmbiente(Ambiente ambiente) throws CadastroException{
		Collection<Servico> col = new ArrayList<Servico>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from servico,ambiente,membro,usuario,bairro,municipio,uf,formacaoacademica,instituicao where " +
						" fk_ambiente=? and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado " +
						" and pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica " +
						" and fk_instituicao=pk_instituicao and fk_membro=pk_membro and fk_ambiente=pk_ambiente";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, ambiente.getPkAmbiente());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Servico servico = new Servico();
					carregar(rs, servico);
					col.add(servico);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Servico consultar(Servico servico) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from servico,ambiente,membro,usuario,bairro,municipio,uf,formacaoacademica,instituicao where " +
			" pk_servico=? and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado " +
			" and pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica " +
			" and fk_instituicao=pk_instituicao and fk_membro=pk_membro and fk_ambiente=pk_ambiente";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, servico.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, servico);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return servico;
	}
	
	private void carregar(ResultSet rs ,Servico servico) throws SQLException{
		//carrega uf
		servico.getMembro().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		servico.getMembro().getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		servico.getMembro().getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		servico.getMembro().getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		servico.getMembro().getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		servico.getMembro().getBairro().setPkBairro(rs.getLong("pk_bairro"));
		servico.getMembro().getBairro().setBairro(rs.getString("no_bairro"));
		
		//formacaoAcademica
		servico.getMembro().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		servico.getMembro().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		servico.getMembro().getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		servico.getMembro().getInstituicao().setNome(rs.getString("no_instituicao"));
		servico.getMembro().getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		servico.getMembro().getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		servico.getMembro().getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		servico.getMembro().getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		servico.getMembro().getInstituicao().setCep(rs.getString("nu_cepInst"));
		
		//carrega usuario
		servico.getMembro().setPkUsuario(rs.getLong("pk_usuario"));
		servico.getMembro().setCpf(rs.getLong("nu_cpf"));
		servico.getMembro().setNome(rs.getString("no_usuario"));
		servico.getMembro().setApelido(rs.getString("no_apelido"));
		servico.getMembro().setDataNasc(rs.getString("dt_nasc"));
		servico.getMembro().setEmail(rs.getString("no_email"));
		servico.getMembro().setSenha(rs.getString("pw_senha"));
		servico.getMembro().setPerguntaChave(rs.getString("ds_perguntaChave"));
		servico.getMembro().setRespostaChave(rs.getString("ds_respostaChave"));
		servico.getMembro().setTipoLogradouro(rs.getString("tp_logradouro"));
		servico.getMembro().setLogradouro(rs.getString("no_logradouro"));
		servico.getMembro().setNumero(rs.getLong("nu_logradouro"));
		servico.getMembro().setComplemento(rs.getString("ds_complemento"));
		servico.getMembro().setCep(rs.getString("nu_cep"));
		servico.getMembro().setStatus(rs.getString("st_usuario"));
		servico.getMembro().setImagem(rs.getString("img_usuario"));
		
		//carrega ambiente
		servico.getAmbiente().setPkAmbiente(rs.getLong("pk_ambiente"));
		servico.getAmbiente().setNome(rs.getString("no_ambiente"));
		servico.getAmbiente().setData(rs.getString("dt_ambiente"));
		servico.getAmbiente().setDescricao(rs.getString("ds_ambiente"));
		servico.getAmbiente().setImagem(rs.getString("no_imagem"));
		
		//carrega servico
		servico.setPkServico(rs.getLong("pk_servico"));
		servico.setNome(rs.getString("no_servico"));
		servico.setData(rs.getString("dt_servico"));
		servico.setDescricao(rs.getString("ds_servico"));
		servico.setImagem(rs.getString("no_imagem"));
		servico.setStatus(rs.getString("st_servico"));
		servico.setAutomatico(rs.getString("fl_automatico"));
	}
}
