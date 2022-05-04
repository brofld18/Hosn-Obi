package at.kaindorf.htl.hosnobi.bl.db;

import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.exceptions.GameNotFoundException;
import at.kaindorf.htl.hosnobi.bl.exceptions.MaxPlayersRechedException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GameDatabaseTest {
    private User[] users = new User[]{
        UserDatabase.getInstance().newUser("Test1"),
                UserDatabase.getInstance().newUser("Test2"),
                UserDatabase.getInstance().newUser("Test3"),
                UserDatabase.getInstance().newUser("Test4"),
    };
    private int gameId;
    @Test
    @Order(0)
    void testUserAdd() {

        gameId = GameDatabase.getInstance().CreateNewGame(users[0]);
        for (int i = 1; i< users.length; i++) {
            try {
                GameDatabase.getInstance().addUserToGame(gameId, users[i]);
            } catch (MaxPlayersRechedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    @Order(1)
    void testAddExceptions() {
        int gameId = GameDatabase.getInstance().CreateNewGame(users[0]);
        assertThrows(GameNotFoundException.class, () -> {
            try {
                GameDatabase.getInstance().addUserToGame(gameId - 1, users[0]);
            } catch (GameNotFoundException gameNotFoundException) { throw gameNotFoundException; }
        });
        try {
            GameDatabase.getInstance().addUserToGame(gameId, users[1]);
            GameDatabase.getInstance().addUserToGame(gameId, users[2]);
            GameDatabase.getInstance().addUserToGame(gameId, users[3]);
        } catch (MaxPlayersRechedException e) {
            throw new RuntimeException(e);
        }
        assertThrows(MaxPlayersRechedException.class, () -> {
            GameDatabase.getInstance().addUserToGame(gameId, UserDatabase.getInstance().newUser("Test"));
        });
    }

    @Test
    @Order(2)
    void gameIdUsed() {
        int gameId = GameDatabase.getInstance().CreateNewGame(users[0]);
        assertEquals(true, GameDatabase.getInstance().GameIdUsed(gameId));
    }

    @Test
    @Order(3)
    void getGame() {
        int gameId = GameDatabase.getInstance().CreateNewGame(users[0]);
        GameManager gameManager = new GameManager(users[0]);
        gameManager.setGameId(gameId);
        assertEquals(gameManager, GameDatabase.getInstance().getGameById(gameId));
    }
}