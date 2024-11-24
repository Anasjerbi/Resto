<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Order Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Orders</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${order != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${order == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${order != null}">
            			Edit order
            		</c:if>
						<c:if test="${order == null}">
            			Add New order
            		</c:if>
					</h2>
				</caption>

				<c:if test="${order != null}">
					<input type="hidden" name="id" value="<c:out value='${order.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>order Name</label> <input type="text"
						value="<c:out value='${order.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>votre Email</label> <input type="text"
						value="<c:out value='${order.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>order description</label> <input type="text"
						value="<c:out value='${order.description}' />" class="form-control"
						name="description">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
