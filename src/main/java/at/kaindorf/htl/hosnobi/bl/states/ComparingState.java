package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.api.websockets.game.GameEndpoint;
import at.kaindorf.htl.hosnobi.bl.db.CardStaticDatabase;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.BaseCard;
import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.Card;
import jakarta.websocket.EncodeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComparingState extends GameState{
    public ComparingState(GameState previousState) {
        super(previousState);

        int lowestPoints = Integer.MAX_VALUE;
        List<User> losers = new ArrayList<>();

        int aliveCounter = 0;
        for (User user :
                getGameManager().getUsers()) {
            int points = 0;
            for (Card card :
                    user.getCards()) {
                points += card.getPoints();
            }
            if(lowestPoints > points) {
                lowestPoints = points;
                losers.clear();
            }
            if(lowestPoints == points) {
                losers.add(user);
            }
        }
        for (User user :
                losers) {
            user.setLives(user.getLives()-1);
        }
        for (User user :
                getGameManager().getUsers()) {
            aliveCounter += user.getLives() == -1 ? 0 : 1;
        }
        if(aliveCounter == 1) EndGame();
        else NextPlayer();
        try {
            GameEndpoint.broadcast(getGameManager());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EncodeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void EndGame() {
        getGameManager().resetCards();
        getGameManager().setGameState(new GameEndState(this));
    }

    @Override
    public void NextPlayer() {
        getGameManager().resetCards();
        getGameManager().setGameState(new HandingOutCardsState(this));
    }
}
