package util;

import java.sql.*;

public abstract class ConnectionFactory {

	private static ConnectionFactory cf;

		public static ConnectionFactory getInstance() {
		try {

			switch (Constantes.CONNECTION_FACTORY_ATUAL) {

			case Constantes.CONNECTION_FACTORY_JNDI: {
				cf = new ConnectionFactoryJNDI();
				break;
			}
			case Constantes.CONNECTION_FACTORY_PROPERTIES: {

				cf = new ConnectionFactoryProperties();
				break;
			}

			case Constantes.CONNECTION_FACTORY_DEFAULT: {

				cf = new ConnectionFactoryDefault();
				break;
			}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return cf;
	}

	public abstract Connection getConnection() throws SQLException;

	public void closeConnection(ResultSet rs, Statement stmt, Connection conn) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

	}

}
