package teste;

import java.util.Collection;

import modelo.Uf;

public class TesteUf {

	public static void main(String[] args) {
		Uf uf = new Uf();
		uf.setPkEstado(1);
		uf.setEstado("Rio de Janeiro");
		uf.setSigla("RJ");
		
		try {
			//uf.incluir();
			//uf.alterar();
			//Uf ufAux = uf.consultar();
			//System.out.println("Uf: "+uf.getPkEstado()+" - "+uf.getEstado()+" - "+uf.getSigla());
			
			Collection<Uf> col = Uf.consultarTodos();
			for(Uf u:col){
				System.out.println("Uf: "+u.getPkEstado()+" - "+u.getEstado()+" - "+u.getSigla());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
		
	}
}
