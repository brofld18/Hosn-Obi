package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.GameManager;

public class LoadingPlayersState extends GameState{
    public LoadingPlayersState(GameManager gameManager, int playerCount) {
        super(gameManager, playerCount);
    }

    @Override
    public void PlayersLoaded() {
        gameManager.setGameState(new HandingOutCardsState(this));
    }

    @Override
    public void EndGame() {

    }
}
