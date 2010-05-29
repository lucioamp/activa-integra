package dao;

import interfaces.MembroTagI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.MembroTag;
import util.CadastroException;
import util.ConnectionFactory;

public class MembroTagDAO implements MembroTagI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (MembroTag membroTag) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into membrotag (fk_membro, fk_tag) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,membroTag.getMembro().getPkUsuario());
				stmt.setLong(2,membroTag.getTag().getPkTag());
								
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
	
	/*public int alterar(MembroTag membroTag) throws CadastroException{
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
	
	public int excluir (MembroTag membroTag) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from membrotag where fk_membro=? and fk_tag = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, membroTag.getMembro().getPkUsuario());
				stmt.setLong(2, membroTag.getTag().getPkTag());
				
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
	
	public MembroTag consultar(MembroTag membroTag) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membrotag, membro, usuario, bairro, municipio, uf, formacaoacademica, tag, instituicao " +
					" where and fk_membro=? and fk_tag=? and fk_membro=pk_membro and fk_tag=pk_tag " +
					" and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and " +
					" pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membroTag.getMembro().getPkUsuario());
			stmt.setLong(2, membroTag.getTag().getPkTag());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, membroTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return membroTag;
	}
	
	public Collection<MembroTag> consultarPorMembro(Membro membro) throws CadastroException{
		Collection<MembroTag> col = new ArrayList<MembroTag>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membrotag, membro, usuario, bairro, municipio, uf, formacaoacademica, tag, instituicao " +
					" where fk_membro=? and fk_membro=pk_membro and fk_tag=pk_tag " +
					" and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and " +
					" pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				MembroTag membroTag = new MembroTag();
				carregar(rs, membroTag);
				col.add(membroTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,MembroTag membroTag) throws SQLException{
		//carrega uf
		membroTag.getMembro().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		membroTag.getMembro().getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		membroTag.getMembro().getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		membroTag.getMembro().getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		membroTag.getMembro().getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		membroTag.getMembro().getBairro().setPkBairro(rs.getLong("pk_bairro"));
		membroTag.getMembro().getBairro().setBairro(rs.getString("no_bairro"));
		
		//formacaoAcademica
		membroTag.getMembro().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		membroTag.getMembro().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		membroTag.getMembro().getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		membroTag.getMembro().getInstituicao().setNome(rs.getString("no_instituicao"));
		membroTag.getMembro().getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		membroTag.getMembro().getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		membroTag.getMembro().getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		membroTag.getMembro().getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		membroTag.getMembro().getInstituicao().setCep(rs.getString("nu_cepInst"));
		
		//carrega usuario
		membroTag.getMembro().setPkUsuario(rs.getLong("pk_usuario"));
		membroTag.getMembro().setCpf(rs.getLong("nu_cpf"));
		membroTag.getMembro().setNome(rs.getString("no_usuario"));
		membroTag.getMembro().setApelido(rs.getString("no_apelido"));
		membroTag.getMembro().setDataNasc(rs.getString("dt_nasc"));
		membroTag.getMembro().setEmail(rs.getString("no_email"));
		membroTag.getMembro().setSenha(rs.getString("pw_senha"));
		membroTag.getMembro().setPerguntaChave(rs.getString("ds_perguntaChave"));
		membroTag.getMembro().setRespostaChave(rs.getString("ds_respostaChave"));
		membroTag.getMembro().setTipoLogradouro(rs.getString("tp_logradouro"));
		membroTag.getMembro().setLogradouro(rs.getString("no_logradouro"));
		membroTag.getMembro().setNumero(rs.getLong("nu_logradouro"));
		membroTag.getMembro().setComplemento(rs.getString("ds_complemento"));
		membroTag.getMembro().setCep(rs.getString("nu_cep"));
		membroTag.getMembro().setStatus(rs.getString("st_usuario"));
		membroTag.getMembro().setImagem(rs.getString("img_usuario"));
		
		//carrega tag
		membroTag.getTag().setPkTag(rs.getLong("pk_tag"));
		membroTag.getTag().setNome(rs.getString("no_tag"));
		membroTag.getTag().setDescricao(rs.getString("ds_tag"));
	}
}
