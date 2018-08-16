package main;

import java.util.ArrayList;

public class TablaDeSimbolos {
	
	private Simbolos simbolos[] = new Simbolos[5];
	private ArrayList<String> variables = new ArrayList<String>();
	
	public TablaDeSimbolos(){
		
		simbolos[0] = new Simbolos("Inicio", "PL" );
		
		simbolos[1] = new Simbolos("creaIcono", "FN");
		simbolos[1].setParametros(1);/*1 es un string*/
		simbolos[1].setParametros(2);/*2 es un entero*/
		simbolos[1].setParametros(2);
		
		simbolos[2] = new Simbolos("mueveIcono", "FN" );
		simbolos[2].setParametros(1);/*1 es un string*/
		simbolos[2].setParametros(2);/*2 es un entero*/
		simbolos[2].setParametros(2);
		
		simbolos[3] = new Simbolos("relaciona", "FN" );
		simbolos[3].setParametros(1);/*1 es un string*/
		simbolos[3].setParametros(1);/*1 es un string*/
		
		simbolos[4] = new Simbolos("Fin", "PL");
		
	}

	public ArrayList<String> getVaribles() {
		return variables;
	}

	public void setVaribles(String varible) {
		variables.add(varible);
	}
	
	
}
