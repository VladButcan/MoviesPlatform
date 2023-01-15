package homepageautentificat.UpgradePage;
import currentpageinterface.CurrentPage;
import json.Actions.Logout;
import json.Users.Users;
import json.Actions.Actions;
import homepageautentificat.MoviesPage.Movies;
import homepageautentificat.MoviesPage.MoviesPage;
import homepageautentificat.HomePageAutentificat;
import homepageneautentificat.HomePageNeautentificat;
import json.JsonOut;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
public final class UpgradesPage implements CurrentPage {
    @Override
    public CurrentPage changePage(final CurrentPage currentPage, final Actions actionsNode,
                                  final List<Users> usersList, final List<Movies> moviesList,
                                  final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        switch (actionsNode.getPage()) {
            case "homepage autentificat":
                return new HomePageAutentificat();
            case "movies":
                ObjectNode userNode = jsonOut.createUserNode();
                ArrayNode moviesNode = jsonOut.moviesList(moviesList);
                jsonOut.createNode(output, moviesNode, userNode);
                return new MoviesPage();
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
        BuyAction buyAction;
        JsonOut jsonOut = new JsonOut();
        switch (actionsNode.getFeature()) {
            case "buy tokens":
                buyAction = new BuyTokens();
                break;
            case "buy premium account":
                buyAction = new BuyAccount();
                break;
            default:
                jsonOut.errorNode(output);
                return currentPage;
        }
        buyAction.buy(actionsNode, usersList, moviesList, output);
        return currentPage;
    }
}
