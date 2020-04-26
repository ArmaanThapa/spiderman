<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

${message}


	<form:form action="user/register" method="post"
		modelAttribute="userDTO">
		


		<form:errors path="*"></form:errors>

		<p>
			User Id
			<form:input path="userId" />
			<form:errors path="userId" />
		</p>
		<p>
			Email
			<form:input path="email" />
			<form:errors path="email" />
		</p>

		<p>
			Phone Number
			<form:input path="phoneNumber" />
			<form:errors path="phoneNumber" />
		<p>
			Courses:
			<form:select path="courses">
				<form:option value="">Select Course</form:option>
				<form:options items="${courses}" />

			</form:select>
			<form:errors path="courses" cssClass="error" />
		</p>

		<tr>
			<td></td>
			<td><form:radiobutton path="agree" value="agree" />Agree <form:radiobutton
					path="disagree" value="disagree" />Disagree</td>
			<td><form:errors path="disagree" cssClass="error" /></td>
		</tr>

		<p>
			<button type="submit">Register</button>
		</p>

	</form:form>

</body>
</html>