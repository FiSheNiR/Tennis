package dao;

import entity.Player;
import exception.DatabaseException;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class PlayerDao {

    private static final String FIND_PLAYER_BY_NAME = "from Player where name = :name";

    public Optional<Player> getPlayerByName(String name) {
        try (Session session = HibernateUtil.getSession()) {
            Player player = session.createSelectionQuery(FIND_PLAYER_BY_NAME, Player.class)
                    .setParameter("name", name)
                    .setMaxResults(1)
                    .uniqueResult();
            return Optional.ofNullable(player);

        } catch (Exception e) {
            throw new DatabaseException("Cant find player with name " + name
                    + " in database " + e.getMessage());
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
