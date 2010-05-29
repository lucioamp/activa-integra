package teste;


import modelo.Bairro;
import modelo.Membro;
import modelo.Municipio;
import modelo.Uf;
import modelo.Usuario;

public class TesteAluno {

	public static void main(String[] args) {
		Uf uf = new Uf();
		uf.setPkEstado(1);
		
		Municipio municipio = new Municipio();
		municipio.setPkMunicipio(1);
		
		Bairro bairro = new Bairro();
		bairro.setPkBairro(1);
		bairro.setMunicipio(municipio);
		
		Membro aluno = new Membro();
		aluno.setPkUsuario(1);
		aluno.setCpf(11111111);
		aluno.setNome("Priscila");
		aluno.setApelido("apelido");
		aluno.setDataNasc("1988-06-07");
		aluno.setEmail("priscila.tobias@gmail.com");
		aluno.setSenha("1234");
		aluno.setPerguntaChave("Qual é o seu nome");
		aluno.setRespostaChave("Priscila");
		aluno.setTipoLogradouro("Rua");
		aluno.setLogradouro("Penedo");
		aluno.setNumero(142);
		aluno.setComplemento("casa 10");
		aluno.setCep("21070740");
		aluno.setStatus("A");
		aluno.setImagem("imagem");
		aluno.setBairro(bairro);
		
		try {
			//aluno.incluir();
			//aluno.excluir();
			
			/*Aluno alunoAux = aluno.consultar();
			System.out.println("Bairro: "+alunoAux.getBairro().getPkBairro()+" - "+alunoAux.getBairro().getBairro());
			System.out.println("Municipio: "+alunoAux.getBairro().getMunicipio().getPkMunicipio()+" - "+alunoAux.getBairro().getMunicipio().getMunicipio());
			System.out.println("Uf: "+alunoAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+alunoAux.getBairro().getMunicipio().getUf().getEstado()+" - "+alunoAux.getBairro().getMunicipio().getUf().getSigla());
			System.out.println("Usuario:"+alunoAux.getPkUsuario()+" - " +alunoAux.getCpf()+" - "+alunoAux.getNome()+" - "+
					alunoAux.getApelido()+" - "+alunoAux.getDataNasc()+" - "+alunoAux.getEmail()+" - "+
					alunoAux.getSenha()+" - "+alunoAux.getPerguntaChave()+" - "+alunoAux.getRespostaChave()+" - "+
					alunoAux.getTipoLogradouro()+" - "+alunoAux.getLogradouro()+" - "+
					alunoAux.getNumero()+" - "+alunoAux.getComplemento()+" - "+
					alunoAux.getStatus()+" - "+alunoAux.getImagem()+" - "+alunoAux.getCep());
			*/
			
			/*Collection<Aluno> col = Aluno.consultarTodosAlunos();
			System.out.println("col"+col.size());
			for(Usuario alunoAux:col){
				System.out.println("Bairro: "+alunoAux.getBairro().getPkBairro()+" - "+alunoAux.getBairro().getBairro());
				System.out.println("Municipio: "+alunoAux.getBairro().getMunicipio().getPkMunicipio()+" - "+alunoAux.getBairro().getMunicipio().getMunicipio());
				System.out.println("Uf: "+alunoAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+alunoAux.getBairro().getMunicipio().getUf().getEstado()+" - "+alunoAux.getBairro().getMunicipio().getUf().getSigla());
				System.out.println("Usuario:"+alunoAux.getPkUsuario()+" - " +alunoAux.getCpf()+" - "+alunoAux.getNome()+" - "+
						alunoAux.getApelido()+" - "+alunoAux.getDataNasc()+" - "+alunoAux.getEmail()+" - "+
						alunoAux.getSenha()+" - "+alunoAux.getPerguntaChave()+" - "+alunoAux.getRespostaChave()+" - "+
						alunoAux.getTipoLogradouro()+" - "+alunoAux.getLogradouro()+" - "+
						alunoAux.getNumero()+" - "+alunoAux.getComplemento()+" - "+
						alunoAux.getStatus()+" - "+alunoAux.getImagem()+" - "+alunoAux.getCep());
			}*/
			
			if(Usuario.validaLogin("priscila.tobias@gmail.com", "1234")){
				System.out.println("valida");
			}else{
				System.out.println("Não valida");
			}
			
			if(Usuario.estaAtivo(aluno)){
				System.out.println("ativo");
			}else{
				System.out.println("inativo");
			}
			
			/*if(Membro.isAluno(aluno)){
				System.out.println("é aluno");
			}else{
				System.out.println("não é aluno");
			}*/
			
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
