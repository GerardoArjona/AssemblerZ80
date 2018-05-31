import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimeraPasada{

    int cl=0;
    int lon;
    String hexCL;

    public void pasadaUno(String fileName){
        
        try {
            String[] fileNameSplit=fileName.split("\\.");
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            BufferedReader ts = new BufferedReader(new FileReader(fileNameSplit[0]+"TS.txt"));
            BufferedReader mnl = new BufferedReader(new FileReader("MnLong.txt"));
            ArrayList<String> etis = new ArrayList<String>(); //todas las etiquetas que se encuentren en el archivo se guardan aquí
            String line;
            String l;
            String mn;
            while(!((line = br.readLine()).equals("    END"))) {
                if(line.indexOf(":")!=-1){
                    String[] etiDef=line.split(":");
                    String eti=etiDef[0].replace(" ", "");
                    etis.add(etiDef[0]);
                    while((l=ts.readLine())!=null) {
                        String[] lSplit=l.split("\\|");
                        hexCL = Integer.toHexString(cl);
                        Writer output = new BufferedWriter(new FileWriter(fileNameSplit[0]+"TS.txt", true));
                        Writer outCl = new BufferedWriter(new FileWriter(fileNameSplit[0]+"CL.txt",true));  
                        output.append(eti+"|"+hexCL+"|s\r\n");
                        output.close();
                        outCl.append(etiDef[1]+"|"+hexCL+"\r\n");
                        outCl.close();
                        String[] instruccion=etiDef[1].split(" ");                
                        while((mn=mnl.readLine())!=null){
                            String[] ins=mn.split("\\|");
                            if (ins[0].equals(instruccion[1])){
                                lon=Integer.parseInt(ins[1]);
                                break;
                            }            
                        }
                        cl=cl+lon;
                        break;
                        }
                }else{
                    String[] instruccion=line.split(" ");
                    while((mn=mnl.readLine())!=null){
                        String[] ins=mn.split("\\|");
                        if (ins[0].equals(instruccion[4])){
                            lon=Integer.parseInt(ins[1]);
                            break;
                        }            
                    }
                    cl=cl+lon;
                }
            }
            for (int i = 0; i < etis.size(); i++) {
                for (int j = i + 1 ; j < etis.size(); j++) {
                    if (etis.get(i).equals(etis.get(j))) {
                             System.out.println("Error: Definicion multiple de la etiqueta: "+etis.get(i));
                             ts.close();
                               File archts = new File(fileNameSplit[0]+"TS.txt");
                               File archcl = new File(fileNameSplit[0]+"CL.txt");
                               File archcod = new File(fileNameSplit[0]+"COD.txt");                          
                               archcl.delete();
                               archts.delete();
                               archcod.delete();
                               break;
                    }
               }
           }
        }catch(IOException exp){
			System.out.println("Error: No se pudo abrir o crear el archivo");
			exp.printStackTrace();
		}
    }

}