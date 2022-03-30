package at.kaindorf.htl.hosnobi.bl;

public class Card {
    public enum CardColor {
        Cross, // -> Kreuz
        Spades, // -> Pik
        Check, // -> Karo
        Heart // -> Herz
    }
    private int id;
    private int points;
    private CardColor color;
}
