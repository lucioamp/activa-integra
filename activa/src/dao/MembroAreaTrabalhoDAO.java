package dao;

import interfaces.MembroAreaTrabalhoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.MembroAreaTrabalho;
import util.CadastroException;
import util.ConnectionFactory;

public class MembroAreaTrabalhoDAO implements MembroAreaTrabalhoI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (MembroAreaTrabalho membroAreaTrabalho) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into membroareatrabalho (fk_membro, fk_areaTrabalho) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,membroAreaTrabalho.getMembro().getPkUsuario());
				stmt.setLong(2,membroAreaTrabalho.getAreaTrabalho().getPkAreaTrabalho());
								
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
	
	/*public int alterar(MembroAreaTrabalho membroAreaTrabalho) throws CadastroException{
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
	
	public int excluir (MembroAreaTrabalho membroAreaTrabalho) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from membroareatrabalho where fk_membro=? and fk_areaTrabalho = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, membroAreaTrabalho.getMembro().getPkUsuario());
				stmt.setLong(2, membroAreaTrabalho.getAreaTrabalho().getPkAreaTrabalho());
				
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
	
	public MembroAreaTrabalho consultar(MembroAreaTrabalho membroAreaTrabalho) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membroareatrabalho, membro, usuario, bairro, municipio, uf, formacaoacademica, areatrabalho, instituicao " +
					" where and fk_membro=? and fk_areaTrabalho=? and fk_membro=pk_membro and fk_areaTrabalho=pk_areaTrabalho " +
					" and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and " +
					" pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membroAreaTrabalho.getMembro().getPkUsuario());
			stmt.setLong(2, membroAreaTrabalho.getAreaTrabalho().getPkAreaTrabalho());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, membroAreaTrabalho);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return membroAreaTrabalho;
	}
	
	public Collection<MembroAreaTrabalho> consultarPorMembro(Membro membro) throws CadastroException{
		Collection<MembroAreaTrabalho> col = new ArrayList<MembroAreaTrabalho>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membroareatrabalho, membro, usuario, bairro, municipio, uf, formacaoacademica, areatrabalho, instituicao " +
					" where fk_membro=? and fk_membro=pk_membro and fk_areaTrabalho=pk_areaTrabalho " +
					" and fk_bairro=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado and " +
					" pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_instituicao=pk_instituicao";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				MembroAreaTrabalho membroAreaTrabalho = new MembroAreaTrabalho();
				carregar(rs, membroAreaTrabalho);
				col.add(membroAreaTrabalho);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,MembroAreaTrabalho membroAreaTrabalho) throws SQLException{
		//carrega uf
		membroAreaTrabalho.getMembro().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		membroAreaTrabalho.getMembro().getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		membroAreaTrabalho.getMembro().getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		membroAreaTrabalho.getMembro().getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		membroAreaTrabalho.getMembro().getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		membroAreaTrabalho.getMembro().getBairro().setPkBairro(rs.getLong("pk_bairro"));
		membroAreaTrabalho.getMembro().getBairro().setBairro(rs.getString("no_bairro"));
		
		//formacaoAcademica
		membroAreaTrabalho.getMembro().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		membroAreaTrabalho.getMembro().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		membroAreaTrabalho.getMembro().getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		membroAreaTrabalho.getMembro().getInstituicao().setNome(rs.getString("no_instituicao"));
		membroAreaTrabalho.getMembro().getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		membroAreaTrabalho.getMembro().getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		membroAreaTrabalho.getMembro().getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		membroAreaTrabalho.getMembro().getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		membroAreaTrabalho.getMembro().getInstituicao().setCep(rs.getString("nu_cepInst"));
		
		//carrega usuario
		membroAreaTrabalho.getMembro().setPkUsuario(rs.getLong("pk_usuario"));
		membroAreaTrabalho.getMembro().setCpf(rs.getLong("nu_cpf"));
		membroAreaTrabalho.getMembro().setNome(rs.getString("no_usuario"));
		membroAreaTrabalho.getMembro().setApelido(rs.getString("no_apelido"));
		membroAreaTrabalho.getMembro().setDataNasc(rs.getString("dt_nasc"));
		membroAreaTrabalho.getMembro().setEmail(rs.getString("no_email"));
		membroAreaTrabalho.getMembro().setSenha(rs.getString("pw_senha"));
		membroAreaTrabalho.getMembro().setPerguntaChave(rs.getString("ds_perguntaChave"));
		membroAreaTrabalho.getMembro().setRespostaChave(rs.getString("ds_respostaChave"));
		membroAreaTrabalho.getMembro().setTipoLogradouro(rs.getString("tp_logradouro"));
		membroAreaTrabalho.getMembro().setLogradouro(rs.getString("no_logradouro"));
		membroAreaTrabalho.getMembro().setNumero(rs.getLong("nu_logradouro"));
		membroAreaTrabalho.getMembro().setComplemento(rs.getString("ds_complemento"));
		membroAreaTrabalho.getMembro().setCep(rs.getString("nu_cep"));
		membroAreaTrabalho.getMembro().setStatus(rs.getString("st_usuario"));
		membroAreaTrabalho.getMembro().setImagem(rs.getString("img_usuario"));
		
		//carrega areaTrabalho
		membroAreaTrabalho.getAreaTrabalho().setPkAreaTrabalho(rs.getLong("pk_areaTrabalho"));
		membroAreaTrabalho.getAreaTrabalho().setNome(rs.getString("no_areaTrabalho"));
		membroAreaTrabalho.getAreaTrabalho().setDescricao(rs.getString("ds_areaTrabalho"));
		
	}
}
