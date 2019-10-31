<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Casa do C�digo</title>
</head>
<body>
	
	<h1>Lista de Produtos </h1>
	
	<div>${mensagem}</div>
	
	<table>
		<thead>
			<tr>
				<td colspan="5" style="text-align:center">
					Livros
				</td>
			</tr>
			<tr>
				<td>Id</td>
				<td>T�tulo</td>
				<td>Descri��o</td>
				<td>P�ginas</td>
				<td>Pre�os</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td>${produto.id}</td>
					<td>${produto.titulo}</td>
					<td>${produto.descricao}</td>
					<td>${produto.paginas}</td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

</body>
</html>