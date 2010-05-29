package teste;

import modelo.Membro;
import modelo.Bairro;
import modelo.Municipio;
import modelo.Uf;
import modelo.Usuario;

public class TesteProfessor {

	public static void main(String[] args) {
		Uf uf = new Uf();
		uf.setPkEstado(1);
		
		Municipio municipio = new Municipio();
		municipio.setPkMunicipio(1);
		
		Bairro bairro = new Bairro();
		bairro.setPkBairro(1);
		bairro.setMunicipio(municipio);
		
		Membro professor = new Membro();
		professor.setPkUsuario(1);
		professor.setCpf(11111111);
		professor.setNome("Priscila");
		professor.setApelido("apelido");
		professor.setDataNasc("1988-06-07");
		professor.setEmail("priscila.tobias@gmail.com");
		professor.setSenha("1234");
		professor.setPerguntaChave("Qual é o seu nome");
		professor.setRespostaChave("Priscila");
		professor.setTipoLogradouro("Rua");
		professor.setLogradouro("Penedo");
		professor.setNumero(142);
		professor.setComplemento("casa 10");
		professor.setCep("21070740");
		professor.setStatus("A");
		professor.setImagem("imagem");
		professor.setBairro(bairro);
		
		try {
			//professor.incluir();
			//professor.excluir();
			
			/*Professor professorAux = professor.consultar();
			System.out.println("Bairro: "+professorAux.getBairro().getPkBairro()+" - "+professorAux.getBairro().getBairro());
			System.out.println("Municipio: "+professorAux.getBairro().getMunicipio().getPkMunicipio()+" - "+professorAux.getBairro().getMunicipio().getMunicipio());
			System.out.println("Uf: "+professorAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+professorAux.getBairro().getMunicipio().getUf().getEstado()+" - "+professorAux.getBairro().getMunicipio().getUf().getSigla());
			System.out.println("Usuario:"+professorAux.getPkUsuario()+" - " +professorAux.getCpf()+" - "+professorAux.getNome()+" - "+
					professorAux.getApelido()+" - "+professorAux.getDataNasc()+" - "+professorAux.getEmail()+" - "+
					professorAux.getSenha()+" - "+professorAux.getPerguntaChave()+" - "+professorAux.getRespostaChave()+" - "+
					professorAux.getTipoLogradouro()+" - "+professorAux.getLogradouro()+" - "+
					professorAux.getNumero()+" - "+professorAux.getComplemento()+" - "+
					professorAux.getStatus()+" - "+professorAux.getImagem()+" - "+professorAux.getCep());
			*/
			
			/*Collection<Professor> col = Professor.consultarTodosProfessores();
			System.out.println("col"+col.size());
			for(Usuario professorAux:col){
				System.out.println("Bairro: "+professorAux.getBairro().getPkBairro()+" - "+professorAux.getBairro().getBairro());
				System.out.println("Municipio: "+professorAux.getBairro().getMunicipio().getPkMunicipio()+" - "+professorAux.getBairro().getMunicipio().getMunicipio());
				System.out.println("Uf: "+professorAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+professorAux.getBairro().getMunicipio().getUf().getEstado()+" - "+professorAux.getBairro().getMunicipio().getUf().getSigla());
				System.out.println("Usuario:"+professorAux.getPkUsuario()+" - " +professorAux.getCpf()+" - "+professorAux.getNome()+" - "+
						professorAux.getApelido()+" - "+professorAux.getDataNasc()+" - "+professorAux.getEmail()+" - "+
						professorAux.getSenha()+" - "+professorAux.getPerguntaChave()+" - "+professorAux.getRespostaChave()+" - "+
						professorAux.getTipoLogradouro()+" - "+professorAux.getLogradouro()+" - "+
						professorAux.getNumero()+" - "+professorAux.getComplemento()+" - "+
						professorAux.getStatus()+" - "+professorAux.getImagem()+" - "+professorAux.getCep());
			}*/
			
			if(Usuario.validaLogin("priscila.tobias@gmail.com", "1234")){
				System.out.println("valida");
			}else{
				System.out.println("Não valida");
			}
			
			if(Usuario.estaAtivo(professor)){
				System.out.println("ativo");
			}else{
				System.out.println("inativo");
			}
			
			/*if(Professor.isProfessor(professor)){
				System.out.println("é professor");
			}else{
				System.out.println("não é professor");
			}*/
			
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
