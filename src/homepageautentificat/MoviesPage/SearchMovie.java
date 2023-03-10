package homepageautentificat.MoviesPage;
import json.Actions.Actions;
import json.Users.Users;
import json.JsonOut;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public final class SearchMovie implements Feature {

    @Override
    public void act(final Actions actionsNode, final List<Users> usersList,
                    final List<Movies> moviesList, final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        ArrayList<Movies> searchResult = new ArrayList<Movies>();
        for (Movies movie: moviesList) {
            if (movie.getName().startsWith(actionsNode.getStartsWith())) {
                searchResult.add(movie);
            }
        }
        FilterMovies filterMovies = new FilterMovies();
        filterMovies.setCurrentMoviesList(searchResult);
        jsonOut = new JsonOut.Builder()
                .error(null)
                .moviesNode(jsonOut.moviesList(searchResult))
                .userNode(jsonOut.createUserNode())
                .build();
        jsonOut.createOutputNode(output);
    }
}
