package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.AvaliacaoMembro;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.AvaliacaoMembroI;

public class AvaliacaoMembroDAO implements AvaliacaoMembroI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (AvaliacaoMembro avaliacaoMembro) throws CadastroException{
		int r = 0;
		try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into avaliacaomembro (fk_membroAvaliador, fk_membro, nu_avaliacao) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1,avaliacaoMembro.getMembroAvaliador().getPkUsuario());
				stmt.setLong(2,avaliacaoMembro.getMembro().getPkUsuario());
				stmt.setLong(3,avaliacaoMembro.getAvaliacao());
				
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
	
	public int alterar(AvaliacaoMembro avaliacaoMembro) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update avaliacaomembro nu_avaliacao=? where fk_membroAvaliador=? and fk_membro=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, avaliacaoMembro.getAvaliacao());
			stmt.setLong(2, avaliacaoMembro.getMembroAvaliador().getPkUsuario());
			stmt.setLong(3, avaliacaoMembro.getMembro().getPkUsuario());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (AvaliacaoMembro avaliacaoMembro) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from avaliacaomembro where fk_membroAvaliador=? and fk_membro=?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,avaliacaoMembro.getMembroAvaliador().getPkUsuario());
				stmt.setLong(2,avaliacaoMembro.getMembro().getPkUsuario());
				
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
	
	public Collection<AvaliacaoMembro> consultarPorMembro(Membro membro) throws CadastroException{
		Collection<AvaliacaoMembro> col = new ArrayList<AvaliacaoMembro>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from avaliacaomembro x,"+
			 " membro a1, usuario a2, bairro a3, municipio a4, uf a5, formacaoacademica a6, instituicao a7,"+
	         " membro b1, usuario b2, bairro b3, municipio b4, uf b5, formacaoacademica b6, instituicao b7"+
	         " where b1.pk_membro=? and x.fk_membroAvaliador=a1.pk_membro and x.fk_membro=b1.pk_membro and"+
			 " a2.fk_bairro=a3.pk_bairro and a3.fk_municipio=a4.pk_municipio and a4.fk_estado=a5.pk_estado and"+
			 " a1.pk_membro=a2.pk_usuario and a6.pk_formacaoAcademica = a1.fk_formacaoAcademica and"+
			 " a2.fk_instituicao=a7.pk_instituicao and"+
			 " b2.fk_bairro=b3.pk_bairro and b3.fk_municipio=b4.pk_municipio and b4.fk_estado=b5.pk_estado and"+
			 " b1.pk_membro=b2.pk_usuario and b6.pk_formacaoAcademica = b1.fk_formacaoAcademica and"+
			 " b2.fk_instituicao=b7.pk_instituicao and";

			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultarTodas :" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				AvaliacaoMembro avaliacaoMembro = new AvaliacaoMembro();
				carregar(rs, avaliacaoMembro);
				col.add(avaliacaoMembro);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
	return col;
	}
	
	public AvaliacaoMembro consultar(AvaliacaoMembro avaliacaoMembro) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from avaliacaomembro x,"+
			 " membro a1, usuario a2, bairro a3, municipio a4, uf a5, formacaoacademica a6, instituicao a7,"+
	         " membro b1, usuario b2, bairro b3, municipio b4, uf b5, formacaoacademica b6, instituicao b7"+
	         " where a1.pk_membro=? and b1.pk_membro=? and" +
			 " x.fk_membroAvaliador=a1.pk_membro and x.fk_membro=b1.pk_membro and"+
			 " a2.fk_bairro=a3.pk_bairro and a3.fk_municipio=a4.pk_municipio and a4.fk_estado=a5.pk_estado and"+
			 " a1.pk_membro=a2.pk_usuario and a6.pk_formacaoAcademica = a1.fk_formacaoAcademica and"+
			 " a2.fk_instituicao=a7.pk_instituicao and"+
			 " b2.fk_bairro=b3.pk_bairro and b3.fk_municipio=b4.pk_municipio and b4.fk_estado=b5.pk_estado and"+
			 " b1.pk_membro=b2.pk_usuario and b6.pk_formacaoAcademica = b1.fk_formacaoAcademica and"+
			 " b2.fk_instituicao=b7.pk_instituicao and";
			
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1,avaliacaoMembro.getMembroAvaliador().getPkUsuario());
			stmt.setLong(2,avaliacaoMembro.getMembro().getPkUsuario());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, avaliacaoMembro);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return avaliacaoMembro;
	}
	
	private void carregar(ResultSet rs ,AvaliacaoMembro avaliacaoMembro) throws SQLException{
		//membro Avaliador
		//carrega uf
		avaliacaoMembro.getMembroAvaliador().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("a5.pk_estado"));
		avaliacaoMembro.getMembroAvaliador().getBairro().getMunicipio().getUf().setEstado(rs.getString("a5.no_estado"));
		avaliacaoMembro.getMembroAvaliador().getBairro().getMunicipio().getUf().setSigla(rs.getString("a5.sg_estado")); 
		
		//carrega municipio
		avaliacaoMembro.getMembroAvaliador().getBairro().getMunicipio().setPkMunicipio(rs.getLong("a4.pk_municipio"));
		avaliacaoMembro.getMembroAvaliador().getBairro().getMunicipio().setMunicipio(rs.getString("a4.no_municipio"));
		
		//carrega bairro
		avaliacaoMembro.getMembroAvaliador().getBairro().setPkBairro(rs.getLong("a3.pk_bairro"));
		avaliacaoMembro.getMembroAvaliador().getBairro().setBairro(rs.getString("a3.no_bairro"));
		
		//formacaoAcademica
		avaliacaoMembro.getMembroAvaliador().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("a6.pk_formacaoAcademica"));
		avaliacaoMembro.getMembroAvaliador().getFormacaoAcademica().setNome(rs.getString("a6.no_formacaoAcademica"));
		
		//carrega instituicao
		avaliacaoMembro.getMembroAvaliador().getInstituicao().setPkInstituicao(rs.getLong("a7.pk_instituicao"));
		avaliacaoMembro.getMembroAvaliador().getInstituicao().setNome(rs.getString("a7.no_instituicao"));
		avaliacaoMembro.getMembroAvaliador().getInstituicao().setTipoLogradouro(rs.getString("a7.tp_logradouroInst"));
		avaliacaoMembro.getMembroAvaliador().getInstituicao().setLogradouro(rs.getString("a7.no_logradouroInst"));
		avaliacaoMembro.getMembroAvaliador().getInstituicao().setNumero(rs.getLong("a7.nu_logradouroInst"));
		avaliacaoMembro.getMembroAvaliador().getInstituicao().setComplemento(rs.getString("a7.ds_complementoInst"));
		avaliacaoMembro.getMembroAvaliador().getInstituicao().setCep(rs.getString("a7.nu_cepInst"));
		
		//carrega usuario
		avaliacaoMembro.getMembroAvaliador().setPkUsuario(rs.getLong("a2.pk_usuario"));
		avaliacaoMembro.getMembroAvaliador().setCpf(rs.getLong("a2.nu_cpf"));
		avaliacaoMembro.getMembroAvaliador().setNome(rs.getString("a2.no_usuario"));
		avaliacaoMembro.getMembroAvaliador().setApelido(rs.getString("a2.no_apelido"));
		avaliacaoMembro.getMembroAvaliador().setDataNasc(rs.getString("a2.dt_nasc"));
		avaliacaoMembro.getMembroAvaliador().setEmail(rs.getString("a2.no_email"));
		avaliacaoMembro.getMembroAvaliador().setSenha(rs.getString("a2.pw_senha"));
		avaliacaoMembro.getMembroAvaliador().setPerguntaChave(rs.getString("a2.ds_perguntaChave"));
		avaliacaoMembro.getMembroAvaliador().setRespostaChave(rs.getString("a2.ds_respostaChave"));
		avaliacaoMembro.getMembroAvaliador().setTipoLogradouro(rs.getString("a2.tp_logradouro"));
		avaliacaoMembro.getMembroAvaliador().setLogradouro(rs.getString("a2.no_logradouro"));
		avaliacaoMembro.getMembroAvaliador().setNumero(rs.getLong("a2.nu_logradouro"));
		avaliacaoMembro.getMembroAvaliador().setComplemento(rs.getString("a2.ds_complemento"));
		avaliacaoMembro.getMembroAvaliador().setCep(rs.getString("a2.nu_cep"));
		avaliacaoMembro.getMembroAvaliador().setStatus(rs.getString("a2.st_usuario"));
		avaliacaoMembro.getMembroAvaliador().setImagem(rs.getString("a2.img_usuario"));
		
		//membro 
		//carrega uf
		avaliacaoMembro.getMembro().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("b5.pk_estado"));
		avaliacaoMembro.getMembro().getBairro().getMunicipio().getUf().setEstado(rs.getString("b5.no_estado"));
		avaliacaoMembro.getMembro().getBairro().getMunicipio().getUf().setSigla(rs.getString("b5.sg_estado")); 
		
		//carrega municipio
		avaliacaoMembro.getMembro().getBairro().getMunicipio().setPkMunicipio(rs.getLong("b4.pk_municipio"));
		avaliacaoMembro.getMembro().getBairro().getMunicipio().setMunicipio(rs.getString("b4.no_municipio"));
		
		//carrega bairro
		avaliacaoMembro.getMembro().getBairro().setPkBairro(rs.getLong("b3.pk_bairro"));
		avaliacaoMembro.getMembro().getBairro().setBairro(rs.getString("b3.no_bairro"));
		
		//formacaoAcademica
		avaliacaoMembro.getMembro().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("b6.pk_formacaoAcademica"));
		avaliacaoMembro.getMembro().getFormacaoAcademica().setNome(rs.getString("b6.no_formacaoAcademica"));
		
		//carrega instituicao
		avaliacaoMembro.getMembro().getInstituicao().setPkInstituicao(rs.getLong("b7.pk_instituicao"));
		avaliacaoMembro.getMembro().getInstituicao().setNome(rs.getString("b7.no_instituicao"));
		avaliacaoMembro.getMembro().getInstituicao().setTipoLogradouro(rs.getString("b7.tp_logradouroInst"));
		avaliacaoMembro.getMembro().getInstituicao().setLogradouro(rs.getString("b7.no_logradouroInst"));
		avaliacaoMembro.getMembro().getInstituicao().setNumero(rs.getLong("b7.nu_logradouroInst"));
		avaliacaoMembro.getMembro().getInstituicao().setComplemento(rs.getString("b7.ds_complementoInst"));
		avaliacaoMembro.getMembro().getInstituicao().setCep(rs.getString("b7.nu_cepInst"));
		
		//carrega usuario
		avaliacaoMembro.getMembro().setPkUsuario(rs.getLong("b2.pk_usuario"));
		avaliacaoMembro.getMembro().setCpf(rs.getLong("b2.nu_cpf"));
		avaliacaoMembro.getMembro().setNome(rs.getString("b2.no_usuario"));
		avaliacaoMembro.getMembro().setApelido(rs.getString("b2.no_apelido"));
		avaliacaoMembro.getMembro().setDataNasc(rs.getString("b2.dt_nasc"));
		avaliacaoMembro.getMembro().setEmail(rs.getString("b2.no_email"));
		avaliacaoMembro.getMembro().setSenha(rs.getString("b2.pw_senha"));
		avaliacaoMembro.getMembro().setPerguntaChave(rs.getString("b2.ds_perguntaChave"));
		avaliacaoMembro.getMembro().setRespostaChave(rs.getString("b2.ds_respostaChave"));
		avaliacaoMembro.getMembro().setTipoLogradouro(rs.getString("b2.tp_logradouro"));
		avaliacaoMembro.getMembro().setLogradouro(rs.getString("b2.no_logradouro"));
		avaliacaoMembro.getMembro().setNumero(rs.getLong("b2.nu_logradouro"));
		avaliacaoMembro.getMembro().setComplemento(rs.getString("b2.ds_complemento"));
		avaliacaoMembro.getMembro().setCep(rs.getString("b2.nu_cep"));
		avaliacaoMembro.getMembro().setStatus(rs.getString("b2.st_usuario"));
		avaliacaoMembro.getMembro().setImagem(rs.getString("b2.img_usuario"));
		
		//carrega avaliacaoMembro
		avaliacaoMembro.setAvaliacao(rs.getLong("x.nu_avaliacao"));
		
	}
}
