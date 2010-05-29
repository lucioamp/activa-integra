package dao;

import interfaces.MensagemI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.Mensagem;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class MensagemDAO implements MensagemI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Mensagem mensagem) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into mensagem (pk_mensagem,ds_assunto,ds_mensagem,fk_membroOrigem, fk_membroDestino) values (?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "mensagem", "pk_mensagem");
				
				stmt.setLong(1,pk);
				stmt.setString(2,mensagem.getAssunto());
				stmt.setString(3,mensagem.getMensagem());
				stmt.setLong(4,mensagem.getMembroOrigem().getPkUsuario());
				stmt.setLong(5,mensagem.getMembroDestino().getPkUsuario());
				
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
	
	public int alterar(Mensagem mensagem) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update mensagem set ds_assunto=?,ds_mensagem=?,fk_membroOrigem=?,fk_membroDestino=? where pk_mensagem=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mensagem.getAssunto());
			stmt.setString(2, mensagem.getMensagem());
			stmt.setLong(3, mensagem.getMembroOrigem().getPkUsuario());
			stmt.setLong(4, mensagem.getMembroDestino().getPkUsuario());
			stmt.setLong(5, mensagem.getPkMensagem());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Mensagem mensagem) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from mensagem where pk_mensagem = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, mensagem.getPkMensagem());
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
	
	public Collection<Mensagem> consultarEnviadas(Membro membro) throws CadastroException{
		Collection<Mensagem> col = new ArrayList<Mensagem>();
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from mensagem where fk_membroOrigem = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultarTodas :" +stmt );
			rs = stmt.executeQuery();
			
			while (rs.next()){
				Mensagem mensagem = new Mensagem();
				carregar(rs, mensagem);
				col.add(mensagem);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
	return col;
	}
	
	public Collection<Mensagem> consultarRecebidas(Membro membro) throws CadastroException{
		Collection<Mensagem> col = new ArrayList<Mensagem>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from mensagem where fk_membroDestino = ?";
				
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, membro.getPkUsuario());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Mensagem mensagem = new Mensagem();
					carregar(rs, mensagem);
					col.add(mensagem);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Mensagem consultar(Mensagem mensagem) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from mensagem m, membro a1, usuario a2, bairro a3, municipio a4, uf a5,"+
					     " formacaoacademica a6, instituicao a7, membro b1, usuario b2, bairro b3, " +
					     " municipio b4, uf b5, formacaoacademica b6, instituicao b7"+
						 " where m.pk_mensagem=? and a2.fk_bairro=a3.pk_bairro and a3.fk_municipio=a4.pk_municipio"+
						 " and a4.fk_estado=a5.pk_estado and a1.pk_membro=a2.pk_usuario and a6.pk_formacaoAcademica = a1.fk_formacaoAcademica"+
						 " and a2.fk_instituicao=a7.pk_instituicao"+
						 " and b2.fk_bairro=b3.pk_bairro and b3.fk_municipio=b4.pk_municipio"+
						 " and b4.fk_estado=b5.pk_estado and b1.pk_membro=b2.pk_usuario and b6.pk_formacaoAcademica = b1.fk_formacaoAcademica"+
						 " and b2.fk_instituicao=b7.pk_instituicao";
			
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, mensagem.getPkMensagem());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, mensagem);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return mensagem;
	}
	
	private void carregar(ResultSet rs ,Mensagem mensagem) throws SQLException{
		
		//carrega mensagem
		mensagem.setPkMensagem(rs.getLong("pk_mensagem"));
		mensagem.setAssunto(rs.getString("ds_assunto"));
		mensagem.setMensagem(rs.getString("ds_mensagem"));
		
		mensagem.getMembroDestino().setPkUsuario(rs.getLong("fk_membroDestino"));
		mensagem.getMembroOrigem().setPkUsuario(rs.getLong("fk_membroOrigem"));
		try {
			if(rs.getLong("fk_membroOrigem") > 0)
				mensagem.getMembroOrigem().consultar();			
			if(rs.getLong("fk_membroDestino") > 0)
				mensagem.getMembroDestino().consultar();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
