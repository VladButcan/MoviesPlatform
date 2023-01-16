package homepageautentificat.SeeDetailsPage;

import json.JsonOut;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.Movies;
import homepageautentificat.MoviesPage.MovieActions;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public final class Like implements MovieActions {
    @Override
    public boolean act(final Actions actionsNode,
                       final List<Users> usersList, final List<Movies> moviesList,
                       final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        for (Movies movie: jsonOut.getCurrentUser().getCredentials().getWatchedMovies()) {
            if (movie.getName().equals(movie.showCurrentMovie().getName())) {
                if (!jsonOut.getCurrentUser().getCredentials().getLikedMovies().contains(movie)) {
                    JsonOut finalJsonOut = jsonOut;
                    usersList.forEach(user -> {
                        if (user.getCredentials().getName().equals(
                                finalJsonOut.getCurrentUser().getCredentials().getName())) {
                            user.getCredentials().setLikedMovies(movie);
                        }
                    });
                    movie.setNumLikes(movie.getNumLikes() + 1);
                    jsonOut = new JsonOut.Builder()
                            .error(null)
                            .moviesNode(jsonOut.createMovieNode(movie))
                            .userNode(jsonOut.createUserNode())
                            .build();
                    jsonOut.createOutputNode(output);
                    return true;
                }
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
