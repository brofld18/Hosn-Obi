package at.kaindorf.htl.hosnobi.bl.resourceFactorys;

import at.kaindorf.htl.hosnobi.bl.resourceFactorys.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseCard extends Card {
    public BaseCard(int id, int points, CardColor color, TenType tenType) {
        super(id, points, color, tenType);
    }

    public static int getDeckCount() {return 33;}
}
