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
<form action="home">
<button class="submit">Home</button>
</form>
<p>${message}</p>



<form:form action="user/update" method="post"
		modelAttribute="updateDTO">
		


		<form:errors path="*"></form:errors>

		
		<p>
			Email
			<form:input path="email" />
			<form:errors path="email" />
		</p>

		
		<p>
			<button type="submit">Reset</button>
		</p>

	</form:form>

</body>
</html>