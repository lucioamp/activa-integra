package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.RecomendacaoMembro;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.RecomendacaoMembroI;

public class RecomendacaoMembroDAO implements RecomendacaoMembroI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (RecomendacaoMembro recomendacaoMembro) throws CadastroException{
		int r = 0;
		try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into recomendacaomembro (fk_membroRecomendador, fk_membroReceptor, fk_membroRecomendado) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1,recomendacaoMembro.getMembroRecomendador().getPkUsuario());
				stmt.setLong(2,recomendacaoMembro.getMembroReceptor().getPkUsuario());
				stmt.setLong(3,recomendacaoMembro.getMembroRecomendado().getPkUsuario());
				
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
	
	public int excluir (RecomendacaoMembro recomendacaoMembro) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from recomendacaomembro where fk_membroRecomendador=? and fk_membroReceptor=? and fk_membroRecomendado=?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,recomendacaoMembro.getMembroRecomendador().getPkUsuario());
				stmt.setLong(2,recomendacaoMembro.getMembroReceptor().getPkUsuario());
				stmt.setLong(3,recomendacaoMembro.getMembroRecomendado().getPkUsuario());
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
	
	public Collection<RecomendacaoMembro> consultarPorMembroReceptor(Membro membro) throws CadastroException{
		Collection<RecomendacaoMembro> col = new ArrayList<RecomendacaoMembro>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from recomendacaomembro x,"+
			 " membro a1, usuario a2, bairro a3, municipio a4, uf a5, formacaoacademica a6, instituicao a7,"+
	         " membro b1, usuario b2, bairro b3, municipio b4, uf b5, formacaoacademica b6, instituicao b7,"+
	         " membro c1, usuario c2, bairro c3, municipio c4, uf c5, formacaoacademica c6, instituicao c7"+
			 " where b1.pk_membro=? and x.fk_membroRecomendador=a1.pk_membro and x.fk_membroReceptor=b1.pk_membro and x.fk_membroRecomendado=c1.pk_membro and"+
			 " a2.fk_bairro=a3.pk_bairro and a3.fk_municipio=a4.pk_municipio and a4.fk_estado=a5.pk_estado and"+
			 " a1.pk_membro=a2.pk_usuario and a6.pk_formacaoAcademica = a1.fk_formacaoAcademica and"+
			 " a2.fk_instituicao=a7.pk_instituicao and"+
			 " b2.fk_bairro=b3.pk_bairro and b3.fk_municipio=b4.pk_municipio and b4.fk_estado=b5.pk_estado and"+
			 " b1.pk_membro=b2.pk_usuario and b6.pk_formacaoAcademica = b1.fk_formacaoAcademica and"+
			 " b2.fk_instituicao=b7.pk_instituicao and"+
			 " c2.fk_bairro=c3.pk_bairro and c3.fk_municipio=c4.pk_municipio and c4.fk_estado=c5.pk_estado and"+
			 " c1.pk_membro=c2.pk_usuario and c6.pk_formacaoAcademica = c1.fk_formacaoAcademica and"+
			 " c2.fk_instituicao=c7.pk_instituicao";

			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultarTodas :" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				RecomendacaoMembro recomendacaoMembro = new RecomendacaoMembro();
				carregar(rs, recomendacaoMembro);
				col.add(recomendacaoMembro);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
	return col;
	}
	
	public RecomendacaoMembro consultar(RecomendacaoMembro recomendacaoMembro) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from recomendacaomembro x,"+
			 " membro a1, usuario a2, bairro a3, municipio a4, uf a5, formacaoacademica a6, instituicao a7,"+
	         " membro b1, usuario b2, bairro b3, municipio b4, uf b5, formacaoacademica b6, instituicao b7,"+
	         " membro c1, usuario c2, bairro c3, municipio c4, uf c5, formacaoacademica c6, instituicao c7"+
			 " where a1.pk_membro=? and b1.pk_membro=? and c1.pk_membro=? and" +
			 " x.fk_membroRecomendador=a1.pk_membro and x.fk_membroReceptor=b1.pk_membro and x.fk_membroRecomendado=c1.pk_membro and"+
			 " a2.fk_bairro=a3.pk_bairro and a3.fk_municipio=a4.pk_municipio and a4.fk_estado=a5.pk_estado and"+
			 " a1.pk_membro=a2.pk_usuario and a6.pk_formacaoAcademica = a1.fk_formacaoAcademica and"+
			 " a2.fk_instituicao=a7.pk_instituicao and"+
			 " b2.fk_bairro=b3.pk_bairro and b3.fk_municipio=b4.pk_municipio and b4.fk_estado=b5.pk_estado and"+
			 " b1.pk_membro=b2.pk_usuario and b6.pk_formacaoAcademica = b1.fk_formacaoAcademica and"+
			 " b2.fk_instituicao=b7.pk_instituicao and"+
			 " c2.fk_bairro=c3.pk_bairro and c3.fk_municipio=c4.pk_municipio and c4.fk_estado=c5.pk_estado and"+
			 " c1.pk_membro=c2.pk_usuario and c6.pk_formacaoAcademica = c1.fk_formacaoAcademica and"+
			 " c2.fk_instituicao=c7.pk_instituicao";
			
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1,recomendacaoMembro.getMembroRecomendador().getPkUsuario());
			stmt.setLong(2,recomendacaoMembro.getMembroReceptor().getPkUsuario());
			stmt.setLong(3,recomendacaoMembro.getMembroRecomendado().getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, recomendacaoMembro);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return recomendacaoMembro;
	}
	
	private void carregar(ResultSet rs ,RecomendacaoMembro recomendacaoMembro) throws SQLException{
		//membro Recomendador
		//carrega uf
		recomendacaoMembro.getMembroRecomendador().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("a5.pk_estado"));
		recomendacaoMembro.getMembroRecomendador().getBairro().getMunicipio().getUf().setEstado(rs.getString("a5.no_estado"));
		recomendacaoMembro.getMembroRecomendador().getBairro().getMunicipio().getUf().setSigla(rs.getString("a5.sg_estado")); 
		
		//carrega municipio
		recomendacaoMembro.getMembroRecomendador().getBairro().getMunicipio().setPkMunicipio(rs.getLong("a4.pk_municipio"));
		recomendacaoMembro.getMembroRecomendador().getBairro().getMunicipio().setMunicipio(rs.getString("a4.no_municipio"));
		
		//carrega bairro
		recomendacaoMembro.getMembroRecomendador().getBairro().setPkBairro(rs.getLong("a3.pk_bairro"));
		recomendacaoMembro.getMembroRecomendador().getBairro().setBairro(rs.getString("a3.no_bairro"));
		
		//formacaoAcademica
		recomendacaoMembro.getMembroRecomendador().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("a6.pk_formacaoAcademica"));
		recomendacaoMembro.getMembroRecomendador().getFormacaoAcademica().setNome(rs.getString("a6.no_formacaoAcademica"));
		
		//carrega instituicao
		recomendacaoMembro.getMembroRecomendador().getInstituicao().setPkInstituicao(rs.getLong("a7.pk_instituicao"));
		recomendacaoMembro.getMembroRecomendador().getInstituicao().setNome(rs.getString("a7.no_instituicao"));
		recomendacaoMembro.getMembroRecomendador().getInstituicao().setTipoLogradouro(rs.getString("a7.tp_logradouroInst"));
		recomendacaoMembro.getMembroRecomendador().getInstituicao().setLogradouro(rs.getString("a7.no_logradouroInst"));
		recomendacaoMembro.getMembroRecomendador().getInstituicao().setNumero(rs.getLong("a7.nu_logradouroInst"));
		recomendacaoMembro.getMembroRecomendador().getInstituicao().setComplemento(rs.getString("a7.ds_complementoInst"));
		recomendacaoMembro.getMembroRecomendador().getInstituicao().setCep(rs.getString("a7.nu_cepInst"));
		
		//carrega usuario
		recomendacaoMembro.getMembroRecomendador().setPkUsuario(rs.getLong("a2.pk_usuario"));
		recomendacaoMembro.getMembroRecomendador().setCpf(rs.getLong("a2.nu_cpf"));
		recomendacaoMembro.getMembroRecomendador().setNome(rs.getString("a2.no_usuario"));
		recomendacaoMembro.getMembroRecomendador().setApelido(rs.getString("a2.no_apelido"));
		recomendacaoMembro.getMembroRecomendador().setDataNasc(rs.getString("a2.dt_nasc"));
		recomendacaoMembro.getMembroRecomendador().setEmail(rs.getString("a2.no_email"));
		recomendacaoMembro.getMembroRecomendador().setSenha(rs.getString("a2.pw_senha"));
		recomendacaoMembro.getMembroRecomendador().setPerguntaChave(rs.getString("a2.ds_perguntaChave"));
		recomendacaoMembro.getMembroRecomendador().setRespostaChave(rs.getString("a2.ds_respostaChave"));
		recomendacaoMembro.getMembroRecomendador().setTipoLogradouro(rs.getString("a2.tp_logradouro"));
		recomendacaoMembro.getMembroRecomendador().setLogradouro(rs.getString("a2.no_logradouro"));
		recomendacaoMembro.getMembroRecomendador().setNumero(rs.getLong("a2.nu_logradouro"));
		recomendacaoMembro.getMembroRecomendador().setComplemento(rs.getString("a2.ds_complemento"));
		recomendacaoMembro.getMembroRecomendador().setCep(rs.getString("a2.nu_cep"));
		recomendacaoMembro.getMembroRecomendador().setStatus(rs.getString("a2.st_usuario"));
		recomendacaoMembro.getMembroRecomendador().setImagem(rs.getString("a2.img_usuario"));
		
		//membro Receptor
		//carrega uf
		recomendacaoMembro.getMembroReceptor().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("b5.pk_estado"));
		recomendacaoMembro.getMembroReceptor().getBairro().getMunicipio().getUf().setEstado(rs.getString("b5.no_estado"));
		recomendacaoMembro.getMembroReceptor().getBairro().getMunicipio().getUf().setSigla(rs.getString("b5.sg_estado")); 
		
		//carrega municipio
		recomendacaoMembro.getMembroReceptor().getBairro().getMunicipio().setPkMunicipio(rs.getLong("b4.pk_municipio"));
		recomendacaoMembro.getMembroReceptor().getBairro().getMunicipio().setMunicipio(rs.getString("b4.no_municipio"));
		
		//carrega bairro
		recomendacaoMembro.getMembroReceptor().getBairro().setPkBairro(rs.getLong("b3.pk_bairro"));
		recomendacaoMembro.getMembroReceptor().getBairro().setBairro(rs.getString("b3.no_bairro"));
		
		//formacaoAcademica
		recomendacaoMembro.getMembroReceptor().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("b6.pk_formacaoAcademica"));
		recomendacaoMembro.getMembroReceptor().getFormacaoAcademica().setNome(rs.getString("b6.no_formacaoAcademica"));
		
		//carrega instituicao
		recomendacaoMembro.getMembroReceptor().getInstituicao().setPkInstituicao(rs.getLong("b7.pk_instituicao"));
		recomendacaoMembro.getMembroReceptor().getInstituicao().setNome(rs.getString("b7.no_instituicao"));
		recomendacaoMembro.getMembroReceptor().getInstituicao().setTipoLogradouro(rs.getString("b7.tp_logradouroInst"));
		recomendacaoMembro.getMembroReceptor().getInstituicao().setLogradouro(rs.getString("b7.no_logradouroInst"));
		recomendacaoMembro.getMembroReceptor().getInstituicao().setNumero(rs.getLong("b7.nu_logradouroInst"));
		recomendacaoMembro.getMembroReceptor().getInstituicao().setComplemento(rs.getString("b7.ds_complementoInst"));
		recomendacaoMembro.getMembroReceptor().getInstituicao().setCep(rs.getString("b7.nu_cepInst"));
		
		//carrega usuario
		recomendacaoMembro.getMembroReceptor().setPkUsuario(rs.getLong("b2.pk_usuario"));
		recomendacaoMembro.getMembroReceptor().setCpf(rs.getLong("b2.nu_cpf"));
		recomendacaoMembro.getMembroReceptor().setNome(rs.getString("b2.no_usuario"));
		recomendacaoMembro.getMembroReceptor().setApelido(rs.getString("b2.no_apelido"));
		recomendacaoMembro.getMembroReceptor().setDataNasc(rs.getString("b2.dt_nasc"));
		recomendacaoMembro.getMembroReceptor().setEmail(rs.getString("b2.no_email"));
		recomendacaoMembro.getMembroReceptor().setSenha(rs.getString("b2.pw_senha"));
		recomendacaoMembro.getMembroReceptor().setPerguntaChave(rs.getString("b2.ds_perguntaChave"));
		recomendacaoMembro.getMembroReceptor().setRespostaChave(rs.getString("b2.ds_respostaChave"));
		recomendacaoMembro.getMembroReceptor().setTipoLogradouro(rs.getString("b2.tp_logradouro"));
		recomendacaoMembro.getMembroReceptor().setLogradouro(rs.getString("b2.no_logradouro"));
		recomendacaoMembro.getMembroReceptor().setNumero(rs.getLong("b2.nu_logradouro"));
		recomendacaoMembro.getMembroReceptor().setComplemento(rs.getString("b2.ds_complemento"));
		recomendacaoMembro.getMembroReceptor().setCep(rs.getString("b2.nu_cep"));
		recomendacaoMembro.getMembroReceptor().setStatus(rs.getString("b2.st_usuario"));
		recomendacaoMembro.getMembroReceptor().setImagem(rs.getString("b2.img_usuario"));
		
		//membro Recomendado
		//carrega uf
		recomendacaoMembro.getMembroRecomendado().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("c5.pk_estado"));
		recomendacaoMembro.getMembroRecomendado().getBairro().getMunicipio().getUf().setEstado(rs.getString("c5.no_estado"));
		recomendacaoMembro.getMembroRecomendado().getBairro().getMunicipio().getUf().setSigla(rs.getString("c5.sg_estado")); 
		
		//carrega municipio
		recomendacaoMembro.getMembroRecomendado().getBairro().getMunicipio().setPkMunicipio(rs.getLong("c4.pk_municipio"));
		recomendacaoMembro.getMembroRecomendado().getBairro().getMunicipio().setMunicipio(rs.getString("c4.no_municipio"));
		
		//carrega bairro
		recomendacaoMembro.getMembroRecomendado().getBairro().setPkBairro(rs.getLong("c3.pk_bairro"));
		recomendacaoMembro.getMembroRecomendado().getBairro().setBairro(rs.getString("c3.no_bairro"));
		
		//formacaoAcademica
		recomendacaoMembro.getMembroRecomendado().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("c6.pk_formacaoAcademica"));
		recomendacaoMembro.getMembroRecomendado().getFormacaoAcademica().setNome(rs.getString("c6.no_formacaoAcademica"));
		
		//carrega instituicao
		recomendacaoMembro.getMembroRecomendado().getInstituicao().setPkInstituicao(rs.getLong("c7.pk_instituicao"));
		recomendacaoMembro.getMembroRecomendado().getInstituicao().setNome(rs.getString("c7.no_instituicao"));
		recomendacaoMembro.getMembroRecomendado().getInstituicao().setTipoLogradouro(rs.getString("c7.tp_logradouroInst"));
		recomendacaoMembro.getMembroRecomendado().getInstituicao().setLogradouro(rs.getString("c7.no_logradouroInst"));
		recomendacaoMembro.getMembroRecomendado().getInstituicao().setNumero(rs.getLong("c7.nu_logradouroInst"));
		recomendacaoMembro.getMembroRecomendado().getInstituicao().setComplemento(rs.getString("c7.ds_complementoInst"));
		recomendacaoMembro.getMembroRecomendado().getInstituicao().setCep(rs.getString("c7.nu_cepInst"));
		
		//carrega usuario
		recomendacaoMembro.getMembroRecomendado().setPkUsuario(rs.getLong("c2.pk_usuario"));
		recomendacaoMembro.getMembroRecomendado().setCpf(rs.getLong("c2.nu_cpf"));
		recomendacaoMembro.getMembroRecomendado().setNome(rs.getString("c2.no_usuario"));
		recomendacaoMembro.getMembroRecomendado().setApelido(rs.getString("c2.no_apelido"));
		recomendacaoMembro.getMembroRecomendado().setDataNasc(rs.getString("c2.dt_nasc"));
		recomendacaoMembro.getMembroRecomendado().setEmail(rs.getString("c2.no_email"));
		recomendacaoMembro.getMembroRecomendado().setSenha(rs.getString("c2.pw_senha"));
		recomendacaoMembro.getMembroRecomendado().setPerguntaChave(rs.getString("c2.ds_perguntaChave"));
		recomendacaoMembro.getMembroRecomendado().setRespostaChave(rs.getString("c2.ds_respostaChave"));
		recomendacaoMembro.getMembroRecomendado().setTipoLogradouro(rs.getString("c2.tp_logradouro"));
		recomendacaoMembro.getMembroRecomendado().setLogradouro(rs.getString("c2.no_logradouro"));
		recomendacaoMembro.getMembroRecomendado().setNumero(rs.getLong("c2.nu_logradouro"));
		recomendacaoMembro.getMembroRecomendado().setComplemento(rs.getString("c2.ds_complemento"));
		recomendacaoMembro.getMembroRecomendado().setCep(rs.getString("c2.nu_cep"));
		recomendacaoMembro.getMembroRecomendado().setStatus(rs.getString("c2.st_usuario"));
		recomendacaoMembro.getMembroRecomendado().setImagem(rs.getString("c2.img_usuario"));
	}
}
