<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Escuela Tracker App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- poner nuevo boton: Agregar Escuela -->
			
			<input type="button" value="Add Escuela" 
				   onclick="window.location.href='add-escuela-form.jsp'; return false;"
				   class="add-escuela-button"
			/>
			
			<table>
			
				<tr>
					<th>Nombre</th>
					<th>TipoEscuela</th>
					<th>Correo</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempEscuela" items="${ESCUELA_LIST}">
					
					<!-- configurar un enlace para cada escuela -->
					<c:url var="tempLink" value="EscuelaControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="escuelaId" value="${tempEscuela.id}" />
					</c:url>

					<!--  configurar un enlace para eliminar una escuela -->
					<c:url var="deleteLink" value="EscuelaControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="escuelaId" value="${tempEscuela.id}" />
					</c:url>
																		
					<tr>
						<td> ${tempEscuela.nombre} </td>
						<td> ${tempEscuela.tescuela} </td>
						<td> ${tempEscuela.correo} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this escuela?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








