package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.api.websockets.game.GameEndpoint;
import jakarta.websocket.EncodeException;

import java.io.IOException;
import java.util.Arrays;

/**
 * This is the first state that waits for players to load in the game
 */
public class LoadingPlayersState extends GameState{
    public LoadingPlayersState(int gameId, int playerCount) {
        super(gameId, playerCount);
    }

    /**
     * This start the game
     */
    @Override
    public void PlayersLoaded() {
        if(Arrays.stream(getGameManager().getUsers()).allMatch(user -> user.isLoaded())) {
            getGameManager().setGameState(new HandingOutCardsState(this));
        }
    }
}
