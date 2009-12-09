package nucleo.usuario;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
	
	protected int idUsuario;
	protected String email;
	protected String senha;
	protected String nomeUsuario;
	protected String CPF;
	protected String telefone;
	protected String celular;
	protected Date dataCadastro;
	protected boolean papelProf;
	protected boolean papelAluno;
	protected boolean papelTutor;
	protected boolean papelAdmin;
	
	public Usuario()
	{
	}

	public Usuario( int idUsuario, String email, String senha, 
					String nomeUsuario, String cpf, String telefone, 
					String celular, Date dataCadastro, boolean papelProf, 
					boolean papelAluno, boolean papelTutor, boolean papelAdmin) 
	{
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.senha = senha;
		this.nomeUsuario = nomeUsuario;
		CPF = cpf;
		this.telefone = telefone;
		this.celular = celular;
		this.dataCadastro = dataCadastro;
		this.papelProf = papelProf;
		this.papelAluno = papelAluno;
		this.papelTutor = papelTutor;
		this.papelAdmin = papelAdmin;
	}

	public Usuario(ResultSet rs) throws SQLException
	{
		super();
		this.idUsuario = rs.getInt("IdUsuario");
		this.email = rs.getString("Email");
		this.senha = rs.getString("Senha");
		this.nomeUsuario = rs.getString("NomeUsuario");
		CPF = rs.getString("CPF");
		this.telefone = rs.getString("Telefone");
		this.celular = rs.getString("Celular");
		this.dataCadastro = rs.getDate("DataCadastro");
		this.papelProf = rs.getBoolean("EhProfessor");
		this.papelAluno = rs.getBoolean("EhAluno");
		this.papelTutor = rs.getBoolean("EhTutor");
		this.papelAdmin = rs.getBoolean("EhAdmin");
	}
	
	public int nivelAcesso()
	{
		if ( this.papelAdmin )
			return 4;
		
		if ( this.papelProf )
			return 3;
		
		if ( this.papelTutor )
			return 2;
		
		if ( this.papelAluno )
			return 1;
		
		return 0;
	}
	
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cpf) {
		CPF = cpf;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isPapelAdmin() {
		return papelAdmin;
	}

	public void setPapelAdmin(boolean papelAdmin) {
		this.papelAdmin = papelAdmin;
	}

	public boolean isPapelAluno() {
		return papelAluno;
	}

	public void setPapelAluno(boolean papelAluno) {
		this.papelAluno = papelAluno;
	}

	public boolean isPapelProf() {
		return papelProf;
	}

	public void setPapelProf(boolean papelProf) {
		this.papelProf = papelProf;
	}

	public boolean isPapelTutor() {
		return papelTutor;
	}

	public void setPapelTutor(boolean papelTutor) {
		this.papelTutor = papelTutor;
	}
}
