public void checaPalarbas()
	{
		for(int i=0; i<lineas.length; i++)
		{
			if(i==0)
			{
				if(this.ts.findPr(lineas[i]) == -1)
				{
					String error = "Palabra Reservada Inicio mal";
					System.out.println(error);
					errores.add(error);
				}
					
			}
			else if(i == lineas.length-1)
			{
				if(this.ts.findPr(lineas[i]) == -1)
				{
					String error = "Palabra Reservada Fin mal";
					System.out.println(error);
				}
			}
					
			else
			{
				String datos[] = separaPalabra(lineas[i]);
				if(this.ts.findPr(datos[0]) == -1)
					System.out.println("La funcion " + datos[0] + " no existe");
				else
				{
					if(datos[0].equals("creaIcono"))
					{
						String parametros[] = datos[1].split(",");
						String aux = parametros[0];
						String aux2 = aux.replace("(", "");
						String var = aux2.replace("\"", "");
						
						this.ts.setVaribles(var);
					}		
				}
			}
		}
	}
	
	public void checaParametros()
	{
		
		for(int i=1; i<lineas.length-1; i++){
			String datos[] = separaPalabra(lineas[i]);
			String x = datos[1];
			String nuevo = x.replace("(", "");
			nuevo = nuevo.replace(")", "");
			
				
		}
						
	}