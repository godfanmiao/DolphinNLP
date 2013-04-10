<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QMind</title>
</head>

<body>
<center>
<h1>QMind</h1>

<form id="form1" name="searchForm" method="get" action="SearchServlet">
  <input type="text" name="searchTextField" id="textfield" />
  <input type="submit" name="button" id="button" value="问一下" />
</form>
</center>
</body>
</html>