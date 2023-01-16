package homepageautentificat.SeeDetailsPage;

import json.JsonOut;
import homepageautentificat.MoviesPage.MovieActions;
import homepageautentificat.MoviesPage.Movies;
import json.Actions.Actions;
import json.Users.Users;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
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
            if (jsonOut.getCurrentUser().getCredentials().getPurchasedMovies()
                    .contains(movies.showCurrentMovie())) {
                if (!jsonOut.getCurrentUser().getCredentials().getWatchedMovies()
                        .contains(movies.showCurrentMovie())) {
                    JsonOut finalJsonOut = jsonOut;
                    usersList.forEach(user -> {
                        if (user.getCredentials().getName()
                                .equals(finalJsonOut.getCurrentUser().getCredentials().getName())) {
                            user.getCredentials().setWatchedMovies(movies.showCurrentMovie());
                        }
                    });
                }
                jsonOut = new JsonOut.Builder()
                        .error(null)
                        .moviesNode(jsonOut.createMovieNode(movies.showCurrentMovie()))
                        .userNode(jsonOut.createUserNode())
                        .build();
                jsonOut.createOutputNode(output);
                return true;
            }
        }
        jsonOut = new JsonOut.Builder()
                .error("Error")
                .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                .userNode(null).build();
        jsonOut.createOutputNode(output);
        return false;
    }
}
