package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EscuelaDbUtil {

	private DataSource dataSource;

	public EscuelaDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Escuela> getEscuela() throws Exception {
		
		List<Escuela> listaDeEscuela = new ArrayList<>();
		
		try (Connection myConn = dataSource.getConnection();
				Statement myStmt = myConn.createStatement();
				ResultSet myRs = myStmt.executeQuery("select * from escuela order by tescuela")){
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String nombre = myRs.getString("nombre");
				String tescuela = myRs.getString("tescuela");
				String correo = myRs.getString("correo");
				
				// create new escuela object
				Escuela tempEscuela = new Escuela(id, nombre, tescuela, correo);
				
				// add it to the list of escuela
				listaDeEscuela.add(tempEscuela);				
			}
			
			return listaDeEscuela;		
		}
				
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addEscuela(Escuela theEscuela) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into escuela "
					   + "(nombre, tescuela, correo) "
					   + "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the escuela
			myStmt.setString(1, theEscuela.getNombre());
			myStmt.setString(2, theEscuela.getTescuela());
			myStmt.setString(3, theEscuela.getCorreo());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Escuela getEscuela(String theEscuelaId) throws Exception {

		Escuela theEscuela = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int escuelaId;
		
		try {
			// convert escuela id to int
			escuelaId = Integer.parseInt(theEscuelaId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected escuela
			String sql = "select * from escuela where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, escuelaId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String nombre = myRs.getString("nombre");
				String tescuela = myRs.getString("tescuela");
				String correo = myRs.getString("correo");
				
				// use the EscuelaId during construction
				theEscuela = new Escuela(escuelaId, nombre, tescuela, correo);
			}
			else {
				throw new Exception("Could not find escuela id: " + escuelaId);
			}				
			
			return theEscuela;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateEscuela(Escuela theEscuela) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update escuela "
						+ "set nombre=?, tescuela=?, correo=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theEscuela.getNombre());
			myStmt.setString(2, theEscuela.getTescuela());
			myStmt.setString(3, theEscuela.getCorreo());
			myStmt.setInt(4, theEscuela.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteEscuela(String theEscuelaId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int escuelaId = Integer.parseInt(theEscuelaId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from escuela where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, escuelaId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















