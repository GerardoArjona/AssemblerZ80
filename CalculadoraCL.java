import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculadoraCL{

	
	public int calcularLongitud(String instruccion, int i, int j){

		try{
			String line;
			String instruc;
			BufferedReader archLong = new BufferedReader(new FileReader("MnLong.txt"));
			String[] ins=instruccion.split(" ");
			if((!ins[j].contains("(")&&!ins[j].contains(")"))&&(ins[j].contains("A")||ins[j].contains("B")||ins[j].contains("C")||ins[j].contains("D")||ins[j].contains("E")||ins[j].contains("H")||ins[j].contains("L"))){
				ins[j]=ins[j].replace('A','R');
				ins[j]=ins[j].replace('B','R');
				ins[j]=ins[j].replace('C','R');
				ins[j]=ins[j].replace('D','R');
				ins[j]=ins[j].replace('E','R');
				ins[j]=ins[j].replace('H','R');
				ins[j]=ins[j].replace('L','R');
			}
			else if(ins[j].contains("(")&&ins[j].contains(")")){
				if(ins[j].contains("A")){
					if(!ins[j].contains("BC")&&!ins[j].contains("DE")){
						return -1;			
					}
				}
			}
			instruc=ins[i]+" "+ins[j];
			//System.out.println(instruc);
			if(instruc.equals("LD R,R"))
				instruc=instruc+"'";
			//System.out.println(instruc);
			while((line=archLong.readLine())!=null){
                	String[] instruccionArch=line.split("\\|");
                	if(instruccionArch[0].equals(instruc)){
						return Integer.parseInt(instruccionArch[1]);
                	}
			}
	}catch(IOException exp){
			exp.printStackTrace();
		}

		return 0;
	}

}