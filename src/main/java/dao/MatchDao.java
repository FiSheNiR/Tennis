package dao;

import entity.Match;
import org.hibernate.Session;
import utils.HibernateUtil;

public class MatchDao {

    public Match save(Match match) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
            return match;
        }
    }
}
