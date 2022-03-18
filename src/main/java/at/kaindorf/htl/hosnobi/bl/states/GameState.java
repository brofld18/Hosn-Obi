package at.kaindorf.htl.hosnobi.bl.states;

public abstract class GameState {
    private int playerCount;
    private int currentPlayer;

    public abstract void NextPlayerTurn(int playerID);
    public abstract void  GameEnd();
    public abstract void PlayerUsedCard(int playerID);

    public GameState(int playerCount) {
        this.playerCount = playerCount;
        currentPlayer = 0;
    }
}
