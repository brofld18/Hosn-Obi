package at.kaindorf.htl.hosnobi.bl.resourceFactorys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Card {
    public enum CardColor {
        Cross, // -> Kreuz
        Spades, // -> Pik
        Check, // -> Karo
        Heart // -> Herz
    }
    public enum TenType {
        Ten,
        Jack,
        Queen,
        King
    }
    private int id;
    private int points;
    private CardColor color;
    private TenType tenType;

    public static int getDeckCount() {return 0;}
}
