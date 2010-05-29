package util;

public class SubstituiString {

	public static String substitui(String frase, String velho, String novo){
		return frase.replace(velho, novo);
	}
	
	public static String removeAcentuacao(String frase){
		frase = frase.replaceAll("�", "c");
		frase = frase.replaceAll("�", "a");
		frase = frase.replaceAll("�", "a");
		frase = frase.replaceAll("�", "a");
		frase = frase.replaceAll("�", "a");
		frase = frase.replaceAll("�", "e");
		frase = frase.replaceAll("�", "e");
		frase = frase.replaceAll("�", "i");
		frase = frase.replaceAll("�", "o");
		frase = frase.replaceAll("�", "o");
		frase = frase.replaceAll("�", "o");
		frase = frase.replaceAll("�", "u");
		
		frase = frase.replaceAll("�", "C");
		frase = frase.replaceAll("�", "A");
		frase = frase.replaceAll("�", "A");
		frase = frase.replaceAll("�", "A");
		frase = frase.replaceAll("�", "A");
		frase = frase.replaceAll("�", "E");
		frase = frase.replaceAll("�", "E");
		frase = frase.replaceAll("�", "I");
		frase = frase.replaceAll("�", "O");
		frase = frase.replaceAll("�", "O");
		frase = frase.replaceAll("�", "O");
		frase = frase.replaceAll("�", "U");
				
		return frase;
	}
	
	public static void main(String[] args) {
		/*String frase = "O [INSTITUI��O] tem o [UNIDADE].";
		frase = SubstituiString.substitui(frase, "[INSTITUI��O]", "Minist�rio da Sa�de");
		frase = SubstituiString.substitui(frase, "[UNIDADE]", "DATASUS");
		System.out.println(frase);*/
		
		String frase = "� � � � � � � � � � � �";
		System.out.println(frase);
		frase = SubstituiString.removeAcentuacao(frase);
		System.out.println(frase);
		
		frase = "� � � � � � � � � � � �";
		System.out.println(frase);
		frase = SubstituiString.removeAcentuacao(frase);
		System.out.println(frase);
	}
}
