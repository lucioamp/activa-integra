package teste;

import java.util.Collection;

import modelo.Municipio;
import modelo.Uf;

public class TesteMunicipio {

	public static void main(String[] args) {
		Uf uf = new Uf();
		uf.setPkEstado(1);
				
		Municipio municipio = new Municipio();
		municipio.setPkMunicipio(1);
		municipio.setMunicipio("Rio de Janeiro");
		municipio.setUf(uf);
		
		try {
			//municipio.incluir();
			//municipio.alterar();
			//municipio.excluir();
			
			/*Municipio municipioAux = municipio.consultar();
			System.out.println("Municipio: "+municipioAux.getPkMunicipio()+" - "+municipioAux.getMunicipio());
			System.out.println("Uf: "+municipioAux.getUf().getPkEstado()+" - "+municipioAux.getUf().getEstado()+" - "+municipioAux.getUf().getSigla());*/
			
			/*Collection<Municipio> col = Municipio.consultarTodos();
			for(Municipio municipioAux:col){
				System.out.println("Municipio: "+municipioAux.getPkMunicipio()+" - "+municipioAux.getMunicipio());
				System.out.println("Uf: "+municipioAux.getUf().getPkEstado()+" - "+municipioAux.getUf().getEstado()+" - "+municipioAux.getUf().getSigla());
				System.out.println("---------------------------------------");
			}*/
			
			Collection<Municipio> col = Municipio.consultarPorUf(uf);
			for(Municipio municipioAux:col){
				System.out.println("Municipio: "+municipioAux.getPkMunicipio()+" - "+municipioAux.getMunicipio());
				System.out.println("Uf: "+municipioAux.getUf().getPkEstado()+" - "+municipioAux.getUf().getEstado()+" - "+municipioAux.getUf().getSigla());
				System.out.println("---------------------------------------");
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
		
	}
}
