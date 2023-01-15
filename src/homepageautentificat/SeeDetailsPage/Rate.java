package homepageautentificat.SeeDetailsPage;

import json.JsonOut;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.MovieActions;
import homepageautentificat.MoviesPage.Movies;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import java.util.Map;

public final class Rate implements MovieActions {
    private final Integer maxRate = 5;
    @Override
    public boolean act(final Actions actionsNode,
                       final List<Users> usersList, final List<Movies> moviesList,
                       final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        if (actionsNode.getRate() > maxRate || actionsNode.getRate() < 0) {
            jsonOut.errorNode(output);
            return false;
        }
        Movies movies = new Movies();
        for (Movies movie: moviesList) {
            if (movie.getName().equals(movies.showCurrentMovie().getName())) {
                if (jsonOut.getCurrentUser().getCredentials().getWatchedMovies().contains(movie)) {
                    if (!jsonOut.getCurrentUser().getCredentials().getRatedMovies()
                            .contains(movie)) {
                        usersList.forEach(user -> {
                            if (user.getCredentials().getName()
                                    .equals(jsonOut.getCurrentUser().getCredentials().getName())) {
                                user.getCredentials().setRatedMovies(movie);
                            }

                        });
                    }
                    Integer changeRate = 0;
                    Double newRate = 0.0;
                    for (Map.Entry<String, Double> entry
                            : movie.showMovieRate().entrySet()) {
                        if (jsonOut.getCurrentUser().getCredentials().getName()
                                .equals(entry.getKey())) {
                            movie.changeRate(jsonOut.getCurrentUser().getCredentials().getName(),
                                    actionsNode.getRate());
                            changeRate = 1;
                        }
                    }
                    if (changeRate == 0) {
                        movie.setAllRates(
                                jsonOut.getCurrentUser().getCredentials().getName(),
                                actionsNode.getRate());
                        movie.setNumRatings(
                                movie.getNumRatings() + 1);
                    }
                    for (Map.Entry<String, Double> entry
                            : movie.showMovieRate().entrySet()) {
                        newRate += entry.getValue();
                    }
                    movie.setRating(
                            newRate / movie.getNumRatings());
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
