package homepageautentificat.MoviesPage;
import json.Actions.Actions;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public final class RatingDecreasingSort implements SortType {
    @Override
    public List<Movies> sort(final Actions actionsNode,
                             final List<Movies> moviesList, final ArrayNode output) {
        moviesList.sort((o1, o2) -> {
            return o2.getRating().compareTo(o1.getRating());
        });
        return moviesList;
    }
}
