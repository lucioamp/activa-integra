package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Municipio;
import modelo.Uf;

import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

import interfaces.MunicipioI;

public class MunicipioDAO implements MunicipioI {
	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Municipio municipio) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into municipio (pk_municipio,no_municipio,fk_estado) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "municipio", "pk_municipio");
				
				stmt.setLong(1,pk);
				stmt.setString(2,municipio.getMunicipio());
				stmt.setLong(3,municipio.getUf().getPkEstado());
				
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
	
	public int alterar(Municipio municipio) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update municipio set no_municipio=?, fk_estado=? where pk_municipio=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, municipio.getMunicipio());
			stmt.setLong(2,municipio.getUf().getPkEstado());
			stmt.setLong(3,municipio.getPkMunicipio());
			System.out.println(stmt);			
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Municipio municipio) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from municipio where pk_municipio = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, municipio.getPkMunicipio());
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
	
	public Collection<Municipio> consultarTodos() throws CadastroException{
		Collection<Municipio> col = new ArrayList<Municipio>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from municipio, uf where fk_estado = pk_estado order by no_estado, no_municipio";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Municipio municipio = new Municipio();
					carregar(rs, municipio);
					col.add(municipio);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Municipio consultar(Municipio municipio) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from municipio, uf where pk_municipio=? and fk_estado = pk_estado";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, municipio.getPkMunicipio());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, municipio);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return municipio;
	}
	
	public Collection<Municipio> consultarPorUf(Uf uf) throws CadastroException{
		Collection<Municipio> col = new ArrayList<Municipio>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from municipio, uf where pk_estado=? and fk_estado = pk_estado";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, uf.getPkEstado());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				Municipio municipio = new Municipio();
				carregar(rs, municipio);
				col.add(municipio);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,Municipio municipio) throws SQLException{
		//carrega uf
		municipio.getUf().setPkEstado(rs.getLong("pk_estado"));
		municipio.getUf().setEstado(rs.getString("no_estado"));
		municipio.getUf().setSigla(rs.getString("sg_estado")); 
		
		//carrega municipio
		municipio.setPkMunicipio(rs.getLong("pk_municipio"));
		municipio.setMunicipio(rs.getString("no_municipio"));
	}

}
