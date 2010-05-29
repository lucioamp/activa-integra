package dao;

import interfaces.CursoExtensaoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.CursoExtensao;
import modelo.Membro;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class CursoExtensaoDAO implements CursoExtensaoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (CursoExtensao cursoExtensao) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into cursoextensao (pk_cursoExtensao,no_cursoExtensao,ds_cursoExtensao, fk_membro) values (?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "cursoExtensao", "pk_cursoExtensao");
				
				stmt.setLong(1,pk);
				stmt.setString(2,cursoExtensao.getNome());
				stmt.setString(3, cursoExtensao.getDescricao());
				stmt.setLong(4, cursoExtensao.getMembro().getPkUsuario());
				
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
	
	public int alterar(CursoExtensao cursoExtensao) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update cursoextensao set no_cursoExtensao=?, ds_cursoExtensao=?, fk_membro=? where pk_cursoExtensao=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cursoExtensao.getNome());
			stmt.setString(2, cursoExtensao.getDescricao());
			stmt.setLong(3, cursoExtensao.getMembro().getPkUsuario());
			stmt.setLong(4, cursoExtensao.getPkCursoExtensao());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (CursoExtensao cursoExtensao) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from cursoextensao where pk_cursoExtensao = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, cursoExtensao.getPkCursoExtensao());
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
	
	public Collection<CursoExtensao> consultarTodos() throws CadastroException{
		Collection<CursoExtensao> col = new ArrayList<CursoExtensao>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from cursoextensao, membro, usuario, formacaoacademica " +
						" where pk_membro=fk_membro and pk_usuario=pk_membro " +
						" and pk_formacaoAcademica = fk_formacaoAcademica";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					CursoExtensao cursoExtensao = new CursoExtensao();
					carregar(rs, cursoExtensao);
					col.add(cursoExtensao);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public CursoExtensao consultar(CursoExtensao cursoExtensao) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from cursoextensao, membro, usuario, formacaoacademica " +
					" where pk_cursoExtensao=? and pk_membro=fk_membro and pk_usuario=pk_membro" +
					" and pk_formacaoAcademica = fk_formacaoAcademica";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, cursoExtensao.getPkCursoExtensao());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, cursoExtensao);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return cursoExtensao;
	}
	
	public Collection<CursoExtensao> consultarPorMembro(Membro membro) throws CadastroException{
		Collection<CursoExtensao> col = new ArrayList<CursoExtensao>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from cursoextensao, membro, usuario, formacaoacademica " +
						" where pk_membro=? and pk_membro=fk_membro and pk_usuario=pk_membro " +
						" and pk_formacaoAcademica = fk_formacaoAcademica";
			
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, membro.getPkUsuario());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					CursoExtensao cursoExtensao = new CursoExtensao();
					carregar(rs, cursoExtensao);
					col.add(cursoExtensao);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	private void carregar(ResultSet rs ,CursoExtensao cursoExtensao) throws SQLException{
		cursoExtensao.getMembro().getBairro().setPkBairro(rs.getLong("fk_bairro"));
		
		//formacaoAcademica
		cursoExtensao.getMembro().getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		cursoExtensao.getMembro().getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		cursoExtensao.getMembro().getInstituicao().setPkInstituicao(rs.getLong("fk_instituicao"));
		
		//carrega usuario
		cursoExtensao.getMembro().setPkUsuario(rs.getLong("pk_usuario"));
		
		//cursoExtensao
		cursoExtensao.setPkCursoExtensao(rs.getLong("pk_cursoExtensao"));
		cursoExtensao.setNome(rs.getString("no_cursoExtensao"));
		cursoExtensao.setDescricao(rs.getString("ds_cursoExtensao"));
	}
}
