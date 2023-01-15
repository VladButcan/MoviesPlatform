package currentpageinterface;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.Movies;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public interface CurrentPage {
    /**
     * Function for call change page action
     * @param currentPage current HomePage Autentificat / Neautentificat
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    default CurrentPage changePage(CurrentPage currentPage, Actions actionsNode,
                                   List<Users> usersList, List<Movies> moviesList,
                                   ArrayNode output) {
        return currentPage;
    }
    /**
     * Function for do something actions on page
     * @param currentPage current HomePage Autentificat / Neautentificat
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     * @return return new HomePage
     */
    default CurrentPage onPage(CurrentPage currentPage, Actions actionsNode,
                               List<Users> usersList, List<Movies> moviesList,
                               ArrayNode output) {
        return currentPage;
    }

    /**
     * Function for act the back action
     * @param currentPage current using page
     * @param moviesList list of movies
     */
    default void goBack(CurrentPage currentPage, List<Movies> moviesList, ArrayNode output) {
    }
}
