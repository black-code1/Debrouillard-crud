<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
		<a href="<%=request.getContextPath() %>/new">Add New Diamond</a>		
	</div>
</div>
<br>
<div class="container">
	<div class="card">
		<div class="card-body">
			<c:if test="${diamond !=null}">
				<form action="update" method="post">
			</c:if>
			<c:if test="${diamond ==null}">
				<form action="insert" method="post">
			</c:if>
			
			<caption>
				<h2>
					<c:if test="${diamond !=null}">
						Edit Diamond	
					</c:if>
					<c:if test="${diamond !=null}">
						Add New Diamond	
					</c:if>
				</h2>
			</caption>
			
			<c:if test="${diamond !=null}">
				<input type="hidden" name="id" value="<c:out value='${diamond.id }' />"/>	
			</c:if>
			<fieldset class="form-group">
				<label>Designation</label>
				<input type="text" name="designation" value="<c:out value='${diamond.designation}'/>"class="form-control" required="required"/>
			</fieldset>
			<fieldset class="form-group">
				<label>Weight</label>
				<input type="text" name="weight" value="<c:out value='${diamond.weight}'/>"class="form-control" required="required"/>
			</fieldset>
			<fieldset class="form-group">
				<label>Price</label>
				<input type="text" name="price" value="<c:out value='${diamond.price}'/>"class="form-control" required="required"/>
			</fieldset>
			<button type="submit" class="btn btn-success">Save</button>
			</form>
					
		</div>
	</div>
</div>
</body>
</html>