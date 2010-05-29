package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Ambiente;
import modelo.Artefato;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.ArtefatoI;

public class ArtefatoDAO implements ArtefatoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Artefato artefato) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into artefato (pk_artefato,no_autor,nu_anoPublicacao,fk_catArtefato) values (?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = (int) artefato.getPkServico();
				
				stmt.setLong(1,pk);
				stmt.setString(2,artefato.getAutor());
				stmt.setLong(3,artefato.getAnoPublicacao());		
				stmt.setLong(4,artefato.getCategoriaArtefato().getPkCatArtefato());			
				
				System.out.println(stmt);
				stmt.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return pk;
	}
	
	public int alterar(Artefato artefato) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update artefato set no_autor=?,nu_anoPublicacao=?,fk_catArtefato=? where pk_artefato=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,artefato.getAutor());
			stmt.setLong(2,artefato.getAnoPublicacao());		
			stmt.setLong(3,artefato.getCategoriaArtefato().getPkCatArtefato());
			stmt.setLong(4, artefato.getPkServico());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Artefato artefato) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from artefato where pk_artefato = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, artefato.getPkServico());
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
	
	public Collection<Artefato> consultarTodosArtefatos() throws CadastroException{
		Collection<Artefato> col = new ArrayList<Artefato>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from artefato a,ambiente b,servico c,usuario d, categoriaartefato e" +
						" where c.fk_ambiente=b.pk_ambiente and a.pk_artefato=c.pk_servico and " +
						" c.fk_membro=d.pk_usuario and a.fk_catArtefato=e.pk_catArtefato";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Artefato artefato = new Artefato();
					carregar(rs, artefato);
					col.add(artefato);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Artefato> consultarArtefatosPorAmbiente(Ambiente ambiente) throws CadastroException{
		Collection<Artefato> col = new ArrayList<Artefato>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from artefato a,ambiente b,servico c,usuario d, categoriaartefato e" +
						" where b.pk_ambiente=? and c.fk_ambiente=b.pk_ambiente and a.pk_artefato=c.pk_servico " +
						" and c.fk_membro=d.pk_usuario and a.fk_catArtefato=e.pk_catArtefato";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, ambiente.getPkAmbiente());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Artefato artefato = new Artefato();
					carregar(rs, artefato);
					col.add(artefato);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}	
	
	public Artefato consultar(Artefato artefato) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from artefato a,ambiente b,servico c,usuario d, categoriaartefato e" +
					" where a.pk_artefato=? and c.fk_ambiente=b.pk_ambiente and a.pk_artefato=a.pk_servico " +
					" and c.fk_membro=d.pk_usuario and a.fk_catArtefato=e.pk_catArtefato";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, artefato.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, artefato);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return artefato;
	}
	
	private void carregar(ResultSet rs ,Artefato artefato) throws SQLException{
		//carrega ambiente
		artefato.getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		artefato.getAmbiente().setNome(rs.getString("b.no_ambiente"));
		artefato.getAmbiente().setData(rs.getString("b.dt_ambiente"));
		artefato.getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		artefato.getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		artefato.setPkServico(rs.getLong("c.pk_servico"));
		artefato.setNome(rs.getString("c.no_servico"));
		artefato.setData(rs.getString("c.dt_servico"));
		artefato.setDescricao(rs.getString("c.ds_servico"));
		artefato.setImagem(rs.getString("c.no_imagem"));
		artefato.setStatus(rs.getString("c.st_servico"));
		artefato.setAutomatico(rs.getString("c.fl_automatico"));
		artefato.getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		artefato.getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		artefato.getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		artefato.getMembro().setCpf(rs.getLong("d.nu_cpf"));
		artefato.getMembro().setNome(rs.getString("d.no_usuario"));
		artefato.getMembro().setApelido(rs.getString("d.no_apelido"));
		artefato.getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		artefato.getMembro().setEmail(rs.getString("d.no_email"));
		artefato.getMembro().setSenha(rs.getString("d.pw_senha"));
		artefato.getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		artefato.getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		artefato.getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		artefato.getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		artefato.getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		artefato.getMembro().setComplemento(rs.getString("d.ds_complemento"));
		artefato.getMembro().setCep(rs.getString("d.nu_cep"));
		artefato.getMembro().setStatus(rs.getString("d.st_usuario"));
		artefato.getMembro().setImagem(rs.getString("d.img_usuario"));
		
		//carrega categoriaArtefato
		artefato.getCategoriaArtefato().setPkCatArtefato(rs.getLong("e.pk_catArtefato"));
		artefato.getCategoriaArtefato().setNome(rs.getString("e.no_catArtefato"));
		artefato.getCategoriaArtefato().setDescricao(rs.getString("e.ds_catArtefato"));
		
		//carrega artefato
		artefato.setAutor(rs.getString("a.no_autor"));
		artefato.setAnoPublicacao(rs.getLong("a.nu_anoPublicacao"));
	}
}
