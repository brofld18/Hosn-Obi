package at.kaindorf.htl.hosnobi.bl.db;

import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.exceptions.GameNotFoundException;
import at.kaindorf.htl.hosnobi.bl.exceptions.MaxPlayersRechedException;
import at.kaindorf.htl.hosnobi.bl.exceptions.UserNotFoundException;

import java.util.*;

public class GameDatabase {
    //region Singleton
    private static GameDatabase theInstance;
    public synchronized static GameDatabase getInstance() {
        if(theInstance == null) {
            theInstance = new GameDatabase();
        }
        return theInstance;
    }
    private GameDatabase() { }
    //endregion

    private static Map<Integer, GameManager> Games = new Hashtable<>();

    public int CreateNewGame(User user) {
        GameManager gameManager = new GameManager(user);
        int gameId = 0;
        do {
            Random random = new Random();
            gameId = random.nextInt(99999999 - 12345678 + 1) + 1;
        } while(GameIdUsed(gameId));
        gameManager.setGameId(gameId);
        Games.put(gameId, gameManager);
        return gameId;
    }

    public boolean GameIdUsed(int gameId) {
        return Games.containsKey(gameId);
    }

    public void addUserToGame(int id, User user) throws MaxPlayersRechedException {
        if(Games.get(id) == null) throw new GameNotFoundException();
        if(Arrays.stream(Games.get(id).getUsers()).anyMatch(u -> u == user))
            return;
        int index = -1;
        for (int i = 0; i < Games.get(id).getUsers().length; i++)
            if(Games.get(id).getUsers()[i] == null) {
                index = i;
                break;
            }
        if(index == -1)
            throw new MaxPlayersRechedException();
        Games.get(id).getUsers()[index] = user;
    }

    public void RemoveUserFromGame(int id, User user) {
        if(!isUserInGame(id, user.getId())) throw new UserNotFoundException();
        if(Games.get(id) == null) throw new GameNotFoundException();
        int index = -1;
        for (int i = 0; i < Games.get(id).getUsers().length; i++)
            if(Games.get(id).getUsers()[i] == user)
                index = i;
        if(index != -1) Games.get(id).getUsers()[index] = null;
    }

    public GameManager getGameById(int id) {
        GameManager game = Games.get(id);
        if(game != null)
            return game;
        throw new GameNotFoundException();
    }

    public boolean isUserInGame(int gameId, int userId) {
        return Arrays.stream(Games.get(gameId).getUsers()).anyMatch(user -> (user != null && user.getId() == userId));
    }
}
