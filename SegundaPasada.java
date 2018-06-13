import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class SegundaPasada {
	
	public void Second(String Archivo, String ArchivoExt){
		try{
	        String lineAsm,lineCod,line;
			ArrayList<String> instrucciones = new ArrayList<String>();
			String[] codigobj,codigobjdos;
			codigobj = new String[673];
			codigobjdos = new String[673];
			BufferedReader archCl = new BufferedReader(new FileReader(Archivo+"CL.txt"));
			BufferedReader archExt = new BufferedReader(new FileReader(ArchivoExt));
			Writer outCod = new BufferedWriter(new FileWriter(Archivo+"COD.txt",true));
			Writer cleanCod = new BufferedWriter(new FileWriter(Archivo+"COD.txt"));
			ClassLoader classLoader = getClass().getClassLoader();
			File fi=new File("codigoInstrucciones.txt");
			/*System.out.println(fi.canRead());
			System.out.println(fi.exists());
			File codob = new File(classLoader.getResource("codigoInstrucciones.txt").getFile());*/
			BufferedReader COD = new BufferedReader(new FileReader(fi));
			//llenamos el arraylist con las instrucciones
			while((lineAsm = archExt.readLine()) != null){
				instrucciones.add(lineAsm);
			}
			String[] simpleArray = new String[ instrucciones.size() ];
			int size = instrucciones.size();
			instrucciones.toArray( simpleArray );
			//llenamos el array con las regex del archivo COD
			for(int i=0 ; i < 673; i++ ){
			 		lineCod=COD.readLine();				
					String[] regexp = lineCod.split("_");
					codigobj[i]=regexp[0];
					codigobjdos[i]=regexp[1];
			}


			cleanCod.append("");
			cleanCod.close();
			// match instrucciones vs COD
			for (int x = 0; x < size ; x++) {
				//System.out.println(simpleArray[x]+"<---");
                for (int j = 0 ; j < 673; j++) {
                	//System.out.println(codigobjdos[j]);
					Pattern patt = Pattern.compile(codigobj[j]);
					Matcher m = patt.matcher(simpleArray[x]);
					if (m.find()) {
						//System.out.println("match "+simpleArray[x]+" "+codigobjdos[j]);
						line=archCl.readLine();
						//System.out.println(line);
						outCod.append(line+"|"+codigobjdos[j]+"\r\n");
					   }
               }
           }

		   outCod.close();

			/*Pattern patt = Pattern.compile("([A-Z]{1,10}[0-9]?[:])? ?LD C,[(][H-L]{1,2}[)]");
			while ((lineAsm = archExt.readLine()) != null) {
				Matcher m = patt.matcher(lineAsm);
				while (m.find()) {
				 System.out.println("math");
				 System.out.println(lineAsm);
				}

			}*/

		}catch(IOException exp){
			System.out.println("Error: No se pudo abrir o crear el archivo");
			exp.printStackTrace();
		}
	}
}