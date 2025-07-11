package servlet;

import entity.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FinishedMatchesPersistenceService;
import utils.ValidationUtils;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatchesServlet", urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {

    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerName = request.getParameter("filter_by_player_name");
        int page = ValidationUtils.validatePage(request.getParameter("page"));

        List<Match> matches = finishedMatchesPersistenceService.getFinishedMatches(playerName, page);

        if (matches.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/error-page.jsp");
            return;
        } else if (matches.size() < finishedMatchesPersistenceService.getPageSize()) {
            request.setAttribute("hasNextPage", false);
        } else {
            request.setAttribute("hasNextPage", true);
        }

        request.setAttribute("matches", matches);
        request.getRequestDispatcher("/matches.jsp").forward(request,response);
    }
}
