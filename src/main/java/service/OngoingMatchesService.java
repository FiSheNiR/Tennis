package service;

import dto.NewMatchRequestDto;
import entity.CurrentMatch;
import entity.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private final Map<UUID, CurrentMatch> currentMatches = new ConcurrentHashMap<>();

    public UUID createMatch(NewMatchRequestDto newMatchRequestDto) {
        UUID uuid = UUID.randomUUID();
        CurrentMatch currentMatch = CurrentMatch.builder()
                .uuid(uuid)
                .player1(newMatchRequestDto.getPlayer1())
                .player2(newMatchRequestDto.getPlayer2())
                .score(0)
                .build();
        currentMatches.put(uuid, currentMatch);
        return uuid;
    }

    public CurrentMatch getCurrentMatch(UUID uuid) {
        return currentMatches.get(uuid);
    }
}
