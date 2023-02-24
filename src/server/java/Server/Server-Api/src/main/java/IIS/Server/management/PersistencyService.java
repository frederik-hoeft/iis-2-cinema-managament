package IIS.Server.management;

import generated.cinema.Movie;

public class PersistencyService {

    private static final PersistencyService instance = new PersistencyService();

    private PersistencyService() {
    }

    public static PersistencyService getInstance() {
        Movie doesThisWork = null;
        return instance;
    }
}
