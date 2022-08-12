package com.examen2.composite;

import java.util.ArrayList;
import java.util.List;

//Composite: Define el comportamiento de los objetos.
//Implementa las operaciones para almacenar a los objetos y gestionarlos
public class ExpressionArchivador implements ComponentDocumento {
	
	//Lista para almacenar los objetos
	private List<ComponentDocumento> paginas = new ArrayList<>();
	
	//Añade objetos a la lista
	@Override
	public void añadir(ComponentDocumento componentDocumento) {
		paginas.add(componentDocumento);

	}
	
	//Elimina objetos a la lista
	@Override
	public void eliminar(ComponentDocumento componentDocumento) {
		paginas.remove(componentDocumento);

	}
	
	//Visualizar el nombre de la pagina insertada
	@Override
	public void ver(int indice) {
		try { 
			System.out.println("Nombre de pagina: " + paginas.get(indice));
		} catch (Exception IndexOutOfBoundsException) {
			System.out.println("No existe una hoja en esa posición");
		}

	}

}
