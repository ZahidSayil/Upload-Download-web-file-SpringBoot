<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Documents</title>
</head>
<body>

<h2>Upload document</h2>
 
<form action="upload" method="post" enctype="multipart/form-data"> <!-- action is controller method  -->
<pre>
Id: <input type="text"  name="id"/>
Document: <input type="file"  name="document"/>

<input type="submit" value="Upload"/>
</pre>
</form>

<table>
<tr>
<th>Id</th>
<th>Name</th>
<th>Link</th>
</tr>

<c:forEach items="${documents }" var="document">
<tr>

<td>${document.id }</td>
<td>${document.name }</td>
<td><a href="download?id=${document.id}">download</a></td>

</tr>

</c:forEach>

</table>
</body>
</html>