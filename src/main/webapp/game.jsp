<%@page import="game.GameBean.GameState" %>
<%@page import="game.Cell" %>
<%@page import="game.Line" %>
<%@page import="java.util.List" %>

<jsp:useBean id="gameBean" scope="session" class="game.GameBean" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tic Tac Toe</title>
</head>
<body>

<h1>Tic Tac Toe</h1>

<table border="1">

<%
List<Line> lines = gameBean.getGridLines();

for(Line line : lines){
%>
<tr>
<%
List<Cell> cells = gameBean.getGridStatus(line);

for(Cell cell : cells){
%>
<td>
<%
if(cell.getState() == GameState.X){
%>
<img src="<%=request.getContextPath()%>/img/state_x.png" alt="X"/>
<%
}
else if(cell.getState() == GameState.O){
%>
<img src="<%=request.getContextPath()%>/img/state_o.png" alt="O"/>
<%
}
else{
    if(request.getAttribute("winner") == null){
%>
<a href="<%=request.getContextPath()%>/gameServlet?Line=<%=cell.getLine()%>&Col=<%=cell.getCol()%>">
<%
    }
%>

<img src="<%=request.getContextPath()%>/img/state_null.png" alt="null"/>

<%
    if(request.getAttribute("winner") == null){
%>
</a>
<%
    }
}
%>
</td>
<%
}
%>
</tr>
<%
}
%>

</table>

<%
if(request.getAttribute("winner") != null){
%>
<h2><%=request.getAttribute("winner")%> Won!</h2>
<form action="<%=request.getContextPath()%>/index.jsp" method="post">
<input type="submit" name="Replay" value="Play again"><br/>
</form>
<%
}
%>

</body>
</html>