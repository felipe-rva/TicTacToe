package game;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import game.GameBean;
import game.GameBean.GamePlayer;

public class GameServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
    HttpServletResponse response)
            throws ServletException, IOException {

        GameBean game = (GameBean)
        request.getSession(true).getAttribute("gameBean");

        int line = Integer.parseInt(request.getParameter("Line"));
        int col = Integer.parseInt(request.getParameter("Col"));

        game.playPlayerTurn(line, col);

        GamePlayer winner = game.getWinner();
        switch(winner){
            case NOBODY:
                if(game.hasEmptyCell()){
                    game.playComputerTurn();
                    switch(game.getWinner()){
                        case NOBODY:
                            break;
                        case COMPUTER:
                            request.setAttribute("winner", "The computer");
                            break;
                        case USER:
                            request.setAttribute("winner", "You");
                            break;
                    }
                }
                break;
            case COMPUTER:
                request.setAttribute("winner", "The computer");
                break;
            case USER:
                request.setAttribute("winner", "You");
                break;
        }
        if(winner == GamePlayer.NOBODY && !game.hasEmptyCell()){
            request.setAttribute("winner", "Nobody");
        }
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
    response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
    response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}