package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Meta;
import modelo.MetaTag;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.MetaTagI;

public class MetaTagDAO implements MetaTagI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (MetaTag metaTag) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into metatag (fk_meta, fk_tag) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,metaTag.getMeta().getPkMeta());
				stmt.setLong(2,metaTag.getTag().getPkTag());
								
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
	
	/*public int alterar(MetaTag ambienteTag) throws CadastroException{
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
	
	public int excluir (MetaTag metaTag) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from metatag where fk_meta=? and fk_tag = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, metaTag.getMeta().getPkMeta());
				stmt.setLong(2, metaTag.getTag().getPkTag());
				
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
	
	public MetaTag consultar(MetaTag metaTag) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from metatag, ambiente, tag, meta " +
					" where fk_meta=? and fk_tag=? and fk_meta=pk_meta and fk_ambiente=pk_ambiente and fk_tag=pk_tag ";		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, metaTag.getMeta().getPkMeta());
			stmt.setLong(2, metaTag.getTag().getPkTag());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, metaTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return metaTag;
	}
	
	public Collection<MetaTag> consultarPorMeta(Meta meta) throws CadastroException{
		Collection<MetaTag> col = new ArrayList<MetaTag>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from metaTag, ambiente, tag, meta " +
						 " where fk_meta=? and fk_meta=pk_meta and fk_ambiente=pk_ambiente and fk_tag=pk_tag ";		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, meta.getPkMeta());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				MetaTag metaTag = new MetaTag();
				carregar(rs, metaTag);
				col.add(metaTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,MetaTag metaTag) throws SQLException{
		//carrega uf
		//metaTag.getMeta().getAmbiente().getProfessor().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		//metaTag.getMeta().getAmbiente().getProfessor().getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		//metaTag.getMeta().getAmbiente().getProfessor().getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		//metaTag.getMeta().getAmbiente().getProfessor().getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		//metaTag.getMeta().getAmbiente().getProfessor().getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		//metaTag.getMeta().getAmbiente().getProfessor().getBairro().setPkBairro(rs.getLong("pk_bairro"));
		//metaTag.getMeta().getAmbiente().getProfessor().getBairro().setBairro(rs.getString("no_bairro"));
		
		//formacaoAcademica
		//metaTag.getMeta().getAmbiente().getProfessor().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		//metaTag.getMeta().getAmbiente().getProfessor().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		/*metaTag.getMeta().getAmbiente().getProfessor().getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		metaTag.getMeta().getAmbiente().getProfessor().getInstituicao().setNome(rs.getString("no_instituicao"));
		metaTag.getMeta().getAmbiente().getProfessor().getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		metaTag.getMeta().getAmbiente().getProfessor().getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		metaTag.getMeta().getAmbiente().getProfessor().getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		metaTag.getMeta().getAmbiente().getProfessor().getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		metaTag.getMeta().getAmbiente().getProfessor().getInstituicao().setCep(rs.getString("nu_cepInst"));
		*/
		//carrega usuario
		metaTag.getMeta().getAmbiente().getProfessor().setPkUsuario(rs.getLong("fk_professor"));
		/*metaTag.getMeta().getAmbiente().getProfessor().setCpf(rs.getLong("nu_cpf"));
		metaTag.getMeta().getAmbiente().getProfessor().setNome(rs.getString("no_usuario"));
		metaTag.getMeta().getAmbiente().getProfessor().setApelido(rs.getString("no_apelido"));
		metaTag.getMeta().getAmbiente().getProfessor().setDataNasc(rs.getString("dt_nasc"));
		metaTag.getMeta().getAmbiente().getProfessor().setEmail(rs.getString("no_email"));
		metaTag.getMeta().getAmbiente().getProfessor().setSenha(rs.getString("pw_senha"));
		metaTag.getMeta().getAmbiente().getProfessor().setPerguntaChave(rs.getString("ds_perguntaChave"));
		metaTag.getMeta().getAmbiente().getProfessor().setRespostaChave(rs.getString("ds_respostaChave"));
		metaTag.getMeta().getAmbiente().getProfessor().setTipoLogradouro(rs.getString("tp_logradouro"));
		metaTag.getMeta().getAmbiente().getProfessor().setLogradouro(rs.getString("no_logradouro"));
		metaTag.getMeta().getAmbiente().getProfessor().setNumero(rs.getLong("nu_logradouro"));
		metaTag.getMeta().getAmbiente().getProfessor().setComplemento(rs.getString("ds_complemento"));
		metaTag.getMeta().getAmbiente().getProfessor().setCep(rs.getString("nu_cep"));
		metaTag.getMeta().getAmbiente().getProfessor().setStatus(rs.getString("st_usuario"));
		metaTag.getMeta().getAmbiente().getProfessor().setImagem(rs.getString("img_usuario"));
		*/
		//carrega ambiente
		metaTag.getMeta().getAmbiente().setPkAmbiente(rs.getLong("pk_ambiente"));
		metaTag.getMeta().getAmbiente().setNome(rs.getString("no_ambiente"));
		metaTag.getMeta().getAmbiente().setData(rs.getString("dt_ambiente"));
		metaTag.getMeta().getAmbiente().setDescricao(rs.getString("ds_ambiente"));
		metaTag.getMeta().getAmbiente().setImagem(rs.getString("no_imagem"));
		
		//carrega meta
		metaTag.getMeta().setPkMeta(rs.getLong("pk_meta"));
		metaTag.getMeta().setDescricao(rs.getString("ds_meta"));
		metaTag.getMeta().setDuracao(rs.getString("ds_duracao")); 
		
		//carrega tag
		metaTag.getTag().setPkTag(rs.getLong("pk_tag"));
		metaTag.getTag().setNome(rs.getString("no_tag"));
		metaTag.getTag().setDescricao(rs.getString("ds_tag"));
	}
}
