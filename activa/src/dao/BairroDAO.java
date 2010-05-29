package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Bairro;
import modelo.Municipio;

import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

import interfaces.BairroI;

public class BairroDAO implements BairroI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Bairro bairro) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into bairro (pk_bairro,no_bairro,fk_municipio) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "bairro", "pk_bairro");
				
				stmt.setLong(1,pk);
				stmt.setString(2,bairro.getBairro());
				stmt.setLong(3,bairro.getMunicipio().getPkMunicipio());
				
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
	
	public int alterar(Bairro bairro) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update bairro set no_bairro=?, fk_municipio=? where pk_bairro=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bairro.getBairro());
			stmt.setLong(2,bairro.getMunicipio().getPkMunicipio());
			stmt.setLong(3,bairro.getPkBairro());
			System.out.println(stmt);			
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Bairro bairro) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from bairro where pk_bairro = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, bairro.getPkBairro());
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
	
	public Collection<Bairro> consultarTodos() throws CadastroException{
		Collection<Bairro> col = new ArrayList<Bairro>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from bairro, municipio, uf where fk_municipio = pk_municipio and fk_estado = pk_estado order by no_estado, no_municipio, no_bairro";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Bairro bairro = new Bairro();
					carregar(rs, bairro);
					col.add(bairro);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Bairro consultar(Bairro bairro) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from bairro, municipio, uf where pk_bairro=? and fk_municipio = pk_municipio and fk_estado = pk_estado";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, bairro.getPkBairro());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, bairro);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return bairro;
	}
	
	public Collection<Bairro> consultarPorMunicipio(Municipio municipio) throws CadastroException{
		Collection<Bairro> col = new ArrayList<Bairro>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from bairro,municipio, uf where pk_municipio = ? and fk_municipio = pk_municipio and fk_estado = pk_estado";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, municipio.getPkMunicipio());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				Bairro bairro = new Bairro();
				carregar(rs, bairro);
				col.add(bairro);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,Bairro bairro) throws SQLException{
		//carrega uf
		bairro.getMunicipio().getUf().setPkEstado(rs.getLong("pk_estado"));
		bairro.getMunicipio().getUf().setEstado(rs.getString("no_estado"));
		bairro.getMunicipio().getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		bairro.getMunicipio().setPkMunicipio(rs.getLong("pk_municipio"));
		bairro.getMunicipio().setMunicipio(rs.getString("no_municipio"));
		
		//carrega bairro
		bairro.setPkBairro(rs.getLong("pk_bairro"));
		bairro.setBairro(rs.getString("no_bairro"));
	}

}
