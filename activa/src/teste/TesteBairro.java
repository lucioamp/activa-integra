package teste;

import java.util.Collection;

import modelo.Bairro;
import modelo.Municipio;


public class TesteBairro {

	public static void main(String[] args) {
		Municipio municipio = new Municipio();
		municipio.setPkMunicipio(1);
		
		Bairro bairro = new Bairro();
		bairro.setPkBairro(1);
		bairro.setBairro("Olaria");
		bairro.setMunicipio(municipio);
		
		try {
			//bairro.incluir();
			//bairro.alterar();
			//bairro.excluir();
			
			/*Bairro bairroAux = bairro.consultar();
			System.out.println("Bairro: "+bairroAux.getPkBairro()+" - "+bairroAux.getBairro());
			System.out.println("Municipio: "+bairroAux.getMunicipio().getPkMunicipio()+" - "+bairroAux.getMunicipio().getMunicipio());
			System.out.println("Uf: "+bairroAux.getMunicipio().getUf().getPkEstado()+" - "+bairroAux.getMunicipio().getUf().getEstado()+" - "+bairroAux.getMunicipio().getUf().getSigla());*/
			
			/*Collection<Bairro> col = Bairro.consultarTodos();
			for(Bairro bairroAux:col){
				System.out.println("Bairro: "+bairroAux.getPkBairro()+" - "+bairroAux.getBairro());
				System.out.println("Municipio: "+bairroAux.getMunicipio().getPkMunicipio()+" - "+bairroAux.getMunicipio().getMunicipio());
				System.out.println("Uf: "+bairroAux.getMunicipio().getUf().getPkEstado()+" - "+bairroAux.getMunicipio().getUf().getEstado()+" - "+bairroAux.getMunicipio().getUf().getSigla());
			}*/
			
			Collection<Bairro> col = Bairro.consultarPorMunicipio(municipio);
			for(Bairro bairroAux:col){
				System.out.println("Bairro: "+bairroAux.getPkBairro()+" - "+bairroAux.getBairro());
				System.out.println("Municipio: "+bairroAux.getMunicipio().getPkMunicipio()+" - "+bairroAux.getMunicipio().getMunicipio());
				System.out.println("Uf: "+bairroAux.getMunicipio().getUf().getPkEstado()+" - "+bairroAux.getMunicipio().getUf().getEstado()+" - "+bairroAux.getMunicipio().getUf().getSigla());
				System.out.println("---------------------------------------");
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
		
	}
}
