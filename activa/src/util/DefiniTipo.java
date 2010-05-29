package util;

public class DefiniTipo {
	
	public static boolean verifica(String str){
		
		try {
			Long.parseLong(str);
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
}
