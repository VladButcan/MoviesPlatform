package homepageautentificat.SeeDetailsPage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import homepageautentificat.MoviesPage.Movies;
import json.Actions.Actions;
import json.Users.Users;

import java.util.List;

public interface AccountType {
    /**
     * Function for purchase the movie by account type
     * @param actionsNode
     * @param usersList
     * @param currentMovie
     * @param output
     */
    boolean purchase(Actions actionsNode,
                  List<Users> usersList, Movies currentMovie, ArrayNode output);
}
