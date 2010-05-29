package teste;

import java.util.Collection;

import modelo.TipoContato;

public class TesteTipoContato {

	public static void main(String[] args) {
		TipoContato tipoContato = new TipoContato();
		tipoContato.setPkTipoContato(1);
		tipoContato.setTipoContato("Celeular");
		
		try {
			//tipoContato.incluir();
			//tipoContato.alterar();
			//tipoContato.excluir();
			
			//TipoContato tipoContatoAux = tipoContato.consultar();
			//System.out.println("Uf: "+tipoContatoAux.getPkTipoContato()+" - "+tipoContatoAux.getTipoContato());
			
			Collection<TipoContato> col = TipoContato.consultarTodos();
			for(TipoContato tc:col){
				System.out.println("Uf: "+tc.getPkTipoContato()+" - "+tc.getTipoContato());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
		
	}
}
