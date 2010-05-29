package dao;

import interfaces.AmbienteI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Ambiente;
import modelo.Membro;
import modelo.Usuario;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class AmbienteDAO implements AmbienteI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Ambiente ambiente) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into ambiente (pk_ambiente,no_ambiente,dt_ambiente,ds_ambiente,no_imagem,fk_professor) values (?,?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "ambiente", "pk_ambiente");
				
				stmt.setLong(1,pk);
				stmt.setString(2,ambiente.getNome());
				stmt.setString(3,ambiente.getData());
				stmt.setString(4, ambiente.getDescricao());
				stmt.setString(5,ambiente.getImagem());
				stmt.setLong(6,ambiente.getProfessor().getPkUsuario());
				
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
	
	public int alterar(Ambiente ambiente) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update ambiente set no_ambiente=?,dt_ambiente=?,ds_ambiente=?,no_imagem=?,fk_professor=? where pk_ambiente=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,ambiente.getNome());
			stmt.setString(2,ambiente.getData());
			stmt.setString(3, ambiente.getDescricao());
			stmt.setString(4,ambiente.getImagem());
			stmt.setLong(5,ambiente.getProfessor().getPkUsuario());
			stmt.setLong(6, ambiente.getPkAmbiente());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int alterarImagem(Ambiente ambiente) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update ambiente set no_imagem=? where pk_ambiente=?";
			
			stmt = conn.prepareStatement(sql);
							

			stmt.setString(1,ambiente.getImagem());
			stmt.setLong(2,ambiente.getPkAmbiente());
			
			System.out.println("altera:"+stmt);
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Ambiente ambiente) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from ambiente where pk_ambiente = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, ambiente.getPkAmbiente());
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
	
	public Collection<Ambiente> consultarTodos() throws CadastroException{
		Collection<Ambiente> col = new ArrayList<Ambiente>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from ambiente,membro,formacaoacademica where pk_membro = fk_professor and pk_formacaoAcademica = fk_formacaoAcademica";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Ambiente ambiente = new Ambiente();
					carregar(rs, ambiente);
					col.add(ambiente);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Ambiente> consultarTodosMenos(Usuario usuario) throws CadastroException{
		Collection<Ambiente> col = new ArrayList<Ambiente>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from ambiente,membro,formacaoacademica where pk_membro = fk_professor and pk_formacaoAcademica = fk_formacaoAcademica and pk_ambiente not in(select fk_ambiente from membroambiente where fk_membro = ?)";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, usuario.getPkUsuario());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Ambiente ambiente = new Ambiente();
					carregar(rs, ambiente);
					col.add(ambiente);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Ambiente consultar(Ambiente ambiente) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from ambiente, membro, usuario, formacaoacademica where " +
			" pk_ambiente=? and pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica and fk_professor=pk_membro";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, ambiente.getPkAmbiente());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, ambiente);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return ambiente;
	}
	
	private void carregar(ResultSet rs ,Ambiente ambiente) throws SQLException{
		/*//carrega uf
		ambiente.getProfessor().getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		ambiente.getProfessor().getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		ambiente.getProfessor().getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		ambiente.getProfessor().getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		ambiente.getProfessor().getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		ambiente.getProfessor().getBairro().setPkBairro(rs.getLong("pk_bairro"));
		ambiente.getProfessor().getBairro().setBairro(rs.getString("no_bairro"));*/
		
		//formacaoAcademica
		ambiente.getProfessor().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		ambiente.getProfessor().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		/*//carrega instituicao
		ambiente.getProfessor().getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		ambiente.getProfessor().getInstituicao().setNome(rs.getString("no_instituicao"));
		ambiente.getProfessor().getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		ambiente.getProfessor().getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		ambiente.getProfessor().getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		ambiente.getProfessor().getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		ambiente.getProfessor().getInstituicao().setCep(rs.getString("nu_cepInst"));
		*/
		
		//carrega usuario
		ambiente.getProfessor().setPkUsuario(rs.getLong("fk_professor"));
		/*ambiente.getProfessor().setCpf(rs.getLong("nu_cpf"));
		ambiente.getProfessor().setNome(rs.getString("no_usuario"));
		ambiente.getProfessor().setApelido(rs.getString("no_apelido"));
		ambiente.getProfessor().setDataNasc(rs.getString("dt_nasc"));
		ambiente.getProfessor().setEmail(rs.getString("no_email"));
		ambiente.getProfessor().setSenha(rs.getString("pw_senha"));
		ambiente.getProfessor().setPerguntaChave(rs.getString("ds_perguntaChave"));
		ambiente.getProfessor().setRespostaChave(rs.getString("ds_respostaChave"));
		ambiente.getProfessor().setTipoLogradouro(rs.getString("tp_logradouro"));
		ambiente.getProfessor().setLogradouro(rs.getString("no_logradouro"));
		ambiente.getProfessor().setNumero(rs.getLong("nu_logradouro"));
		ambiente.getProfessor().setComplemento(rs.getString("ds_complemento"));
		ambiente.getProfessor().setCep(rs.getString("nu_cep"));
		ambiente.getProfessor().setStatus(rs.getString("st_usuario"));
		ambiente.getProfessor().setImagem(rs.getString("img_usuario"));*/
		
		//carrega ambiente
		ambiente.setPkAmbiente(rs.getLong("pk_ambiente"));
		ambiente.setNome(rs.getString("no_ambiente"));
		ambiente.setData(rs.getString("dt_ambiente"));
		ambiente.setDescricao(rs.getString("ds_ambiente"));
		ambiente.setImagem(rs.getString("no_imagem"));
	}

	@Override
	public Collection<Membro> consultarMembros(Ambiente ambiente) throws CadastroException {
		Collection<Membro> col = new ArrayList<Membro>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membroambiente where fk_ambiente = ?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, ambiente.getPkAmbiente());
			
			System.out.println("UfDAO Consultar consultarTodas :" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				Membro membro = new Membro();
				membro.setPkUsuario(rs.getLong("fk_membro"));
				membro.consultar();
				col.add(membro);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
}
