package homepageautentificat.UpgradePage;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.Movies;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public interface BuyAction {
    /**
     * Change between buy token or premium account
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    void buy(Actions actionsNode,
             List<Users> usersList, List<Movies> moviesList,
             ArrayNode output);
}
