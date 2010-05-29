package teste;

import java.util.Collection;

import modelo.AreaInteresse;
import modelo.AreaInteresseTag;
import modelo.Tag;

public class TesteAreaInteresseTag {

	public static void main(String[] args) {
		AreaInteresse areaInteresse = new AreaInteresse();
		areaInteresse.setPkAreaInteresse(1);
		
		Tag tag = new Tag();
		tag.setPkTag(1);
		
		AreaInteresseTag areaInteresseTag = new AreaInteresseTag();
		areaInteresseTag.setAreaInteresse(areaInteresse);
		areaInteresseTag.setTag(tag);
		
		try {
			//areaInteresseTag.incluir();
			//areaInteresseTag.excluir();
			
			/*AreaInteresseTag areaInteresseTagAux = areaInteresseTag.consultar();
			System.out.println("areaInteresse: "+areaInteresseTagAux.getAreaInteresse().getPkAreaInteresse()+" - "+areaInteresseTagAux.getAreaInteresse().getNome()+" - "+areaInteresseTagAux.getAreaInteresse().getDescricao());
			System.out.println("tag: "+areaInteresseTagAux.getTag().getPkTag()+" - "+areaInteresseTagAux.getTag().getNome()+" - "+areaInteresseTagAux.getTag().getDescricao());
			*/
			
			Collection<AreaInteresseTag> col = AreaInteresseTag.consultarPorAreaInteresse(areaInteresse);
			for(AreaInteresseTag areaInteresseTagAux:col){
				System.out.println("areaInteresse: "+areaInteresseTagAux.getAreaInteresse().getPkAreaInteresse()+" - "+areaInteresseTagAux.getAreaInteresse().getNome()+" - "+areaInteresseTagAux.getAreaInteresse().getDescricao());
				System.out.println("tag: "+areaInteresseTagAux.getTag().getPkTag()+" - "+areaInteresseTagAux.getTag().getNome()+" - "+areaInteresseTagAux.getTag().getDescricao());
			}
		} catch (Exception e) {
			System.out.println("Não foi possível realizar a operação");
		}
	}
}
