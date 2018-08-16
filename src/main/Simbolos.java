package main;

import java.util.ArrayList;

public class Simbolos {
	private String simbolo;
	private String tipo;
	private ArrayList<Integer> parametros = new ArrayList<Integer>();
	
	public Simbolos(String simbolo, String tipo, ArrayList<Integer> parametros) {
		this.simbolo = simbolo;
		this.tipo = tipo;
		this.parametros = parametros;
	}
	
	public Simbolos(String simbolo, String tipo) {
		this.simbolo = simbolo;
		this.tipo = tipo;
		this.parametros = null;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Integer> getParametros() {
		return parametros;
	}

	public void setParametros(int n) {
		parametros.add(n);
	}
	
	
	
	
}
