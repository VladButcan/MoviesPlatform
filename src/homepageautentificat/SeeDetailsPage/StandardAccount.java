package homepageautentificat.SeeDetailsPage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import homepageautentificat.MoviesPage.Movies;
import json.Actions.Actions;
import json.JsonOut;
import json.Users.Users;
import java.util.List;

public final class StandardAccount implements AccountType {
    @Override
    public boolean purchase(final Actions actionsNode, final List<Users> usersList,
                         final Movies currentMovie, final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();

        for (Users user: usersList) {
            if (user.getCredentials().getName().equals(jsonOut.getUserName())) {

                if (user.getCredentials().getTokensCount() > 1) {
                    jsonOut.setUserTokensCount(jsonOut.getUserTokensCount() - 2);
                    user.getCredentials().setTokensCount(jsonOut.getUserTokensCount());
                } else {
                    jsonOut.errorNode(output);
                    return false;
                }
                user.getCredentials().setPurchasedMovies(currentMovie);
                ObjectNode userNode = jsonOut.createUserNode();
                ArrayNode moviesNode = jsonOut.createMovieNode(currentMovie);
                jsonOut.createNode(output, moviesNode, userNode);
                return true;
            }
        }
        return false;
    }
}
