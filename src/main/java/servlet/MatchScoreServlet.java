package servlet;

import entity.CurrentMatch;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        int playerId = Integer.parseInt(req.getParameter("player"));

        CurrentMatch currentMatch = ongoingMatchesService.getCurrentMatch(uuid);
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(currentMatch);

        matchScoreCalculationService.pointWon(playerId);

        if (matchScoreCalculationService.isMatchFinished()){
            //TODO Отдаем закончеенный матч
        }
    }
}
