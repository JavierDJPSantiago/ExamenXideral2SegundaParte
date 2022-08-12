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
		
		// create our escuela db util ... and pass in the conn pool / datasource
		try {
			escuelaDbUtil = new EscuelaDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing escuela
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
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

		// read escuela id from form data
		String theEscuelaId = request.getParameter("escuelaId");
		
		// delete escuela from database
		escuelaDbUtil.deleteEscuela(theEscuelaId);
		
		// send them back to "list escuela" page
		listEscuela(request, response);
	}

	private void updateEscuela(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read escuela info from form data
		int id = Integer.parseInt(request.getParameter("escuelaId"));
		String nombre = request.getParameter("nombre");
		String tescuela = request.getParameter("tescuela");
		String correo = request.getParameter("correo");
		
		// create a new escuela object
		Escuela theEscuela = new Escuela(id, nombre, tescuela, correo);
		
		// perform update on database
		escuelaDbUtil.updateEscuela(theEscuela);
		
		// send them back to the "list escuela" page
		listEscuela(request, response);
		
	}

	private void loadEscuela(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read escuela id from form data
		String theEscuelaId = request.getParameter("escuelaId");
		
		// get escuela from database (db util)
		Escuela theEscuela = escuelaDbUtil.getEscuela(theEscuelaId);
		
		// place escuela in the request attribute
		request.setAttribute("THE_ESCUELA", theEscuela);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-escuela-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addEscuela(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read escuela info from form data
		String nombre = request.getParameter("nombre");
		String tescuela = request.getParameter("tescuela");
		String correo = request.getParameter("correo");		
		
		// create a new escuela object
		Escuela theEscuela = new Escuela(nombre, tescuela, correo);
		
		// add the escuela to the database
		escuelaDbUtil.addEscuela(theEscuela);
				
		// send back to main page (the escuela list)
		listEscuela(request, response);
	}

	private void listEscuela(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get escuela from db util
		List<Escuela> escuela = escuelaDbUtil.getEscuela();
		
		// add escuela to the request
		request.setAttribute("ECUELA_LIST", escuela);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-escuela.jsp");
		dispatcher.forward(request, response);
	}

}













