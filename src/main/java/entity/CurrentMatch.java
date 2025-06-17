package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import service.Score;

import java.util.UUID;


@Getter
@Setter
@Builder
public class CurrentMatch {

    private UUID uuid;
    private Player player1;
    private Player player2;
    private Score player1Score;
    private Score player2Score;

}
