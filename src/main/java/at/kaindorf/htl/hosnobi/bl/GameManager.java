package at.kaindorf.htl.hosnobi.bl;

import at.kaindorf.htl.hosnobi.bl.states.GameState;
import lombok.Data;

@Data
public class GameManager {
    private GameState gameState;
    private User[] users;
    public GameManager() {
        //TODO: Start with beginning GameState
        users = new User[4];
    }
}
