import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class PrimeraPasada{

    int cl=0;

    public void pasadaUno(String fileName){
        
        try {
            String[] fileNameSplit=fileName.split("\\.");
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            BufferedReader ts = new BufferedReader(new FileReader(fileNameSplit[0]+"TS.txt"));
            String line;
            String l;
            while(!((line = br.readLine()).equals("    END"))) {
                if(line.indexOf(":")!=-1){
                    String[] etiDef=line.split(":");
                    while((l=ts.readLine())!=null) {
                        String[] lSplit=l.split("\\|");
                        if(lSplit[0].equals(etiDef))
                            System.out.println("Error");
                        else{ 
                            Writer output = new BufferedWriter(new FileWriter(fileNameSplit[0]+"TS.txt", true));  
                            output.append(etiDef[0]+"\r\n");
                            output.close();
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