package json.Actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import currentpageinterface.CurrentPage;
import homepageautentificat.MoviesPage.Movies;
import json.Users.Users;

import java.util.List;

public final class OnPage implements TypeOfActions {
    @Override
    public CurrentPage act(CurrentPage currentPage,
                    final List<Users> usersList, final Actions actions,
                    final List<Movies> moviesList, final ArrayNode output) {
        Back back = new Back();
        currentPage = actions.onPage(currentPage, actions, usersList,
                moviesList, output);
        if (!back.getHistoryAccessPage().get(back.getHistoryAccessPage().size() - 1)
                .getClass().getName().equals(currentPage.getClass().getName())) {
            back.getHistoryAccessPage().add(currentPage);
        }
        return currentPage;
    }
}
