package homepageautentificat.UpgradePage;

import json.Actions.Actions;
import json.Users.Users;
import json.JsonOut;
import homepageautentificat.MoviesPage.Movies;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public final class BuyTokens implements BuyAction {
    @Override
    public void buy(final Actions actionsNode,
                    final List<Users> usersList, final List<Movies> moviesList,
                    final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        if (Integer.parseInt(jsonOut.getUserBalance())
                >= Integer.parseInt(actionsNode.getCount())) {
            jsonOut.setUserBalance(Integer.toString(
                    Integer.parseInt(jsonOut.getUserBalance())
                            - Integer.parseInt(actionsNode.getCount())));
            jsonOut.setUserTokensCount(
                    Integer.parseInt(actionsNode.getCount()) + jsonOut.getUserTokensCount());
            usersList.forEach(user -> {
                if (user.getCredentials().getName().equals(jsonOut.getUserName())) {
                    user.getCredentials().setBalance(jsonOut.getUserBalance());
                    user.getCredentials().setTokensCount(jsonOut.getUserTokensCount());
                }
            });
        }
    }
}
