package at.kaindorf.htl.hosnobi.bl.db;

import at.kaindorf.htl.hosnobi.bl.resourceFactorys.BaseCard;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.Card;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardStaticDatabase {
    //region Singleton
    private static CardStaticDatabase theInstance;
    public synchronized static CardStaticDatabase getInstance() {
        if(theInstance == null) {
            theInstance = new CardStaticDatabase();
        }
        return theInstance;
    }
    private CardStaticDatabase() { }
    //endregion

    private Type cardType = BaseCard.class;

    public List<Card> getCards() {
        List<Card> cards;
        switch (cardType.getTypeName()) {
            default:
                int idCounter = 0;
                cards = new ArrayList();
                for (Card.CardColor cardColor :
                        Card.CardColor.values()) {
                    for (int i = 1; i <= 8; i++) {
                        int points = 6;
                        Card.TenType tenType = null;
                        if(i%8 < 3) points += i%8;
                        else if(i%8 == 7) points = 11;
                        else {
                            points = 10;
                            tenType = i%8 == 3 ? Card.TenType.Ten :
                                    i%8 == 4 ? Card.TenType.Jack :
                                    i%8 == 5 ? Card.TenType.Queen : Card.TenType.King;
                        }
                        cards.add(new BaseCard(idCounter++, points, cardColor, tenType));
                    }
                }
                return cards;
        }
    }
}
