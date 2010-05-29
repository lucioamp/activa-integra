package modelo.integra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autenticacao extends NivelWADL {
	private String name;
	private String style;
	private String type;
	private String authMode;

	public Autenticacao() {
		super();

		name = "";
		style = "";
		type = "";
		authMode = "";
	}

	public Autenticacao(int idAplicacao, String name, String style, String type, String authMode) {
		this.name = name;
		this.style = style;
		this.type = type;
		this.authMode = authMode;
	}

	public Autenticacao(ResultSet rs) throws SQLException {
		name = rs.getString("Name");
		style = rs.getString("style");
		type = rs.getString("type");
		authMode = rs.getString("authMode");
	}

	public void preparaStatetementInclusao(PreparedStatement pst) throws SQLException {
		pst.setString(2, getName());
		pst.setString(3, getStyle());
		pst.setString(4, getType());
		pst.setString(5, getAuthMode());
	}

	public String getAuthMode() {
		return authMode;
	}

	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return "Autentica='" + name + "' style='" + style + "'" + " type='" + type + "' authMode='" + authMode + "'";
	}

}
