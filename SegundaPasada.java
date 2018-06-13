import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class SegundaPasada {
	ArrayList<String> Cl1= new ArrayList<String>(0);
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
						if (simpleArray[x].contains("(IX+")) {
							int num;
							String Hola,aux;
							String C1="";
							String C2="";
							String codigo="";
							Hola = simpleArray[x];
							String[] Datos= Hola.split("X+");
							int lon;
							lon=Datos[1].length();
					    	Datos[1]=Datos[1].substring(1, lon-1);
					    	aux=Datos[1];
					    	 if (aux.length()==1) {
				    	    	 aux="0"+aux;
				    	     }
				    	    if(aux.length()==3) {	
								 aux="0"+aux;
				    	     }
				    	     if(aux.length()==4) {	 
				    	    	 C1=aux.substring(0,2);
								 C2=aux.substring(2,4);                                 
								 codigo=Datos[1]+C2+C1;
								 aux=codigo;
								 }
				    	    num=Integer.parseInt(aux);
						    aux=Integer.toHexString(num).toUpperCase();
						    if (aux.length()==1) {
				    	    	 aux="0"+aux;
				    	     }
				    	    if(aux.length()==3) {	
								 aux="0"+aux;
				    	     }
				    	     if(aux.length()==4) {	 
				    	    	 C1=aux.substring(0,2);
								 C2=aux.substring(2,4);                                 
								 codigo=Datos[1]+C2+C1;
								 aux=codigo;
								 }
					    	codigobjdos[j]= codigobjdos[j]+aux;
					    	//System.out.println("SUBCADENA: " + codigobjdos[j]);
					     }
						if (simpleArray[x].contains("(IY+")) {
							int num;
							String Hola,aux;
							String C1="";
							String C2="";
							String codigo="";
							Hola = simpleArray[x];
							String[] Datos= Hola.split("Y+");
							int lon;
							lon=Datos[1].length();
					    	Datos[1]=Datos[1].substring(1, lon-1);
					    	aux=Datos[1];
					    	 if (aux.length()==1) {
				    	    	 aux="0"+aux;
				    	     }
				    	    if(aux.length()==3) {	
								 aux="0"+aux;
				    	     }
				    	     if(aux.length()==4) {	 
				    	    	 C1=aux.substring(0,2);
								 C2=aux.substring(2,4);                                 
								 codigo=Datos[1]+C2+C1;
								 aux=codigo;
								 }
				    	    num=Integer.parseInt(aux);
						    aux=Integer.toHexString(num).toUpperCase();
						    if (aux.length()==1) {
				    	    	 aux="0"+aux;
				    	     }
				    	    if(aux.length()==3) {	
								 aux="0"+aux;
				    	     }
				    	     if(aux.length()==4) {	 
				    	    	 C1=aux.substring(0,2);
								 C2=aux.substring(2,4);                                 
								 codigo=Datos[1]+C2+C1;
								 aux=codigo;
								 }
						    codigobjdos[j]= codigobjdos[j]+aux;
					    	//System.out.println("SUBCADENA: " + codigobjdos[j]);
					     }
						outCod.append(simpleArray[x]+"|"+codigobjdos[j]+"\r\n");
					
					   }
               }
           }

		   outCod.close();
		
		   try {
				String linea="";
				String linea2="";
				String linea3="";
	            String[] linea5;
				String linea6="";
				BufferedReader bx = new BufferedReader (new InputStreamReader(new FileInputStream (Archivo+"COD.txt")));
				BufferedReader by = new BufferedReader (new InputStreamReader(new FileInputStream (Archivo+"CL.txt")));
				while((linea = bx.readLine())!=null){
					Cl1.add(linea);
				}
	                         
				for(int q=0; q<Cl1.size(); q++ ){
					   String cadena;
					   String cadena2="";
					   linea2=by.readLine();
					   linea3=bx.readLine();
					   cadena2 = linea2.substring(linea2.length()-2);
					   System.out.println(cadena2+"----------"+"---------"+ Cl1.get(q));
	                                    
				} 
			}catch(IOException er) {
				System.out.println("ERROR AL ABRIR LOS ARCHIVOS");
			}
		   
		   
		   
		}catch(IOException exp){
			System.out.println("Error: No se pudo abrir o crear el archivo");
			exp.printStackTrace();
		}
		
		 
	}
}