package modelo;

import java.util.ArrayList;
import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import util.ModuloEvento;
import dao.UsuarioDAO;
import interfaces.UsuarioI;

public class Usuario {

	protected long pkUsuario;
	protected long cpf;
	protected String nome;
	protected String apelido;
	protected String dataNasc;
	protected String email;
	protected String senha;
	protected String perguntaChave;
	protected String respostaChave;
	protected String tipoLogradouro;
	protected String logradouro;
	protected long numero;
	protected String complemento;
	protected String cep;
	protected String status;
	protected String imagem;
	protected Bairro bairro;
	protected Instituicao instituicao;
	private Collection<MicroBlog> microBlogs = new ArrayList<MicroBlog>();
	private ArrayList<ModuloEvento> modulos = new ArrayList<ModuloEvento>();
	
	private static UsuarioI dao;
	
	public Usuario(){
		this.bairro = new Bairro();
		this.instituicao = new Instituicao();
	}
	
	public long getPkUsuario() {
		return pkUsuario;
	}
	public void setPkUsuario(long pkUsuario) {
		this.pkUsuario = pkUsuario;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPerguntaChave() {
		return perguntaChave;
	}
	public void setPerguntaChave(String perguntaChave) {
		this.perguntaChave = perguntaChave;
	}
	public String getRespostaChave() {
		return respostaChave;
	}
	public void setRespostaChave(String respostaChave) {
		this.respostaChave = respostaChave;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	public void setMicroBlogs(Collection<MicroBlog> microBlogs) {
		this.microBlogs = microBlogs;
	}

	public Collection<MicroBlog> getMicroBlogs() {
		return microBlogs;
	}

	private static UsuarioI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new UsuarioDAO();
				break;

			default:
				break;
			}
		}
		return dao; 
	}
	
	public int incluir() throws CadastroException{
		return getDAO().incluir(this);
	}
	
	public int cadastrar() throws CadastroException{
		return getDAO().cadastrar(this);
	}

	public int alterar() throws CadastroException{
		return getDAO().alterar(this);
	}
	
	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	public static Collection<Usuario> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public Usuario consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Usuario consultarPorLogin(String login) throws CadastroException{
		return getDAO().consultarPorLogin(login);
	}
	
	public static boolean validaLogin(String login, String senha) throws CadastroException{
		return getDAO().validaLogin(login, senha);
	}
	
	public static boolean estaAtivo(Usuario usuario) throws CadastroException{
		return getDAO().estaAtivo(usuario);
	}
	
	public static boolean existeEmail(String email) throws CadastroException{
		return getDAO().existeEmail(email);
	}
	
	public static boolean existeCpf(Long cpf) throws CadastroException{
		return getDAO().existeCpf(cpf);
	}
	
	public int alterarImagem() throws CadastroException{
		return getDAO().alterarImagem(this);
	}

	public void setModulos(ArrayList<ModuloEvento> modulos) {
		this.modulos = modulos;
	}

	public ArrayList<ModuloEvento> getModulos() {
		return modulos;
	}
	
	public ModuloEvento adicionarModulo(String nome)
	{
		ModuloEvento modulo = new ModuloEvento(nome);
		
		this.modulos.add(modulo);
		
		return modulo;
	}
	
	public ModuloEvento getModuloPorNome(String nome)
	{
		for (ModuloEvento modulo : this.modulos)
		{
			if(modulo.getNome().equals(nome))
				return modulo;
		}
		
		return null;
	}
}
