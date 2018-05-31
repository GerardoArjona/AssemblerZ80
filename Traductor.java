import java.util.Scanner; 
import java.io.File; 
 
public class Traductor{

    public static void main(String a []){
        Scanner sc=new Scanner (System.in);

        System.out.println("Ingrese el nombre del archivo: ");
        String fileName=sc.nextLine();
        String[] filesplit=fileName.split("\\.");
        File f=new File(fileName);

        if(f.exists() && filesplit[1].contains("asm")){
            CreadorTablas creadorTablas = new CreadorTablas();        
            creadorTablas.creandoTablas(fileName, "TS","SIMBOLO|VALOR|DEFINICION" );
            creadorTablas.creandoTablas(fileName, "CL","ISNTRUCCION|CL" );
            creadorTablas.creandoTablas(fileName, "COD","INSTRUCCION|CODIGO" );

            PrimeraPasada p1 = new PrimeraPasada();        
            p1.pasadaUno(fileName);
        }
        else
            System.out.println("\nEl archivo no existe o la extension no es valida");



    }

}