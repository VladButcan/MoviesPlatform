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
        if (Integer.parseInt(jsonOut.getCurrentUser().getCredentials().getBalance())
                >= Integer.parseInt(actionsNode.getCount())) {
            jsonOut.getCurrentUser().getCredentials().setBalance(Integer.toString(
                    Integer.parseInt(jsonOut.getCurrentUser().getCredentials().getBalance())
                            - Integer.parseInt(actionsNode.getCount())));
            jsonOut.getCurrentUser().getCredentials().setTokensCount(
                    Integer.parseInt(actionsNode.getCount())
                            + jsonOut.getCurrentUser().getCredentials().getTokensCount());
            usersList.forEach(user -> {
                if (user.getCredentials().getName()
                        .equals(jsonOut.getCurrentUser().getCredentials().getName())) {
                    user.getCredentials().setBalance(
                        jsonOut.getCurrentUser().getCredentials().getBalance());
                    user.getCredentials().setTokensCount(
                        jsonOut.getCurrentUser().getCredentials().getTokensCount());
                }
            });
        }
    }
}
