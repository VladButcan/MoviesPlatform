package homepageautentificat.MoviesPage;
import json.Users.Users;
import json.Actions.Actions;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public interface Feature {
    /**
     * Change between feature for current page
     * @param actionsNode one node from actions
     * @param usersList list of Users
     * @param moviesList list of movies
     * @param output output Array Node
     */
    void act(Actions actionsNode, List<Users> usersList, List<Movies> moviesList,
             ArrayNode output);
}
