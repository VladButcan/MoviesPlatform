package json.Actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import homepageautentificat.MoviesPage.Movies;
import jdk.dynalink.NamedOperation;
import json.Credentials.Notifications;
import json.JsonOut;
import json.Users.Users;

import java.util.List;

public final class DeleteMovie {
    /**
     * Function for delete movie from movie list and return new movie list for update database
     * @param moviesList list of movies
     * @param actionsNode actions node from json input
     * @param output array node for json output
     */
    public List<Movies> delete(final List<Users> usersList, final List<Movies> moviesList,
                               final Actions actionsNode, final ArrayNode output) {
        Notifications notifications = new Notifications();
        for (Movies movie: moviesList) {
            if (movie.getName().equals(actionsNode.getDeletedMovie())) {
                for (Users user: usersList) {
                    if (user.getCredentials().getPurchasedMovies().contains(movie)) {
                        user.getCredentials().getPurchasedMovies().remove(movie);
                        user.getCredentials().getWatchedMovies().remove(movie);
                        user.getCredentials().getRatedMovies().remove(movie);
                        user.getCredentials().getLikedMovies().remove(movie);
                        if (user.getCredentials().getAccountType().equals("premium")) {
                            user.getCredentials().setNumFreePremiumMovies(
                                    user.getCredentials().getNumFreePremiumMovies() + 1);
                        } else {
                            user.getCredentials().setBalance(
                                    user.getCredentials().getBalance() + 2);
                        }
                        notifications.setMovieName(movie.getName());
                        notifications.setMessage("DELETE");
                        user.getCredentials().setNotifications(notifications);
                    }
                }
                moviesList.remove(movie);
                return moviesList;
            }
        }
        JsonOut jsonOut = new JsonOut();
        jsonOut.errorNode(output);
        return moviesList;
    }
}
