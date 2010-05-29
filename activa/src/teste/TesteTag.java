package teste;

import java.util.Collection;

import modelo.Tag;

public class TesteTag {

	public static void main(String[] args) {
		Tag tag = new Tag();
		tag.setPkTag(1);
		tag.setNome("Java");
		tag.setDescricao("Estudo da linguagem");
		
		try {
			//tag.incluir();
			//tag.alterar();
			//tag.excluir();
			
			/*Tag tagAux = tag.consultar();
			System.out.println("tag: "+tagAux.getPkTag()+" - "+tagAux.getNome()+" - "+tagAux.getDescricao());
			*/
			
			Collection<Tag> col = Tag.consultarTodos();
			for(Tag tagAux:col){
				System.out.println("tag: "+tagAux.getPkTag()+" - "+tagAux.getNome()+" - "+tagAux.getDescricao());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
