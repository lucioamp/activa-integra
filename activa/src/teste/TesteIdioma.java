package teste;

import java.util.Collection;

import modelo.Idioma;

public class TesteIdioma {

	public static void main(String[] args) {
		Idioma idioma = new Idioma();
		idioma.setPkIdioma(1);
		idioma.setNome("Espanhol");
		
		try {
			//idioma.incluir();
			//idioma.alterar();
			//idioma.excluir();
			
			//Idioma idiomaAux = idioma.consultar();
			//System.out.println("idioma: "+idiomaAux.getPkIdioma()+" - "+idiomaAux.getNome());
			
			Collection<Idioma> col = Idioma.consultarTodos();
			for(Idioma idiomaAux:col){
				System.out.println("idioma: "+idiomaAux.getPkIdioma()+" - "+idiomaAux.getNome());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
