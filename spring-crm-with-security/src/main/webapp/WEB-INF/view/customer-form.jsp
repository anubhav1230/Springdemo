<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>


<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Save Customer</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<style>
.error {
	color: red;
}
</style>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Customer</h3>
		<!-- Place for messages: error, alert etc ... -->
		<div class="form-group">
			<div class="col-xs-15">
				<div>

					<!-- Check for email error -->
					<c:if test="${emailError != null}">

						<div class="error">${emailError}</div>

					</c:if>

				</div>
			</div>
		</div>

		<form:form action="saveCustomer" modelAttribute="customer"
			method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />

			<table>
				<tbody>
					<tr>
						<td><label>First name(*):</label></td>
						<td><form:input path="firstName" /></td>
						<td><form:errors path="firstName" cssClass="error"></form:errors>
					</tr>

					<tr>
						<td><label>Last name(*):</label></td>
						<td><form:input path="lastName" /></td>
						<td><form:errors path="lastName" cssClass="error"></form:errors>
					</tr>

					<tr>
						<td><label>Email(*):</label></td>
						<td><form:input path="email" /></td>
						<td><form:errors path="email" cssClass="error"></form:errors></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>




		</form:form>

		<div style=""></div>

		<p>
			<a href="${pageContext.request.contextPath}/customer/list">Back
				to List</a>
		</p>

	</div>

</body>

</html>










