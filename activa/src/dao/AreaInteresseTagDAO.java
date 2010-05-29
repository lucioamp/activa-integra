package dao;

import interfaces.AreaInteresseTagI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.AreaInteresseTag;
import modelo.AreaInteresse;
import util.CadastroException;
import util.ConnectionFactory;

public class AreaInteresseTagDAO implements AreaInteresseTagI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (AreaInteresseTag areaInteresseTag) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into areainteressetag (fk_areaInteresse,fk_tag) values (?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,areaInteresseTag.getAreaInteresse().getPkAreaInteresse());
				stmt.setLong(2,areaInteresseTag.getTag().getPkTag());
				
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
	
	/*public int alterar(ContatoInstituicao contatoInstituicao) throws CadastroException{
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
	
	public int excluir (AreaInteresseTag areaInteresseTag) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from areainteressetag where fk_areaInteresse = ? and fk_tag=?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, areaInteresseTag.getAreaInteresse().getPkAreaInteresse());
				stmt.setLong(2, areaInteresseTag.getTag().getPkTag());
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
	
	public AreaInteresseTag consultar(AreaInteresseTag areaInteresseTag) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from areainteressetag, areainteresse, tag where fk_areaInteresse=? and fk_tag=? and fk_areaInteresse=pk_areaInteresse and fk_tag=pk_tag";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, areaInteresseTag.getAreaInteresse().getPkAreaInteresse());
			stmt.setLong(2, areaInteresseTag.getTag().getPkTag());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, areaInteresseTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return areaInteresseTag;
	}
	
	public Collection<AreaInteresseTag> consultarPorAreaInteresse(AreaInteresse areaInteresse) throws CadastroException{
		Collection<AreaInteresseTag> col = new ArrayList<AreaInteresseTag>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from areainteressetag, areainteresse, tag where fk_areaInteresse=? and fk_areaInteresse=pk_areaInteresse and fk_tag=pk_tag";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, areaInteresse.getPkAreaInteresse());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				AreaInteresseTag areaInteresseTag = new AreaInteresseTag();
				carregar(rs, areaInteresseTag);
				col.add(areaInteresseTag);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return col;
	}
	
	private void carregar(ResultSet rs ,AreaInteresseTag areaInteresseTag) throws SQLException{
		//carrega areaInteresse
		areaInteresseTag.getAreaInteresse().setPkAreaInteresse(rs.getLong("pk_areaInteresse"));
		areaInteresseTag.getAreaInteresse().setNome(rs.getString("no_areaInteresse"));
		areaInteresseTag.getAreaInteresse().setDescricao(rs.getString("ds_areaInteresse"));
		
		//carrega tag
		areaInteresseTag.getTag().setPkTag(rs.getLong("pk_tag"));
		areaInteresseTag.getTag().setNome(rs.getString("no_tag"));
		areaInteresseTag.getTag().setDescricao(rs.getString("ds_tag"));
		
	}
}
