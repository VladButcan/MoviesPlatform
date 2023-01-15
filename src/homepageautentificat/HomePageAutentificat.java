package homepageautentificat;

import json.Actions.Logout;
import json.JsonOut;
import homepageautentificat.MoviesPage.MoviesPage;
import homepageautentificat.MoviesPage.Movies;
import homepageautentificat.UpgradePage.UpgradesPage;
import currentpageinterface.CurrentPage;
import homepageneautentificat.HomePageNeautentificat;
import json.Actions.Actions;
import json.Users.Users;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

public final class HomePageAutentificat implements CurrentPage {
    /**
     * Function for change page Home Page Autentificat to new page
     * @param currentPage current HomePage Autentificat
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
        switch (actionsNode.getPage()) {
            case "movies":
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
                jsonOut.errorNode(output);
                return currentPage;
        }
    }

    /**
     * Function for do something actions on page Home Page Autentificat
     * @param currentPage current HomePage Autentificat
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node output Array Node
     */
    public CurrentPage onPage(final CurrentPage currentPage, final Actions actionsNode,
                              final  List<Users> usersList, final List<Movies> moviesList,
                              final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        jsonOut.errorNode(output);
        return currentPage;
    }
}
