package main;

import java.util.ArrayList;

public class Simbolos
{
	private String simbolo;
	private String tipo;
	private ArrayList<Integer> parametros;
	
	public Simbolos(String simbolo, String tipo) 
	{
		this.simbolo = simbolo;
		this.tipo = tipo;
		this.parametros = new ArrayList<Integer>();
	}

	public String getSimbolo() 
	{
		return simbolo;
	}

	public void setSimbolo(String simbolo)
	{
		this.simbolo = simbolo;
	}

	public String getTipo() 
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public int getParametro(int indice) 
	{
		return parametros.get(indice);
	}

	public void agregaParametro(int n) 
	{
		parametros.add(n);
	}
	
	public int getTamano()
	{
		return this.parametros.size();
	}
		
}
