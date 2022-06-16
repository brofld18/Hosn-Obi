package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.db.GameDatabase;
import at.kaindorf.htl.hosnobi.bl.exceptions.IllegalStateMethodCallException;
import com.google.gson.annotations.Expose;

import java.util.Map;

public abstract class GameState {

    private int gameId;
    public GameManager getGameManager() {
        return GameDatabase.getInstance().getGameById(gameId);
    }

    private int playerCount;
    public int currentPlayer;
    public boolean lastRound;

    public void PlayersLoaded() throws IllegalStateMethodCallException {
        throw new IllegalStateMethodCallException();
    }
    public void ChooseCard(Map<Integer, Integer> wantSwapped) throws IllegalStateMethodCallException {
        throw new IllegalStateMethodCallException();
    }
    public void PlayerBlock() throws IllegalStateMethodCallException {
        throw new IllegalStateMethodCallException();
    }
    public void NextPlayer() throws IllegalStateMethodCallException {
        throw new IllegalStateMethodCallException();
    }
    public void EndGame() throws IllegalStateMethodCallException {
        throw new IllegalStateMethodCallException();
    }

    public GameState(int gameId, int playerCount) {
        this.playerCount = playerCount;
        this.gameId = gameId;
        this.currentPlayer = 0;
        this.lastRound = false;
    }
    public GameState(GameState previousState) {
        this.playerCount = previousState.playerCount;
        this.gameId = previousState.gameId;
        this.currentPlayer = previousState.currentPlayer;
        this.lastRound = previousState.lastRound;
    }
}
