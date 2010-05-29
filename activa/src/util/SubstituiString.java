package util;

public class SubstituiString {

	public static String substitui(String frase, String velho, String novo){
		return frase.replace(velho, novo);
	}
	
	public static String removeAcentuacao(String frase){
		frase = frase.replaceAll("ç", "c");
		frase = frase.replaceAll("à", "a");
		frase = frase.replaceAll("á", "a");
		frase = frase.replaceAll("ã", "a");
		frase = frase.replaceAll("â", "a");
		frase = frase.replaceAll("é", "e");
		frase = frase.replaceAll("ê", "e");
		frase = frase.replaceAll("í", "i");
		frase = frase.replaceAll("ó", "o");
		frase = frase.replaceAll("õ", "o");
		frase = frase.replaceAll("ô", "o");
		frase = frase.replaceAll("ú", "u");
		
		frase = frase.replaceAll("Ç", "C");
		frase = frase.replaceAll("À", "A");
		frase = frase.replaceAll("Á", "A");
		frase = frase.replaceAll("Ã", "A");
		frase = frase.replaceAll("Â", "A");
		frase = frase.replaceAll("É", "E");
		frase = frase.replaceAll("Ê", "E");
		frase = frase.replaceAll("Í", "I");
		frase = frase.replaceAll("Ó", "O");
		frase = frase.replaceAll("Õ", "O");
		frase = frase.replaceAll("Ô", "O");
		frase = frase.replaceAll("Ú", "U");
				
		return frase;
	}
	
	public static void main(String[] args) {
		/*String frase = "O [INSTITUIÇÃO] tem o [UNIDADE].";
		frase = SubstituiString.substitui(frase, "[INSTITUIÇÃO]", "Ministério da Saúde");
		frase = SubstituiString.substitui(frase, "[UNIDADE]", "DATASUS");
		System.out.println(frase);*/
		
		String frase = "ç à á ã â é ê í ó õ ô ú";
		System.out.println(frase);
		frase = SubstituiString.removeAcentuacao(frase);
		System.out.println(frase);
		
		frase = "Ç À Á Ã Â É Ê Í Ó Õ Ô Ú";
		System.out.println(frase);
		frase = SubstituiString.removeAcentuacao(frase);
		System.out.println(frase);
	}
}
