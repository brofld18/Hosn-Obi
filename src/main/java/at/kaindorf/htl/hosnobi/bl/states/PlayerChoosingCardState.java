package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.exceptions.IllegalStateMethodCallException;

public class PlayerChoosingCardState extends GameState {
    public PlayerChoosingCardState(GameState previousState) {
        super(previousState);
    }

    @Override
    public void ChoseCard(int oldcard, int newCard) throws IllegalStateMethodCallException {

    }

    @Override
    public void EndGame() {

    }


}
