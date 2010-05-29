package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Instituicao;

import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

import interfaces.InstituicaoI;

public class InstituicaoDAO implements InstituicaoI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Instituicao instituicao) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into instituicao (pk_instituicao, no_instituicao, tp_logradouroInst, no_logradouroInst, nu_logradouroInst, ds_complementoInst, nu_cepInst, fk_bairroInst) values (?,?,?,?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "instituicao", "pk_instituicao");
				
				stmt.setLong(1,pk);
				stmt.setString(2,instituicao.getNome());
				stmt.setString(3,instituicao.getTipoLogradouro());
				stmt.setString(4,instituicao.getLogradouro());
				stmt.setLong(5,instituicao.getNumero());
				stmt.setString(6,instituicao.getComplemento());
				stmt.setString(7,instituicao.getCep());
				stmt.setLong(8,instituicao.getBairro().getPkBairro());
				
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
	
	public int alterar(Instituicao instituicao) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update instituicao set no_instituicao=?, tp_logradouroInst=?, no_logradouroInst=?, nu_logradouroInst=?, ds_complementoInst=?, nu_cepInst=?, fk_bairroInst=? where pk_instituicao=?";
			
			stmt = conn.prepareStatement(sql);
						
			stmt.setString(1,instituicao.getNome());
			stmt.setString(2,instituicao.getTipoLogradouro());
			stmt.setString(3,instituicao.getLogradouro());
			stmt.setLong(4,instituicao.getNumero());
			stmt.setString(5,instituicao.getComplemento());
			stmt.setString(6,instituicao.getCep());
			stmt.setLong(7,instituicao.getBairro().getPkBairro());
			stmt.setLong(8,instituicao.getPkInstituicao());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Instituicao instituicao) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from instituicao where pk_instituicao = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, instituicao.getPkInstituicao());
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
	
	public Collection<Instituicao> consultarTodos() throws CadastroException{
		Collection<Instituicao> col = new ArrayList<Instituicao>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from instituicao, bairro, municipio, uf where fk_bairroInst=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Instituicao instituicao = new Instituicao();
					carregar(rs, instituicao);
					col.add(instituicao);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Instituicao consultar(Instituicao instituicao) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from instituicao, bairro, municipio, uf where pk_instituicao=? and fk_bairroInst=pk_bairro and fk_municipio=pk_municipio and fk_estado=pk_estado";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, instituicao.getPkInstituicao());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, instituicao);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return instituicao;
	}
	
	private void carregar(ResultSet rs ,Instituicao instituicao) throws SQLException{
		//carrega uf
		instituicao.getBairro().getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		instituicao.getBairro().getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		instituicao.getBairro().getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		instituicao.getBairro().getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		instituicao.getBairro().getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		instituicao.getBairro().setPkBairro(rs.getLong("pk_bairro"));
		instituicao.getBairro().setBairro(rs.getString("no_bairro"));
		
		//carrega instituicao
		instituicao.setPkInstituicao(rs.getLong("pk_instituicao"));
		instituicao.setNome(rs.getString("no_instituicao"));
		instituicao.setTipoLogradouro(rs.getString("tp_logradouroInst"));
		instituicao.setLogradouro(rs.getString("no_logradouroInst"));
		instituicao.setNumero(rs.getLong("nu_logradouroInst"));
		instituicao.setComplemento(rs.getString("ds_complementoInst"));
		instituicao.setCep(rs.getString("nu_cepInst"));
	}

}
