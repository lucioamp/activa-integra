package teste;

import modelo.Bairro;
import modelo.Membro;
import modelo.Municipio;
import modelo.Uf;
import modelo.Usuario;

public class TesteMembro {

	public static void main(String[] args) {
		Uf uf = new Uf();
		uf.setPkEstado(1);
		
		Municipio municipio = new Municipio();
		municipio.setPkMunicipio(1);
		
		Bairro bairro = new Bairro();
		bairro.setPkBairro(1);
		bairro.setMunicipio(municipio);
		
		Membro membro = new Membro();
		membro.setPkUsuario(1);
		membro.setCpf(11111111);
		membro.setNome("Priscila");
		membro.setApelido("apelido");
		membro.setDataNasc("1988-06-07");
		membro.setEmail("priscila.tobias@gmail.com");
		membro.setSenha("1234");
		membro.setPerguntaChave("Qual é o seu nome");
		membro.setRespostaChave("Priscila");
		membro.setTipoLogradouro("Rua");
		membro.setLogradouro("Penedo");
		membro.setNumero(142);
		membro.setComplemento("casa 10");
		membro.setCep("21070740");
		membro.setStatus("A");
		membro.setImagem("imagem");
		membro.setBairro(bairro);
		
		try {
			//membro.incluir();
			//membro.excluir();
			
			/*Membro membroAux = membro.consultar();
			System.out.println("Bairro: "+membroAux.getBairro().getPkBairro()+" - "+membroAux.getBairro().getBairro());
			System.out.println("Municipio: "+membroAux.getBairro().getMunicipio().getPkMunicipio()+" - "+membroAux.getBairro().getMunicipio().getMunicipio());
			System.out.println("Uf: "+membroAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+membroAux.getBairro().getMunicipio().getUf().getEstado()+" - "+membroAux.getBairro().getMunicipio().getUf().getSigla());
			System.out.println("Usuario:"+membroAux.getPkUsuario()+" - " +membroAux.getCpf()+" - "+membroAux.getNome()+" - "+
					membroAux.getApelido()+" - "+membroAux.getDataNasc()+" - "+membroAux.getEmail()+" - "+
					membroAux.getSenha()+" - "+membroAux.getPerguntaChave()+" - "+membroAux.getRespostaChave()+" - "+
					membroAux.getTipoLogradouro()+" - "+membroAux.getLogradouro()+" - "+
					membroAux.getNumero()+" - "+membroAux.getComplemento()+" - "+
					membroAux.getStatus()+" - "+membroAux.getImagem()+" - "+membroAux.getCep());*/
			
			
			/*Collection<Membro> col = Membro.consultarTodosMembros();
			System.out.println("col"+col.size());
			for(Usuario membroAux:col){
				System.out.println("Bairro: "+membroAux.getBairro().getPkBairro()+" - "+membroAux.getBairro().getBairro());
				System.out.println("Municipio: "+membroAux.getBairro().getMunicipio().getPkMunicipio()+" - "+membroAux.getBairro().getMunicipio().getMunicipio());
				System.out.println("Uf: "+membroAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+membroAux.getBairro().getMunicipio().getUf().getEstado()+" - "+membroAux.getBairro().getMunicipio().getUf().getSigla());
				System.out.println("Usuario:"+membroAux.getPkUsuario()+" - " +membroAux.getCpf()+" - "+membroAux.getNome()+" - "+
						membroAux.getApelido()+" - "+membroAux.getDataNasc()+" - "+membroAux.getEmail()+" - "+
						membroAux.getSenha()+" - "+membroAux.getPerguntaChave()+" - "+membroAux.getRespostaChave()+" - "+
						membroAux.getTipoLogradouro()+" - "+membroAux.getLogradouro()+" - "+
						membroAux.getNumero()+" - "+membroAux.getComplemento()+" - "+
						membroAux.getStatus()+" - "+membroAux.getImagem()+" - "+membroAux.getCep());
			}*/
			
			if(Usuario.validaLogin("priscila.tobias@gmail.com", "1234")){
				System.out.println("valida");
			}else{
				System.out.println("Não valida");
			}
			
			if(Usuario.estaAtivo(membro)){
				System.out.println("ativo");
			}else{
				System.out.println("inativo");
			}
			
			if(Membro.isMembro(membro)){
				System.out.println("é membro");
			}else{
				System.out.println("não é membro");
			}
			
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
