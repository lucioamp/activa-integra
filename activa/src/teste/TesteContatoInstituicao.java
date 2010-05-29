package teste;

import java.util.Collection;

import modelo.ContatoInstituicao;
import modelo.Instituicao;
import modelo.TipoContato;

public class TesteContatoInstituicao {

	public static void main(String[] args) {
		TipoContato tipoContato = new TipoContato();
		tipoContato.setPkTipoContato(1);
		
		Instituicao instituicao = new Instituicao();
		instituicao.setPkInstituicao(1);
		
		ContatoInstituicao contatoInstituicao = new ContatoInstituicao();
		contatoInstituicao.setContato("1111111");
		contatoInstituicao.setTipoContato(tipoContato);
		contatoInstituicao.setInstituicao(instituicao);
		
		try {
			//contatoInstituicao.incluir();
			//contatoInstituicao.alterar();
			//contatoInstituicao.excluir();
			
			/*ContatoInstituicao contatoInstituicaoAux = contatoInstituicao.consultar();
			System.out.println("TipoContato: "+contatoInstituicaoAux.getTipoContato().getPkTipoContato()+" - "+contatoInstituicaoAux.getTipoContato().getTipoContato());
			System.out.println("Instituicao:"+contatoInstituicaoAux.getInstituicao().getPkInstituicao()+" - " +contatoInstituicaoAux.getInstituicao().getNome()+" - "+contatoInstituicaoAux.getInstituicao().getTipoLogradouro()+" - "+contatoInstituicaoAux.getInstituicao().getLogradouro()+" - "+contatoInstituicaoAux.getInstituicao().getNumero()+" - "+contatoInstituicaoAux.getInstituicao().getComplemento()+" - "+contatoInstituicaoAux.getInstituicao().getCep());
			System.out.println("ContatoInstituicao:"+contatoInstituicaoAux.getContato());
			*/
			
			/*Collection<ContatoInstituicao> col = ContatoInstituicao.consultarTodos();
			for(ContatoInstituicao contatoInstituicaoAux:col){
				System.out.println("TipoContato: "+contatoInstituicaoAux.getTipoContato().getPkTipoContato()+" - "+contatoInstituicaoAux.getTipoContato().getTipoContato());
				System.out.println("Instituicao:"+contatoInstituicaoAux.getInstituicao().getPkInstituicao()+" - " +contatoInstituicaoAux.getInstituicao().getNome()+" - "+contatoInstituicaoAux.getInstituicao().getTipoLogradouro()+" - "+contatoInstituicaoAux.getInstituicao().getLogradouro()+" - "+contatoInstituicaoAux.getInstituicao().getNumero()+" - "+contatoInstituicaoAux.getInstituicao().getComplemento()+" - "+contatoInstituicaoAux.getInstituicao().getCep());
				System.out.println("ContatoInstituicao:"+contatoInstituicaoAux.getContato());
			}*/
			
			Collection<ContatoInstituicao> col = ContatoInstituicao.consultarPorInstituicao(instituicao);
			for(ContatoInstituicao contatoInstituicaoAux:col){
				System.out.println("TipoContato: "+contatoInstituicaoAux.getTipoContato().getPkTipoContato()+" - "+contatoInstituicaoAux.getTipoContato().getTipoContato());
				System.out.println("Instituicao:"+contatoInstituicaoAux.getInstituicao().getPkInstituicao()+" - " +contatoInstituicaoAux.getInstituicao().getNome()+" - "+contatoInstituicaoAux.getInstituicao().getTipoLogradouro()+" - "+contatoInstituicaoAux.getInstituicao().getLogradouro()+" - "+contatoInstituicaoAux.getInstituicao().getNumero()+" - "+contatoInstituicaoAux.getInstituicao().getComplemento()+" - "+contatoInstituicaoAux.getInstituicao().getCep());
				System.out.println("ContatoInstituicao:"+contatoInstituicaoAux.getContato());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		} 
		
	}
}
