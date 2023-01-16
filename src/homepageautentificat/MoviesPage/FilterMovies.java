package homepageautentificat.MoviesPage;
import json.Actions.Actions;
import json.Users.Users;
import json.JsonOut;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public final class FilterMovies implements Feature {
    private static List<Movies> currentMoviesList;
    @Override
    public void act(final Actions actionsNode, final List<Users> usersList,
                    final List<Movies> moviesList, final ArrayNode output) {
        List<Movies> filterMoviesList = moviesList;
        if (actionsNode.getFilters().getSort().getRating() != null
                || actionsNode.getFilters().getSort().getDuration() != null) {
            Sort sort = new Sort();
            filterMoviesList =  sort.act(actionsNode, moviesList, output);
        }
        if (!actionsNode.getFilters().getContains().getActors().equals(new ArrayList<String>())
                || !actionsNode.getFilters().getContains().getGenre()
                   .equals(new ArrayList<String>())) {
            Contains contains = new Contains();
            filterMoviesList = contains.act(actionsNode, filterMoviesList, output);
        }
        JsonOut jsonOut = new JsonOut();
        jsonOut = new JsonOut.Builder()
                .error(null)
                .moviesNode(jsonOut.moviesList(filterMoviesList))
                .userNode(jsonOut.createUserNode())
                .build();
        jsonOut.createOutputNode(output);
        currentMoviesList = filterMoviesList;
    }

    /**
     * @return the current movie list after filter
     */
    public List<Movies> showCurrentMoviesList() {
        return currentMoviesList;
    }
    /**
     * @param currentMoviesList list of movies after filter
     */
    public void setCurrentMoviesList(final List<Movies> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }
}
