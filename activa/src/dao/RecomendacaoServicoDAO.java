package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.RecomendacaoServico;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.RecomendacaoServicoI;

public class RecomendacaoServicoDAO implements RecomendacaoServicoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (RecomendacaoServico recomendacaoServico) throws CadastroException{
		int r = 0;
		try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into recomendacaoservico (fk_membroRecomendador, fk_membroReceptor, fk_servico) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1,recomendacaoServico.getMembroRecomendador().getPkUsuario());
				stmt.setLong(2,recomendacaoServico.getMembroReceptor().getPkUsuario());
				stmt.setLong(3,recomendacaoServico.getServico().getPkServico());
				
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
	
	/*public int alterar(RecomendacaoMembro recomendacaoMembro) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update mensagem set ds_assunto=?,ds_mensagem=?,fk_membroOrigem=?,fk_membroDestino=? where pk_mensagem=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mensagem.getAssunto());
			stmt.setString(2, mensagem.getMensagem());
			stmt.setLong(3, mensagem.getMembroOrigem().getPkUsuario());
			stmt.setLong(4, mensagem.getMembroDestino().getPkUsuario());
			stmt.setLong(5, mensagem.getPkMensagem());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}*/
	
	public int excluir (RecomendacaoServico recomendacaoServico) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from recomendacaoservico where fk_membroRecomendador=? and fk_membroReceptor=? and fk_servico=?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,recomendacaoServico.getMembroRecomendador().getPkUsuario());
				stmt.setLong(2,recomendacaoServico.getMembroReceptor().getPkUsuario());
				stmt.setLong(3,recomendacaoServico.getServico().getPkServico());
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
	
	public Collection<RecomendacaoServico> consultarPorMembroReceptor(Membro membro) throws CadastroException{
		Collection<RecomendacaoServico> col = new ArrayList<RecomendacaoServico>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from recomendacaoservico x,"+
			 " membro a1, usuario a2, bairro a3, municipio a4, uf a5, formacaoacademica a6, instituicao a7,"+
	         " membro b1, usuario b2, bairro b3, municipio b4, uf b5, formacaoacademica b6, instituicao b7,"+
	         " servico c1, ambiente c2"+
			 " where b1.pk_membro=? and x.fk_membroRecomendador=a1.pk_membro and x.fk_membroReceptor=b1.pk_membro and x.fk_servico=c1.pk_servico and"+
			 " a2.fk_bairro=a3.pk_bairro and a3.fk_municipio=a4.pk_municipio and a4.fk_estado=a5.pk_estado and"+
			 " a1.pk_membro=a2.pk_usuario and a6.pk_formacaoAcademica = a1.fk_formacaoAcademica and"+
			 " a2.fk_instituicao=a7.pk_instituicao and"+
			 " b2.fk_bairro=b3.pk_bairro and b3.fk_municipio=b4.pk_municipio and b4.fk_estado=b5.pk_estado and"+
			 " b1.pk_membro=b2.pk_usuario and b6.pk_formacaoAcademica = b1.fk_formacaoAcademica and"+
			 " b2.fk_instituicao=b7.pk_instituicao and"+
			 " c1.fk_ambiente=c2.pk_ambiente";

			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultarTodas :" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				RecomendacaoServico recomendacaoServico = new RecomendacaoServico();
				carregar(rs, recomendacaoServico);
				col.add(recomendacaoServico);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
	return col;
	}
	
	public RecomendacaoServico consultar(RecomendacaoServico recomendacaoServico) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from recomendacaoServico x,"+
			 " membro a1, usuario a2, bairro a3, municipio a4, uf a5, formacaoAcademica a6, instituicao a7,"+
	         " membro b1, usuario b2, bairro b3, municipio b4, uf b5, formacaoAcademica b6, instituicao b7,"+
	         " servico c1, ambiente c2"+
			 " where a1.pk_membro=? and b1.pk_membro=? and c1.pk_servico=? and" +
			 " x.fk_membroRecomendador=a1.pk_membro and x.fk_membroReceptor=b1.pk_membro and x.fk_servico=c1.pk_servico and"+
			 " a2.fk_bairro=a3.pk_bairro and a3.fk_municipio=a4.pk_municipio and a4.fk_estado=a5.pk_estado and"+
			 " a1.pk_membro=a2.pk_usuario and a6.pk_formacaoAcademica = a1.fk_formacaoAcademica and"+
			 " a2.fk_instituicao=a7.pk_instituicao and"+
			 " b2.fk_bairro=b3.pk_bairro and b3.fk_municipio=b4.pk_municipio and b4.fk_estado=b5.pk_estado and"+
			 " b1.pk_membro=b2.pk_usuario and b6.pk_formacaoAcademica = b1.fk_formacaoAcademica and"+
			 " b2.fk_instituicao=b7.pk_instituicao and"+
			 " c1.fk_ambiente=c2.pk_ambiente";
			
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1,recomendacaoServico.getMembroRecomendador().getPkUsuario());
			stmt.setLong(2,recomendacaoServico.getMembroReceptor().getPkUsuario());
			stmt.setLong(3,recomendacaoServico.getServico().getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, recomendacaoServico);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return recomendacaoServico;
	}
	
	private void carregar(ResultSet rs ,RecomendacaoServico recomendacaoServico) throws SQLException{
		//membro Recomendador
		//carrega uf
		recomendacaoServico.getMembroRecomendador().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("a5.pk_estado"));
		recomendacaoServico.getMembroRecomendador().getBairro().getMunicipio().getUf().setEstado(rs.getString("a5.no_estado"));
		recomendacaoServico.getMembroRecomendador().getBairro().getMunicipio().getUf().setSigla(rs.getString("a5.sg_estado")); 
		
		//carrega municipio
		recomendacaoServico.getMembroRecomendador().getBairro().getMunicipio().setPkMunicipio(rs.getLong("a4.pk_municipio"));
		recomendacaoServico.getMembroRecomendador().getBairro().getMunicipio().setMunicipio(rs.getString("a4.no_municipio"));
		
		//carrega bairro
		recomendacaoServico.getMembroRecomendador().getBairro().setPkBairro(rs.getLong("a3.pk_bairro"));
		recomendacaoServico.getMembroRecomendador().getBairro().setBairro(rs.getString("a3.no_bairro"));
		
		//formacaoAcademica
		recomendacaoServico.getMembroRecomendador().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("a6.pk_formacaoAcademica"));
		recomendacaoServico.getMembroRecomendador().getFormacaoAcademica().setNome(rs.getString("a6.no_formacaoAcademica"));
		
		//carrega instituicao
		recomendacaoServico.getMembroRecomendador().getInstituicao().setPkInstituicao(rs.getLong("a7.pk_instituicao"));
		recomendacaoServico.getMembroRecomendador().getInstituicao().setNome(rs.getString("a7.no_instituicao"));
		recomendacaoServico.getMembroRecomendador().getInstituicao().setTipoLogradouro(rs.getString("a7.tp_logradouroInst"));
		recomendacaoServico.getMembroRecomendador().getInstituicao().setLogradouro(rs.getString("a7.no_logradouroInst"));
		recomendacaoServico.getMembroRecomendador().getInstituicao().setNumero(rs.getLong("a7.nu_logradouroInst"));
		recomendacaoServico.getMembroRecomendador().getInstituicao().setComplemento(rs.getString("a7.ds_complementoInst"));
		recomendacaoServico.getMembroRecomendador().getInstituicao().setCep(rs.getString("a7.nu_cepInst"));
		
		//carrega usuario
		recomendacaoServico.getMembroRecomendador().setPkUsuario(rs.getLong("a2.pk_usuario"));
		recomendacaoServico.getMembroRecomendador().setCpf(rs.getLong("a2.nu_cpf"));
		recomendacaoServico.getMembroRecomendador().setNome(rs.getString("a2.no_usuario"));
		recomendacaoServico.getMembroRecomendador().setApelido(rs.getString("a2.no_apelido"));
		recomendacaoServico.getMembroRecomendador().setDataNasc(rs.getString("a2.dt_nasc"));
		recomendacaoServico.getMembroRecomendador().setEmail(rs.getString("a2.no_email"));
		recomendacaoServico.getMembroRecomendador().setSenha(rs.getString("a2.pw_senha"));
		recomendacaoServico.getMembroRecomendador().setPerguntaChave(rs.getString("a2.ds_perguntaChave"));
		recomendacaoServico.getMembroRecomendador().setRespostaChave(rs.getString("a2.ds_respostaChave"));
		recomendacaoServico.getMembroRecomendador().setTipoLogradouro(rs.getString("a2.tp_logradouro"));
		recomendacaoServico.getMembroRecomendador().setLogradouro(rs.getString("a2.no_logradouro"));
		recomendacaoServico.getMembroRecomendador().setNumero(rs.getLong("a2.nu_logradouro"));
		recomendacaoServico.getMembroRecomendador().setComplemento(rs.getString("a2.ds_complemento"));
		recomendacaoServico.getMembroRecomendador().setCep(rs.getString("a2.nu_cep"));
		recomendacaoServico.getMembroRecomendador().setStatus(rs.getString("a2.st_usuario"));
		recomendacaoServico.getMembroRecomendador().setImagem(rs.getString("a2.img_usuario"));
		
		//membro Receptor
		//carrega uf
		recomendacaoServico.getMembroReceptor().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("b5.pk_estado"));
		recomendacaoServico.getMembroReceptor().getBairro().getMunicipio().getUf().setEstado(rs.getString("b5.no_estado"));
		recomendacaoServico.getMembroReceptor().getBairro().getMunicipio().getUf().setSigla(rs.getString("b5.sg_estado")); 
		
		//carrega municipio
		recomendacaoServico.getMembroReceptor().getBairro().getMunicipio().setPkMunicipio(rs.getLong("b4.pk_municipio"));
		recomendacaoServico.getMembroReceptor().getBairro().getMunicipio().setMunicipio(rs.getString("b4.no_municipio"));
		
		//carrega bairro
		recomendacaoServico.getMembroReceptor().getBairro().setPkBairro(rs.getLong("b3.pk_bairro"));
		recomendacaoServico.getMembroReceptor().getBairro().setBairro(rs.getString("b3.no_bairro"));
		
		//formacaoAcademica
		recomendacaoServico.getMembroReceptor().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("b6.pk_formacaoAcademica"));
		recomendacaoServico.getMembroReceptor().getFormacaoAcademica().setNome(rs.getString("b6.no_formacaoAcademica"));
		
		//carrega instituicao
		recomendacaoServico.getMembroReceptor().getInstituicao().setPkInstituicao(rs.getLong("b7.pk_instituicao"));
		recomendacaoServico.getMembroReceptor().getInstituicao().setNome(rs.getString("b7.no_instituicao"));
		recomendacaoServico.getMembroReceptor().getInstituicao().setTipoLogradouro(rs.getString("b7.tp_logradouroInst"));
		recomendacaoServico.getMembroReceptor().getInstituicao().setLogradouro(rs.getString("b7.no_logradouroInst"));
		recomendacaoServico.getMembroReceptor().getInstituicao().setNumero(rs.getLong("b7.nu_logradouroInst"));
		recomendacaoServico.getMembroReceptor().getInstituicao().setComplemento(rs.getString("b7.ds_complementoInst"));
		recomendacaoServico.getMembroReceptor().getInstituicao().setCep(rs.getString("b7.nu_cepInst"));
		
		//carrega usuario
		recomendacaoServico.getMembroReceptor().setPkUsuario(rs.getLong("b2.pk_usuario"));
		recomendacaoServico.getMembroReceptor().setCpf(rs.getLong("b2.nu_cpf"));
		recomendacaoServico.getMembroReceptor().setNome(rs.getString("b2.no_usuario"));
		recomendacaoServico.getMembroReceptor().setApelido(rs.getString("b2.no_apelido"));
		recomendacaoServico.getMembroReceptor().setDataNasc(rs.getString("b2.dt_nasc"));
		recomendacaoServico.getMembroReceptor().setEmail(rs.getString("b2.no_email"));
		recomendacaoServico.getMembroReceptor().setSenha(rs.getString("b2.pw_senha"));
		recomendacaoServico.getMembroReceptor().setPerguntaChave(rs.getString("b2.ds_perguntaChave"));
		recomendacaoServico.getMembroReceptor().setRespostaChave(rs.getString("b2.ds_respostaChave"));
		recomendacaoServico.getMembroReceptor().setTipoLogradouro(rs.getString("b2.tp_logradouro"));
		recomendacaoServico.getMembroReceptor().setLogradouro(rs.getString("b2.no_logradouro"));
		recomendacaoServico.getMembroReceptor().setNumero(rs.getLong("b2.nu_logradouro"));
		recomendacaoServico.getMembroReceptor().setComplemento(rs.getString("b2.ds_complemento"));
		recomendacaoServico.getMembroReceptor().setCep(rs.getString("b2.nu_cep"));
		recomendacaoServico.getMembroReceptor().setStatus(rs.getString("b2.st_usuario"));
		recomendacaoServico.getMembroReceptor().setImagem(rs.getString("b2.img_usuario"));
		
		//carrega ambiente
		recomendacaoServico.getServico().getAmbiente().setPkAmbiente(rs.getLong("c2.pk_ambiente"));
		recomendacaoServico.getServico().getAmbiente().setNome(rs.getString("c2.no_ambiente"));
		recomendacaoServico.getServico().getAmbiente().setData(rs.getString("c2.dt_ambiente"));
		recomendacaoServico.getServico().getAmbiente().setDescricao(rs.getString("c2.ds_ambiente"));
		recomendacaoServico.getServico().getAmbiente().setImagem(rs.getString("c2.no_imagem"));
		
		//carrega servico
		recomendacaoServico.getServico().setPkServico(rs.getLong("c1.pk_servico"));
		recomendacaoServico.getServico().setNome(rs.getString("c1.no_servico"));
		recomendacaoServico.getServico().setData(rs.getString("c1.dt_servico"));
		recomendacaoServico.getServico().setDescricao(rs.getString("c1.ds_servico"));
		recomendacaoServico.getServico().setImagem(rs.getString("c1.no_imagem"));
		recomendacaoServico.getServico().setStatus(rs.getString("c1.st_servico"));
		recomendacaoServico.getServico().setAutomatico(rs.getString("c1.fl_automatico"));
	}
}
