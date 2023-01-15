package homepageautentificat.SeeDetailsPage;

import json.Actions.Logout;
import json.JsonOut;
import homepageautentificat.UpgradePage.UpgradesPage;
import homepageautentificat.HomePageAutentificat;
import currentpageinterface.CurrentPage;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.MovieActions;
import homepageautentificat.MoviesPage.Movies;
import homepageautentificat.MoviesPage.FilterMovies;
import homepageneautentificat.HomePageNeautentificat;
import homepageautentificat.MoviesPage.MoviesPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

public final class SeeDetailsPage implements CurrentPage {
    @Override
    public CurrentPage changePage(final CurrentPage currentPage, final Actions actionsNode,
                                  final List<Users> usersList, final List<Movies> moviesList,
                                  final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        switch (actionsNode.getPage()) {
            case "homepage autentificat":
                return new HomePageAutentificat();
            case "movies":
                FilterMovies filterMovies = new FilterMovies();
                filterMovies.setCurrentMoviesList(moviesList);
                ObjectNode userNode = jsonOut.createUserNode();
                ArrayNode moviesNode = jsonOut.moviesList(moviesList);
                jsonOut.createNode(output, moviesNode, userNode);
                return new MoviesPage();
            case "upgrades":
                return new UpgradesPage();
            case "logout":
                Logout logout = new Logout();
                logout.act();
                return new HomePageNeautentificat();
            default:
                return currentPage;
        }
    }
    @Override
    public CurrentPage onPage(final CurrentPage currentPage, final Actions actionsNode,
                              final List<Users> usersList, final List<Movies> moviesList,
                              final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        MovieActions movieActions;

        switch (actionsNode.getFeature()) {
            case "purchase":
                movieActions = new Purchase();
                break;
            case "watch":
                movieActions = new Watch();
                break;
            case "like":
                movieActions = new Like();
                break;
            case "rate":
                movieActions = new Rate();
                break;
            case "subscribe":
                Subscribe subscribe = new Subscribe();
                subscribe.act(actionsNode, usersList, output);
                return currentPage;
            default:
                jsonOut.errorNode(output);
                return currentPage;
        }
        movieActions.act(actionsNode, usersList, moviesList, output);
        return currentPage;
    }

    /**
     * Function for see details for something movie that is give in action node
     * @param actionsNode each actions node from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    public CurrentPage act(final Actions actionsNode, final List<Movies> moviesList,
                           final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        Movies movies = new Movies();
        for (Movies movie: moviesList) {
            if (movie.getName().equals(actionsNode.getMovie())) {
                if (movie.getCountriesBanned().contains(jsonOut.getUserCountry())) {
                    jsonOut.errorNode(output);
                    return new MoviesPage();
                } else {
                    movies.setCurrentMovie(movie);
                    ObjectNode userNode = jsonOut.createUserNode();
                    ArrayNode moviesNode = jsonOut.createMovieNode(movie);
                    jsonOut.createNode(output, moviesNode, userNode);
                    return new SeeDetailsPage();
                }
            }
        }
        jsonOut.errorNode(output);
        return new MoviesPage();
    }
}
