package json.Credentials;

import homepageautentificat.MoviesPage.Movies;
import json.Actions.Notify;

public final class Notifications implements Notify {
    private String movieName;
    private String message;
    /**
     * Function for set notify
     * @param notifications notification about delete or add movie
     */
    public void setNotify(final Notifications notifications, final Movies movies) {
        movieName = notifications.getMovieName();
        message = notifications.getMessage();
    }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
