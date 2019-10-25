<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P> <hr>
<p> yoyoyo </p>


<form action="/web/listTest" method="post">
	<input type="checkbox" name="aa" value="AAA">
	<input type="checkbox" name="aa" value="BBB">
	<input type="checkbox" name="aa" value="CCC">
	<input type="checkbox" name="aa" value="DDD">
	<input type="submit">
</form>

</body>
</html>
