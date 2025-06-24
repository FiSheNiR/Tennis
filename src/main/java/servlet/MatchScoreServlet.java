package servlet;

import entity.CurrentMatch;
import entity.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        CurrentMatch currentMatch = ongoingMatchesService.getCurrentMatch(uuid);
        req.setAttribute("currentMatch", currentMatch);
        req.getRequestDispatcher("/match-score.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        int playerId = Integer.parseInt(req.getParameter("playerId"));

        CurrentMatch currentMatch = ongoingMatchesService.getCurrentMatch(uuid);
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(currentMatch);

        matchScoreCalculationService.pointWon(playerId);

        if (matchScoreCalculationService.isMatchFinished()){
            Player winner = matchScoreCalculationService.getMatchWinner();
            finishedMatchesPersistenceService.saveMatch(currentMatch, winner);
            ongoingMatchesService.removeMatch(uuid);
            resp.sendRedirect( req.getContextPath() + "/");
        }

        resp.sendRedirect( req.getContextPath() + "/match-score?uuid=" + uuid.toString());
    }
}
