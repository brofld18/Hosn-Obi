package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.GameManager;

public class StartingState extends GameState{
    public StartingState(GameManager gameManager, int playerCount) {
        super(gameManager, playerCount);
    }

    @Override
    public void AllUsersLoaded() {

    }

    @Override
    public void NextPlayerTurn(int playerID) {

    }

    @Override
    public void GameEnd() {

    }

    @Override
    public void PlayerUsedCard(int playerID) {

    }
}
