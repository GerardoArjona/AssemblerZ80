import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

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
            String line;
            String l;
            String mn;
            out:
            while(!((line = br.readLine()).equals("    END"))) {
                if(line.indexOf(":")!=-1){
                    String[] etiDef=line.split(":");
                    String eti=etiDef[0].replace(" ", "");
                    while((l=ts.readLine())!=null) {
                        String[] lSplit=l.split("\\|");
                        System.out.println(etiDef[0]);
                        if(lSplit[0].equals(etiDef[0])){
                            System.out.println("Error");
                            ts.close();  
                            File archts = new File(fileNameSplit[0]+"TS.txt");
                            File archcl = new File(fileNameSplit[0]+"CL.txt");
                            File archcod = new File(fileNameSplit[0]+"COD.txt");                          
                            archcl.delete();
                            archts.delete();
                            archcod.delete();
                            break out;
                        }else{
                            hexCL = Integer.toHexString(cl);
                            Writer output = new BufferedWriter(new FileWriter(fileNameSplit[0]+"TS.txt", true));  
                            output.append(eti+"|"+hexCL+"|s\r\n");
                            output.close();
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
            System.out.println(cl);
		}catch(IOException exp){
			System.out.println("Error: No se pudo abrir o crear el archivo");
			exp.printStackTrace();
		}
    }

}