package service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Score {
    private int setWins = 0;
    private int gameWins = 0;
    private int pointWins = 0;


    public void addSetWins() {
        setWins++;
    }

    public void addGameWins(){
        gameWins++;
    }

    public void addPointWins(){
        pointWins++;
    }

    public void resetPoints(){
        pointWins = 0;
    }

    public void resetGameWins(){
        gameWins = 0;
    }
}
