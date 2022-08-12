package com.examen2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;


import com.examen2.composite.*;

class Examen2Test {

	@BeforeEach
	void beforeEach(TestInfo prueba) {
		System.out.println("Iniciando prueba para " + prueba.getDisplayName());
	}
	
	@AfterEach
	void afterEach (TestInfo info) {
		System.out.println("Terminando prueba para " + info.getDisplayName());

	}
	
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
	
	@Test
	void performanceTest() {
		List<ComponentDocumento> paginas = new ArrayList<>();
		assertTimeout (Duration.ofSeconds(5), () ->{
			for(int i = 0; i< 5 ; i++) {
				ComponentDocumento indice = new LeafHoja("Indice");
				paginas.add(indice);
				int j = i;
				System.out.println(j);
			}
		});
	}
	
	@Test
	void repetirTest() {
		LeafHoja hoja = new LeafHoja("Hola");
		assertFalse("hola como estas".contains(hoja.getNombre()));
	}
	
	@Test
	void tamañoForTest() {
		List<ComponentDocumento> paginas = new ArrayList<>();
		int j = paginas.size();
		boolean a = false;
		ComponentDocumento indice = new LeafHoja("Indice");
		for(int i = 0; i < j; i++ ) {
		paginas.add(indice);
		a = true;
		}
		
		assertEquals(false, a);
	}
	

	
}
