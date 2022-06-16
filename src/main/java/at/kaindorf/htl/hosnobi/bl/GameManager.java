package at.kaindorf.htl.hosnobi.bl;

import at.kaindorf.htl.hosnobi.bl.db.CardStaticDatabase;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.BaseCard;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.Card;
import at.kaindorf.htl.hosnobi.bl.states.GameState;
import at.kaindorf.htl.hosnobi.bl.states.LoadingPlayersState;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class GameManager {
    private int gameId;
    
    private GameState gameState;
    private User[] users;
    private Card[] tableDeck = new Card[3];

    private List<Card> cardsUsed = new ArrayList<>() {};
    private List<Card> cardsAvailable = new ArrayList<>();
    public GameManager(User user) {
        users = new User[4];
        users[0] = user;
        cardsAvailable = CardStaticDatabase.getInstance().getCards();
    }

    public void startGame() {
        if(Arrays.stream(users).allMatch(user -> user != null)) {
            gameState = new LoadingPlayersState(this.getGameId(),
                    (int) Arrays.stream(users).filter(user -> user != null).count());
        }
    }

    public void resetCards() {
        cardsUsed.clear();
        cardsAvailable = CardStaticDatabase.getInstance().getCards();
    }
}
