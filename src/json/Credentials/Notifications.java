package json.Credentials;

import homepageautentificat.SeeDetailsPage.Notify;

public final class Notifications implements Notify {
    private String movieName;
    private String message;

    public void setNotify(Notifications notifications) {
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
