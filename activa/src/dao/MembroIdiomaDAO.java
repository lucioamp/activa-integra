package dao;

import interfaces.MembroIdiomaI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.MembroIdioma;
import util.CadastroException;
import util.ConnectionFactory;

public class MembroIdiomaDAO implements MembroIdiomaI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (MembroIdioma membroIdioma) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into membroidioma (fk_membro, fk_idioma) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,membroIdioma.getMembro().getPkUsuario());
				stmt.setLong(2,membroIdioma.getIdioma().getPkIdioma());
								
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
	
	/*public int alterar(MembroIdioma membroIdioma) throws CadastroException{
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
	
	public int excluir (MembroIdioma membroIdioma) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from membroidioma where fk_membro=? and fk_idioma = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, membroIdioma.getMembro().getPkUsuario());
				stmt.setLong(2, membroIdioma.getIdioma().getPkIdioma());
				
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
	
	public MembroIdioma consultar(MembroIdioma membroIdioma) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membroidioma, membro, usuario, formacaoacademica, idioma " +
					" where and fk_membro=? and fk_idioma=? and fk_membro=pk_membro and fk_idioma=pk_idioma " +
					" and pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membroIdioma.getMembro().getPkUsuario());
			stmt.setLong(2, membroIdioma.getIdioma().getPkIdioma());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, membroIdioma);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return membroIdioma;
	}
	
	public Collection<MembroIdioma> consultarPorMembro(Membro membro) throws CadastroException{
		Collection<MembroIdioma> col = new ArrayList<MembroIdioma>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membroidioma, membro, usuario, formacaoacademica, idioma" +
					" where fk_membro=? and fk_membro=pk_membro and fk_idioma=pk_idioma " +
					"and pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				MembroIdioma membroIdioma = new MembroIdioma();
				carregar(rs, membroIdioma);
				col.add(membroIdioma);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,MembroIdioma membroIdioma) throws SQLException{
		//carrega uf
		//membroIdioma.getMembro().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		//membroIdioma.getMembro().getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		//membroIdioma.getMembro().getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		//membroIdioma.getMembro().getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		//membroIdioma.getMembro().getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		membroIdioma.getMembro().getBairro().setPkBairro(rs.getLong("fk_bairro"));
		//membroIdioma.getMembro().getBairro().setBairro(rs.getString("no_bairro"));
		
		//formacaoAcademica
		membroIdioma.getMembro().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		membroIdioma.getMembro().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		membroIdioma.getMembro().getInstituicao().setPkInstituicao(rs.getLong("fk_instituicao"));
		/*membroIdioma.getMembro().getInstituicao().setNome(rs.getString("no_instituicao"));
		membroIdioma.getMembro().getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		membroIdioma.getMembro().getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		membroIdioma.getMembro().getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		membroIdioma.getMembro().getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		membroIdioma.getMembro().getInstituicao().setCep(rs.getString("nu_cepInst"));*/
		
		//carrega usuario
		membroIdioma.getMembro().setPkUsuario(rs.getLong("pk_usuario"));
		membroIdioma.getMembro().setCpf(rs.getLong("nu_cpf"));
		membroIdioma.getMembro().setNome(rs.getString("no_usuario"));
		membroIdioma.getMembro().setApelido(rs.getString("no_apelido"));
		membroIdioma.getMembro().setDataNasc(rs.getString("dt_nasc"));
		membroIdioma.getMembro().setEmail(rs.getString("no_email"));
		membroIdioma.getMembro().setSenha(rs.getString("pw_senha"));
		membroIdioma.getMembro().setPerguntaChave(rs.getString("ds_perguntaChave"));
		membroIdioma.getMembro().setRespostaChave(rs.getString("ds_respostaChave"));
		membroIdioma.getMembro().setTipoLogradouro(rs.getString("tp_logradouro"));
		membroIdioma.getMembro().setLogradouro(rs.getString("no_logradouro"));
		membroIdioma.getMembro().setNumero(rs.getLong("nu_logradouro"));
		membroIdioma.getMembro().setComplemento(rs.getString("ds_complemento"));
		membroIdioma.getMembro().setCep(rs.getString("nu_cep"));
		membroIdioma.getMembro().setStatus(rs.getString("st_usuario"));
		membroIdioma.getMembro().setImagem(rs.getString("img_usuario"));
		
		//carrega idioma
		membroIdioma.getIdioma().setPkIdioma(rs.getLong("pk_idioma"));
		membroIdioma.getIdioma().setNome(rs.getString("no_idioma"));
		
	}
}
