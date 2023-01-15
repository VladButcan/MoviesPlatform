package homepageautentificat.MoviesPage;
import json.Actions.Actions;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public interface SortType {
    /**
     * Interface for implement the sort type (decreasing or increasing)
     * @param actionsNode each actions node from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    List<Movies> sort(Actions actionsNode, List<Movies> moviesList, ArrayNode output);
}
