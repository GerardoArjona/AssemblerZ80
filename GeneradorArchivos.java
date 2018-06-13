import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.PrintWriter; 
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class GeneradorArchivos{


    public static void generar(String fileName){
        try {

        	String lineCL="";
        	String cod="";
            String[] fileNameSplit=fileName.split("\\.");
			PrintWriter hex = new PrintWriter(new OutputStreamWriter ( new FileOutputStream(fileNameSplit[0]+".hex")), true);
			PrintWriter cl = new PrintWriter(new OutputStreamWriter ( new FileOutputStream(fileNameSplit[0]+".lst")), true);
			BufferedReader ts = new BufferedReader(new FileReader(fileNameSplit[0]+"TS.txt"));
			BufferedReader archCl = new BufferedReader(new FileReader(fileNameSplit[0]+"CL.txt"));
			BufferedReader archCOD = new BufferedReader(new FileReader(fileNameSplit[0]+"COD.txt"));
			while((lineCL = archCl.readLine()) != null){
				cl.println(lineCL);
			}
			cl.println("\r\n");
			while((lineCL = ts.readLine()) != null){
				if(!lineCL.contains(fileNameSplit[0]+"TS"))
					cl.println(lineCL);
			}
			cl.close();
			ts.close();
			archCl.close();
			while((cod = archCOD.readLine()) != null){
				hex.println(cod);
			}
			hex.close();
			archCOD.close();

           	File archts = new File(fileNameSplit[0]+"TS.txt");
           	File archcl = new File(fileNameSplit[0]+"CL.txt");
           	File archcod = new File(fileNameSplit[0]+"COD.txt");                          
           	archcl.delete();
           	if(archcl.exists())
           		System.out.println("nose borroS");
           	archts.delete();
           	archcod.delete();
		}catch(IOException exp){
			System.out.println("Error: No se pudo abrir o crear el archivo");
			exp.printStackTrace();
		}
    }
}