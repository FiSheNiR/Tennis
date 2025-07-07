package entity;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentMatch {

    private UUID uuid;
    private Player player1;
    private Player player2;
    private Score player1Score;
    private Score player2Score;

}
