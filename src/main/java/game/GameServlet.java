package game;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import game.GameBean;
import game.GameBean.GamePlayer;

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        GameBean game = (GameBean) request.getSession(true)
                .getAttribute("gameBean");

        if (game == null) {
            game = new GameBean();
            request.getSession().setAttribute("gameBean", game);
        }

        String lineParam = request.getParameter("Line");
        String colParam = request.getParameter("Col");

        if (lineParam != null && colParam != null) {

            int line = Integer.parseInt(lineParam);
            int col = Integer.parseInt(colParam);

            game.playPlayerTurn(line, col);

            GamePlayer winner = game.getWinner();

            switch (winner) {
                case NOBODY:
                    if (game.hasEmptyCell()) {
                        game.playComputerTurn();
                        winner = game.getWinner();

                        switch (winner) {
                            case COMPUTER:
                                request.setAttribute("winner", "la computadora");
                                break;
                            case USER:
                                request.setAttribute("winner", "usted");
                                break;
                            default:
                                break;
                        }
                    }
                    break;

                case COMPUTER:
                    request.setAttribute("winner", "la computadora");
                    break;

                case USER:
                    request.setAttribute("winner", "usted");
                    break;
            }

            if (winner == GamePlayer.NOBODY && !game.hasEmptyCell()) {
                request.setAttribute("winner", "empate");
            }
        }

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "TicTacToe Game Servlet";
    }
}