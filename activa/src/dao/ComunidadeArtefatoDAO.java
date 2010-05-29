package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Comunidade;
import modelo.ComunidadeArtefato;
import modelo.Artefato;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.ComunidadeArtefatoI;

public class ComunidadeArtefatoDAO implements ComunidadeArtefatoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (ComunidadeArtefato comunidadeArtefato) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into comunidadeartefato (fk_comunidade, fk_artefato) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,comunidadeArtefato.getComunidade().getPkServico());
				stmt.setLong(2,comunidadeArtefato.getArtefato().getPkServico());
								
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
	
	/*public int alterar(ComunidadeArtefato membroArtefato) throws CadastroException{
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
	
	public int excluir (ComunidadeArtefato comunidadeArtefato) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from comunidadeartefato where fk_comunidade=? and fk_artefato = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, comunidadeArtefato.getComunidade().getPkServico());
				stmt.setLong(2, comunidadeArtefato.getArtefato().getPkServico());
				
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
	
	public ComunidadeArtefato consultar(ComunidadeArtefato comunidadeArtefato) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from comunidade a, servico a1, artefato b, servico b1, comunidadeartefato c," +
						 " categoriacomunidade d, categoriaartefato e"+
						 " where a.pk_comunidade=? and b.pk_artefato=? and a.pk_comunidade=a1.pk_servico and b.pk_artefato=b1.pk_servico"+
						 " and a.pk_comunidade=c.fk_comunidade and b.pk_artefato=c.fk_artefato" +
						 " and d.pk_catComunidade=a.fk_catComunidade and e.pk_catArtefato=b.fk_catArtefato";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, comunidadeArtefato.getComunidade().getPkServico());
			stmt.setLong(2, comunidadeArtefato.getArtefato().getPkServico());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, comunidadeArtefato);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return comunidadeArtefato;
	}
	
	public Collection<ComunidadeArtefato> consultarPorComunidade(Comunidade comunidade) throws CadastroException{
		Collection<ComunidadeArtefato> col = new ArrayList<ComunidadeArtefato>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from comunidade a, servico a1, artefato b, servico b1, comunidadeartefato c," +
						 " categoriacomunidade d, categoriaartefato e"+
						 " where a.pk_comunidade=? and a.pk_comunidade=a1.pk_servico and b.pk_artefato=b1.pk_servico"+
						 " and a.pk_comunidade=c.fk_comunidade and b.pk_artefato=c.fk_artefato" +
						 " and d.pk_catComunidade=a.fk_catComunidade and e.pk_catArtefato=b.fk_catArtefato";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, comunidade.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				ComunidadeArtefato membroArtefato = new ComunidadeArtefato();
				carregar(rs, membroArtefato);
				col.add(membroArtefato);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	public Collection<ComunidadeArtefato> consultarPorArtefato(Artefato artefato) throws CadastroException{
		Collection<ComunidadeArtefato> col = new ArrayList<ComunidadeArtefato>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from comunidade a, servico a1, artefato b, servico b1, comunidadeartefato c," +
						 " categoriacomunidade d, categoriaartefato e"+
						 " where b.pk_artefato=? and a.pk_comunidade=a1.pk_servico and b.pk_artefato=b1.pk_servico"+
						 " and a.pk_comunidade=c.fk_comunidade and b.pk_artefato=c.fk_artefato" +
						 " and d.pk_catComunidade=a.fk_catComunidade and e.pk_catArtefato=b.fk_catArtefato";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, artefato.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				ComunidadeArtefato membroArtefato = new ComunidadeArtefato();
				carregar(rs, membroArtefato);
				col.add(membroArtefato);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,ComunidadeArtefato comunidadeArtefato) throws SQLException{
		//carrega servico
		comunidadeArtefato.getComunidade().setPkServico(rs.getLong("a1.pk_servico"));
		comunidadeArtefato.getComunidade().setNome(rs.getString("a1.no_servico"));
		comunidadeArtefato.getComunidade().setData(rs.getString("a1.dt_servico"));
		comunidadeArtefato.getComunidade().setDescricao(rs.getString("a1.ds_servico"));
		comunidadeArtefato.getComunidade().setImagem(rs.getString("a1.no_imagem"));
		comunidadeArtefato.getComunidade().setStatus(rs.getString("a1.st_servico"));
		comunidadeArtefato.getComunidade().setAutomatico(rs.getString("a1.fl_automatico"));
		comunidadeArtefato.getComunidade().getMembro().setPkUsuario(rs.getLong("a1.fk_membro"));
		comunidadeArtefato.getComunidade().getAmbiente().setPkAmbiente(rs.getLong("a1.fk_ambiente"));
		
		//carrega categoriaComunidade
		comunidadeArtefato.getComunidade().getCategoriaComunidade().setPkCatComunidade(rs.getLong("d.pk_catComunidade"));
		comunidadeArtefato.getComunidade().getCategoriaComunidade().setNome(rs.getString("d.no_catComunidade"));
		comunidadeArtefato.getComunidade().getCategoriaComunidade().setDescricao(rs.getString("d.ds_catComunidade"));
		
		//carrega servico
		comunidadeArtefato.getArtefato().setPkServico(rs.getLong("b1.pk_servico"));
		comunidadeArtefato.getArtefato().setNome(rs.getString("b1.no_servico"));
		comunidadeArtefato.getArtefato().setData(rs.getString("b1.dt_servico"));
		comunidadeArtefato.getArtefato().setDescricao(rs.getString("b1.ds_servico"));
		comunidadeArtefato.getArtefato().setImagem(rs.getString("b1.no_imagem"));
		comunidadeArtefato.getArtefato().setStatus(rs.getString("b1.st_servico"));
		comunidadeArtefato.getArtefato().setAutomatico(rs.getString("b1.fl_automatico"));
		comunidadeArtefato.getArtefato().getMembro().setPkUsuario(rs.getLong("b1.fk_membro"));
		comunidadeArtefato.getArtefato().getAmbiente().setPkAmbiente(rs.getLong("b1.fk_ambiente"));
		
		//carrega categoriaArtefato
		comunidadeArtefato.getArtefato().getCategoriaArtefato().setPkCatArtefato(rs.getLong("e.pk_catArtefato"));
		comunidadeArtefato.getArtefato().getCategoriaArtefato().setNome(rs.getString("e.no_catArtefato"));
		comunidadeArtefato.getArtefato().getCategoriaArtefato().setDescricao(rs.getString("e.ds_catArtefato"));
		
		//carrega artefato
		comunidadeArtefato.getArtefato().setAutor(rs.getString("b.no_autor"));
		comunidadeArtefato.getArtefato().setAnoPublicacao(rs.getLong("b.nu_anoPublicacao"));
	}
}
