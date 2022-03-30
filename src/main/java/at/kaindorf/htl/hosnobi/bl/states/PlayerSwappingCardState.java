package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.exceptions.IllegalStateMethodCallException;

public class PlayerSwappingCardState extends GameState{
    public PlayerSwappingCardState(GameState previousState) {
        super(previousState);
    }

    @Override
    /**
     * @param cardID -1 is equivalent to all cards
     */
    public void ChoseCard(int cardID) throws IllegalStateMethodCallException {
        //TODO: Assign active switch card


    }

    @Override
    public void EndGame() {

    }
}
