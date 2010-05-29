package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Comunidade;
import modelo.ComunidadeTag;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.ComunidadeTagI;

public class ComunidadeTagDAO implements ComunidadeTagI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (ComunidadeTag comunidadeTag) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into comunidadetag (fk_comunidade,fk_tag) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,comunidadeTag.getComunidade().getPkServico());
				stmt.setLong(2,comunidadeTag.getTag().getPkTag());
				
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
	
	/*public int alterar(ContatoInstituicao contatoInstituicao) throws CadastroException{
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
	
	public int excluir (ComunidadeTag comunidadeTag) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from comunidadetag where fk_comunidade = ? and fk_tag=?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, comunidadeTag.getComunidade().getPkServico());
				stmt.setLong(2, comunidadeTag.getTag().getPkTag());
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
	
	public ComunidadeTag consultar(ComunidadeTag comunidadeTag) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
				
			String sql = "select * from comunidade a,ambiente b,servico c,usuario d, categoriacomunidade e, comunidadetag  f, tag g" +
						 " where f.fk_comunidade=? and f.fk_tag=? and c.fk_ambiente=b.pk_ambiente and a.pk_comunidade=c.pk_servico " +
						 " and c.fk_membro=d.pk_usuario and a.fk_catComunidade=e.pk_catComunidade and a.pk_comunidade=f.fk_comunidade" +
						 " and f.fk_tag=g.pk_tag";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, comunidadeTag.getComunidade().getPkServico());
			stmt.setLong(2, comunidadeTag.getTag().getPkTag());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, comunidadeTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return comunidadeTag;
	}
	
	public Collection<ComunidadeTag> consultarPorComunidade(Comunidade comunidade) throws CadastroException{
		Collection<ComunidadeTag> col = new ArrayList<ComunidadeTag>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
					
			String sql = "select * from comunidade a,ambiente b,servico c,usuario d, categoriacomunidade e, comunidadetag  f, tag g" +
			             " where fk_comunidade=? and c.fk_ambiente=b.pk_ambiente and a.pk_comunidade=c.pk_servico " +
						 " and c.fk_membro=d.pk_usuario and a.fk_catComunidade=e.pk_catComunidade and a.pk_comunidade=f.fk_comunidade" +
						 " and f.fk_tag=g.pk_tag";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, comunidade.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				ComunidadeTag comunidadeTag = new ComunidadeTag();
				carregar(rs, comunidadeTag);
				col.add(comunidadeTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,ComunidadeTag comunidadeTag) throws SQLException{
		//carrega ambiente
		comunidadeTag.getComunidade().getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		comunidadeTag.getComunidade().getAmbiente().setNome(rs.getString("b.no_ambiente"));
		comunidadeTag.getComunidade().getAmbiente().setData(rs.getString("b.dt_ambiente"));
		comunidadeTag.getComunidade().getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		comunidadeTag.getComunidade().getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		comunidadeTag.getComunidade().setPkServico(rs.getLong("c.pk_servico"));
		comunidadeTag.getComunidade().setNome(rs.getString("c.no_servico"));
		comunidadeTag.getComunidade().setData(rs.getString("c.dt_servico"));
		comunidadeTag.getComunidade().setDescricao(rs.getString("c.ds_servico"));
		comunidadeTag.getComunidade().setImagem(rs.getString("c.no_imagem"));
		comunidadeTag.getComunidade().setStatus(rs.getString("c.st_servico"));
		comunidadeTag.getComunidade().setAutomatico(rs.getString("c.fl_automatico"));
		comunidadeTag.getComunidade().getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		comunidadeTag.getComunidade().getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		comunidadeTag.getComunidade().getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		comunidadeTag.getComunidade().getMembro().setCpf(rs.getLong("d.nu_cpf"));
		comunidadeTag.getComunidade().getMembro().setNome(rs.getString("d.no_usuario"));
		comunidadeTag.getComunidade().getMembro().setApelido(rs.getString("d.no_apelido"));
		comunidadeTag.getComunidade().getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		comunidadeTag.getComunidade().getMembro().setEmail(rs.getString("d.no_email"));
		comunidadeTag.getComunidade().getMembro().setSenha(rs.getString("d.pw_senha"));
		comunidadeTag.getComunidade().getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		comunidadeTag.getComunidade().getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		comunidadeTag.getComunidade().getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		comunidadeTag.getComunidade().getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		comunidadeTag.getComunidade().getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		comunidadeTag.getComunidade().getMembro().setComplemento(rs.getString("d.ds_complemento"));
		comunidadeTag.getComunidade().getMembro().setCep(rs.getString("d.nu_cep"));
		comunidadeTag.getComunidade().getMembro().setStatus(rs.getString("d.st_usuario"));
		comunidadeTag.getComunidade().getMembro().setImagem(rs.getString("d.img_usuario"));
		
		//carrega categoriaComunidade
		comunidadeTag.getComunidade().getCategoriaComunidade().setPkCatComunidade(rs.getLong("e.pk_catComunidade"));
		comunidadeTag.getComunidade().getCategoriaComunidade().setNome(rs.getString("e.no_catComunidade"));
		comunidadeTag.getComunidade().getCategoriaComunidade().setDescricao(rs.getString("e.ds_catComunidade"));
		
		//carrega tag
		comunidadeTag.getTag().setPkTag(rs.getLong("g.pk_tag"));
		comunidadeTag.getTag().setNome(rs.getString("g.no_tag"));
		comunidadeTag.getTag().setDescricao(rs.getString("g.ds_tag"));
		
	}
}
