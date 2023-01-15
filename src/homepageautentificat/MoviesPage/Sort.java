package homepageautentificat.MoviesPage;
import json.Actions.Actions;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;

public final class Sort {
    private String rating;
    private String duration;
    public Sort() {
        rating = null;
        duration = null;
    }

    /**
     * Function for sort the movies list by duration or rating
     * @param moviesList list of movies
     * @param output output Array Node
     */
    public List<Movies> act(final Actions actionsNode,
                            final List<Movies> moviesList, final ArrayNode output) {
        List<Movies> moviesSortList = new ArrayList<Movies>();
        SortType sortType = null;
        if (actionsNode.getFilters().getSort().getDuration() != null) {
            switch (actionsNode.getFilters().getSort().getDuration()) {
                case "increasing": sortType = new DurationIncreasingSort();
                    break;
                case "decreasing": sortType = new DurationDecreasingSort();
                    break;
                default:
                    break;
            }
            moviesSortList = sortType.sort(actionsNode, moviesList, output);
            return moviesSortList;
        }
        if (actionsNode.getFilters().getSort().getRating() != null) {
            switch (actionsNode.getFilters().getSort().getRating()) {
                case "increasing": sortType = new RatingIncreasingSort();
                    break;
                case "decreasing": sortType = new RatingDecreasingSort();
                    break;
                default:
                    break;
            }
            moviesSortList = sortType.sort(actionsNode, moviesList, output);
            return moviesSortList;
        }
        return moviesSortList;
    }

    public String getRating() {
        return rating;
    }
    public void setRating(final String rating) {
        this.rating = rating;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(final String duration) {
        this.duration = duration;
    }
}
