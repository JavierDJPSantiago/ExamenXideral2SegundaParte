package com.examen2.composite;

//Manipula a los objeos de la composición a travéz de la interfaz
public class Cliente {

	public static void main(String[] args) {
		
		ComponentDocumento libro1 = new ExpressionArchivador();
		ComponentDocumento indice = new LeafHoja("Indice");
		libro1.añadir(indice);
		libro1.ver(0);
        ComponentDocumento inicio = new LeafHoja("Inicio");
        libro1.añadir(inicio);
        libro1.ver(1);
        libro1.eliminar(inicio);
        libro1.ver(1);
        
        ComponentDocumento libro2 = new ExpressionArchivador();
        ComponentDocumento intermedio = new LeafHoja("Intermedio");
        libro2.añadir(intermedio);
        libro2.ver(0);
        ComponentDocumento ultima = new LeafHoja("Ultima");
        libro2.añadir(ultima);
        libro2.ver(1);
        libro2.eliminar(ultima);
        libro2.ver(1);
        
        //Utilizando una referencia de tipo LeafHoja no funcionaran los metodos como se desea
        LeafHoja ejemplo = new LeafHoja("Indice");
        ejemplo.añadir(ejemplo);
        ejemplo.eliminar(ejemplo);
        ejemplo.ver(0);
       
		
		
		
		
	}
	
}
