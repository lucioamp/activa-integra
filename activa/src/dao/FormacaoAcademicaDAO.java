package dao;

import interfaces.FormacaoAcademicaI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.FormacaoAcademica;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class FormacaoAcademicaDAO implements FormacaoAcademicaI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (FormacaoAcademica formacaoAcademica) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into formacaoacademica (pk_formacaoAcademica,no_formacaoAcademica) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "formacaoacademica", "pk_formacaoAcademica");
				
				stmt.setLong(1,pk);
				stmt.setString(2,formacaoAcademica.getNome());
				
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
	
	public int alterar(FormacaoAcademica formacaoAcademica) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update formacaoacademica set no_formacaoAcademica=? where pk_formacaoAcademica=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, formacaoAcademica.getNome());
			stmt.setLong(2, formacaoAcademica.getPkFormacaoAcademica());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (FormacaoAcademica formacaoAcademica) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from formacaoacademica where pk_formacaoAcademica = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, formacaoAcademica.getPkFormacaoAcademica());
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
	
	public Collection<FormacaoAcademica> consultarTodos() throws CadastroException{
		Collection<FormacaoAcademica> col = new ArrayList<FormacaoAcademica>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from formacaoacademica";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					FormacaoAcademica formacaoAcademica = new FormacaoAcademica();
					carregar(rs, formacaoAcademica);
					col.add(formacaoAcademica);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public FormacaoAcademica consultar(FormacaoAcademica formacaoAcademica) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from formacaoacademica where pk_formacaoAcademica=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, formacaoAcademica.getPkFormacaoAcademica());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, formacaoAcademica);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return formacaoAcademica;
	}
	
	private void carregar(ResultSet rs ,FormacaoAcademica formacaoAcademica) throws SQLException{
		formacaoAcademica.setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		formacaoAcademica.setNome(rs.getString("no_formacaoAcademica"));	
	}
}
