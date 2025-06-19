import entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    void testGetName() {
        Player player = new Player();
        player.setName("Alice");
        assertEquals("Alice", player.getName());
    }

    @Test
    void testAllArgsConstructor() {
        Player player = new Player(1, "Bob");
        assertEquals(1, player.getId());
        assertEquals("Bob", player.getName());
    }
}
