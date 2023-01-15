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
import com.fasterxml.jackson.databind.node.ObjectNode;
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
                filterMovies.setCurrentMoviesList(moviesList);
                ObjectNode userNode = jsonOut.createUserNode();
                ArrayNode moviesNode = jsonOut.moviesList(moviesList);
                jsonOut.createNode(output, moviesNode, userNode);
                return new MoviesPage();
            default:
                jsonOut.errorNode(output);
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
                jsonOut.errorNode(output);
                return new MoviesPage();
        }
        feature.act(actionsNode, usersList, moviesList, output);
        return currentPage;
    }

    @Override
    public void goBack(final CurrentPage currentPage, final List<Movies> moviesList,
                       final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        ObjectNode userNode = jsonOut.createUserNode();
        ArrayNode moviesNode = jsonOut.moviesList(moviesList);
        jsonOut.createNode(output, moviesNode, userNode);
    }
}
