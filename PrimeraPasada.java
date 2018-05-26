import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class PrimeraPasada{

    int cl=0;
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
            while(!((line = br.readLine()).equals("    END"))) {
                if(line.indexOf(":")!=-1){
                    String[] etiDef=line.split(":");
                    while((l=ts.readLine())!=null) {
                        String[] lSplit=l.split("\\|");
                        if(lSplit[0].equals(etiDef[0]))
                            System.out.println("Error");
                        else{
                            System.out.println(cl);
                            hexCL = Integer.toHexString(cl);
                            Writer output = new BufferedWriter(new FileWriter(fileNameSplit[0]+"TS.txt", true));  
                            output.append(etiDef[0]+"|"+hexCL+"|s\r\n");
                            output.close();
                            String[] instruccion=etiDef[1].split(" ");
                            while((mn=mnl.readLine())!=null){
                                System.out.println(cl);
                                String[] ins=mn.split("\\|");
                                if (ins[0].equals(instruccion[1])){
                                    int lon=Integer.parseInt(ins[1]);
                                    cl=cl+lon;
                                    //System.out.println(cl);
                                }            
                            }
                            break;
                        }
                    }
                }
            }
		}catch(IOException exp){
			System.out.println("Error: No se pudo abrir o crear el archivo");
			exp.printStackTrace();
		}
    }

}