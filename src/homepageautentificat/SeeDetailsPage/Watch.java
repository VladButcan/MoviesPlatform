package homepageautentificat.SeeDetailsPage;

import json.JsonOut;
import homepageautentificat.MoviesPage.MovieActions;
import homepageautentificat.MoviesPage.Movies;
import json.Actions.Actions;
import json.Users.Users;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

public final class Watch implements MovieActions {
    /**
     * Function for watch the movie if user bought this movie
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    @Override
    public boolean act(final Actions actionsNode,
                       final List<Users> usersList, final List<Movies> moviesList,
                       final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        Movies movies = new Movies();
        if (moviesList.contains(movies.showCurrentMovie())) {
            if (jsonOut.getUserPurchasedMovies().contains(movies.showCurrentMovie())) {
                if (!jsonOut.getUserWatchedMovies().contains(movies.showCurrentMovie())) {
                    usersList.forEach(user -> {
                        if (user.getCredentials().getName().equals(jsonOut.getUserName())) {
                            user.getCredentials().setWatchedMovies(movies.showCurrentMovie());
                        }
                    });
                }
                ObjectNode userNode = jsonOut.createUserNode();
                ArrayNode moviesNode = jsonOut.createMovieNode(movies.showCurrentMovie());
                jsonOut.createNode(output, moviesNode, userNode);
                return true;
            }
        }
        jsonOut.errorNode(output);
        return false;
    }
}
