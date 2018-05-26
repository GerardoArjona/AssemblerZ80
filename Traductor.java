import java.util.Scanner; 
 
public class Traductor{

    public static void main(String a []){
        Scanner sc=new Scanner (System.in);

        System.out.println("Ingrese el nombre del archivo: ");
        String fileName=sc.nextLine();

        CreadorTablas creadorTablas = new CreadorTablas();        
        creadorTablas.creandoTablas(fileName, "TS","SIMBOLO \tVALOR \tDEFINICION" );
        creadorTablas.creandoTablas(fileName, "CL","ISNTRUCCION \tCL" );
        creadorTablas.creandoTablas(fileName, "COD","INSTRUCCION \tCODIGO" );




    }

}