package util;

import java.io.File;

public class RecuperaArquivosDiretorio {

	public static String recuperar(String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		
		String arquivos = "";
			
		if (arquivo.isDirectory()) { 
			for (File arquivo1 : arquivo.listFiles()) { 
				if (arquivo1.isFile()) { 
					arquivos += arquivo1.getName()+"/";
				}
			} 
		}
		
		return arquivos;
		
		//String nomeArquivoPrincipal;
		//String separador;
		
		//verificar se é windows ou linux
		/*if(System.getProperty("os.name").contains("Windows")){
			nomeArquivoPrincipal = "C:\\Eclipse Ganymede - SASS\\workspace\\sass\\WebContent\\biblioteca";
			//separador = "\\";
		}
		else{
			nomeArquivoPrincipal = "";
			//separador = "/";
		}*/
		
		/*File arquivo = new File(nomeArquivo);
		
		String arvore = "";
			
		if (arquivo.isDirectory()) { 
			for (File arquivo1 : arquivo.listFiles()) { 
				//System.out.println("--->"+arquivo1.getName());
				arvore += arquivo1.getName()+";";
				//-------------------------------------
				if (arquivo1.isDirectory()) { 
					for (File arquivo2 : arquivo1.listFiles()) { 
						//System.out.println("------>"+arquivo2.getName());
						arvore += arquivo2.getName()+"%";
						//-------------------------------------
						if (arquivo2.isDirectory()) { 
							for (File arquivo3 : arquivo2.listFiles()) { 
								//System.out.println("--------->"+arquivo3.getName());
								arvore += arquivo3.getName()+"#";
							} 
						}
						/*else{ 
							System.out.println("--------->"+arquivo2.getName()); 
						}*/
						/*arvore += "@";
					} 
				}else{ 
					//System.out.println("------>"+arquivo1.getName());
					arvore += arquivo1.getName();
				}
				arvore += "/";
			} 
		}else{ 
			System.out.println("--->"+arquivo.getName()); 
		}*/
		
		//return arvore;
	}
	
	public static void main(String[] args) {
		String nomeArquivo = "C:\\Eclipse Ganymede - SASS\\workspace\\sass\\WebContent\\biblioteca\\materialDidatico";
		String arvore = RecuperaArquivosDiretorio.recuperar(nomeArquivo);
		
		System.out.println(nomeArquivo);
		System.out.println(arvore);
		
		/*String diretorios[] = arvore.split("/\\s*");
		for(int i=0;i<diretorios.length;i++){
			if(!diretorios[i].equals("")){
				System.out.print("Diretório"+(i+1)+": ");
				String diretoriosNivel1[] = diretorios[i].split(";\\s*");
				
				System.out.println(diretoriosNivel1[0]);
				
				String contDir = diretoriosNivel1[1];
				String contDirDir[] = contDir.split("@\\s*");
				for(int j=0;j<contDirDir.length;j++){
					if(!contDirDir[j].equals("")){
						System.out.print("-------------Diretório"+(j+1)+": ");
						
						System.out.println(contDirDir[j]);
						
					}
				}
				System.out.println("");
			}	
		}*/
	}
}
