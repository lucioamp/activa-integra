package util;

/*import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;*/

public class QuebraCadeia {

	public static String[] recuperaChavesInstituicaoUnidadeProjetoDataCriacao(String str){
		String[] chaves = null;
		
		try{
			String strAux = str.substring(1, str.length());
			String[] strAux2 = strAux.split("\\.");
			chaves = new String[strAux2.length];
			for (int i=0; i < strAux2.length; i++){
				chaves[i] = strAux2[i];
			}	
								
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		return chaves;
	}
	
	public static long[] recuperaCodigosPerguntaItem(String str){
		long[] codigos = null;
		
		try{
			String strAux = str.substring(1, str.length());
			String[] strAux2 = strAux.split("\\.");
			codigos = new long[strAux2.length];
			for (int i=0; i < strAux2.length; i++){
				codigos[i] = Integer.parseInt(strAux2[i]);
			}	
					
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return codigos;
	}
	public static String[] recuperaCodigosModuloPerguntaItemOpcao(String str){
		String[] codigos = null;
	
		try{
			String strAux = str.substring(1, str.length());
			String[] strAux2 = strAux.split("\\.");
			codigos = new String[strAux2.length];
			for (int i=0; i < strAux2.length; i++){
				codigos[i] = strAux2[i];
			}	
					
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return codigos;
	}
	
/*
	public static void main(String[] args) {
		String str = "R.18.10.0.1.N.1";
		
		try{
			String[] codigos = QuebraCadeia.recuperaCodigosModuloPerguntaItemOpcao(str);
			for (int i=0;i <codigos.length; i++){
				System.out.println(codigos[i]);
			}
			
			/*String[] chaves = QuebraCadeia.recuperaChavesInstituicaoUnidadeProjetoDataCriacao("q1.1.1.2008-12-03");
			for (int i=0;i <chaves.length; i++){
				System.out.println(chaves[i]);
			}
			
			
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  			  
			java.util.Date data = (Date)formatter.parse("2008-12-03 09:00:45");
			System.out.println("Data passada como parametro : " + data);
			
			
			java.util.Date data2 = new Date(System.currentTimeMillis()) ;
			System.out.println("Data Hora de hoje : " + data2.toString());
			
			String dataHoraAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())); 
			System.out.println("Data/Hora : " + dataHoraAtual);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
*/
}
