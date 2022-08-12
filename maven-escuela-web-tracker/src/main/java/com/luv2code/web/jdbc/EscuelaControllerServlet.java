package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/EscuelaControllerServlet")
public class EscuelaControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EscuelaDbUtil escuelaDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// crea nuestra escuela db util... y pasa en el conn pool/datasource
		try {
			escuelaDbUtil = new EscuelaDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// leer el parámetro "comando"
			String theCommand = request.getParameter("command");
			// si falta el comando, se muestra de forma predeterminada escuela
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// ruta al método apropiado
			switch (theCommand) {
			
			case "LIST":
				listEscuela(request, response);
				break;
				
			case "ADD":
				addEscuela(request, response);
				break;
				
			case "LOAD":
				loadEscuela(request, response);
				break;
				
			case "UPDATE":
				updateEscuela(request, response);
				break;
			
			case "DELETE":
				deleteEscuela(request, response);
				break;
				
			default:
				listEscuela(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteEscuela(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// leer id de la escuela de los datos del formulario
		String theEscuelaId = request.getParameter("escuelaId");
		
		// delete escuela from database
		escuelaDbUtil.deleteEscuela(theEscuelaId);
		
		// eliminar escuela de la base de datos
		listEscuela(request, response);
	}

	private void updateEscuela(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// leer la información de la escuela de los datos del formulario
		int id = Integer.parseInt(request.getParameter("escuelaId"));
		String nombre = request.getParameter("nombre");
		String tescuela = request.getParameter("tescuela");
		String correo = request.getParameter("correo");
		
		// crea un nuevo objeto escuela
		Escuela theEscuela = new Escuela(id, nombre, tescuela, correo);
		
		// realizar la actualización en la base de datos
		escuelaDbUtil.updateEscuela(theEscuela);
		
		// enviarlos de vuelta a la pagina "list escuela"
		listEscuela(request, response);
		
	}

	private void loadEscuela(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// lee la identificación de la escuela de los datos del formulario
		String theEscuelaId = request.getParameter("escuelaId");
		
		// obtener escuela de la base de datos (db util)
		Escuela theEscuela = escuelaDbUtil.getEscuela(theEscuelaId);
		
		// colocar escuela en el atributo de solicitud
		request.setAttribute("THE_ESCUELA", theEscuela);
		
		// enviar a la página jsp: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-escuela-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addEscuela(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// lee la información de la escuela de los datos del formulario
		String nombre = request.getParameter("nombre");
		String tescuela = request.getParameter("tescuela");
		String correo = request.getParameter("correo");		
		
		// crea un nuevo objeto escuela
		Escuela theEscuela = new Escuela(nombre, tescuela, correo);
		
		// agregar la escuela a la base de datos
		escuelaDbUtil.addEscuela(theEscuela);
				
		// enviar de vuelta a la página principal (la lista de escuelas)
		listEscuela(request, response);
	}

	private void listEscuela(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		// obtener escuela de db util
		List<Escuela> escuela = escuelaDbUtil.getEscuela();
		
		// agregar escuela a la solicitud
		request.setAttribute("ESCUELA_LIST", escuela);
				
		// enviar a la página JSP (ver)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-escuela.jsp");
		dispatcher.forward(request, response);
	}

}













