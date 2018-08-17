package main;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Interprete 
{
	private TablaDeSimbolos ts;
	private String text;
	public ArrayList<String>  errores= new ArrayList<String>();
	public String lineas [] ;

	
	public Interprete(String text)
	{
		ts = new TablaDeSimbolos();
		this.text = text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String [] splitLines()
	{	
		String lines[] = this.text.split("\n");
		return lines;
	}
	
	public void quitaEspacios()
	{
		lineas = this.splitLines();
		for(int i=0; i<4; i++){
			lineas[i] = lineas[i].replaceAll("\\s","");
		}
		
	}
	
	public String [] separaPalabra(String cad)
	{
		int i = 0, j;
		String cabecera = "", parametros = "";
		String datos[] = new String[2];
		while(i < cad.length() && cad.charAt(i) != '(')
		{
				cabecera += cad.charAt(i);
			i++;
		}
		j = i;
		while(j < cad.length())
		{
			parametros += cad.charAt(j);
			j++;
		}
		
		datos[0] = cabecera;
		datos[1] = parametros;		
		
		String nuevo = datos[1].replace("(", "");
		String otro = nuevo.replace(")", "");

	
		datos[1] = otro;
		
		//System.out.println(cabecera);
		//System.out.println(otro);
		
		return datos;
		
	}
	
	public String []  splitParameters(String text)
	{
		String par[] = text.split(",");
		return par;
	}
	public int checkLexic(String reservedWord )
	{
		System.out.println("Comienza analisis Lexico");
		int word = ts.findPr(reservedWord);
		
		if(word == -1)					
			printError(11);
		
		System.out.println("Termina analisis Lexico");
		return word;
	}
	public boolean checkSintaxis(int rW, String text)
	{
		System.out.println("Comienza analisis Sintáctico");
		boolean r = false;
		String parameters [] = splitParameters(text);
		//System.out.println("parameters: " + parameters.length + "simbols= "+ ts.simbolos[rW].getTamano());
		
		//for (String s: parameters) 
		//{
			//System.out.println(s);
		//}
		
		if(parameters.length == ts.simbolos[rW].getTamano())
		{
			for(int i = 0; i < parameters.length; i++)
			{
				if(ts.simbolos[rW].getParametro(i) == 2)
				{
					if (!isInteger(parameters[i]))
						printError(22);
				}
				//si es 1, verificar exixtencia
				else
				{						
					if(checkSemantics(parameters[i],"",rW))
						r = true;				
					
				}
			}
		}
		else
		{
			printError(21);
		}	
		
		System.out.println("Termina analisis Sintáctico");
		return r;
	}
	
	
	public static boolean isInteger(String s) 
	{
      boolean isValidInteger = false;
      try
      {
         Integer.parseInt(s);	
         // s is a valid integer	 
         isValidInteger = true;
      }
      catch (NumberFormatException ex)
      {
         // s is not an integer
      }	 
      return isValidInteger;
	}
	
	public boolean checkSemantics(String text, String text2, int flag)
	{
		System.out.println("Comienza analisis Semántico");
		boolean finded = ts.findVar(text);
		boolean r = false;
		
		switch(flag)
		{
			case 1://Crear
				if(!finded)
				{
					r = true;
					ts.setVaribles(text);
					create(text,1,1);
				}
				else
					printError(31);
				break;
			
			case 2://Mover
				if(finded)
				{
					r = true;
					move(text,2,2);
				}
				else
					printError(31);
				break;
			
			case 3://Relacionar
				if(finded)
				{
					if(ts.findVar(text2))//Validar segunda cadena
					{
						r = true;
						relate(text,text2);
					}					
				}
				else
					printError(31);					
				break;
		}
		

		/*	
			if(flag == 1)//Para crear
			{
				if(!finded)
				{
					r = true;
					ts.setVaribles(text);
				}
				else
					printError(31);
			}
			else if(finded)
			{
				if(flag == 2)//mover
				{
					move(text,1,2);
					r = true;
				}					
				else if(flag == 3)
				{
					r = true;
					relate(text,text);
				}
			}
			else
				printError(32);
		*/

		System.out.println("Termina analisis Semántico");
		return r;
	}		
	
	
	public void create(String name, int x, int y)
	{
		System.out.println("Se ha creado un nuevo ícono.");
		System.out.println("Nombre: " + name);
		System.out.println("Poscisión: (" + x + "," + y + ")");
	}
	
	public void move(String name, int x, int y)
	{
		System.out.println("Se ha movido el ícono: " + name);		
		System.out.println("a la poscisión: (" + x + "," + y + ")");
	}
	
	public void relate(String nameA,String nameB)
	{
		System.out.println(nameA + " -> " + nameB);
	}
	
	public String printError(int code)
	{
		String er = "";
		
		switch (code)
		{
			case 11:
				er = "Error lexico, no se reconoce referencia";
				break;
			case 21:
				er = "Error sintáctico, no coincide el no de argumentos";
				break;				
			case 22:
				er = "Error sintáctico, no coincide el tipo de parametros";
				break;
			case 31:
				er = "Error semántico, la variable ya existe.";
				break;
			case 32:
				er = "Error semántico, la variable referenciada no existe";
				break;							
		}
		
		System.out.println(er);
		return er;
	}
	
	public void analize()
	{
		System.out.println("Comienza el análisis");
		int rw, c=0;
		String temp [] = new String[2];
		quitaEspacios();

		if((checkLexic(lineas[0]) == 0) && (checkLexic(lineas[lineas.length-1]) == 4))
		{
			for(int i= 1; i < lineas.length-2; i++)
			{				
				temp = separaPalabra(lineas[i]);
				rw = checkLexic(temp[0]);
				//System.out.println(temp[0]+ " , " + temp[1] );				
				System.out.println("id pr= "+rw);
				if(rw > 0)
				{
					if(!checkSintaxis(rw,temp[1]))
						break;
				}				 					
			}

		}	
		System.out.println("Finalizando analisis");
	
	}
	
	
	
}
