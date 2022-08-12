package com.examen2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.examen2.composite.*;

class Examen2Test {

	@Test
	void testString() {
		String nombre = "nombre";
		assertEquals("nombre",nombre);	
	}
	
	@Test
	void testValorObjeto() {
		String testvalor = "Hola";
		LeafHoja hoja = new LeafHoja("Hola");
		assertEquals(testvalor,hoja.getNombre());	
	}
	
	@Test
	void TestAdd() {
		List<ComponentDocumento> paginas = new ArrayList<>();
		ComponentDocumento indice = new LeafHoja("Indice");
		boolean si = paginas.add(indice);
		assertEquals(true,si);	

	}
	
	@Test
	void TestRemove() {
		List<ComponentDocumento> paginas = new ArrayList<>();
		ComponentDocumento indice = new LeafHoja("Indice");
		paginas.add(indice);
		boolean si = paginas.remove(indice);
		assertEquals(true,si);	

	}
	
	
}
