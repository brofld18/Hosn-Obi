package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.api.websockets.game.GameEndpoint;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.BaseCard;
import at.kaindorf.htl.hosnobi.bl.db.UserDatabase;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.Card;
import jakarta.websocket.EncodeException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static at.kaindorf.htl.hosnobi.bl.api.websockets.game.GameEndpoint.broadcast;

public class HandingOutCardsState extends GameState{
    public HandingOutCardsState(GameState previousState) {
        super(previousState);
        for (User user :
                getGameManager().getUsers()) {
            for (int i = 0; i < 3; i++) {
                int index = (int) (Math.random()*(getGameManager().getCardsAvailable().stream().count()));
                user.getCards()[i] = getGameManager().getCardsAvailable().get(index);
                getGameManager().getCardsUsed().add(getGameManager().getCardsAvailable().get(index));
                getGameManager().getCardsAvailable().remove(index);
            }
        }
        for (int i = 0; i < 3; i++) {
            int index = (int) (Math.random()*(getGameManager().getCardsAvailable().stream().count()));
            getGameManager().getTableDeck()[i] = getGameManager().getCardsAvailable().get(index);
            getGameManager().getCardsUsed().add(getGameManager().getCardsAvailable().get(index));
            getGameManager().getCardsAvailable().remove(index);
        }
        getGameManager().setGameState(new PlayerSwappingCardState(this));
        try {
            broadcast(getGameManager());
        } catch (IOException e) {}
        catch (EncodeException e) {}
    }


    public void SwapCards(User user, boolean swap) {
        if(swap) {
            Card[] cards = getGameManager().getTableDeck();
            getGameManager().setTableDeck(user.getCards());
            user.setCards(cards);
        }
        try {
            broadcast(getGameManager());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EncodeException e) {
            throw new RuntimeException(e);
        }
        getGameManager().setGameState(new PlayerSwappingCardState(this));
    }
}
