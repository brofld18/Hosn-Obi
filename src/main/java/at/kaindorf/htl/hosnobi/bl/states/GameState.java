package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.GameManager;

public abstract class GameState {
    public GameManager gameManager;

    private int playerCount;
    private int currentPlayer;

    public abstract void AllUsersLoaded();
    public abstract void NextPlayerTurn(int playerID);
    public abstract void GameEnd();
    public abstract void PlayerUsedCard(int playerID);

    public GameState(GameManager gameManager, int playerCount) {
        this.playerCount = playerCount;
        this.gameManager = gameManager;
        currentPlayer = 0;
    }
}
