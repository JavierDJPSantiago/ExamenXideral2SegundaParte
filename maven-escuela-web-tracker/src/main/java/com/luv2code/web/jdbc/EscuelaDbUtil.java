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
				
				// recuperar datos de la fila
				int id = myRs.getInt("id");
				String nombre = myRs.getString("nombre");
				String tescuela = myRs.getString("tescuela");
				String correo = myRs.getString("correo");
				
				// crea un nuevo objeto escuela
				Escuela tempEscuela = new Escuela(id, nombre, tescuela, correo);
				
				// agregarlo a la lista de escuela
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
				myConn.close();  // realmente no lo cierra... simplemente lo vuelve a colocar en el grupo de conexiones
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addEscuela(Escuela theEscuela) throws Exception {

		
		
		
		try (
			// obtener conexión db
			Connection myConn = dataSource.getConnection();
			
			// sql para insertar
			PreparedStatement myStmt = myConn.prepareStatement("insert into escuela "
					   + "(nombre, tescuela, correo) "
					   + "values (?, ?, ?)");
			){
			// establecer los valores de parámetro para la escuela
			myStmt.setString(1, theEscuela.getNombre());
			myStmt.setString(2, theEscuela.getTescuela());
			myStmt.setString(3, theEscuela.getCorreo());
			
			// ejecutar insertar sql
			myStmt.execute();
		}
		
	}

	public Escuela getEscuela(String theEscuelaId) throws Exception {

		Escuela theEscuela = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int escuelaId;
		
		try {
			// convertir id de la escuela a int
			escuelaId = Integer.parseInt(theEscuelaId);
			
			// obtener conexión a la base de datos
			myConn = dataSource.getConnection();
			
			// crear sql para obtener la escuela seleccionada
			String sql = "select * from escuela where id=?";
			
			// crear declaración preparada
			myStmt = myConn.prepareStatement(sql);
			
			// establecer parámetros
			myStmt.setInt(1, escuelaId);
			
			// ejecutar declaración
			myRs = myStmt.executeQuery();
			
			// // recuperar datos de la fila del conjunto de resultados
			if (myRs.next()) {
				String nombre = myRs.getString("nombre");
				String tescuela = myRs.getString("tescuela");
				String correo = myRs.getString("correo");
				
				// usar EscuelaId durante la construcción
				theEscuela = new Escuela(escuelaId, nombre, tescuela, correo);
			}
			else {
				throw new Exception("Could not find escuela id: " + escuelaId);
			}				
			
			return theEscuela;
		}
		finally {
			// limpiar objetos JDBC
			close(myConn, myStmt, myRs);
		}
	}

	public void updateEscuela(Escuela theEscuela) throws Exception {
		
		

		try (
				// obtener conexión db
			Connection myConn = dataSource.getConnection();
			
			// preparar declaración
			PreparedStatement myStmt = myConn.prepareStatement("update escuela "
					+ "set nombre=?, tescuela=?, correo=? "
					+ "where id=?");
				) {
			// establecer parámetros
			myStmt.setString(1, theEscuela.getNombre());
			myStmt.setString(2, theEscuela.getTescuela());
			myStmt.setString(3, theEscuela.getCorreo());
			myStmt.setInt(4, theEscuela.getId());
			
			// ejecutar sentencia SQL
			myStmt.execute();
		}
		
	}

	public void deleteEscuela(String theEscuelaId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convertir id de estudiante a int
			int escuelaId = Integer.parseInt(theEscuelaId);
			
			// obtener conexión a la base de datos
			myConn = dataSource.getConnection();
			
			// crear sql para eliminar estudiante
			String sql = "delete from escuela where id=?";
			
			// preparar declaración
			myStmt = myConn.prepareStatement(sql);
			
			// establecer parámetros
			myStmt.setInt(1, escuelaId);
			
			// ejecutar sentencia sql
			myStmt.execute();
		}
		finally {
			// limpia el código JDBC
			close(myConn, myStmt, null);
		}	
	}
}















