package homepageneautentificat.LoginPage;
import homepageautentificat.HomePageAutentificat;
import homepageneautentificat.HomePageNeautentificat;
import json.JsonOut;
import currentpageinterface.CurrentPage;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.Movies;
import homepageautentificat.MoviesPage.FilterMovies;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public final class LoginPage implements CurrentPage {
    /**
     * Function for login the current user in system
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
                if (user.getCredentials().getPassword()
                        .equals(actionsNode.getCredentials().getPassword())) {
                    jsonOut.setCurrentUser(user);
                    jsonOut.setActiveUser(user.getCredentials().getName());

                    jsonOut = new JsonOut.Builder()
                            .error(null)
                            .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                            .userNode(jsonOut.createUserNode())
                            .build();
                    jsonOut.createOutputNode(output);

                    FilterMovies filterMovies = new FilterMovies();
                    filterMovies.setCurrentMoviesList(moviesList);
                    return new HomePageAutentificat();
                }
            }
        }
        jsonOut = new JsonOut.Builder()
                .error("Error")
                .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                .userNode(null).build();
        jsonOut.createOutputNode(output);
        return new HomePageNeautentificat();
    }
}
