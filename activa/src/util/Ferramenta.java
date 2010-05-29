package util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Ferramenta {

	/*
		// Desenvolvido por Renato Machado
		// [Exemplo]
		{
		 	String param = "nome: Teste&nome: Teste2";
		 	
		 	ArrayList<HashMap<String, String>> resultado = Ferramenta.stringToList(param);
			
			for(HashMap<String, String> object:resultado)
				System.out.println(object.get("nome"));
				
			//[Saida]
			//	Teste
			//	Teste2
		}
	*/
	public static ArrayList<HashMap<String, String>> stringToList(String param)
	{
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		
		param = param.trim();
		if(param.length() > 0)
		{
			if(param.indexOf('&') == -1)
			{
				map = new HashMap<String, String>();
				
				String separador = (param.indexOf('#') != -1 ? "#" : ",");
				
				for(String attributes:param.split(separador))
				{
					if(attributes.trim().length() > 0)
					{
						String[] attribute = attributes.split(":");
						map.put(attribute[0].trim(), (attribute.length == 1 ? "" : attribute[1].trim()));
					}
				}
				list.add(map);
			}else
			{
				for(String object:param.split("&"))
				{
					map = new HashMap<String, String>();
					String separador = (param.indexOf('#') != -1 ? "#" : ",");
					for(String attributes:object.split(separador))
					{
						if(attributes.trim().length() > 0)
						{
							String[] attribute = attributes.split(":");
							map.put(attribute[0].trim(), (attribute.length == 1 ? "" : attribute[1].trim()));
						}
					}
					list.add(map);
				}
			}
		}
				
		return list;
	}
	
	/*
	// Desenvolvido por Renato Machado
	// [Exemplo]
	{
		
	}
	*/
	public static String formatarData(String data, boolean ptbr)
	{
		if(data == null || data.length() < 10)
			return null;
		
		String separador = (data.indexOf('/') != -1 ? "/" : "-");		
		String[] _data = (data.indexOf(" ") != -1 ? data.split(" ")[0] : data).split(separador);
		
		char _separador = (ptbr ? '/' : '-');		
		return _data[2]+_separador+_data[1]+_separador+_data[0];		
	}
	
	/*
	// Desenvolvido por Renato Machado
	// [Exemplo]
	{
		Ferramenta.formatarCpf("123.456.789-01");
		//[Saida]
		// 12345678901
	}
	*/
	public static Long formatarCpf(String cpf)
	{
		if(cpf.length() != 14)
			return null;
		
		return Long.parseLong(cpf.substring(0, 3)+cpf.substring(4, 7)+cpf.substring(8, 11)+cpf.substring(12, 14));
	}
	
	/*
	// Desenvolvido por Renato Machado
	// [Exemplo]
	{
		Ferramenta.formatarCpf(12345678901);
		//[Saida]
		// 123.456.789-01
	}
	*/
	public static String formatarCpf(Long cpf)
	{		
		Locale locale = new Locale("EN");  
		DecimalFormat decimalFormat = new DecimalFormat( "", new DecimalFormatSymbols( locale ) );  
		decimalFormat.setGroupingUsed( false );		
		String _cpf = decimalFormat.format(cpf);
		
		if(_cpf.length() != 11)
			return null;
		
		return _cpf.substring(0, 3)+"."+_cpf.substring(3, 6)+"."+_cpf.substring(6, 9)+"-"+_cpf.substring(9, 11);
	}
	
	public static String md5(String senha){  
		String sen = "";  
		MessageDigest md = null;  
		try {  
			md = MessageDigest.getInstance("MD5");  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		}  
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
		sen = hash.toString(16);
		
		return sen;
	}
	
	public static String clearScape(String string)
	{
		return string.replaceAll("\n", "%#!");
	}	
	
	public static String nl2br(String string)
	{
		return string.replaceAll("\n", "<br/>");
	}	
}
