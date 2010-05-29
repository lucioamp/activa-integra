package dao;

import interfaces.ContatoInstituicaoI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Instituicao;
import modelo.ContatoInstituicao;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class ContatoInstituicaoDAO implements ContatoInstituicaoI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (ContatoInstituicao contatoInstituicao) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into contatoinstituicao (pk_contatoInstituicao,fk_tipoContato, fk_instituicao,ds_contato) values (?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "contatoinstituicao", "pk_contatoInstituicao");
				
				stmt.setLong(1,pk);
				stmt.setLong(2,contatoInstituicao.getTipoContato().getPkTipoContato());
				stmt.setLong(3,contatoInstituicao.getInstituicao().getPkInstituicao());
				stmt.setString(4, contatoInstituicao.getContato());
				
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
	
	public int alterar(ContatoInstituicao contatoInstituicao) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update contatoinstituicao set ds_contato=?, fk_tipoContato=?, fk_instituicao=? where pk_contatoInstituicao=?";
			
			System.out.println(contatoInstituicao.getPkContatoInstituicao()+"<--");
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, contatoInstituicao.getContato());
			stmt.setLong(2, contatoInstituicao.getTipoContato().getPkTipoContato());
			stmt.setLong(3, contatoInstituicao.getInstituicao().getPkInstituicao());
			stmt.setLong(4, contatoInstituicao.getPkContatoInstituicao());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (ContatoInstituicao contatoInstituicao) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from contatoinstituicao where pk_contatoInstituicao=?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, contatoInstituicao.getPkContatoInstituicao());
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
	
	public Collection<ContatoInstituicao> consultarTodos() throws CadastroException{
		Collection<ContatoInstituicao> col = new ArrayList<ContatoInstituicao>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from contatoinstituicao,tipocontato,instituicao where fk_tipoContato=pk_tipoContato and fk_instituicao=pk_instituicao";
			
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
	}
	
	public ContatoInstituicao consultar(ContatoInstituicao contatoInstituicao) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from contatoinstituicao, tipocontato, instituicao where pk_contatoInstituicao=? and fk_tipoContato=pk_tipoContato and fk_instituicao=pk_instituicao";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, contatoInstituicao.getPkContatoInstituicao());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, contatoInstituicao);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return contatoInstituicao;
	}
	
	public Collection<ContatoInstituicao> consultarPorInstituicao(Instituicao instituicao) throws CadastroException{
		Collection<ContatoInstituicao> col = new ArrayList<ContatoInstituicao>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from contatoinstituicao, tipocontato, instituicao where fk_instituicao=? and fk_tipoContato=pk_tipoContato and fk_instituicao=pk_instituicao";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, instituicao.getPkInstituicao());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
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
	}
	
	private void carregar(ResultSet rs ,ContatoInstituicao contatoInstituicao) throws SQLException{
		//carrega tipoContato
		contatoInstituicao.getTipoContato().setPkTipoContato(rs.getLong("pk_tipoContato"));
		contatoInstituicao.getTipoContato().setTipoContato(rs.getString("ds_tipoContato"));	
		
		//carrega instituicao
		contatoInstituicao.getInstituicao().setPkInstituicao(rs.getLong("pk_instituicao"));
		contatoInstituicao.getInstituicao().setNome(rs.getString("no_instituicao"));
		contatoInstituicao.getInstituicao().setTipoLogradouro(rs.getString("tp_logradouroInst"));
		contatoInstituicao.getInstituicao().setLogradouro(rs.getString("no_logradouroInst"));
		contatoInstituicao.getInstituicao().setNumero(rs.getLong("nu_logradouroInst"));
		contatoInstituicao.getInstituicao().setComplemento(rs.getString("ds_complementoInst"));
		contatoInstituicao.getInstituicao().setCep(rs.getString("nu_cepInst"));
		
		//carrega contatoInstituicao
		contatoInstituicao.setPkContatoInstituicao(rs.getLong("pk_contatoInstituicao"));
		contatoInstituicao.setContato(rs.getString("ds_contato"));
		
	}
}
