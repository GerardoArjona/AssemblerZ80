import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

public class SegundaPasada {
	
	public void Second(String NombreArchivo, String ArchivoExt){
		try{
	        String line;
			String instruc;
			//BufferedReader archSplit = new BufferedReader(new FileReader(NombreArchivo+"CL.txt"));
			BufferedReader archExt = new BufferedReader(new FileReader(ArchivoExt));
		}catch(IOException exp){
			System.out.println("Error: No se pudo abrir o crear el archivo");
			exp.printStackTrace();
		}
	}
}