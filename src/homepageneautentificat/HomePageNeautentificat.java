package homepageneautentificat;

import json.JsonOut;
import currentpageinterface.CurrentPage;
import json.Actions.Actions;
import json.Users.Users;
import homepageautentificat.MoviesPage.Movies;
import homepageneautentificat.RegisterPage.RegisterPage;
import homepageneautentificat.LoginPage.LoginPage;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public final class HomePageNeautentificat implements CurrentPage {
    @Override
    public CurrentPage changePage(final CurrentPage currentPage, final Actions actionsNode,
                                  final List<Users> usersList, final List<Movies> moviesList,
                                  final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        switch (actionsNode.getPage()) {
            case "register":
                return new RegisterPage();
            case "login":
                return new LoginPage();
            default:
                jsonOut = new JsonOut.Builder()
                        .error("Error")
                        .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                        .userNode(null).build();
                jsonOut.createOutputNode(output);
                return currentPage;
        }
    }
}
