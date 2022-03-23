package at.kaindorf.htl.hosnobi.bl.db;

public class UserStaticDatabase {
    //region Singleton
    private static UserStaticDatabase theInstance;
    private UserStaticDatabase() {}
    public synchronized static UserStaticDatabase getInstance() {
        if(theInstance == null) {
            theInstance = new UserStaticDatabase();
        }
        return theInstance;
    }
    //endregion


}
