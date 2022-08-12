<!DOCTYPE html>
<html>

<head>
	<title>Add Escuela</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-escuela-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Escuela</h3>
		
		<form action="EscuelaControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Nombre :</label></td>
						<td><input type="text" name="nombre" /></td>
					</tr>

					<tr>
						<td><label>Tipo escuela :</label></td>
						<td><input type="text" name="tescuela" /></td>
					</tr>

					<tr>
						<td><label>Correo :</label></td>
						<td><input type="text" name="correo" /></td>
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











