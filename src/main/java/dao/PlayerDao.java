package dao;

import entity.Player;
import exception.DatabaseException;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class PlayerDao {
    public Optional<Player> getPlayerByName(String name) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<Player> players =
                    session.createSelectionQuery("from Player where name = :name", Player.class)
                    .setParameter("name", name)
                            .getResultList();
            session.getTransaction().commit();
            return players.isEmpty() ? Optional.empty() : Optional.of(players.get(0));
        }
    }

    public Player savePlayer(Player player) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
            return player;
        } catch (Exception e) {
            throw new DatabaseException("Cant save player with name " + player.getName()
                    + " to database " + e.getMessage());
        }
    }
}
