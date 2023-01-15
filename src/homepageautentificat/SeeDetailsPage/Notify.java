package homepageautentificat.SeeDetailsPage;

import json.Credentials.Notifications;

public interface Notify {
    /**
     * Function for notify the user
     * @param notifications notification about delete or add movie
     */
    void setNotify(Notifications notifications);
}
