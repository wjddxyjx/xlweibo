<%@ page language="java" import="java.util.*,java.text.*,weibo.model.UserService,weibo.model.Blah" %>
<%
 	String username = 
 		(String) request.getSession().getAttribute("login");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta content='text/html;charset=UTF-8' http-equiv='content-type'>
  	<title>Gossip 微博</title>
	<link rel='stylesheet' href='WEB-INF/resource/css/member.css' type='text/css'>
  </head>
  <body>
    <div class='leftPanel'>
    	<img src='WEB-INF/resource/images/caterpillar.jpg' alt='Gossip 微博' />
    	<br><br>
    	<a href='logout.do?username="${ sessionScope.login }'>注销${ sessionScope.login }</a>
    </div>
    <form method='post' action='message.do'>
    分享新鲜事。。。<br>
    <%
    String blabla = request.getParameter("blabla");
    if(blabla == null){
    	blabla="";
    }else{
    %>
    信息要在140字以内
    <% 
    }
     %>
     <textarea rows="4" cols="60" name='blabla'>${ requestScope.blabla }</textarea><br>
    <button type='submit'>送出</button>
    </form>
    <table style='text-align:left; width:510px;height:88px;'
    		border='0' cellpadding='2' cellspacing='2'>
    	<thead>
    	<tr>
    		<th><hr></th>
    	</tr>
    	</thead>
    	<tbody>
    	<%
    		 DateFormat dateFormat=DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,Locale.CHINESE);
    		 List<Blah>blahs = (List<Blah>)request.getAttribute("blahs");
    		 for(Blah blah : blahs){
    	 %>
    	 <tr>
    	 <td style='vertical-align:top;'>
    	 	<%=blah.getUsername()%><br>
    	 	<%=blah.getTxt() %><br>
    	 	<%=dateFormat.format(blah.getDate()) %>
    	 	<a href='delete.do?message=<%=blah.getDate().getTime() %>'>删除</a>
    	 	<hr>
    	 </td>
    	 </tr>
    	 <%
    	 }
    	  %>
    	 
    	</tbody>
    
    </table>
    <hr style='width:100%;height:1px;'>
  </body>
</html>
