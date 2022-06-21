package at.kaindorf.htl.hosnobi.bl.resourceFactorys;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Card {
    public enum CardColor {
        @SerializedName("clubs")
        Cross, // -> Kreuz
        @SerializedName("diamonds")
        Spades, // -> Pik
        @SerializedName("spades")
        Check, // -> Karo
        @SerializedName("hearts")
        Heart // -> Herz
    }
    public enum TenType {
        @SerializedName("ten")
        Ten,
        @SerializedName("jack")
        Jack,
        @SerializedName("queen")
        Queen,
        @SerializedName("king")
        King
    }
    private int id;
    private int points;
    private CardColor color;
    private TenType tenType;

    public static int getDeckCount() {return 0;}
}
