package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.exceptions.IllegalStateMethodCallException;

public class HandingOutCardsState extends GameState{
    public HandingOutCardsState(GameState previousState) {
        super(previousState);
    }

    @Override
    /**
     * @param cardID If -2 player chose to keep cards. If -1, player chose to take new cards
     */
    public void ChoseCard(int oldCardId, int newCardId) throws IllegalStateMethodCallException {
        if(newCardId == -1) {
            //TODO: take new cards
        } else if(oldCardId == -1) {
            //TODO: keep cards
        } else {
            throw new IllegalStateMethodCallException();
        }
        gameManager.setGameState(new PlayerChoosingCardState(this));
    }

    @Override
    public void EndGame() {

    }
}
