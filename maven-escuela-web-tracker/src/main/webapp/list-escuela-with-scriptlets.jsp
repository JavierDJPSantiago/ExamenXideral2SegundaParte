<%@ page import="java.util.*, com.luv2code.web.jdbc.*" %>
<!DOCTYPE html>
<html>

<head>
	<title>Escuela Tracker App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%
	// obtener la escuela de respuesta(enviado por servlet)
	List<Escuela> theEscuela = (List<Escuela>) request.getAttribute("ESCUELA_LIST");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<table>
			
				<tr>
					<th>Nombre</th>
					<th>Tipo Escuela</th>
					<th>Correo</th>
				</tr>
				
				<% for (Escuela tempEscuela : theEscuela) { %>
				
					<tr>
						<td> <%= tempEscuela.getNombre() %> </td>
						<td> <%= tempEscuela.getTescuela() %> </td>
						<td> <%= tempEscuela.getCorreo() %> </td>
					</tr>
				
				<% } %>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








