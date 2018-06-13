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
			System.out.println(ins[i]+"<---");
			//System.out.println(ins[j]);
			//System.out.println(ins.length);
			if(ins[i].contains("JR")){
				//System.out.println("contiene JR");
				String[] s=ins[j].split("\\,");
				//System.out.println(s[0]);
				if(s.length>1){
					s[1]=",E";
					ins[j]="";
					ins[j]=s[0]+s[1];
				}else{
					ins[j]="E";
				}
				//System.out.println(s[0]);
				//System.out.println(ins[j]);
			}
			else if(ins[i].contains("PUSH")){
				if(ins[j].contains("BC")||ins[j].contains("DE")||ins[j].contains("HL")||ins[j].contains("SP")){
					ins[j]="QQ";
				}else if(ins[j].contains("IX")){
					ins[j]="IX";
				}else if(ins[j].contains("IY")){
					ins[j]="IY";
				}
			}else if(ins[i].contains("POP")){
				System.out.println("Contiene POP");
				if(ins[j].contains("BC")||ins[j].contains("DE")||ins[j].contains("HL")||ins[j].contains("SP")){
					//System.out.println("Contiene ");
					//System.out.println(ins[j]);
					ins[j]="QQ";
				}else if(ins[j].contains("IX")){
					//System.out.println("Contiene IX ");
					ins[j]="IY";
				}else if(ins[j].contains("IY")){
					//System.out.println("Contiene IY ");
					ins[j]="IY";
				}
			}
			else if(ins[i].contains("CP")){
				System.out.println("Contiene CP");
				if(ins[j].contains("IX")){
					//System.out.println("Contiene ");
					//System.out.println(ins[j]);
					ins[j]="(IX+D)";
				}else if(ins[j].contains("IY")){
					//System.out.println("Contiene IX ");
					ins[j]="(IY+D)";
				}else if(ins[j].contains("HL")){
					//System.out.println("Contiene IY ");
					ins[j]="(HL)";
				}else if(ins[j].contains("HL")){
					//System.out.println("Contiene IY ");
					ins[j]="(HL)";
				}else if(ins[j].contains("A")||ins[j].contains("B")||ins[j].contains("C")||ins[j].contains("D")||ins[j].contains("E")||ins[j].contains("H")||ins[j].contains("L")){
					ins[j]=ins[j].replace('A','R');
					ins[j]=ins[j].replace('B','R');
					ins[j]=ins[j].replace('C','R');
					ins[j]=ins[j].replace('D','R');
					ins[j]=ins[j].replace('E','R');
					ins[j]=ins[j].replace('H','R');
					ins[j]=ins[j].replace('L','R');
				}else if(ins[j].contains("0")||ins[j].contains("1")||ins[j].contains("3")||ins[j].contains("2")||ins[j].contains("4")||ins[j].contains("5")||ins[j].contains("6")||ins[j].contains("7")||ins[j].contains("8")||ins[j].contains("9")||ins[j].contains("0")){
					ins[j]=ins[j].replace('1','N');
					ins[j]=ins[j].replace('2','N');
					ins[j]=ins[j].replace('3','N');
					ins[j]=ins[j].replace('4','N');
					ins[j]=ins[j].replace('5','N');
					ins[j]=ins[j].replace('6','N');
					ins[j]=ins[j].replace('7','N');
					ins[j]=ins[j].replace('8','N');
					ins[j]=ins[j].replace('9','N');
					ins[j]=ins[j].replace('0','N');
				}

			}
			else if(ins[i].contains("JP")){
				//System.out.println("contiene JP");
				//System.out.println(ins[j]);
				String[] s=ins[j].split("\\,");
				//System.out.println(s[0]);
				if(s.length>1){
					s[1]=",NN";
					s[0]="CC";
					ins[j]="";
					ins[j]=s[0]+s[1];
				}else{
					if(!s[0].contains("(")){
						ins[j]="NN";
					}
				}
				//System.out.println(s[0]);
				//System.out.println(ins[j]);
			}
			if(ins[j].contains("BC")||ins[j].contains("DE")||ins[j].contains("HL")||ins[j].contains("SP")||(ins[i].contains("LD")&&ins[j].contains("IX"))||(ins[i].contains("LD")&&ins[j].contains("IY"))){
				//System.out.println("contiene LD de 16 bits");
				String[] s=ins[j].split("\\,");
				if(s[1].contains("(")){
					s[1]=",(NN)";
					s[0]="DD";
					ins[j]="";
					ins[j]=s[0]+s[1];
				}
			}
			else if((!ins[j].contains("(")&&!ins[j].contains(")"))&&(ins[j].contains("A")||ins[j].contains("B")||ins[j].contains("C")||ins[j].contains("D")||ins[j].contains("E")||ins[j].contains("H")||ins[j].contains("L"))){
				ins[j]=ins[j].replace('A','R');
				ins[j]=ins[j].replace('B','R');
				ins[j]=ins[j].replace('C','R');
				ins[j]=ins[j].replace('D','R');
				ins[j]=ins[j].replace('E','R');
				ins[j]=ins[j].replace('H','R');
				ins[j]=ins[j].replace('L','R');
				ins[j]=ins[j].replace('1','N');
				ins[j]=ins[j].replace('2','N');
				ins[j]=ins[j].replace('3','N');
				ins[j]=ins[j].replace('4','N');
				ins[j]=ins[j].replace('5','N');
				ins[j]=ins[j].replace('6','N');
				ins[j]=ins[j].replace('7','N');
				ins[j]=ins[j].replace('8','N');
				ins[j]=ins[j].replace('9','N');
			}
			else if(ins[j].contains("(")&&ins[j].contains(")")){
				//System.out.println("Contiene ( )");
				if(!ins[j].contains("A")){
					//System.out.println("No Contiene A");
					if(ins[j].contains("IX+")||ins[j].contains("IY+")){
							//System.out.println("Contiene IX o IY");
							String[] s=ins[j].split("\\,");
							//System.out.println(s[0]);
							//System.out.println(s[1]);
							if(s[1].contains("(")){
								s[0]=s[0].replace('B','R');
								s[0]=s[0].replace('C','R');
								s[0]=s[0].replace('D','R');
								s[0]=s[0].replace('E','R');
								s[0]=s[0].replace('H','R');
								s[0]=s[0].replace('L','R');
								s[1]=s[1].replace('1','D');
								s[1]=s[1].replace('2','D');
								s[1]=s[1].replace('3','D');
								s[1]=s[1].replace('4','D');
								s[1]=s[1].replace('5','D');
								s[1]=s[1].replace('6','D');
								s[1]=s[1].replace('7','D');
								s[1]=s[1].replace('8','D');
								s[1]=s[1].replace('9','D');
								//System.out.println(s[0]+"<-");
								//System.out.println(s[1]+"<-");
							}else if(s[0].contains("(")){
								//System.out.println("<-");
								s[1]=s[1].replace('B','R');
								s[1]=s[1].replace('C','R');
								s[1]=s[1].replace('D','R');
								s[1]=s[1].replace('E','R');
								s[1]=s[1].replace('H','R');
								s[1]=s[1].replace('L','R');
								s[0]=s[0].replace('1','D');
								s[0]=s[0].replace('2','D');
								s[0]=s[0].replace('3','D');
								s[0]=s[0].replace('4','D');
								s[0]=s[0].replace('5','D');
								s[0]=s[0].replace('6','D');
								s[0]=s[0].replace('7','D');
								s[0]=s[0].replace('8','D');
								s[0]=s[0].replace('9','D');
								//System.out.println(s[0]+"<-");
								//System.out.println(s[1]+"<-");
								
							}
							ins[j]="";
							ins[j]=s[0]+","+s[1];
							//System.out.println(ins[j]+"<-");
					}else if(ins[j].contains("HL")){
						//System.out.println("Contiene HL");
						String[] s=ins[j].split("\\,");
						//System.out.println(s[0]);
						//System.out.println(s[1]);
						if(s[1].contains("(")){
							s[0]=s[0].replace('B','R');
							s[0]=s[0].replace('C','R');
							s[0]=s[0].replace('D','R');
							s[0]=s[0].replace('E','R');
							s[0]=s[0].replace('H','R');
							s[0]=s[0].replace('L','R');
							//System.out.println(s[0]+"<-");
						}else if(s[0].contains("(")){
							s[1]=s[1].replace('B','R');
							s[1]=s[1].replace('C','R');
							s[1]=s[1].replace('D','R');
							s[1]=s[1].replace('E','R');
							s[1]=s[1].replace('H','R');
							s[1]=s[1].replace('L','R');
						}
						ins[j]="";
						ins[j]=s[0]+","+s[1];
					}
				}else if(ins[j].contains("A")){
					if((ins[j].contains("B")||ins[j].contains("C")||ins[j].contains("D")||ins[j].contains("E")||ins[j].contains("H")||ins[j].contains("L"))){
						//System.out.println("Contiene A,B,C,...");
						if(ins[j].contains("HL")){
							System.out.println("Contiene HL");
							ins[j]=ins[j].replace('A','R');
							ins[j]=ins[j].replace('B','R');
							ins[j]=ins[j].replace('C','R');
							ins[j]=ins[j].replace('D','R');
							ins[j]=ins[j].replace('E','R');
							ins[j]=ins[j].replace('H','R');
							ins[j]=ins[j].replace('L','R');
						}else if(!ins[j].contains("BC")&&!ins[j].contains("DE")){
							//System.out.println("No contiene DE o BC");
							return -1;			
						}
					}
				}
			}
			instruc=ins[i]+" "+ins[j];
			//System.out.println(instruc+">___");
			if(instruc.equals("LD R,R"))
				instruc=instruc+"'";
			System.out.println(instruc+"**************************");
			while((line=archLong.readLine())!=null){
                	String[] instruccionArch=line.split("\\|");
                	if(instruccionArch[0].equals(instruc)){
						archLong.close();
						System.out.println(Integer.parseInt(instruccionArch[1]));
						return Integer.parseInt(instruccionArch[1]);
                	}
			}
	}catch(IOException exp){
			exp.printStackTrace();
	}catch(ArrayIndexOutOfBoundsException e){
		try{
			BufferedReader archLong = new BufferedReader(new FileReader("MnLong.txt"));
			String line="";
			String[] ins=instruccion.split(" ");
			for(int k=0; k<ins.length; k++){
				if(!ins[k].equals("")){
					while((line=archLong.readLine())!=null){
						String[] instruccionArch=line.split("\\|");
						if(instruccionArch[0].equals(ins[k])){
							archLong.close();
						 	System.out.println(instruccionArch[0]+"**************************");
							System.out.println(Integer.parseInt(instruccionArch[1]));
							return Integer.parseInt(instruccionArch[1]);
						}
					}
				}
			}
		}catch(IOException exp){
			exp.printStackTrace();
		}			
	}

		return 0;
	}

}