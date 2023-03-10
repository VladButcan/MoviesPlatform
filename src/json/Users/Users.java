package json.Users;
import homepageautentificat.MoviesPage.Movies;
import json.Credentials.Notify;
import json.Credentials.Credentials;
import json.Credentials.Notifications;
import json.Json;

import java.util.List;

public final class Users implements Notify {
    private Credentials credentials = new Credentials();

    /**
     * Function for notify all users that they subscribed to some genre of new movie genres
     * @param notifications notification about delete or add movie
     */
    public void setNotify(final Notifications notifications, final Movies movie) {
        List<Users> usersArrayList = new Json().getUsers();
        for (Users user: usersArrayList) {
            if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                for (String genre: movie.getGenres()) {
                    if (user.getCredentials().getSubscribeGenres().contains(genre)) {
                        user.getCredentials().setNotifications(notifications);
                        break;
                    }
                }
            }
        }
    }
    public Credentials getCredentials() {
        return credentials;
    }
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }
}
