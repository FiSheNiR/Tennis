package service;

import dao.MatchDao;
import entity.CurrentMatch;
import entity.Match;
import entity.Player;

public class FinishedMatchesPersistenceService {

    private final MatchDao matchDao = new MatchDao();

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
