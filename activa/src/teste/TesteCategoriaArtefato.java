package teste;

import java.util.Collection;

import modelo.CategoriaArtefato;

public class TesteCategoriaArtefato {

	public static void main(String[] args) {
		CategoriaArtefato categoriaArtefato = new CategoriaArtefato();
		categoriaArtefato.setPkCatArtefato(1);
		categoriaArtefato.setNome("Lalal");
		categoriaArtefato.setDescricao("oioioi");
		
		try {
			//categoriaArtefato.incluir();
			//categoriaArtefato.alterar();
			//categoriaArtefato.excluir();
			
			//CategoriaArtefato categoriaArtefatoAux = categoriaArtefato.consultar();
			//System.out.println("categoriaArtefato: "+categoriaArtefatoAux.getPkCatArtefato()+" - "+categoriaArtefatoAux.getNome()+" - "+categoriaArtefatoAux.getDescricao());
			
			Collection<CategoriaArtefato> col = CategoriaArtefato.consultarTodos();
			for(CategoriaArtefato categoriaArtefatoAux:col){
				System.out.println("categoriaArtefato: "+categoriaArtefatoAux.getPkCatArtefato()+" - "+categoriaArtefatoAux.getNome()+" - "+categoriaArtefatoAux.getDescricao());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
