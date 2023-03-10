package homepageautentificat.UpgradePage;

import com.fasterxml.jackson.databind.node.ArrayNode;
import json.Actions.Actions;
import json.Users.Users;
import json.JsonOut;
import homepageautentificat.MoviesPage.Movies;
import java.util.List;

public final class BuyAccount implements BuyAction {
    private final Integer priceForPremiumAccount = 10;
    /**
     * Function for buy premium account
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     */
    @Override
    public void buy(final Actions actionsNode,
                    final List<Users> usersList, final List<Movies> moviesList,
                    final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        jsonOut.getCurrentUser().getCredentials().setTokensCount(
            jsonOut.getCurrentUser().getCredentials().getTokensCount() - priceForPremiumAccount);
        jsonOut.getCurrentUser().getCredentials().setAccountType("premium");
        usersList.forEach(user -> {
            if (user.getCredentials().getName()
                    .equals(jsonOut.getCurrentUser().getCredentials().getName())) {
                user.getCredentials().setTokensCount(
                    jsonOut.getCurrentUser().getCredentials().getTokensCount());
                user.getCredentials().setAccountType("premium");
            }
        });
    }
}
