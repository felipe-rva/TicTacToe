<jsp:useBean id="gameBean" scope="session" class="game.GameBean" /> 

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Triki</title> 
    </head> 
    <body> 
        <h1>Triki</h1> 
        <form action="entryServlet" method="post"> 
            <input type="submit" name="User" value="tu inicias"><br/> 
            <input type="submit" name="Computer" value="la computadora inicia"> 
        </form> 
    </body> 
</html> 