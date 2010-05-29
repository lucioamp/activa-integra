package dao;

import interfaces.MembroAreaInteresseI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.MembroAreaInteresse;
import util.CadastroException;
import util.ConnectionFactory;

public class MembroAreaInteresseDAO implements MembroAreaInteresseI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (MembroAreaInteresse membroAreaInteresse) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into membroareainteresse (fk_membro, fk_areaInteresse) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,membroAreaInteresse.getMembro().getPkUsuario());
				stmt.setLong(2,membroAreaInteresse.getAreaInteresse().getPkAreaInteresse());
								
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
	
	/*public int alterar(MembroAreaInteresse membroAreaInteresse) throws CadastroException{
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
	
	public int excluir (MembroAreaInteresse membroAreaInteresse) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from membroareainteresse where fk_membro=? and fk_areaInteresse = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, membroAreaInteresse.getMembro().getPkUsuario());
				stmt.setLong(2, membroAreaInteresse.getAreaInteresse().getPkAreaInteresse());
				
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
	
	public MembroAreaInteresse consultar(MembroAreaInteresse membroAreaInteresse) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membroareainteresse, membro, usuario, formacaoacademica, areainteresse " +
					" where and fk_membro=? and fk_areaInteresse=? and fk_membro=pk_membro and fk_areaInteresse=pk_areaInteresse and" +
					" pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membroAreaInteresse.getMembro().getPkUsuario());
			stmt.setLong(2, membroAreaInteresse.getAreaInteresse().getPkAreaInteresse());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, membroAreaInteresse);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return membroAreaInteresse;
	}
	
	public Collection<MembroAreaInteresse> consultarPorMembro(Membro membro) throws CadastroException{
		Collection<MembroAreaInteresse> col = new ArrayList<MembroAreaInteresse>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membroareainteresse, membro, usuario, formacaoacademica, areainteresse " +
					" where fk_membro=? and fk_membro=pk_membro and fk_areaInteresse=pk_areaInteresse and" +
					" pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				MembroAreaInteresse membroAreaInteresse = new MembroAreaInteresse();
				carregar(rs, membroAreaInteresse);
				col.add(membroAreaInteresse);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,MembroAreaInteresse membroAreaInteresse) throws SQLException{
		//carrega bairro
		membroAreaInteresse.getMembro().getBairro().setPkBairro(rs.getLong("fk_bairro"));
		
		//formacaoAcademica
		membroAreaInteresse.getMembro().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		membroAreaInteresse.getMembro().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		membroAreaInteresse.getMembro().getInstituicao().setPkInstituicao(rs.getLong("fk_instituicao"));
		
		//carrega usuario
		membroAreaInteresse.getMembro().setPkUsuario(rs.getLong("pk_usuario"));
		membroAreaInteresse.getMembro().setCpf(rs.getLong("nu_cpf"));
		membroAreaInteresse.getMembro().setNome(rs.getString("no_usuario"));
		membroAreaInteresse.getMembro().setApelido(rs.getString("no_apelido"));
		membroAreaInteresse.getMembro().setDataNasc(rs.getString("dt_nasc"));
		membroAreaInteresse.getMembro().setEmail(rs.getString("no_email"));
		membroAreaInteresse.getMembro().setSenha(rs.getString("pw_senha"));
		membroAreaInteresse.getMembro().setPerguntaChave(rs.getString("ds_perguntaChave"));
		membroAreaInteresse.getMembro().setRespostaChave(rs.getString("ds_respostaChave"));
		membroAreaInteresse.getMembro().setTipoLogradouro(rs.getString("tp_logradouro"));
		membroAreaInteresse.getMembro().setLogradouro(rs.getString("no_logradouro"));
		membroAreaInteresse.getMembro().setNumero(rs.getLong("nu_logradouro"));
		membroAreaInteresse.getMembro().setComplemento(rs.getString("ds_complemento"));
		membroAreaInteresse.getMembro().setCep(rs.getString("nu_cep"));
		membroAreaInteresse.getMembro().setStatus(rs.getString("st_usuario"));
		membroAreaInteresse.getMembro().setImagem(rs.getString("img_usuario"));
		
		//carrega areaInteresse
		membroAreaInteresse.getAreaInteresse().setPkAreaInteresse(rs.getLong("pk_areaInteresse"));
		membroAreaInteresse.getAreaInteresse().setNome(rs.getString("no_areaInteresse"));
		membroAreaInteresse.getAreaInteresse().setDescricao(rs.getString("ds_areaInteresse"));
		
	}
}
