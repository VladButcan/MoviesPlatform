package homepageautentificat.MoviesPage;
import json.Actions.Actions;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public final class RatingIncreasingSort implements SortType {
    @Override
    public List<Movies> sort(final Actions actionsNode,
                             final List<Movies> moviesList, final ArrayNode output) {
            moviesList.sort((o1, o2) -> {
                return o1.getRating().compareTo(o2.getRating());
            });
        return moviesList;
    }
}
