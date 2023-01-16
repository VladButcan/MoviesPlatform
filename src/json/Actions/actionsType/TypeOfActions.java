package json.Actions.actionsType;

import com.fasterxml.jackson.databind.node.ArrayNode;
import currentpageinterface.CurrentPage;
import homepageautentificat.MoviesPage.Movies;
import json.Actions.Actions;
import json.Users.Users;

import java.util.List;

public interface TypeOfActions {
    /**
     * Function
     * @param currentPage variable for save current user page access
     * @param usersList list of users
     * @param actions action node from json input
     * @param moviesList list of movies
     * @param output array node for output
     */
    CurrentPage act(CurrentPage currentPage,
             List<Users> usersList, Actions actions,
             List<Movies> moviesList, ArrayNode output);
}
