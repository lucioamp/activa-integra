package teste;

import java.util.Collection;

import modelo.AreaInteresse;

public class TesteAreaInteresse {

	public static void main(String[] args) {
		AreaInteresse areaInteresse = new AreaInteresse();
		areaInteresse.setPkAreaInteresse(1);
		areaInteresse.setNome("PHP");
		areaInteresse.setDescricao("Estudo da linguagem");
		
		try {
			//areaInteresse.incluir();
			//areaInteresse.alterar();
			//areaInteresse.excluir();
			
			//AreaInteresse areaInteresseAux = areaInteresse.consultar();
			//System.out.println("areaInteresse: "+areaInteresseAux.getPkAreaInteresse()+" - "+areaInteresseAux.getNome()+" - "+areaInteresseAux.getDescricao());
			
			Collection<AreaInteresse> col = AreaInteresse.consultarTodos();
			for(AreaInteresse areaInteresseAux:col){
				System.out.println("areaInteresse: "+areaInteresseAux.getPkAreaInteresse()+" - "+areaInteresseAux.getNome()+" - "+areaInteresseAux.getDescricao());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
