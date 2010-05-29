package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Aula;
import modelo.ArquivoAula;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;
import interfaces.ArquivoAulaI;

public class ArquivoAulaDAO implements ArquivoAulaI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (ArquivoAula arquivoAula) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into arquivoaula (pk_arquivoAula,no_arquivoAula,fk_aula) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "arquivoAula", "pk_arquivoAula");
				
				stmt.setLong(1,pk);
				stmt.setString(2,arquivoAula.getNome());
				stmt.setLong(3,arquivoAula.getAula().getPkAula());	
								
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
	
	public int alterar(ArquivoAula arquivoAula) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update arquivoaula set no_arquivoAula=?,fk_aula=? where pk_arquivoAula=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,arquivoAula.getNome());
			stmt.setLong(2,arquivoAula.getAula().getPkAula());	
			stmt.setLong(3, arquivoAula.getPkArquivoAula());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (ArquivoAula arquivoAula) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from arquivoaula where pk_arquivoAula = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, arquivoAula.getPkArquivoAula());
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
	
	public Collection<ArquivoAula> consultarTodos() throws CadastroException{
		Collection<ArquivoAula> col = new ArrayList<ArquivoAula>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from arquivoaula";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					ArquivoAula arquivoAula = new ArquivoAula();
					carregar(rs, arquivoAula);
					col.add(arquivoAula);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<ArquivoAula> consultarPorAula(Aula aula) throws CadastroException{
		Collection<ArquivoAula> col = new ArrayList<ArquivoAula>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from arquivoaula where fk_aula=?";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, aula.getPkAula());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					ArquivoAula arquivoAula = new ArquivoAula();
					carregar(rs, arquivoAula);
					col.add(arquivoAula);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}	
	
	public ArquivoAula consultar(ArquivoAula arquivoAula) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from arquivoaula where pk_arquivoAula =? ";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, arquivoAula.getPkArquivoAula());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, arquivoAula);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return arquivoAula;
	}
	
	private void carregar(ResultSet rs ,ArquivoAula arquivoAula) throws SQLException{
		//carrega aula
		if(rs.getLong("fk_aula") > 0)
		{
			arquivoAula.getAula().setPkAula(rs.getLong("fk_aula"));
			try {
				arquivoAula.getAula().consultar();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//carrega arquivoAula
		arquivoAula.setPkArquivoAula(rs.getLong("pk_arquivoAula"));
		arquivoAula.setNome(rs.getString("no_arquivoAula"));
		
	}
}
