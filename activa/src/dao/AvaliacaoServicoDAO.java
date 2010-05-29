package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.AvaliacaoServico;
import modelo.Servico;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.AvaliacaoServicoI;

public class AvaliacaoServicoDAO implements AvaliacaoServicoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (AvaliacaoServico avaliacaoServico) throws CadastroException{
		int r = 0;
		try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into avaliacaoservico (fk_membro, fk_servico, nu_avaliacao) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1,avaliacaoServico.getMembro().getPkUsuario());
				stmt.setLong(2,avaliacaoServico.getServico().getPkServico());
				stmt.setLong(3,avaliacaoServico.getAvaliacao());
				
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
	
	public int alterar(AvaliacaoServico avaliacaoServico) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update avaliacaoservico nu_avaliacao=? where fk_membro=? and fk_servico=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, avaliacaoServico.getAvaliacao());
			stmt.setLong(2, avaliacaoServico.getMembro().getPkUsuario());
			stmt.setLong(3, avaliacaoServico.getServico().getPkServico());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (AvaliacaoServico avaliacaoServico) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from avaliacaoservico where fk_membro=? and fk_servico=?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,avaliacaoServico.getMembro().getPkUsuario());
				stmt.setLong(2,avaliacaoServico.getServico().getPkServico());
				
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
	
	public Collection<AvaliacaoServico> consultarPorServico(Servico servico) throws CadastroException{
		Collection<AvaliacaoServico> col = new ArrayList<AvaliacaoServico>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from avaliacaoservico x,"+
			 " membro a1, usuario a2, bairro a3, municipio a4, uf a5, formacaoacademica a6, instituicao a7,"+
	         " servico b1, ambiente b2"+
	         " where b1.pk_servico=? and x.fk_membro=b1.pk_membro and"+
			 " a2.fk_bairro=a3.pk_bairro and a3.fk_municipio=a4.pk_municipio and a4.fk_estado=a5.pk_estado and"+
			 " a1.pk_membro=a2.pk_usuario and a6.pk_formacaoAcademica = a1.fk_formacaoAcademica and"+
			 " a2.fk_instituicao=a7.pk_instituicao and"+
			 " b1.fk_ambiente=b2.pk_ambiente";

			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, servico.getPkServico());
			
			System.out.println("UfDAO Consultar consultarTodas :" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				AvaliacaoServico avaliacaoServico = new AvaliacaoServico();
				carregar(rs, avaliacaoServico);
				col.add(avaliacaoServico);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
	return col;
	}
	
	public AvaliacaoServico consultar(AvaliacaoServico avaliacaoServico) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from avaliacaoservico x,"+
			 " membro a1, usuario a2, bairro a3, municipio a4, uf a5, formacaoacademica a6, instituicao a7,"+
	         " servico b1, ambiente b2"+
	         " where a1.pk_membro=? and b1.pk_servico=? and x.fk_membro=b1.pk_membro and"+
			 " a2.fk_bairro=a3.pk_bairro and a3.fk_municipio=a4.pk_municipio and a4.fk_estado=a5.pk_estado and"+
			 " a1.pk_membro=a2.pk_usuario and a6.pk_formacaoAcademica = a1.fk_formacaoAcademica and"+
			 " a2.fk_instituicao=a7.pk_instituicao and"+
			 " b1.fk_ambiente=b2.pk_ambiente";
			
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1,avaliacaoServico.getMembro().getPkUsuario());
			stmt.setLong(2,avaliacaoServico.getServico().getPkServico());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, avaliacaoServico);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return avaliacaoServico;
	}
	
	private void carregar(ResultSet rs ,AvaliacaoServico avaliacaoServico) throws SQLException{
		//membro 
		//carrega uf
		avaliacaoServico.getMembro().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("a5.pk_estado"));
		avaliacaoServico.getMembro().getBairro().getMunicipio().getUf().setEstado(rs.getString("a5.no_estado"));
		avaliacaoServico.getMembro().getBairro().getMunicipio().getUf().setSigla(rs.getString("a5.sg_estado")); 
		
		//carrega municipio
		avaliacaoServico.getMembro().getBairro().getMunicipio().setPkMunicipio(rs.getLong("a4.pk_municipio"));
		avaliacaoServico.getMembro().getBairro().getMunicipio().setMunicipio(rs.getString("a4.no_municipio"));
		
		//carrega bairro
		avaliacaoServico.getMembro().getBairro().setPkBairro(rs.getLong("a3.pk_bairro"));
		avaliacaoServico.getMembro().getBairro().setBairro(rs.getString("a3.no_bairro"));
		
		//formacaoAcademica
		avaliacaoServico.getMembro().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("a6.pk_formacaoAcademica"));
		avaliacaoServico.getMembro().getFormacaoAcademica().setNome(rs.getString("a6.no_formacaoAcademica"));
		
		//carrega instituicao
		avaliacaoServico.getMembro().getInstituicao().setPkInstituicao(rs.getLong("a7.pk_instituicao"));
		avaliacaoServico.getMembro().getInstituicao().setNome(rs.getString("a7.no_instituicao"));
		avaliacaoServico.getMembro().getInstituicao().setTipoLogradouro(rs.getString("a7.tp_logradouroInst"));
		avaliacaoServico.getMembro().getInstituicao().setLogradouro(rs.getString("a7.no_logradouroInst"));
		avaliacaoServico.getMembro().getInstituicao().setNumero(rs.getLong("a7.nu_logradouroInst"));
		avaliacaoServico.getMembro().getInstituicao().setComplemento(rs.getString("a7.ds_complementoInst"));
		avaliacaoServico.getMembro().getInstituicao().setCep(rs.getString("a7.nu_cepInst"));
		
		//carrega usuario
		avaliacaoServico.getMembro().setPkUsuario(rs.getLong("a2.pk_usuario"));
		avaliacaoServico.getMembro().setCpf(rs.getLong("a2.nu_cpf"));
		avaliacaoServico.getMembro().setNome(rs.getString("a2.no_usuario"));
		avaliacaoServico.getMembro().setApelido(rs.getString("b2.no_apelido"));
		avaliacaoServico.getMembro().setDataNasc(rs.getString("a2.dt_nasc"));
		avaliacaoServico.getMembro().setEmail(rs.getString("a2.no_email"));
		avaliacaoServico.getMembro().setSenha(rs.getString("a2.pw_senha"));
		avaliacaoServico.getMembro().setPerguntaChave(rs.getString("a2.ds_perguntaChave"));
		avaliacaoServico.getMembro().setRespostaChave(rs.getString("a2.ds_respostaChave"));
		avaliacaoServico.getMembro().setTipoLogradouro(rs.getString("a2.tp_logradouro"));
		avaliacaoServico.getMembro().setLogradouro(rs.getString("a2.no_logradouro"));
		avaliacaoServico.getMembro().setNumero(rs.getLong("a2.nu_logradouro"));
		avaliacaoServico.getMembro().setComplemento(rs.getString("a2.ds_complemento"));
		avaliacaoServico.getMembro().setCep(rs.getString("a2.nu_cep"));
		avaliacaoServico.getMembro().setStatus(rs.getString("a2.st_usuario"));
		avaliacaoServico.getMembro().setImagem(rs.getString("a2.img_usuario"));
		
		//carrega ambiente
		avaliacaoServico.getServico().getAmbiente().setPkAmbiente(rs.getLong("b2.pk_ambiente"));
		avaliacaoServico.getServico().getAmbiente().setNome(rs.getString("b2.no_ambiente"));
		avaliacaoServico.getServico().getAmbiente().setData(rs.getString("b2.dt_ambiente"));
		avaliacaoServico.getServico().getAmbiente().setDescricao(rs.getString("b2.ds_ambiente"));
		avaliacaoServico.getServico().getAmbiente().setImagem(rs.getString("b2.no_imagem"));
		
		//carrega servico
		avaliacaoServico.getServico().setPkServico(rs.getLong("b1.pk_servico"));
		avaliacaoServico.getServico().setNome(rs.getString("b1.no_servico"));
		avaliacaoServico.getServico().setData(rs.getString("b1.dt_servico"));
		avaliacaoServico.getServico().setDescricao(rs.getString("b1.ds_servico"));
		avaliacaoServico.getServico().setImagem(rs.getString("b1.no_imagem"));
		avaliacaoServico.getServico().setStatus(rs.getString("b1.st_servico"));
		avaliacaoServico.getServico().setAutomatico(rs.getString("bs1.fl_automatico"));
		
		//carrega avaliacaoMembro
		avaliacaoServico.setAvaliacao(rs.getLong("x.nu_avaliacao"));
		
	}
}
