package com.examen2.composite;

//Representa a los objetos de la composición
public class LeafHoja implements ComponentDocumento{
	
	//Los objetos contienen un objeto de tipo String
	private String nombre;
	
	public LeafHoja(String nombre) {
		super();
		this.setNombre(nombre);
	}
	
	//Objetos referenciados por esta clase no pueden ser añadidos
	@Override
	public void añadir(ComponentDocumento componentDocumento) {
        System.out.println("No se puede agregar la hoja");
		
	}
	
	//Objetos referenciados por esta clase no pueden ser eliminados
	@Override
	public void eliminar(ComponentDocumento componentDocumento) {
        System.out.println("No se puede quitar la hoja");
		
	}
	
	//Objetos referenciados por esta clase no pueden mostrar su cadena de texto
	@Override
	public void ver(int indice) {
        System.out.println("No puede visualizar el nombre de la pagina");
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return  nombre;
	}
	
	
	
	
	
	
}
