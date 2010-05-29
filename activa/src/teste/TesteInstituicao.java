package teste;

import java.util.Collection;

import modelo.Bairro;
import modelo.Instituicao;
import modelo.Municipio;
import modelo.Uf;

public class TesteInstituicao {

	public static void main(String[] args) {
		Uf uf = new Uf();
		uf.setPkEstado(1);
		
		Municipio municipio = new Municipio();
		municipio.setPkMunicipio(1);
		
		Bairro bairro = new Bairro();
		bairro.setPkBairro(1);
		bairro.setMunicipio(municipio);
		
		Instituicao instituicao = new Instituicao();
		instituicao.setPkInstituicao(1);
		instituicao.setNome("Datasus");
		instituicao.setTipoLogradouro("Rua");
		instituicao.setLogradouro("México");
		instituicao.setNumero(128);
		instituicao.setComplemento("complemento");
		instituicao.setCep("21021210");
		instituicao.setBairro(bairro);
		
		try {
			//instituicao.incluir();
			//instituicao.alterar();
			//instituicao.excluir();
			
			/*Instituicao instituicaoAux = instituicao.consultar();
			System.out.println("Bairro: "+instituicaoAux.getBairro().getPkBairro()+" - "+instituicaoAux.getBairro().getBairro());
			System.out.println("Municipio: "+instituicaoAux.getBairro().getMunicipio().getPkMunicipio()+" - "+instituicaoAux.getBairro().getMunicipio().getMunicipio());
			System.out.println("Uf: "+instituicaoAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+instituicaoAux.getBairro().getMunicipio().getUf().getEstado()+" - "+instituicaoAux.getBairro().getMunicipio().getUf().getSigla());
			System.out.println("Instituicao:"+instituicaoAux.getPkInstituicao()+" - " +instituicaoAux.getNome()+" - "+instituicaoAux.getTipoLogradouro()+" - "+instituicaoAux.getLogradouro()+" - "+instituicaoAux.getNumero()+" - "+instituicaoAux.getComplemento()+" - "+instituicaoAux.getCep());
			*/
			
			Collection<Instituicao> col = Instituicao.consultarTodos();
			for(Instituicao instituicaoAux:col){
				System.out.println("Bairro: "+instituicaoAux.getBairro().getPkBairro()+" - "+instituicaoAux.getBairro().getBairro());
				System.out.println("Municipio: "+instituicaoAux.getBairro().getMunicipio().getPkMunicipio()+" - "+instituicaoAux.getBairro().getMunicipio().getMunicipio());
				System.out.println("Uf: "+instituicaoAux.getBairro().getMunicipio().getUf().getPkEstado()+" - "+instituicaoAux.getBairro().getMunicipio().getUf().getEstado()+" - "+instituicaoAux.getBairro().getMunicipio().getUf().getSigla());
				System.out.println("Instituicao:"+instituicaoAux.getPkInstituicao()+" - " +instituicaoAux.getNome()+" - "+instituicaoAux.getTipoLogradouro()+" - "+instituicaoAux.getLogradouro()+" - "+instituicaoAux.getNumero()+" - "+instituicaoAux.getComplemento()+" - "+instituicaoAux.getCep());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
