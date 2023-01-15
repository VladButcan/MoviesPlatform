package homepageautentificat.MoviesPage;
import json.Actions.Actions;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public final class DurationIncreasingSort implements SortType {
    @Override
    public List<Movies> sort(final Actions actionsNode,
                             final List<Movies> moviesList, final ArrayNode output) {
        moviesList.sort((o1, o2) -> {
            if (o1.getDuration().equals(o2.getDuration())) {
                switch (actionsNode.getFilters().getSort().getRating()) {
                    case "increasing":
                        return o1.getRating().compareTo(o2.getRating());
                    case "decreasing":
                        return o2.getRating().compareTo(o1.getRating());
                    default:
                        break;
                }
            }
            return o1.getDuration().compareTo(o2.getDuration());
        });
        return moviesList;
    }
}
