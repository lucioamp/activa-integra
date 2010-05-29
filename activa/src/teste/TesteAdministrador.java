package teste;

import modelo.Bairro;
import modelo.Administrador;
import modelo.Municipio;
import modelo.Uf;
import modelo.Usuario;

public class TesteAdministrador {

	public static void main(String[] args) {
		Uf uf = new Uf();
		uf.setPkEstado(1);
		
		Municipio municipio = new Municipio();
		municipio.setPkMunicipio(1);
		
		Bairro bairro = new Bairro();
		bairro.setPkBairro(1);
		bairro.setMunicipio(municipio);
		
		Administrador administrador = new Administrador();
		administrador.setPkUsuario(1);
		administrador.setCpf(11111111);
		administrador.setNome("Priscila");
		administrador.setApelido("apelido");
		administrador.setDataNasc("1988-06-07");
		administrador.setEmail("priscila.tobias@gmail.com");
		administrador.setSenha("1234");
		administrador.setPerguntaChave("Qual é o seu nome");
		administrador.setRespostaChave("Priscila");
		administrador.setTipoLogradouro("Rua");
		administrador.setLogradouro("Penedo");
		administrador.setNumero(142);
		administrador.setComplemento("casa 10");
		administrador.setCep("21070740");
		administrador.setStatus("A");
		administrador.setImagem("imagem");
		administrador.setBairro(bairro);
		
		try {
			//administrador.incluir();
			//administrador.excluir();
			
			/*Administrador administradorAux = administrador.consultar();
			System.out.println("Bairro: "+administradorAux.getBairro().getPkBairro()+" - "+administradorAux.getBairro().getBairro());
			System.out.println("Municipio: "+administradorAux.getBairro().getMunicipio().getPkMunicipio()+" - "+administradorAux.getBairro().getMunicipio().getMunicipio());
			System.out.println("Uf: "+administradorAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+administradorAux.getBairro().getMunicipio().getUf().getEstado()+" - "+administradorAux.getBairro().getMunicipio().getUf().getSigla());
			System.out.println("Usuario:"+administradorAux.getPkUsuario()+" - " +administradorAux.getCpf()+" - "+administradorAux.getNome()+" - "+
					administradorAux.getApelido()+" - "+administradorAux.getDataNasc()+" - "+administradorAux.getEmail()+" - "+
					administradorAux.getSenha()+" - "+administradorAux.getPerguntaChave()+" - "+administradorAux.getRespostaChave()+" - "+
					administradorAux.getTipoLogradouro()+" - "+administradorAux.getLogradouro()+" - "+
					administradorAux.getNumero()+" - "+administradorAux.getComplemento()+" - "+
					administradorAux.getStatus()+" - "+administradorAux.getImagem()+" - "+administradorAux.getCep());
			*/
			
			/*Collection<Administrador> col = Administrador.consultarTodosAdministradores();
			System.out.println("col"+col.size());
			for(Usuario administradorAux:col){
				System.out.println("Bairro: "+administradorAux.getBairro().getPkBairro()+" - "+administradorAux.getBairro().getBairro());
				System.out.println("Municipio: "+administradorAux.getBairro().getMunicipio().getPkMunicipio()+" - "+administradorAux.getBairro().getMunicipio().getMunicipio());
				System.out.println("Uf: "+administradorAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+administradorAux.getBairro().getMunicipio().getUf().getEstado()+" - "+administradorAux.getBairro().getMunicipio().getUf().getSigla());
				System.out.println("Usuario:"+administradorAux.getPkUsuario()+" - " +administradorAux.getCpf()+" - "+administradorAux.getNome()+" - "+
						administradorAux.getApelido()+" - "+administradorAux.getDataNasc()+" - "+administradorAux.getEmail()+" - "+
						administradorAux.getSenha()+" - "+administradorAux.getPerguntaChave()+" - "+administradorAux.getRespostaChave()+" - "+
						administradorAux.getTipoLogradouro()+" - "+administradorAux.getLogradouro()+" - "+
						administradorAux.getNumero()+" - "+administradorAux.getComplemento()+" - "+
						administradorAux.getStatus()+" - "+administradorAux.getImagem()+" - "+administradorAux.getCep());
			}*/
			
			if(Usuario.validaLogin("priscila.tobias@gmail.com", "1234")){
				System.out.println("valida");
			}else{
				System.out.println("Não valida");
			}
			
			if(Usuario.estaAtivo(administrador)){
				System.out.println("ativo");
			}else{
				System.out.println("inativo");
			}
			
			if(Administrador.isAdministrador(administrador)){
				System.out.println("é administrador");
			}else{
				System.out.println("não é administrador");
			}
			
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
