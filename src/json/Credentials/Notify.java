package json.Credentials;

import homepageautentificat.MoviesPage.Movies;

public interface Notify {
    /**
     * Function for notify the user
     * @param notifications notification about delete or add movie
     */
    void setNotify(Notifications notifications, Movies movies);
}
