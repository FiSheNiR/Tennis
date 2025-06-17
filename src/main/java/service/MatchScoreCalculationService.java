package service;

import entity.CurrentMatch;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchScoreCalculationService {

    private static final int SETS_TO_WIN_MATCH = 2;
    private static final int GAMES_TO_WIN_SET = 6;
    private static final int DIFFERENCE_BETWEEN_POINTS_TO_WIN = 2;
    private static final int DIFFERENCE_BETWEEN_GAMES_TO_WIN = 2;
    private static final int POINTS_TO_WIN_GAME = 4;

    CurrentMatch currentMatch;



    public void pointWon(int pointWinnerId){
        if (isGameOver()){
            return;
        }
        if(pointWinnerId == 1){
            currentMatch.getPlayer1Score().addPointWins();
        } else if(pointWinnerId == 2){
            currentMatch.getPlayer2Score().addPointWins();
        }
        updateGameScore();
        updateSetScore();
    }

    public boolean isGameOver(){
        return currentMatch.getPlayer1Score().getSetWins() == SETS_TO_WIN_MATCH
                || currentMatch.getPlayer2Score().getSetWins() == SETS_TO_WIN_MATCH;
    }

    private void updateGameScore(){
        if (currentMatch.getPlayer1Score().getGameWins() >= POINTS_TO_WIN_GAME
                && currentMatch.getPlayer1Score().getPointWins() - DIFFERENCE_BETWEEN_POINTS_TO_WIN
                >= currentMatch.getPlayer2Score().getPointWins()){
            currentMatch.getPlayer1Score().addGameWins();
            resetPointsScore();
        }
        if (currentMatch.getPlayer2Score().getGameWins() >= POINTS_TO_WIN_GAME
                && currentMatch.getPlayer2Score().getPointWins() - DIFFERENCE_BETWEEN_POINTS_TO_WIN
                >= currentMatch.getPlayer1Score().getPointWins()){
            currentMatch.getPlayer1Score().addGameWins();
            resetPointsScore();
        }
    }

    private void updateSetScore(){
        if (currentMatch.getPlayer1Score().getGameWins() >=GAMES_TO_WIN_SET
                && currentMatch.getPlayer1Score().getGameWins() - DIFFERENCE_BETWEEN_GAMES_TO_WIN
                >= currentMatch.getPlayer2Score().getGameWins()){
            currentMatch.getPlayer1Score().addSetWins();
            resetGameAndPointsScore();
        }
        if (currentMatch.getPlayer2Score().getGameWins() >=GAMES_TO_WIN_SET
                && currentMatch.getPlayer2Score().getGameWins() - DIFFERENCE_BETWEEN_GAMES_TO_WIN
                >= currentMatch.getPlayer1Score().getGameWins()){
            currentMatch.getPlayer2Score().addSetWins();
            resetGameAndPointsScore();
        }
    }

    private void resetGameAndPointsScore(){
        currentMatch.getPlayer1Score().resetGameWins();
        currentMatch.getPlayer2Score().resetGameWins();
        resetPointsScore();
    }

    private void resetPointsScore(){
        currentMatch.getPlayer1Score().resetPoints();
        currentMatch.getPlayer2Score().resetPoints();
    }

    private int getMatchWinner(){
        if (currentMatch.getPlayer1Score().getSetWins() == SETS_TO_WIN_MATCH){
            return currentMatch.getPlayer1().getId();
        } else {
            return currentMatch.getPlayer2().getId();
        }
    }


}
