package teste;

import java.util.Collection;

import modelo.CategoriaComunidade;

public class TesteCategoriaComunidade {

	public static void main(String[] args) {
		CategoriaComunidade categoriaComunidade = new CategoriaComunidade();
		categoriaComunidade.setPkCatComunidade(1);
		categoriaComunidade.setNome("Lalal");
		categoriaComunidade.setDescricao("lalalal");
		
		try {
			//categoriaComunidade.incluir();
			//categoriaComunidade.alterar();
			//categoriaComunidade.excluir();
			
			//CategoriaComunidade categoriaComunidadeAux = categoriaComunidade.consultar();
			//System.out.println("categoriaComunidade: "+categoriaComunidadeAux.getPkCatComunidade()+" - "+categoriaComunidadeAux.getNome()+" - "+categoriaComunidadeAux.getDescricao());
			
			Collection<CategoriaComunidade> col = CategoriaComunidade.consultarTodos();
			for(CategoriaComunidade categoriaComunidadeAux:col){
				System.out.println("categoriaComunidade: "+categoriaComunidadeAux.getPkCatComunidade()+" - "+categoriaComunidadeAux.getNome()+" - "+categoriaComunidadeAux.getDescricao());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
