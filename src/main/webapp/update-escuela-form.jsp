<!DOCTYPE html>
<html>

<head>
	<title>Update Escuela</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-escuela-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar SEP</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Escuela</h3>
		
		<form action="EscuelaControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="escuelaId" value="${THE_ESCUELA.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Escuela:</label></td>
						<td><input type="text" name="nombre" 
								   value="${THE_ESCUELA.nombre}" /></td>
					</tr>

					<tr>
						<td><label>Tipo escuela:</label></td>
						<td><input type="text" name="tescuela" 
								   value="${THE_ESCUELA.tescuela}" /></td>
					</tr>

					<tr>
						<td><label>Correo:</label></td>
						<td><input type="text" name="correo" 
								   value="${THE_ESCUELA.correo}" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="EscuelaControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











