package homepageautentificat.SeeDetailsPage;

import json.JsonOut;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.MovieActions;
import homepageautentificat.MoviesPage.Movies;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public final class Purchase implements MovieActions {
    /**
     * Function for purchase the movie if the user country is not baned
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    @Override
    public boolean act(final Actions actionsNode,
                       final List<Users> usersList, final List<Movies> moviesList,
                       final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        Movies movies = new Movies();
        if (moviesList.contains(movies.showCurrentMovie())) {
            if (!movies.showCurrentMovie()
                    .getCountriesBanned().contains(
                            jsonOut.getCurrentUser().getCredentials().getCountry())) {
                if (!jsonOut.getCurrentUser().getCredentials().getPurchasedMovies().contains(
                                                                    movies.showCurrentMovie())) {
                    AccountType accountType = null;
                    switch (jsonOut.getCurrentUser().getCredentials().getAccountType()) {
                        case "premium":
                            accountType = new PremiumAccount();
                            break;
                        case "standard":
                            accountType = new StandardAccount();
                            break;
                        default:
                            break;
                    }
                    accountType.purchase(
                            actionsNode, usersList, movies.showCurrentMovie(), output);
                    return true;
                }
            }
        }
        jsonOut = new JsonOut.Builder()
                .error("Error")
                .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                .userNode(null).build();
        jsonOut.createOutputNode(output);
        return false;
    }
}
