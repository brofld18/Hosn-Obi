package at.kaindorf.htl.hosnobi.bl.db;

import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.exceptions.GameNotFoundException;
import at.kaindorf.htl.hosnobi.bl.exceptions.MaxPlayersRechedException;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class GameStaticDatabase {
    //region Singleton
    private static GameStaticDatabase theInstance;
    public synchronized static GameStaticDatabase getInstance() {
        if(theInstance == null) {
            theInstance = new GameStaticDatabase();
        }
        return theInstance;
    }
    private GameStaticDatabase() { }
    //endregion

    private static int idIndex = 0;
    private static Dictionary<Integer, GameManager> Games = new Hashtable<>();

    public GameManager CreateNewGame() {
        GameManager gameManager = new GameManager();
        Games.put(idIndex++, gameManager);
        return gameManager;
    }

    public void AddUserToGame(int id, User user) throws MaxPlayersRechedException {
        if(Games.get(id) != null) throw new GameNotFoundException();
        if(Arrays.stream(Games.get(id).getUsers()).anyMatch(u -> u == user))
            return;
        int index = -1;
        for (int i = 0; i < Games.get(id).getUsers().length; i++)
            if(Games.get(id).getUsers()[i] == null)
                index = i;
        if(index == -1)
            throw new MaxPlayersRechedException();
        Games.get(id).getUsers()[index] = user;
    }

    public void RemoveUserFromGame(int id, User user) {
        if(Games.get(id) != null) throw new GameNotFoundException();
        int index = -1;
        for (int i = 0; i < Games.get(id).getUsers().length; i++)
            if(Games.get(id).getUsers()[i] == user)
                index = i;
        if(index != -1) Games.get(id).getUsers()[index] = null;
    }
}
