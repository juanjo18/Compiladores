package main;

import java.util.ArrayList;

public class TablaDeSimbolos 
{
	
	public Simbolos simbolos[] = new Simbolos[5];
	public ArrayList<String> variables = new ArrayList<String>();
	
	public TablaDeSimbolos()
	{
		
		simbolos[0] = new Simbolos("Inicio", "PR" );
		
		simbolos[1] = new Simbolos("creaIcono", "FN");
		simbolos[1].agregaParametro(1);//String
		simbolos[1].agregaParametro(2);//Entero
		simbolos[1].agregaParametro(2);
		
		simbolos[2] = new Simbolos("mueveIcono", "FN");
		simbolos[2].agregaParametro(1);
		simbolos[2].agregaParametro(2);
		simbolos[2].agregaParametro(2);
		
		simbolos[3] = new Simbolos("relaciona", "FN");
		simbolos[3].agregaParametro(1);
		simbolos[3].agregaParametro(1);

		simbolos[4] = new Simbolos("Fin", "PR" );
	}

	public ArrayList<String> getVaribles() 
	{
		return variables;
	}

	public void setVaribles(String varible) 
	{
		variables.add(varible);
	}
	
	public int findPr(String name)
	{
		int word = -1;
		for(int i=0; i<5; i++)
		{
			if(simbolos[i].getSimbolo().equals(name))
			{
				word = i;
				break;
			}		
		}
		return word;
	}

	
	public boolean findVar(String name)
	{
		boolean siEsta = false;
		for(int i=0; i<variables.size(); i++)
		{
			if(variables.get(i).equals(name))
			{
				siEsta = true;
				break;
			}		
		}
		return siEsta;
	}
}
