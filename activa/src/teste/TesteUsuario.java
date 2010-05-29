package teste;

import java.util.Collection;

import modelo.Bairro;
import modelo.Usuario;
import modelo.Municipio;
import modelo.Uf;

public class TesteUsuario {

	public static void main(String[] args) {
		Uf uf = new Uf();
		uf.setPkEstado(1);
		
		Municipio municipio = new Municipio();
		municipio.setPkMunicipio(1);
		
		Bairro bairro = new Bairro();
		bairro.setPkBairro(1);
		bairro.setMunicipio(municipio);
		
		Usuario usuario = new Usuario();
		usuario.setPkUsuario(1);
		usuario.setCpf(11111111);
		usuario.setNome("Felipe");
		usuario.setApelido("apelido");
		usuario.setDataNasc("1988-06-07");
		usuario.setEmail("priscila.tobias@gmail.com");
		usuario.setSenha("1234");
		usuario.setPerguntaChave("Qual é o seu nome");
		usuario.setRespostaChave("Priscila");
		usuario.setTipoLogradouro("Rua");
		usuario.setLogradouro("Penedo");
		usuario.setNumero(142);
		usuario.setComplemento("casa 10");
		usuario.setCep("21070740");
		usuario.setStatus("A");
		usuario.setImagem("imagem");
		usuario.setBairro(bairro);
		
		try {
			//usuario.incluir();
			//usuario.alterar();
			//usuario.excluir();
			
			/*Usuario usuarioAux = usuario.consultar();
			System.out.println("Bairro: "+usuarioAux.getBairro().getPkBairro()+" - "+usuarioAux.getBairro().getBairro());
			System.out.println("Municipio: "+usuarioAux.getBairro().getMunicipio().getPkMunicipio()+" - "+usuarioAux.getBairro().getMunicipio().getMunicipio());
			System.out.println("Uf: "+usuarioAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+usuarioAux.getBairro().getMunicipio().getUf().getEstado()+" - "+usuarioAux.getBairro().getMunicipio().getUf().getSigla());
			System.out.println("Usuario:"+usuarioAux.getPkUsuario()+" - " +usuarioAux.getCpf()+" - "+usuarioAux.getNome()+" - "+
					usuarioAux.getApelido()+" - "+usuarioAux.getDataNasc()+" - "+usuarioAux.getEmail()+" - "+
					usuarioAux.getSenha()+" - "+usuarioAux.getPerguntaChave()+" - "+usuarioAux.getRespostaChave()+" - "+
					usuarioAux.getTipoLogradouro()+" - "+usuarioAux.getLogradouro()+" - "+
					usuarioAux.getNumero()+" - "+usuarioAux.getComplemento()+" - "+
					usuarioAux.getStatus()+" - "+usuarioAux.getImagem()+" - "+usuarioAux.getCep());
			*/
			
			Collection<Usuario> col = Usuario.consultarTodos();
			System.out.println("col"+col.size());
			for(Usuario usuarioAux:col){
				System.out.println("Bairro: "+usuarioAux.getBairro().getPkBairro()+" - "+usuarioAux.getBairro().getBairro());
				System.out.println("Municipio: "+usuarioAux.getBairro().getMunicipio().getPkMunicipio()+" - "+usuarioAux.getBairro().getMunicipio().getMunicipio());
				System.out.println("Uf: "+usuarioAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+usuarioAux.getBairro().getMunicipio().getUf().getEstado()+" - "+usuarioAux.getBairro().getMunicipio().getUf().getSigla());
				System.out.println("Usuario:"+usuarioAux.getPkUsuario()+" - " +usuarioAux.getCpf()+" - "+usuarioAux.getNome()+" - "+
						usuarioAux.getApelido()+" - "+usuarioAux.getDataNasc()+" - "+usuarioAux.getEmail()+" - "+
						usuarioAux.getSenha()+" - "+usuarioAux.getPerguntaChave()+" - "+usuarioAux.getRespostaChave()+" - "+
						usuarioAux.getTipoLogradouro()+" - "+usuarioAux.getLogradouro()+" - "+
						usuarioAux.getNumero()+" - "+usuarioAux.getComplemento()+" - "+
						usuarioAux.getStatus()+" - "+usuarioAux.getImagem()+" - "+usuarioAux.getCep());
			}
			
			/*if(Usuario.validaLogin("priscila.tobias@gmail.com", "1234")){
				System.out.println("valida");
			}else{
				System.out.println("Não valida");
			}
			
			if(Usuario.estaAtivo(usuario)){
				System.out.println("ativo");
			}else{
				System.out.println("inativo");
			}*/
			
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
