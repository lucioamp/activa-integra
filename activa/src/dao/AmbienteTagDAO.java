package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Ambiente;
import modelo.AmbienteTag;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.AmbienteTagI;

public class AmbienteTagDAO implements AmbienteTagI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (AmbienteTag ambienteTag) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into ambientetag (fk_ambiente, fk_tag) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,ambienteTag.getAmbiente().getPkAmbiente());
				stmt.setLong(2,ambienteTag.getTag().getPkTag());
								
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
	
	/*public int alterar(AmbienteTag ambienteTag) throws CadastroException{
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
	
	public int excluir (AmbienteTag ambienteTag) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from ambientetag where fk_ambiente=? and fk_tag = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, ambienteTag.getAmbiente().getPkAmbiente());
				stmt.setLong(2, ambienteTag.getTag().getPkTag());
				
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
	
	public AmbienteTag consultar(AmbienteTag ambienteTag) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from ambientetag, ambiente, tag " +
					" where and fk_ambiente=? and fk_tag=? and fk_ambiente=pk_ambiente and fk_tag=pk_tag ";		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, ambienteTag.getAmbiente().getPkAmbiente());
			stmt.setLong(2, ambienteTag.getTag().getPkTag());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, ambienteTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return ambienteTag;
	}
	
	public Collection<AmbienteTag> consultarPorAmbiente(Ambiente ambiente) throws CadastroException{
		Collection<AmbienteTag> col = new ArrayList<AmbienteTag>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from ambientetag, ambiente, tag" +
					" where fk_ambiente=? and fk_ambiente=pk_ambiente and fk_tag=pk_tag ";		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, ambiente.getPkAmbiente());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				AmbienteTag ambienteTag = new AmbienteTag();
				carregar(rs, ambienteTag);
				col.add(ambienteTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,AmbienteTag ambienteTag) throws SQLException{
		//carrega uf
		//ambienteTag.getAmbiente().getProfessor().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		//ambienteTag.getAmbiente().getProfessor().getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		//ambienteTag.getAmbiente().getProfessor().getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		//ambienteTag.getAmbiente().getProfessor().getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		//ambienteTag.getAmbiente().getProfessor().getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		//ambienteTag.getAmbiente().getProfessor().getBairro().setPkBairro(rs.getLong("pk_bairro"));
		//ambienteTag.getAmbiente().getProfessor().getBairro().setBairro(rs.getString("no_bairro"));
		
		//formacaoAcademica
		//ambienteTag.getAmbiente().getProfessor().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		//ambienteTag.getAmbiente().getProfessor().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		/*ambienteTag.getAmbiente().getProfessor().getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		ambienteTag.getAmbiente().getProfessor().getInstituicao().setNome(rs.getString("no_instituicao"));
		ambienteTag.getAmbiente().getProfessor().getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		ambienteTag.getAmbiente().getProfessor().getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		ambienteTag.getAmbiente().getProfessor().getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		ambienteTag.getAmbiente().getProfessor().getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		ambienteTag.getAmbiente().getProfessor().getInstituicao().setCep(rs.getString("nu_cepInst"));
		*/
		//carrega usuario
		ambienteTag.getAmbiente().getProfessor().setPkUsuario(rs.getLong("fk_professor"));
		/*ambienteTag.getAmbiente().getProfessor().setCpf(rs.getLong("nu_cpf"));
		ambienteTag.getAmbiente().getProfessor().setNome(rs.getString("no_usuario"));
		ambienteTag.getAmbiente().getProfessor().setApelido(rs.getString("no_apelido"));
		ambienteTag.getAmbiente().getProfessor().setDataNasc(rs.getString("dt_nasc"));
		ambienteTag.getAmbiente().getProfessor().setEmail(rs.getString("no_email"));
		ambienteTag.getAmbiente().getProfessor().setSenha(rs.getString("pw_senha"));
		ambienteTag.getAmbiente().getProfessor().setPerguntaChave(rs.getString("ds_perguntaChave"));
		ambienteTag.getAmbiente().getProfessor().setRespostaChave(rs.getString("ds_respostaChave"));
		ambienteTag.getAmbiente().getProfessor().setTipoLogradouro(rs.getString("tp_logradouro"));
		ambienteTag.getAmbiente().getProfessor().setLogradouro(rs.getString("no_logradouro"));
		ambienteTag.getAmbiente().getProfessor().setNumero(rs.getLong("nu_logradouro"));
		ambienteTag.getAmbiente().getProfessor().setComplemento(rs.getString("ds_complemento"));
		ambienteTag.getAmbiente().getProfessor().setCep(rs.getString("nu_cep"));
		ambienteTag.getAmbiente().getProfessor().setStatus(rs.getString("st_usuario"));
		ambienteTag.getAmbiente().getProfessor().setImagem(rs.getString("img_usuario"));*/
		
		//carrega ambiente
		ambienteTag.getAmbiente().setPkAmbiente(rs.getLong("pk_ambiente"));
		ambienteTag.getAmbiente().setNome(rs.getString("no_ambiente"));
		ambienteTag.getAmbiente().setData(rs.getString("dt_ambiente"));
		ambienteTag.getAmbiente().setDescricao(rs.getString("ds_ambiente"));
		ambienteTag.getAmbiente().setImagem(rs.getString("no_imagem"));
		
		//carrega tag
		ambienteTag.getTag().setPkTag(rs.getLong("pk_tag"));
		ambienteTag.getTag().setNome(rs.getString("no_tag"));
		ambienteTag.getTag().setDescricao(rs.getString("ds_tag"));
	}
}
