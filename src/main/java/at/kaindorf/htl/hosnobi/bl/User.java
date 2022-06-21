package at.kaindorf.htl.hosnobi.bl;

import at.kaindorf.htl.hosnobi.bl.resourceFactorys.BaseCard;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.Card;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @NonNull
    private int id;
    @NonNull
    private String username;
    @NonNull
    private Card[] cards;
    @NonNull
    private int lives;
    @NonNull
    private boolean loaded = false;
    @NonNull
    private boolean blocked = false;
}
