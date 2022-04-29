package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.exceptions.IllegalStateMethodCallException;

public abstract class GameState {
    public GameManager gameManager;

    private int playerCount;
    private int currentPlayer;
    private boolean lastRound;

    public void PlayersLoaded() throws IllegalStateMethodCallException {
        throw new IllegalStateMethodCallException();
    }
    public void ChoseCard(int oldCardId, int newCardId) throws IllegalStateMethodCallException {
        throw new IllegalStateMethodCallException();
    }
    public void PlayerBlock() throws IllegalStateMethodCallException {
        throw new IllegalStateMethodCallException();
    }
    public void NextPlayer() throws IllegalStateMethodCallException {
        throw new IllegalStateMethodCallException();
    }
    public abstract void EndGame();

    public GameState(GameManager gameManager, int playerCount) {
        this.playerCount = playerCount;
        this.gameManager = gameManager;
        this.currentPlayer = 0;
        this.lastRound = false;
    }
    public GameState(GameState previousState) {
        this.playerCount = previousState.playerCount;
        this.gameManager = previousState.gameManager;
        currentPlayer = previousState.currentPlayer;
    }
}
