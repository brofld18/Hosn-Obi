package at.kaindorf.htl.hosnobi.bl.db;

import at.kaindorf.htl.hosnobi.bl.GameManager;
import at.kaindorf.htl.hosnobi.bl.User;
import at.kaindorf.htl.hosnobi.bl.exceptions.UserNotFoundException;
import at.kaindorf.htl.hosnobi.bl.resourceFactorys.Card;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    //region Singleton
    private static UserDatabase theInstance;
    private UserDatabase() {}
    public synchronized static UserDatabase getInstance() {
        if(theInstance == null) {
            theInstance = new UserDatabase();
        }
        return theInstance;
    }
    //endregion

    private List<User> userList = new ArrayList<>();

    public boolean userExists(int userId) {
        return userList.stream().anyMatch(user1 -> user1.getId() == userId);
    }

    public boolean userExists(String username) {
        return userList.stream().anyMatch(user1 -> user1.getUsername().equals(username));
    }

    public synchronized User newUser(String username /*TODO: Add gamesettings*/) {
        User user = new User(userList.size(), username, new Card[3], 2);
        userList.add(user);
        return user;
    }

    public User getUserById(int id) {
        if(userExists(id)) {
            return userList.stream().filter(user -> user.getId() == id).findAny().get();
        }
        throw new UserNotFoundException();
    }
}
