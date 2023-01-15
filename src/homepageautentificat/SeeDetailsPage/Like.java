package homepageautentificat.SeeDetailsPage;

import json.JsonOut;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.Movies;
import homepageautentificat.MoviesPage.MovieActions;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

public final class Like implements MovieActions {
    @Override
    public boolean act(final Actions actionsNode,
                       final List<Users> usersList, final List<Movies> moviesList,
                       final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        for (Movies movie: jsonOut.getUserWatchedMovies()) {
            if (movie.getName().equals(movie.showCurrentMovie().getName())) {
                if (!jsonOut.getUserLikedMovies().contains(movie)) {
                    usersList.forEach(user -> {
                        if (user.getCredentials().getName().equals(jsonOut.getUserName())) {
                            user.getCredentials().setLikedMovies(movie);
                        }
                    });
                    movie.setNumLikes(movie.getNumLikes() + 1);
                    ObjectNode userNode = jsonOut.createUserNode();
                    ArrayNode moviesNode = jsonOut.createMovieNode(movie);
                    jsonOut.createNode(output, moviesNode, userNode);
                    return true;
                }
            }
        }
        jsonOut.errorNode(output);
        return false;
    }
}
