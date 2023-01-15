package homepageautentificat.SeeDetailsPage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import homepageautentificat.MoviesPage.Movies;
import json.Actions.Actions;
import json.JsonOut;
import json.Users.Users;

import java.util.List;

public final class Subscribe {
    /**
     * Function for subscribe for one genre
     * @param actionsNode node of actions from json input file
     * @param usersList list of users from json input file
     * @param output output array node
     */
    public boolean act(final Actions actionsNode,
                    final List<Users> usersList, final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        Movies movies = new Movies();
        if (movies.showCurrentMovie().getGenres().contains(
                actionsNode.getSubscribedGenre())) {
            for (Users user : usersList) {
                if (user.getCredentials().getName().equals(jsonOut.getUserName())) {
                    if (user.getCredentials().getSubscribeGenres().contains(
                            actionsNode.getSubscribedGenre())) {
                        jsonOut.errorNode(output);
                        return false;
                    }
                    user.getCredentials().setSubscribeGenres(actionsNode.getSubscribedGenre());
                    return true;
                }
            }
        }
        jsonOut.errorNode(output);
        return false;
    }
}
