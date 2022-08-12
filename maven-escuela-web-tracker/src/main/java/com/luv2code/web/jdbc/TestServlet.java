package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Clase de implementación de servlet TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Definir fuente de datos/grupo de conexiones para inyección de recursos
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	

    /**
    * @see HttpServlet#doGet(solicitud HttpServletRequest, respuesta HttpServletResponse)
    */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		try (Connection myConn = dataSource.getConnection();
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from escuela");
			){
			while (myRs.next()) {
				String correo = myRs.getString("correo");
				out.println(correo);
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}







