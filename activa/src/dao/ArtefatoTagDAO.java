package dao;

import interfaces.ArtefatoTagI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Artefato;
import modelo.ArtefatoTag;
import util.CadastroException;
import util.ConnectionFactory;

public class ArtefatoTagDAO implements ArtefatoTagI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (ArtefatoTag artefatoTag) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into artefatotag (fk_artefato,fk_tag) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,artefatoTag.getArtefato().getPkServico());
				stmt.setLong(2,artefatoTag.getTag().getPkTag());
				
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
	
	public int excluir (ArtefatoTag artefatoTag) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from artefatotag where fk_artefato = ? and fk_tag=?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, artefatoTag.getArtefato().getPkServico());
				stmt.setLong(2, artefatoTag.getTag().getPkTag());
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
	
	public ArtefatoTag consultar(ArtefatoTag artefatoTag) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
				
			String sql = "select * from artefato a,ambiente b,servico c,usuario d, categoriaartefato e, artefatotag  f, tag g" +
						 " where f.fk_artefato=? and f.fk_tag=? and c.fk_ambiente=b.pk_ambiente and a.pk_artefato=c.pk_servico " +
						 " and c.fk_membro=d.pk_usuario and a.fk_catArtefato=e.pk_catArtefato and a.pk_artefato=f.fk_artefato" +
						 " and f.fk_tag=g.pk_tag";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, artefatoTag.getArtefato().getPkServico());
			stmt.setLong(2, artefatoTag.getTag().getPkTag());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, artefatoTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return artefatoTag;
	}
	
	public Collection<ArtefatoTag> consultarPorArtefato(Artefato artefato) throws CadastroException{
		Collection<ArtefatoTag> col = new ArrayList<ArtefatoTag>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
					
			String sql = "select * from artefato a,ambiente b,servico c,usuario d, categoriaartefato e, artefatotag  f, tag g" +
			             " where fk_artefato=? and c.fk_ambiente=b.pk_ambiente and a.pk_artefato=c.pk_servico " +
						 " and c.fk_membro=d.pk_usuario and a.fk_catArtefato=e.pk_catArtefato and a.pk_artefato=f.fk_artefato" +
						 " and f.fk_tag=g.pk_tag";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, artefato.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				ArtefatoTag artefatoTag = new ArtefatoTag();
				carregar(rs, artefatoTag);
				col.add(artefatoTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,ArtefatoTag artefatoTag) throws SQLException{
		//carrega ambiente
		artefatoTag.getArtefato().getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		artefatoTag.getArtefato().getAmbiente().setNome(rs.getString("b.no_ambiente"));
		artefatoTag.getArtefato().getAmbiente().setData(rs.getString("b.dt_ambiente"));
		artefatoTag.getArtefato().getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		artefatoTag.getArtefato().getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		artefatoTag.getArtefato().setPkServico(rs.getLong("c.pk_servico"));
		artefatoTag.getArtefato().setNome(rs.getString("c.no_servico"));
		artefatoTag.getArtefato().setData(rs.getString("c.dt_servico"));
		artefatoTag.getArtefato().setDescricao(rs.getString("c.ds_servico"));
		artefatoTag.getArtefato().setImagem(rs.getString("c.no_imagem"));
		artefatoTag.getArtefato().setStatus(rs.getString("c.st_servico"));
		artefatoTag.getArtefato().setAutomatico(rs.getString("c.fl_automatico"));
		artefatoTag.getArtefato().getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		artefatoTag.getArtefato().getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		artefatoTag.getArtefato().getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		artefatoTag.getArtefato().getMembro().setCpf(rs.getLong("d.nu_cpf"));
		artefatoTag.getArtefato().getMembro().setNome(rs.getString("d.no_usuario"));
		artefatoTag.getArtefato().getMembro().setApelido(rs.getString("d.no_apelido"));
		artefatoTag.getArtefato().getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		artefatoTag.getArtefato().getMembro().setEmail(rs.getString("d.no_email"));
		artefatoTag.getArtefato().getMembro().setSenha(rs.getString("d.pw_senha"));
		artefatoTag.getArtefato().getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		artefatoTag.getArtefato().getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		artefatoTag.getArtefato().getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		artefatoTag.getArtefato().getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		artefatoTag.getArtefato().getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		artefatoTag.getArtefato().getMembro().setComplemento(rs.getString("d.ds_complemento"));
		artefatoTag.getArtefato().getMembro().setCep(rs.getString("d.nu_cep"));
		artefatoTag.getArtefato().getMembro().setStatus(rs.getString("d.st_usuario"));
		artefatoTag.getArtefato().getMembro().setImagem(rs.getString("d.img_usuario"));
		
		//carrega categoriaArtefato
		artefatoTag.getArtefato().getCategoriaArtefato().setPkCatArtefato(rs.getLong("e.pk_catArtefato"));
		artefatoTag.getArtefato().getCategoriaArtefato().setNome(rs.getString("e.no_catArtefato"));
		artefatoTag.getArtefato().getCategoriaArtefato().setDescricao(rs.getString("e.ds_catArtefato"));
		
		//carrega tag
		artefatoTag.getTag().setPkTag(rs.getLong("g.pk_tag"));
		artefatoTag.getTag().setNome(rs.getString("g.no_tag"));
		artefatoTag.getTag().setDescricao(rs.getString("g.ds_tag"));
		
		//carrega artefato
		artefatoTag.getArtefato().setAutor(rs.getString("a.no_autor"));
		artefatoTag.getArtefato().setAnoPublicacao(rs.getLong("a.nu_anoPublicacao"));
	}
}
