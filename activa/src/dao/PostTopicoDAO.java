package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Forum;
import modelo.Membro;
import modelo.PostTopico;
import modelo.Topico;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;
import interfaces.PostTopicoI;

public class PostTopicoDAO implements PostTopicoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (PostTopico postTopico) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into posttopico (pk_postTopico,ds_post,dt_post,fk_topico,fk_membro) values (?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "posttopico", "pk_postTopico");
				
				stmt.setLong(1,pk);
				stmt.setString(2,postTopico.getDescricao());
				stmt.setString(3,postTopico.getData());
				stmt.setLong(4, postTopico.getTopico().getPkTopico());
				stmt.setLong(5, postTopico.getMembro().getPkUsuario());		
				
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
	
	public int alterar(PostTopico postTopico) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update posttopico set ds_post=?,dt_post=?,fk_topico=?,fk_membro=? where pk_postTopico=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,postTopico.getDescricao());
			stmt.setString(2,postTopico.getData());
			stmt.setLong(3, postTopico.getTopico().getPkTopico());
			stmt.setLong(4, postTopico.getMembro().getPkUsuario());	
			stmt.setLong(5, postTopico.getPkPostTopico());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (PostTopico postTopico) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from posttopico where pk_postTopico = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, postTopico.getPkPostTopico());
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
	
	public Collection<PostTopico> consultarTodos() throws CadastroException{
		Collection<PostTopico> col = new ArrayList<PostTopico>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from forum a,categoriaforum b,etapa c,meta d,ambiente e, tarefa f, servico g, topico h, posttopico i, usuario j"+
							" where g.fk_ambiente=e.pk_ambiente and c.fk_meta=d.pk_meta and b.fk_etapa=c.pk_etapa"+
							" and a.fk_catForum=b.pk_catForum and f.pk_tarefa=a.fk_tarefa and a.pk_forum=g.pk_servico " +
							" and a.pk_forum=h.fk_forum and h.pk_topico=i.fk_topico and j.pk_usuario=i.fk_membro";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					PostTopico postTopico = new PostTopico();
					carregar(rs, postTopico);
					col.add(postTopico);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<PostTopico> consultarPorTopico(Topico topico) throws CadastroException{
		Collection<PostTopico> col = new ArrayList<PostTopico>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from forum a,categoriaforum b,etapa c,meta d,ambiente e, tarefa f, servico g, topico h, posttopico i, usuario j"+
							" where h.pk_topico=? and g.fk_ambiente=e.pk_ambiente and c.fk_meta=d.pk_meta and b.fk_etapa=c.pk_etapa"+
							" and a.fk_catForum=b.pk_catForum and f.pk_tarefa=a.fk_tarefa and a.pk_forum=g.pk_servico " +
							" and a.pk_forum=h.fk_forum and h.pk_topico=i.fk_topico and j.pk_usuario=i.fk_membro";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, topico.getPkTopico());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					PostTopico postTopico = new PostTopico();
					carregar(rs, postTopico);
					col.add(postTopico);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public PostTopico consultar(PostTopico postTopico) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from forum a,categoriaforum b,etapa c,meta d,ambiente e, tarefa f, servico g, topico h, posttopico i, usuario j"+
					" where i.pk_postTopico=? and g.fk_ambiente=e.pk_ambiente and c.fk_meta=d.pk_meta and b.fk_etapa=c.pk_etapa"+
					" and a.fk_catForum=b.pk_catForum and f.pk_tarefa=a.fk_tarefa and a.pk_forum=g.pk_servico " +
					" and a.pk_forum=h.fk_forum and h.pk_topico=i.fk_topico and j.pk_usuario=i.fk_membro";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, postTopico.getPkPostTopico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, postTopico);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return postTopico;
	}
	
	private void carregar(ResultSet rs ,PostTopico postTopico) throws SQLException{
		//carrega meta
		Forum forum = new Forum();
		forum.setPkServico(rs.getLong("g.pk_servico"));
		
		Membro membro = new Membro();
		membro.setPkUsuario(rs.getLong("j.pk_usuario"));
		
		
		try {
			membro.consultar();
			forum.consultar();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		postTopico.getTopico().setForum(forum);
		postTopico.setMembro(membro);
		
		//carrega topico
		postTopico.getTopico().setPkTopico(rs.getLong("h.pk_topico"));
		postTopico.getTopico().setDescricao(rs.getString("h.ds_topico"));
		postTopico.getTopico().setData(rs.getString("h.dt_topico"));
		
		//carrega postTopico
		postTopico.setPkPostTopico(rs.getLong("i.pk_postTopico"));
		postTopico.setDescricao(rs.getString("i.ds_post"));
		postTopico.setData(rs.getString("i.dt_post"));
	}
}
