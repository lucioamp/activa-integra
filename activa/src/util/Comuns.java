package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Comuns {

	public static int getMaxId(Connection connection, String nomeTabela, String nomeCampo) {
		PreparedStatement ps = null;
		ResultSet rs = null;		
		int id = 0;
		
		String sql = "SELECT Max(" + nomeCampo + ") as id FROM " + nomeTabela;
		
		try {
			ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			id = 1;
			while (rs.next()) {
				id = rs.getInt("id") + 1;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro em getMaxId");
		}
		
		return id;
	}
	
	public static long getProximaChave(Connection connection, String nomeTabela) {
		PreparedStatement ps = null;
		ResultSet rs = null;		
				
		String sql = "SHOW TABLE STATUS LIKE '"+ nomeTabela +"'";
		
		try {
			ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if (rs.next()){
				return rs.getLong("auto_increment");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro em getMaxId");
		}
		
		return -1;
	}
	
	public static String formataDataSet(String data) {
        String aux = data; 
        String part[] = null;
        part = aux.split("/");
        return part[2]+"-"+part[1]+"-"+part[0];
	}
	
	public static String formataDataGet(String data) {
        String aux = data; 
        String part[] = null;
        part = aux.split("-");
        return part[2]+"/"+part[1]+"/"+part[0];
	}
}