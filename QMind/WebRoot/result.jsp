<%@page import="java.util.ArrayList"%>
<%@page import="com.dolphinnlp.qmind.model.QA"%>
<%@page import="com.dolphinnlp.qmind.model.Answer"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QMind</title>
</head>

<body>
<%
	String qid = (String)session.getAttribute("qid");
	ArrayList<QA> ansList = (ArrayList<QA>)session.getAttribute("ansList");
	for(QA ans : ansList)
	{
		if (ans.getQuestion().getQid().equals(qid))
		{
			%>
			<br><%="问题：" %>
			<%=ans.getQuestion().getQtitle() %></br>
			<%
			if (ans.getQuestion().getQcontent().isEmpty() == false)
			{
				%>
				<br><%="问题描述：" %>
				<%=ans.getQuestion().getQcontent() %></br>
				<%
			}
			for(Answer res : ans.getAnswers())
			{
				if(res.getIsbest().equals("1"))
				{
					%>
					<br><%="最佳答案：" %>
					<%=res.getAcontent() %></br>
					<%
				}
				else
				{
					%>
					<br><%="答案：" %>
					<%=res.getAcontent() %></br>
					<%
				}
			}
			break;
		}
	}
	%>
</body>
</html>