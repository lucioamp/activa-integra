package dao;

import interfaces.MembroAmbienteI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

//import modelo.Membro;
import modelo.MembroAmbiente;
import modelo.Usuario;
import util.CadastroException;
import util.ConnectionFactory;

public class MembroAmbienteDAO implements MembroAmbienteI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (MembroAmbiente membroAmbiente) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into membroambiente (fk_membro, fk_ambiente) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,membroAmbiente.getMembro().getPkUsuario());
				stmt.setLong(2,membroAmbiente.getAmbiente().getPkAmbiente());
								
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
	
	/*public int alterar(MembroAmbiente membroAmbiente) throws CadastroException{
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
	
	public int excluir (MembroAmbiente membroAmbiente) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from membroambiente where fk_membro=? and fk_ambiente = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, membroAmbiente.getMembro().getPkUsuario());
				stmt.setLong(2, membroAmbiente.getAmbiente().getPkAmbiente());
				
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
	
	public MembroAmbiente consultar(MembroAmbiente membroAmbiente) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membroambiente, membro, ambiente " +
					" where and fk_membro=? and fk_ambiente=? and fk_membro=pk_membro and fk_ambiente=pk_ambiente";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membroAmbiente.getMembro().getPkUsuario());
			stmt.setLong(2, membroAmbiente.getAmbiente().getPkAmbiente());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, membroAmbiente);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return membroAmbiente;
	}
	
	public Collection<MembroAmbiente> consultarPorMembro(Usuario membro) throws CadastroException{
		Collection<MembroAmbiente> col = new ArrayList<MembroAmbiente>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membroambiente, membro, ambiente " +
					" where fk_membro=? and fk_ambiente=pk_ambiente and pk_membro=fk_membro";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				MembroAmbiente membroAmbiente = new MembroAmbiente();
				carregar(rs, membroAmbiente);
				col.add(membroAmbiente);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,MembroAmbiente membroAmbiente) throws SQLException{
		if(rs.getLong("fk_membro") > 0)
		{
			membroAmbiente.getMembro().setPkUsuario(rs.getLong("fk_membro"));
			try {
				membroAmbiente.getMembro().consultar();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(rs.getLong("fk_ambiente") > 0)
		{
			membroAmbiente.getAmbiente().setPkAmbiente(rs.getLong("fk_ambiente"));
			try {
				membroAmbiente.getAmbiente().consultar();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
}
