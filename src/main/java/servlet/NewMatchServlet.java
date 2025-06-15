package servlet;

import dto.NewMatchRequestDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1 = request.getParameter("player1");
        String player2 = request.getParameter("player2");

        NewMatchRequestDto newMatchRequestDto = new NewMatchRequestDto(player1, player2);
        UUID uuid = ongoingMatchesService.createMatch(newMatchRequestDto);

        response.sendRedirect("/match-score?uuid=" + uuid);
    }
}
