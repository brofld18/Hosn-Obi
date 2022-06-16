package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.api.websockets.game.GameEndpoint;
import at.kaindorf.htl.hosnobi.bl.db.GameDatabase;
import jakarta.websocket.EncodeException;

import java.io.IOException;

public class GameEndState extends GameState {
    public GameEndState(GameState previousState) {
        super(previousState);
        try {
            GameEndpoint.broadcast(getGameManager());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EncodeException e) {
            throw new RuntimeException(e);
        }
    }

    public void PlayersLoaded() {
        for (User user :
                getGameManager().getUsers()) {
            user.setLives(2);
        }
        getGameManager().setGameState(new HandingOutCardsState(this));
    }

    @Override
    public void EndGame() {

    }
}
