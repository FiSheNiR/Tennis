package service;

import dao.MatchDao;
import entity.CurrentMatch;
import entity.Match;
import entity.Player;

import java.util.List;

public class FinishedMatchesPersistenceService {


    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
    private final MatchDao matchDao = new MatchDao();
    private final int PAGE_SIZE = 5;

    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }

    public int getPageSize() {
        return PAGE_SIZE;
    }

    public List<Match> getFinishedMatches(String playerName, int page) {
        List<Match> matches;
        if (playerName == null || playerName.isEmpty()) {
            matches = matchDao.findAllMatches(page, PAGE_SIZE);
        } else {
            matches = matchDao.findAllMatchesByPlayerName(playerName, page, PAGE_SIZE);
        }
        return matches;
    }

    public void saveMatch(CurrentMatch currentMatch, Player winner) {
        Match match = convertCurrentMatchToEntity(currentMatch, winner);
        matchDao.save(match);
    }

    private Match convertCurrentMatchToEntity(CurrentMatch currentMatch, Player winner) {
        Match match = new Match();
        match.setPlayer1(currentMatch.getPlayer1());
        match.setPlayer2(currentMatch.getPlayer2());
        match.setWinner(winner);
        return match;
    }
}
