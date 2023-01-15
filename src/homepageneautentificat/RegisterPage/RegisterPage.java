package homepageneautentificat.RegisterPage;

import homepageautentificat.HomePageAutentificat;
import homepageneautentificat.HomePageNeautentificat;
import json.JsonOut;
import currentpageinterface.CurrentPage;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.Movies;
import homepageautentificat.MoviesPage.FilterMovies;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

public final class RegisterPage implements CurrentPage {
    /**
     * Function for register the new user in system and set he's credential for current user
     * @param currentPage current HomePage Autentificat / Neautentificat
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    @Override
    public CurrentPage onPage(final CurrentPage currentPage, final Actions actionsNode,
                              final List<Users> usersList, final List<Movies> moviesList,
                              final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        for (Users user: usersList) {
            if (user.getCredentials().getName().equals(actionsNode.getCredentials().getName())) {
                jsonOut.errorNode(output);
                return new HomePageNeautentificat();
            }
        }
        FilterMovies filterMovies = new FilterMovies();
        filterMovies.setCurrentMoviesList(moviesList);
        Users newUser = new Users();
        newUser.setCredentials(actionsNode.getCredentials());
        usersList.add(newUser);

        jsonOut.createUser(actionsNode);
        ObjectNode userNode  = jsonOut.createUserNode();
        jsonOut.createNode(output, userNode);

        return new HomePageAutentificat();
    }
}
