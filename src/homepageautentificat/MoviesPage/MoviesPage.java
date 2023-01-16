package homepageautentificat.MoviesPage;
import currentpageinterface.CurrentPage;
import json.Actions.Logout;
import json.Users.Users;
import json.Actions.Actions;
import homepageautentificat.SeeDetailsPage.SeeDetailsPage;
import homepageautentificat.HomePageAutentificat;
import homepageneautentificat.HomePageNeautentificat;
import json.JsonOut;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public final class MoviesPage implements CurrentPage {
    /**
     * Function for change the movies page to another page
     * @param currentPage current HomePage Autentificat / Neautentificat
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    @Override
    public CurrentPage changePage(final CurrentPage currentPage, final Actions actionsNode,
                                  final  List<Users> usersList, final List<Movies> moviesList,
                                  final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        FilterMovies filterMovies = new FilterMovies();
        switch (actionsNode.getPage()) {
            case "see details":
                SeeDetailsPage seeDetailsPage = new SeeDetailsPage();
                return seeDetailsPage
                        .act(actionsNode, filterMovies.showCurrentMoviesList(), output);
            case "homepage autentificat":
                return new HomePageAutentificat();
            case "logout":
                Logout logout = new Logout();
                logout.act();
                return new HomePageNeautentificat();
            case "movies":
                jsonOut = new JsonOut.Builder()
                        .error(null)
                        .moviesNode(jsonOut.moviesList(moviesList))
                        .userNode(jsonOut.createUserNode())
                        .build();
                jsonOut.createOutputNode(output);
                filterMovies.setCurrentMoviesList(moviesList);
                return new MoviesPage();
            default:
                jsonOut = new JsonOut.Builder()
                        .error("Error")
                        .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                        .userNode(null).build();
                jsonOut.createOutputNode(output);
                return currentPage;
        }
    }

    /**
     * Function for do permission actions on current movies page
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
        Feature feature;
        switch (actionsNode.getFeature()) {
            case "search":
                feature = new SearchMovie();
                break;
            case "filter":
                feature = new FilterMovies();
                break;
            default:
                jsonOut = new JsonOut.Builder()
                        .error("Error")
                        .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                        .userNode(null).build();
                jsonOut.createOutputNode(output);
                return new MoviesPage();
        }
        feature.act(actionsNode, usersList, moviesList, output);
        return currentPage;
    }

    @Override
    public void goBack(final CurrentPage currentPage, final List<Movies> moviesList,
                       final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        jsonOut = new JsonOut.Builder()
                .error(null)
                .moviesNode(jsonOut.moviesList(moviesList))
                .userNode(jsonOut.createUserNode())
                .build();
        jsonOut.createOutputNode(output);
    }
}
