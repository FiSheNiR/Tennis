package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Score {
    private int setWins = 0;
    private int gameWins = 0;
    private int pointWins = 0;
    private String points = "0";
    private Points pointsView = Points.ZERO;

    public void addSetWins() {
        setWins++;
    }

    public void addGameWins(){
        gameWins++;
    }

    public void addPointWins(){
        pointsView = pointsView.next();
        points = pointsView.getValue();
        pointWins++;
    }

    public void resetPointsView(){
        pointsView = Points.ZERO;
    }

    public void resetPoints(){
        resetPointsView();
        points = pointsView.getValue();
        pointWins = 0;
    }

    public void resetGameWins(){
        gameWins = 0;
    }
}
