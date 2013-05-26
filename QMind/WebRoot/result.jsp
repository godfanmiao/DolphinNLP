<%@page import="java.util.ArrayList"%>
<%@page import="java.net.*"%>
<%@page import="com.dolphinnlp.qmind.model.QA"%>
<%@page import="com.dolphinnlp.qmind.model.Answer"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>QMind搜索</title>
<style>body{color:#000;background:#fff;padding:6px 0 0;margin:0;position:relative}body,th,td,.p1,.p2{font-family:arial}p,form,ol,ul,li,dl,dt,dd,h3{margin:0;padding:0;list-style:none}input{padding-top:0;padding-bottom:0;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;box-sizing:border-box}table,img{border:0}td{font-size:9pt;line-height:18px}em{font-style:normal;color:#cc0000}a em{text-decoration:underline}cite{font-style:normal;color:#008000}.m,a.m{color:#666}a.m:visited{color:#606}.g,a.g{color:#008000}.c{color:#77c}.f14{font-size:14px}.f10{font-size:10.5pt}.f16{font-size:16px}#u,#head,#tool,#search,#foot{font-size:12px}.p1{line-height:120%;margin-left:-12pt}.p2{width:100%;line-height:120%;margin-left:-12pt}#out{_margin-left:880px;_zoom:1}#in{_position:relative;_float:left;_margin-left:-880px}#wrapper{min-width:880px;_zoom:1}#container{padding-left:20px}.container_s{width:967px}.container_l{width:1187px}#content_left{width:636px}#content_right{border-left:1px solid #e1e1e1;float:right}.container_s #content_right{width:281px}.container_l #content_right{width:451px}#u{color:#999;white-space:nowrap;position:absolute;right:10px;top:4px;_top:0;z-index:299}#u a{color:#00c;margin:0 5px}#u .reg{margin:0}#u .last{margin-right:0}#u .un{font-weight:bold;padding-right:11px;margin-right:5px;background:url(http://www.baidu.com/cache/global/img/bg-1.1.0.png) no-repeat right -195px}#u ul{width:100%;background:#fff;border:1px solid #9b9b9b}#u li{height:25px}#u li a{width:100%;height:25px;line-height:25px;display:block;text-align:left;text-decoration:none;text-indent:6px;margin:0;filter:none\9}#u li a:hover{background:#ebebeb}#u li.nl{border-top:1px solid #ebebeb}#userMenu{width:64px;position:absolute;right:7px;_right:2px;top:15px;top:14px\9;*top:15px;padding-top:4px;display:none;*background:#fff}#user{position:relative;display:inline-block}#head{padding-left:20px}.fm{clear:both;position:relative;z-index:297}.nv a,.nv b,.btn,#page,#more{font-size:14px}.s_nav{height:45px}.s_nav .s_logo{margin-right:20px;float:left}.s_nav .s_logo img{border:0;display:block}.s_nav .s_tab{line-height:18px;padding:20px 0 0;float:left}.s_nav a{color:#0000cc;font-size:14px}.s_nav b{font-size:14px}.s_ipt_wr{width:533px;height:30px;display:inline-block;margin-right:5px;background:url(http://s1.bdstatic.com/r/www/img/i-1.0.0.png) no-repeat -304px 0;border:1px solid #b6b6b6;border-color:#7b7b7b #b6b6b6 #b6b6b6 #7b7b7b;vertical-align:top}.s_ipt{width:520px;height:22px;font:16px/22px arial;margin:5px 0 0 7px;padding:0;background:#fff;border:0;outline:0;-webkit-appearance:none}.s_btn{width:95px;height:32px;padding-top:2px\9;font-size:14px;padding:0;background:#ddd url(http://s1.bdstatic.com/r/www/img/i-1.0.0.png);border:0;cursor:pointer}.s_btn_h{background-position:-100px 0}.s_btn_wr{width:97px;height:34px;display:inline-block;background:url(http://s1.bdstatic.com/r/www/img/i-1.0.0.png) no-repeat -202px 0;*position:relative;z-index:0;vertical-align:top}.sethf{padding:0;margin:0;font-size:14px}.set_h{display:none;behavior:url(#default#homepage)}.set_f{display:none}.shouji{margin-left:22px}#tb_mr{color:#00c;cursor:pointer;position:relative;z-index:298}#tb_mr b{font-weight:normal;text-decoration:underline}#tb_mr small{font-size:11px}#page{font:14px simsun;white-space:nowrap}#page a,#page strong{display:inline-block;vertical-align:text-bottom;height:54px;text-align:center;line-height:22px;text-decoration:none;overflow:hidden;margin-right:5px;background:white}#page a{cursor:pointer}#page a:hover{background:0}#page .n:hover,#page a:hover .pc{background:#ebebeb}#page .n{height:22px;padding:0 6px;border:1px solid #e7ecf0}#page span{display:block}#page .pc{width:22px;height:22px;border:1px solid #e7ecf0;cursor:pointer}#page .fk{width:24px;height:30px;background:url("http://s1.bdstatic.com/r/www/cache/global/img/bg-1.1.0.png") -35px -309px no-repeat;cursor:pointer}#page strong .fk,#page strong .pc{cursor:auto}#page .fkd{background-position:-35px -303px}#page .fk_cur{background-position:2px -312px}#page strong .pc{border:0;width:24px;height:24px;line-height:24px}#page .nums{display:inline-block;vertical-align:text-bottom}#rs{width:100%;background:#fff;padding:8px 0;margin:20px 0 0}#rs td{width:5%}#rs th{font-size:14px;font-weight:normal;line-height:19px;white-space:nowrap;text-align:left;vertical-align:top}#rs .tt{font-weight:bold;padding:0 10px 0 20px}#rs_top{font-size:14px;margin-bottom:22px}#rs_top a{margin-right:18px}#search{padding:35px 0 16px 20px}#search .s_help{position:relative;top:10px}#foot{height:20px;line-height:20px;color:#77c;background:#e6e6e6;text-align:center}#foot span{color:#666}.site_tip{font-size:13px;line-height:18px;padding:3px 0 5px 72px;margin-bottom:20px;background:url(http://s1.bdstatic.com/r/www/img/bg-1.0.0.gif) no-repeat}.to_zhidao,.to_tieba{font-size:16px;line-height:24px;margin:20px 0 0;padding:0 0 0 32px;background:url(http://s1.bdstatic.com/r/www/img/bg-1.0.0.gif) no-repeat 0 -68px}.to_tieba{background-position:0 -102px}.f{line-height:115%;*line-height:120%;font-size:100%;width:33.7em;word-break:break-all;word-wrap:break-word}.h{margin-left:8px;width:100%}.r{word-break:break-all;cursor:hand;width:238px}.t{font-weight:normal;font-size:medium}.pl{padding-left:3px;height:8px;padding-right:2px;font-size:14px}.mo,a.mo:link,a.mo:visited{color:#666;font-size:100%;line-height:10px}.htb{margin-bottom:5px}.jc a{color:#cc0000}a font[size="3"] font,font[size="3"] a font{text-decoration:underline}div.blog,div.bbs{color:#707070;padding-top:3px}.result{width:40em;table-layout:fixed}
.result-op .f{word-wrap:normal}.nums{font-size:12px;color:#999}.tools{width:230px;position:absolute;top:10px;white-space:nowrap}#mHolder{width:62px;position:relative;z-index:296;top:-18px;margin-left:9px;margin-right:-12px;display:none}#mCon{height:18px;position:absolute;right:7px;top:3px;top:6px\9;cursor:pointer;padding:0 18px 0 0;line-height:18px;background:url(http://s1.bdstatic.com/r/www/img/bg-1.0.0.gif) no-repeat right -133px;background-position:right -135px\9}#mCon span{color:#00c;cursor:default;display:block}#mCon .hw{text-decoration:underline;cursor:pointer}#mMenu{width:56px;border:1px solid #9b9b9b;position:absolute;right:7px;top:23px;display:none;background:#fff}#mMenu a{width:100%;height:100%;color:#00c;display:block;line-height:22px;text-indent:6px;text-decoration:none;filter:none\9}#mMenu a:hover{background:#ebebeb}#mMenu .ln{height:1px;background:#ebebeb;overflow:hidden;font-size:1px;line-height:1px;margin-top:-1px}.op_LAMP{background:url("http://s1.bdstatic.com/r/www/cache/global/img/aladdinIcon-1.0.gif") no-repeat 0 2px;color:#77C;display:inline-block;font-size:13px;height:12px;*height:14px;width:16px;text-decoration:none;zoom:1}.EC_mr15{margin-left:0}.pd15{padding-left:0}.map_1{width:30em;font-size:80%;line-height:145%}.map_2{width:25em;font-size:80%;line-height:145%}.favurl{background-repeat:no-repeat;background-position:0 1px;padding-left:20px}.dan_tip{font-size:12px;margin-top:4px}.unsafe_txttip_o{cursor:pointer;display:inline-block;margin-right:4px;height:18px;line-height:16px;*line-height:19px;font-size:12px;color:#e10602;background:url('http://www.baidu.com/cache/global/img/bg-1.0.15.png') no-repeat left -762px;padding-left:18px;width:31px}.dan_tip a{color:#b95b07}#more,#u ul,#mMenu,.msg_holder{box-shadow:1px 1px 2px #ccc;-moz-box-shadow:1px 1px 2px #ccc;-webkit-box-shadow:1px 1px 2px #ccc;filter:progid:DXImageTransform.Microsoft.Shadow(Strength=2,Direction=135,Color="#cccccc")\9}.hit_top{background:url("http://www.baidu.com/cache/global/img/bg-1.1.0.png") no-repeat left -619px;padding-left:20px;line-height:18px;margin:0 15px 10px 0;width:516px}.hit_top_01{background:0;padding-left:0;*line-height:20px;width:33.7em}.hit_top_01 i{display:inline-block;background:url("http://www.baidu.com/cache/global/img/bg-1.1.0.png") no-repeat left -446px;width:16px;height:16px;vertical-align:-3px;margin-right:3px;*vertical-align:1px;_vertical-align:2px}.f a font[size="3"] font,.f font[size="-1"] a font{text-decoration:underline}h3 a font{text-decoration:underline}.c-title{font-weight:normal;font-size:16px}.c-title-size{font-size:16px}.c-abstract{font-size:13px}.c-abstract-size{font-size:13px}.c-showurl{color:#008000;font-size:13px}.c-showurl-color{color:#008000}.c-cache-color{color:#666}.c-lightblue{color:#77C}.c-highlight-color{color:#C00}.c-clearfix:after{content:".";display:block;height:0;clear:both;visibility:hidden}.c-clearfix{zoom:1}.c-wrap{word-break:break-all;word-wrap:break-word}.icp_info{color:#666;margin-top:3px}.icp_info span{width:11px;height:13px;display:inline-block;background:url(http://www.baidu.com/cache/global/img/bg-1.1.0.png) -28px -223px;margin-right:5px;vertical-align:-2px;*vertical-align:2px}.icon-gw{background:url('http://www.baidu.com/cache/global/img/bg-1.0.15.png') no-repeat left -793px;vertical-align:middle;height:18px;*height:17px;*padding-top:1px;line-height:16px;*margin:4px 0 0 0;padding-left:18px;width:31px;font-size:12px;overflow:hidden;display:inline-block;color:#00c}#con-at{margin-bottom:15px}#con-ar{padding-left:10px;margin-bottom:40px}#con-at .result-op{margin-bottom:15px;font-size:13px;line-height:1.52em}#con-ar .result-op{margin-bottom:28px;font-size:13px;line-height:1.52em}#content_left .result-op,#content_left .result{margin-bottom:18px}#lg img{vertical-align:top;margin-bottom:3px}
.wgt-ask {
border-bottom: 1px solid #f1f1f1;
background-color: #fff;
background-repeat: repeat-x;
padding: 20px 30px 10px;}
.wgt-best {
border-top: 1px solid #e9eee3;
border-bottom: 1px solid #e9eee3;
background-color: #f1fedd;
padding: 14px 30px 20px;
padding-top: 0;
position: relative;}
.wgt-best .content {
word-wrap: break-word;
}
.replyask-content, .content {
word-wrap: break-word;
word-break: break-all;
}
.line, .clearfix {
}
.mb-10 {
margin-bottom: 10px;
}
pre {
white-space: pre-wrap;
word-wrap: break-word;
}
code, kbd, pre, samp {
font-family: arial,courier new,courier,宋体b8b\4f53,monospace;
}
.line2{
border-bottom:1px solid #dcdcdc;
}
.answer_name{
font-size:14px;
font-weight:bold;
color:blue;
margin-top:10px;
font-family:'幼圆'；
}
.answer{
font-size:15px;
margin-top:10px;
margin-bottom:10px;
color:#666;
font-family:'黑体'；
}
</style>
</head>

<body>
<div id="head">
<p id="lg"><img src="logo.jpg" width="200" height="30"></p>
<form name="f" action="SearchServlet" class="fm"><input type="hidden" name="ie" value="utf-8"><input type="hidden" name="bs" value="xx"><input type="hidden" name="f" value="8"><input type="hidden" name="rsv_bp" value="1"><input type="hidden" name="rsv_spt" value="3"><span class="s_ipt_wr"><input name="searchTextField" id="kw" class="s_ipt" value="" maxlength="100" autocomplete="off"></span><span class="s_btn_wr"><input type="submit" id="su" value="搜一下" class="s_btn" onmousedown="this.className='s_btn s_btn_h'" onmouseout="this.className='s_btn'"></span>
</form>
</div>
<br>
<div id="container" class="container_l"> 
<div id="content_right" >

<%
	ArrayList<QA> ansList_new = (ArrayList<QA>)session.getAttribute("ansList_new");	
	String query_new = (String)session.getAttribute("query_new");
	int i = 0;
	for(QA ans_new : ansList_new)
	{
		i ++;
		%>
		<table cellpadding="0" cellspacing="0" class="result" id="<%=i%>">
		<tbody>
		<tr>
		<td class="f" style="padding-bottom:4px;">
		<h3 class="t">
		<a href="RankServlet?qid=<%=ans_new.getQuestion().getQid()%>&query=<%=URLEncoder.encode(query_new, "UTF-8") %>" target="_blank">
		<%
		String title = ans_new.getQuestion().getQtitle();
		int begin = 0;
		while (title.indexOf(query_new, begin) >= 0)
		{
			%>
			<%=title.substring(begin, title.indexOf(query_new, begin))%>
			<em><%=query_new%></em>
			<%
			begin = title.indexOf(query_new, begin) + query_new.length();
		}
		if (begin < title.length())
		{
			%>
			<%=title.substring(begin)%>
			<%
		}
		 %>
		</a>
		</h3>
		<font size="-1" class="m"><%=ans_new.getAnswers().size() %>个回答 - 类别: <%=ans_new.getQuestion().getCategory() %></font>
		<br>
		<font size="-1">
		<span class="m">
		<%
		if (ans_new.getAnswers().get(0).getIsbest().equals("1"))
		{
			%>
			最佳答案: 
			<%
		}
		else
		{
			%>
			答案: 
			<%
		}
		%>
		</span>
		<%
		title = ans_new.getAnswers().get(0).getAcontent();
		if (title.length() > 70)
			title = title.substring(0, 70) + "...";
		begin = 0;
		while (title.indexOf(query_new, begin) >= 0)
		{
			%>
			<%=title.substring(begin, title.indexOf(query_new, begin))%>
			<em><%=query_new%></em>
			<%
			begin = title.indexOf(query_new, begin) + query_new.length();
		}
		if (begin < title.length())
		{
			%>
			<%=title.substring(begin)%>
			<%
		}
		if (i == 10)
		{
		%>
		</font>
		</td></tr></tbody></table>
		<%
			break;
		}
		%>
		</font>
		</td></tr></tbody></table>
		<%
	}
 %>

<br>
</div>
<div id="content_left">
<%
QA ans = (QA)session.getAttribute("ans");
%>
<article class="grid qb-content" id="qb-content">
<div class="wgt-ask accuse-response line mb-5" id="wgt-ask"> 
<h1 class="mb-5" accuse="qTitle">   
<span class="ask-title"><%=ans.getQuestion().getQtitle() %></span> </h1>
</div>

<%
for(Answer res : ans.getAnswers())
{
	%>
	<div class="line2"></div>
	<div class="answer">
	<%
	if(res.getIsbest().equals("1"))
	{
		%>
<%="最佳答案：" + res.getAcontent()%>
		<%
	}
	else
	{
		%>
<%="答案：" + res.getAcontent()%>
		<%
	}
	%>
	</div>
	<%
}
%>
</article>
</div>
</div>
</body>
</html>
