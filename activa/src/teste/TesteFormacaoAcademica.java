package teste;

import java.util.Collection;

import modelo.FormacaoAcademica;

public class TesteFormacaoAcademica {

	public static void main(String[] args) {
		FormacaoAcademica formacaoAcademica = new FormacaoAcademica();
		formacaoAcademica.setPkFormacaoAcademica(1);
		formacaoAcademica.setNome("Mestre");
		
		try {
			//formacaoAcademica.incluir();
			//formacaoAcademica.alterar();
			//formacaoAcademica.excluir();
			
			//FormacaoAcademica formacaoAcademicaAux = formacaoAcademica.consultar();
			//System.out.println("formacaoAcademica: "+formacaoAcademicaAux.getPkFormacaoAcademica()+" - "+formacaoAcademicaAux.getNome());
			
			Collection<FormacaoAcademica> col = FormacaoAcademica.consultarTodos();
			for(FormacaoAcademica formacaoAcademicaAux:col){
				System.out.println("formacaoAcademica: "+formacaoAcademicaAux.getPkFormacaoAcademica()+" - "+formacaoAcademicaAux.getNome());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
