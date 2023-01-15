package homepageautentificat.MoviesPage;
import json.Actions.Actions;
import json.Users.Users;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public interface MovieActions {
    /**
     * Functions for change the actions on movies page
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    boolean act(Actions actionsNode,
                List<Users> usersList, List<Movies> moviesList,
                ArrayNode output);
}
