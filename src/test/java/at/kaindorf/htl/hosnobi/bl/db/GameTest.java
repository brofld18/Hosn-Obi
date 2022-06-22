package at.kaindorf.htl.hosnobi.bl.db;

import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.exceptions.MaxPlayersRechedException;
import at.kaindorf.htl.hosnobi.bl.states.GameEndState;
import at.kaindorf.htl.hosnobi.bl.states.HandingOutCardsState;
import at.kaindorf.htl.hosnobi.bl.states.LoadingPlayersState;
import at.kaindorf.htl.hosnobi.bl.states.PlayerSwappingCardState;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {


    @Test
    void GameTestBasic() throws MaxPlayersRechedException {
        UserDatabase.getInstance().newUser("Test1");
        UserDatabase.getInstance().newUser("Test2");
        UserDatabase.getInstance().newUser("Test3");
        UserDatabase.getInstance().newUser("Test4");

        int gameId = GameDatabase.getInstance().CreateNewGame(UserDatabase.getInstance().getUserById(0));
        GameDatabase.getInstance().addUserToGame(gameId, UserDatabase.getInstance().getUserById(1));
        GameDatabase.getInstance().addUserToGame(gameId, UserDatabase.getInstance().getUserById(2));
        GameDatabase.getInstance().addUserToGame(gameId, UserDatabase.getInstance().getUserById(3));

        GameManager gameManager = GameDatabase.getInstance().getGameById(gameId);

        gameManager.startGame();
        assertEquals(gameManager.getGameState() instanceof LoadingPlayersState, true);
        ((LoadingPlayersState) gameManager.getGameState()).PlayersLoaded();

        boolean done = false;
        while (!done) {
            assertEquals(gameManager.getGameState() instanceof HandingOutCardsState, true);

            //Player 1 swap
            assertEquals(gameManager.getGameState() instanceof PlayerSwappingCardState, true);
            ((PlayerSwappingCardState) gameManager.getGameState()).ChooseCard(Map.of(
                    0, 1,
                    1, 2,
                    2, 0
            ));
            ((PlayerSwappingCardState) gameManager.getGameState()).NextPlayer();

            //Player 2 swap
            assertEquals(gameManager.getGameState() instanceof PlayerSwappingCardState, true);
            ((PlayerSwappingCardState) gameManager.getGameState()).ChooseCard(Map.of(
                    0, 1,
                    1, 2
            ));
            ((PlayerSwappingCardState) gameManager.getGameState()).NextPlayer();

            //Player 3 swap
            assertEquals(gameManager.getGameState() instanceof PlayerSwappingCardState, true);
            ((PlayerSwappingCardState) gameManager.getGameState()).ChooseCard(Map.of());
            ((PlayerSwappingCardState) gameManager.getGameState()).NextPlayer();

            //Player 3 swap
            assertEquals(gameManager.getGameState() instanceof PlayerSwappingCardState, true);
            ((PlayerSwappingCardState) gameManager.getGameState()).ChooseCard(Map.of(
                    0, 1,
                    1, 2
            ));
            ((PlayerSwappingCardState) gameManager.getGameState()).NextPlayer();

            //Player 1 swap
            assertEquals(gameManager.getGameState() instanceof PlayerSwappingCardState, true);
            ((PlayerSwappingCardState) gameManager.getGameState()).PlayerBlock();
            ((PlayerSwappingCardState) gameManager.getGameState()).ChooseCard(Map.of(
                    0, 1,
                    1, 2
            ));
            ((PlayerSwappingCardState) gameManager.getGameState()).NextPlayer();

            //Player 2 swap
            assertEquals(gameManager.getGameState() instanceof PlayerSwappingCardState, true);
            ((PlayerSwappingCardState) gameManager.getGameState()).ChooseCard(Map.of());
            ((PlayerSwappingCardState) gameManager.getGameState()).NextPlayer();

            //Player 3 swap
            assertEquals(gameManager.getGameState() instanceof PlayerSwappingCardState, true);
            ((PlayerSwappingCardState) gameManager.getGameState()).ChooseCard(Map.of());
            ((PlayerSwappingCardState) gameManager.getGameState()).NextPlayer();

            //Player 4 swap
            assertEquals(gameManager.getGameState() instanceof PlayerSwappingCardState, true);
            ((PlayerSwappingCardState) gameManager.getGameState()).ChooseCard(Map.of());
            ((PlayerSwappingCardState) gameManager.getGameState()).NextPlayer();

            if (gameManager.getGameState().getClass() == GameEndState.class) {
                done = true;
            }
        }
    }
}
