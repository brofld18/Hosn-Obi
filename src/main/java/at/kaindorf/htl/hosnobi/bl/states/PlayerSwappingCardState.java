package at.kaindorf.htl.hosnobi.bl.states;

import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.api.websockets.game.GameEndpoint;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.BaseCard;
import at.kaindorf.htl.hosnobi.bl.db.UserDatabase;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.Card;
import jakarta.websocket.EncodeException;

import java.io.IOException;
import java.util.Map;

public class PlayerSwappingCardState extends GameState{
    public PlayerSwappingCardState(GameState previousState) {
        super(previousState);
        this.lastRound = false;
        try {
            GameEndpoint.broadcast(getGameManager());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EncodeException e) {
            throw new RuntimeException(e);
        }
    }
    public PlayerSwappingCardState(PlayerSwappingCardState previousState) {
        super(previousState);
        lastUser = previousState.lastUser;
        boolean hosn = false;
        for (User user :
                getGameManager().getUsers()) {
            int points = 0;
            for (Card card :
                    user.getCards()) {
                points += card.getPoints();
            }
            if(points >= 31) {
                hosn = true;
            }
        }
        getGameManager().setGameState(new ComparingState(this));
        try {
            GameEndpoint.broadcast(getGameManager());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EncodeException e) {
            throw new RuntimeException(e);
        }
    }

    private int lastUser;

    @Override
    public void ChooseCard(Map<Integer, Integer> wantSwapped) {
        Card[] userCards = UserDatabase.getInstance().getUserById(currentPlayer).getCards();
        Card[] tableCards = getGameManager().getTableDeck();

        for (Integer keyValue :
                wantSwapped.keySet()) {
            Card userCard = userCards[wantSwapped.get(keyValue)];
            userCards[wantSwapped.get(keyValue)] = tableCards[keyValue];
            tableCards[keyValue] = userCard;
        }
        UserDatabase.getInstance().getUserById(currentPlayer).setCards(userCards);
        getGameManager().setTableDeck(tableCards);
    }

    @Override
    public void NextPlayer() {
        if(lastRound && ((currentPlayer == getGameManager().getUsers().length-1 && lastUser == 0) || (currentPlayer+1 == lastUser))) {
            getGameManager().setGameState(new ComparingState(this));
            return;
        }
        if(currentPlayer == getGameManager().getUsers().length - 1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
        getGameManager().setGameState(new PlayerSwappingCardState(this));
        if(getGameManager().getUsers()[currentPlayer].getLives() == -1) {
            NextPlayer();
        }
    }

    @Override
    public void PlayerBlock() {
        lastRound = true;
        lastUser = currentPlayer;
    }
}
