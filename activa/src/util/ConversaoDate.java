package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class ConversaoDate {
	
	private static java.util.Date dataHoraAtual;
	private static java.sql.Date dataHoraAtualSQL;
	
	private static java.util.Date dataHora;
	private static java.sql.Date dataHoraSQL;
		
	
	public ConversaoDate() {
		dataHoraAtual = new Date(System.currentTimeMillis());
		setDataHoraAtualSQL(new java.sql.Date( dataHoraAtual.getTime() ));
	}
		
	public static String getDataAtualFormatoBR(){
		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		return formatoData.format(new Date(System.currentTimeMillis())).toString();
	}
	
	public static String converteDataFormatoBR(String data){
		String dataD[] = data.split("-");
		return dataD[2]+"/"+dataD[1]+"/"+dataD[0];
		
		/*DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			Date dataD = (Date)formatoData.parse(data);
			data = dataD.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public static String getDataHoraAtualFormatoBR(){
		DateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return formatoDataHora.format(new Date(System.currentTimeMillis())).toString();
	}	
		
	public static java.util.Date converteDataFormatoBRparaDate(String d){
		try{
			DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			dataHora = (Date)formatoData.parse(d);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return dataHora;
	}
	public static java.util.Date converteDataHoraFormatoBRparaDate(String dh){
		try{
			DateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			dataHora = (Date)formatoDataHora.parse(dh);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return dataHora;
	}
	
	public static java.util.Date converteDataFormatoPadraoparaDate(String d){
		try{
			DateFormat formatoDataPadrao = new SimpleDateFormat("yyyy-MM-dd");
			dataHora = (Date)formatoDataPadrao.parse(d);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dataHora;
	}
	public static java.util.Date converteDataHoraFormatoPadraoparaDate(String dh){
		try{
			DateFormat formatoDataHoraPadrao = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
			dataHora = (Date)formatoDataHoraPadrao.parse(dh);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return dataHora;
	}
	
	public static String converteDateUtilparaString(java.util.Date data){
		String dataString = "";
		try{
			DateFormat formatoDataHoraPadrao = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
			dataString = formatoDataHoraPadrao.format(data);
			//System.out.println("DataHora Util para String : " + dataString);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return dataString;
	}
	
	public static java.sql.Date converteDataHoraFormatoBRparaDateSQL(String dh){		
		return (new java.sql.Date(converteDataHoraFormatoBRparaDate(dh).getTime()));
	}		
	
	public static java.sql.Date converteDataHoraFormatoPadraoparaDateSQL(String dh){		
		return (new java.sql.Date(converteDataHoraFormatoPadraoparaDate(dh).getTime()));
	}
	
	public static java.util.Date getDataHoraAtual() {
		return dataHoraAtual;
	}

	public static void setDataHoraAtual(java.util.Date dataHoraAtual) {
		ConversaoDate.dataHoraAtual = dataHoraAtual;
	}

	public static String getMes(int mes){
		switch (mes) {
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Março";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";
		default:
			return "inválido";
		}
	}
	
	public static void main(String[] args) {
		/*		
		System.out.println( " (String) DataAtualFormatoBR " + ConversaoDate.getDataAtualFormatoBR() );
		System.out.println( " (String) DataHoraAtualFormatoBR " + ConversaoDate.getDataHoraAtualFormatoBR() );
		
		System.out.println( " (util.Date) converteDataFormatoBRparaDate " + ConversaoDate.converteDataFormatoBRparaDate("21/12/1985") );
		System.out.println( " (util.Date) converteDataHoraFormatoBRparaDate " + ConversaoDate.converteDataHoraFormatoBRparaDate("21/12/1985 20:50:03") );
		
		System.out.println( " (util.Date) converteDataFormatoPadraoparaDate " + ConversaoDate.converteDataFormatoPadraoparaDate("1985-12-21") );
		System.out.println( " (util.Date) converteDataHoraFormatoPadraoparaDate " + ConversaoDate.converteDataHoraFormatoPadraoparaDate("1985-12-21 20:50:03") );
				
		System.out.println( " (sql.Date) converteDataHoraFormatoBRparaDateSQL " + ConversaoDate.converteDataHoraFormatoBRparaDateSQL("21/12/1985 20:50:03") );		
		System.out.println( " (sql.Date) converteDataHoraFormatoPadraoparaDateSQL " + ConversaoDate.converteDataHoraFormatoPadraoparaDateSQL("1985-12-21 20:50:03") );
		*/
		//System.out.println( " (sql.Date) converteDataHoraUtilParaSql " + ConversaoDate.converteDataHoraFormatoPadraoparaDateSQL("1985-12-21 20:50:03") );
	}

	public static void setDataHoraAtualSQL(java.sql.Date dataHoraAtualSQL) {
		ConversaoDate.dataHoraAtualSQL = dataHoraAtualSQL;
	}

	public static java.sql.Date getDataHoraAtualSQL() {
		return dataHoraAtualSQL;
	}

	public static void setDataHoraSQL(java.sql.Date dataHoraSQL) {
		ConversaoDate.dataHoraSQL = dataHoraSQL;
	}

	public static java.sql.Date getDataHoraSQL() {
		return dataHoraSQL;
	}
	
		
}
