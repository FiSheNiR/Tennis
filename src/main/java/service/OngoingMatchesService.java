package service;

import dao.PlayerDao;
import dto.NewMatchRequestDto;
import entity.CurrentMatch;
import entity.Match;
import entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private PlayerDao playerDao;

    private final Map<UUID, CurrentMatch> currentMatches = new ConcurrentHashMap<>();

    public UUID createMatch(NewMatchRequestDto newMatchRequestDto) {
        UUID uuid = UUID.randomUUID();
        CurrentMatch currentMatch = CurrentMatch.builder()
                .uuid(uuid)
                .player1(getOrCreatePlayer(newMatchRequestDto.getPlayer1()))
                .player2(getOrCreatePlayer(newMatchRequestDto.getPlayer2()))
                .player1Score(new Score())
                .player2Score(new Score())
                .build();
        currentMatches.put(uuid, currentMatch);
        return uuid;
    }

    public CurrentMatch getCurrentMatch(UUID uuid) {
        return currentMatches.get(uuid);
    }

    public Player getOrCreatePlayer(String playerName) {
        Optional<Player> playerOptional = playerDao.getPlayerByName(playerName);
        return playerOptional.orElseGet(() -> playerDao.savePlayer(new Player(null, playerName)));
    }
}
