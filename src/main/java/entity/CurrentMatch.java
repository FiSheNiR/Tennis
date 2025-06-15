package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Builder
public class CurrentMatch {

    private UUID uuid;
    private String player1;
    private String player2;
    private int score;

}
