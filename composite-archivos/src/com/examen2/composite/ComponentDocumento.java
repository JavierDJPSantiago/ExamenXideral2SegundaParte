package com.examen2.composite;

//Interfaz común para los objetos de la composición
//Implementarás un comportamiento a las sub clases
public interface ComponentDocumento {

	void añadir(ComponentDocumento componentDocumento);

	void eliminar(ComponentDocumento componentDocumento);

	void ver(int indice);
}
