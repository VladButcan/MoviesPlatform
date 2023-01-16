package homepageautentificat.SeeDetailsPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import homepageautentificat.MoviesPage.Movies;
import json.Actions.Actions;
import json.JsonOut;
import json.Users.Users;

import java.util.ArrayList;
import java.util.List;

public final class PremiumAccount implements AccountType {
    @Override
    public boolean purchase(final Actions actionsNode, final List<Users> usersList,
                         final Movies currentMovie, final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        for (Users user: usersList) {
            if (user.getCredentials().getName().equals(
                    jsonOut.getCurrentUser().getCredentials().getName())) {
                if (jsonOut.getCurrentUser().getCredentials().getNumFreePremiumMovies() > 0) {
                    jsonOut.getCurrentUser().getCredentials().setNumFreePremiumMovies(
                    jsonOut.getCurrentUser().getCredentials().getNumFreePremiumMovies() - 1);
                    user.getCredentials().setNumFreePremiumMovies(
                            jsonOut.getCurrentUser().getCredentials().getNumFreePremiumMovies());
                } else {
                    if (user.getCredentials().getTokensCount() > 1) {
                        jsonOut.getCurrentUser().getCredentials().setTokensCount(
                                jsonOut.getCurrentUser().getCredentials().getTokensCount() - 2);
                        user.getCredentials().setTokensCount(
                                jsonOut.getCurrentUser().getCredentials().getTokensCount());
                    } else {
                        jsonOut = new JsonOut.Builder()
                                .error("Error")
                                .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                                .userNode(null).build();
                        jsonOut.createOutputNode(output);
                        return false;
                    }
                }
                user.getCredentials().setPurchasedMovies(currentMovie);
                jsonOut = new JsonOut.Builder()
                        .error(null)
                        .moviesNode(jsonOut.createMovieNode(currentMovie))
                        .userNode(jsonOut.createUserNode())
                        .build();
                jsonOut.createOutputNode(output);
                return true;
            }
        }
        return false;
    }
}
