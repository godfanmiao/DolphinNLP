<%@page import="java.util.ArrayList"%>
<%@page import="com.dolphinnlp.qmind.model.QA"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QMind</title>
</head>

<body>
<h1>QMind</h1>

<%
	ArrayList<QA> ansList = (ArrayList<QA>)session.getAttribute("ansList");	
	for(QA ans : ansList)
	{
		%>		
		<br><a href = "RankServlet?qid=<%=ans.getQuestion().getQid()%>"><%=ans.getQuestion().getQtitle()%></a></br>
		<%
			if (ans.getAnswers().size() > 0)
			{
			%>
			<br><%=ans.getAnswers().get(0).getAcontent() %></br>
			<%
			}
		 %>
		<%		 
	}
 %>
</body>
</html>