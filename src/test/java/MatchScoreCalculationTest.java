import entity.CurrentMatch;
import entity.Player;
import org.junit.jupiter.api.*;
import service.MatchScoreCalculationService;
import service.Score;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationTest {

    private CurrentMatch currentMatch;
    private MatchScoreCalculationService matchScoreCalculationService;

    @BeforeEach
    void setUp() {
        // Подготовка объектов перед каждым тестом
        Player player1 = new Player(1, "Bob");
        Player player2 = new Player(2, "John");
        Score score1 = new Score();
        Score score2 = new Score();
        UUID uuid = UUID.randomUUID();

        currentMatch = new CurrentMatch();
        currentMatch.setUuid(uuid);
        currentMatch.setPlayer1(player1);
        currentMatch.setPlayer2(player2);
        currentMatch.setPlayer1Score(score1);
        currentMatch.setPlayer2Score(score2);

        matchScoreCalculationService = new MatchScoreCalculationService(currentMatch);
    }

    @Test
    void gameContinueOnTieBreak() {
        currentMatch.getPlayer1Score().setPointWins(3);
        currentMatch.getPlayer2Score().setPointWins(3);
        matchScoreCalculationService.pointWon(1);
        assertEquals(0, currentMatch.getPlayer1Score().getGameWins());
    }

    @Test
    void gameWins() {
        currentMatch.getPlayer1Score().setPointWins(3);
        matchScoreCalculationService.pointWon(1);
        assertEquals(1, currentMatch.getPlayer1Score().getGameWins());
        assertEquals(0, currentMatch.getPlayer1Score().getPointWins());
    }

    @Test
    void setWins() {
        currentMatch.getPlayer1Score().setGameWins(5);
        currentMatch.getPlayer1Score().setPointWins(3);
        currentMatch.getPlayer2Score().setGameWins(4);
        matchScoreCalculationService.pointWon(1);
        assertEquals(1, currentMatch.getPlayer1Score().getSetWins());
    }

    @Test
    void setWinsInTieBreak() {
        currentMatch.getPlayer1Score().setGameWins(6);
        currentMatch.getPlayer2Score().setGameWins(5);
        currentMatch.getPlayer2Score().setPointWins(3);
        matchScoreCalculationService.pointWon(2);
        currentMatch.getPlayer2Score().setPointWins(6);
        currentMatch.getPlayer1Score().setPointWins(6);
        matchScoreCalculationService.pointWon(2);
        matchScoreCalculationService.pointWon(2);
        assertEquals(1, currentMatch.getPlayer2Score().getSetWins());
    }
}