package dao;

import entity.Match;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class MatchDao {

    private static final String FIND_BY_NAME = "from Match m where m.player1.name = :player or m.player2.name = :player";
    private static final String FIND_ALL = "select m from Match m";

    public List<Match> findAllMatchesByPlayerName(String playerName, int page, int pageSize) {
        List<Match> matchesList;
        try (Session session = HibernateUtil.getSession()) {
            matchesList = session.createSelectionQuery(FIND_BY_NAME, Match.class)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .setParameter("player", playerName).getResultList();
        }
        return matchesList;
    }

    public List<Match> findAllMatches(int page, int pageSize) {
        List<Match> matchesList;
        try (Session session = HibernateUtil.getSession()) {
            matchesList = session.createQuery(FIND_ALL, Match.class)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
        return matchesList;
    }

    public void save(Match match) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }
}
