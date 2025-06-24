package service;

import entity.CurrentMatch;
import entity.Player;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchScoreCalculationService {

    private static final int SETS_TO_WIN_MATCH = 2;
    private static final int GAMES_TO_WIN_SET = 6;
    private static final int DIFFERENCE_BETWEEN_POINTS_TO_WIN = 2;
    private static final int DIFFERENCE_BETWEEN_GAMES_TO_WIN = 2;
    private static final int POINTS_TO_WIN_GAME = 4;
    private static final int POINTS_TO_WIN_TIEBREAK = 7;

    CurrentMatch currentMatch;



    public void pointWon(int pointWinnerId){
        if (isMatchFinished()){
            return;
        }
        if(pointWinnerId == 1){
            currentMatch.getPlayer1Score().addPointWins();
        } else if(pointWinnerId == 2){
            currentMatch.getPlayer2Score().addPointWins();
        }
        if (isTiebreak()) {
            updateGameScore(POINTS_TO_WIN_TIEBREAK);
        } else {
            updateGameScore(POINTS_TO_WIN_GAME);
        }
        updateSetScore();
    }


    public boolean isMatchFinished(){
        return currentMatch.getPlayer1Score().getSetWins() == SETS_TO_WIN_MATCH
                || currentMatch.getPlayer2Score().getSetWins() == SETS_TO_WIN_MATCH;
    }

    public Player getMatchWinner(){
        if (currentMatch.getPlayer1Score().getSetWins() == SETS_TO_WIN_MATCH){
            return currentMatch.getPlayer1();
        } else {
            return currentMatch.getPlayer2();
        }
    }

    public boolean isTiebreak(){
        return currentMatch.getPlayer1Score().getGameWins() == GAMES_TO_WIN_SET
                && currentMatch.getPlayer2Score().getGameWins() == GAMES_TO_WIN_SET;
    }

    private void updateGameScore(int numberOfPointsToWin){
        if (currentMatch.getPlayer1Score().getPointWins() >= numberOfPointsToWin
                && currentMatch.getPlayer1Score().getPointWins() - DIFFERENCE_BETWEEN_POINTS_TO_WIN
                >= currentMatch.getPlayer2Score().getPointWins()){
            if (isTiebreak()){
                currentMatch.getPlayer1Score().addSetWins();
            } else {
                currentMatch.getPlayer1Score().addGameWins();
            }
            resetPointsScore();
        }
        if (currentMatch.getPlayer2Score().getPointWins() >= numberOfPointsToWin
                && currentMatch.getPlayer2Score().getPointWins() - DIFFERENCE_BETWEEN_POINTS_TO_WIN
                >= currentMatch.getPlayer1Score().getPointWins()){
            if (isTiebreak()){
                currentMatch.getPlayer2Score().addSetWins();
            } else {
                currentMatch.getPlayer2Score().addGameWins();
            }
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




}
