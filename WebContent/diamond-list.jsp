<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Diamond Shop</title>
</head>
<body>
<header>
	<nav>
		<div>
			<a href="#">Debrouillard</a>
		</div>
		<ul>
			<li><a href="<%=request.getContextPath() %>/list">Diamonds</a></li>
		</ul>
	</nav>
</header><br>
<div class="row">
	<div class="container">
		<li><a href="<%=request.getContextPath() %>/new">Add New Diamond</a></li>		
	</div>
</div>
<br>
<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Designation</th>
			<th>Weight</th>
			<th>Price</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="diamond" items="${listDiamond}">
			<tr>
				<td><c:out value="${diamond.id}"/></td>
				<td><c:out value="${diamond.designation}"/></td>
				<td><c:out value="${diamond.weight}"/></td>
				<td><c:out value="${diamond.price}"/></td>
				<td><a href="delete?id=<c:out value="${diamond.id}"/>">delete </a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>