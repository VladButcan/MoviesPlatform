package json.Actions.actionsType;

import com.fasterxml.jackson.databind.node.ArrayNode;
import currentpageinterface.CurrentPage;
import homepageautentificat.HomePageAutentificat;
import homepageautentificat.MoviesPage.Movies;
import homepageneautentificat.HomePageNeautentificat;
import json.Actions.Actions;
import json.JsonOut;
import json.Users.Users;

import java.util.ArrayList;
import java.util.List;

public final class Back implements TypeOfActions {
    private static ArrayList<CurrentPage> historyAccessPage;
    @Override
    public CurrentPage act(CurrentPage currentPage,
                           final List<Users> usersList, final Actions actions,
                           final List<Movies> moviesList, final ArrayNode output) {
        Back back = new Back();

        back.resetHistoryAccessPage(back.act(back.getHistoryAccessPage(), output));
        currentPage = back.getHistoryAccessPage().get(back.getHistoryAccessPage().size() - 1);
        actions.goBack(currentPage, moviesList, output);

        return currentPage;
    }
    /**
     * Function for act the back action and change array list for pages history
     * @param historyAccessPages Array list of Current Page from save the all accessed pages
     * @param output array node for json output
     */
    public ArrayList<CurrentPage> act(final ArrayList<CurrentPage> historyAccessPages,
                                      final ArrayNode output) {
        if (historyAccessPages.get(historyAccessPages.size() - 1)
                .getClass().getName().equals(HomePageNeautentificat.class.getName())
                || historyAccessPages.get(historyAccessPages.size() - 1)
                .getClass().getName().equals(HomePageAutentificat.class.getName())) {
            JsonOut jsonOut = new JsonOut();
            jsonOut = new JsonOut.Builder()
                    .error("Error")
                    .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                    .userNode(null).build();
            jsonOut.createOutputNode(output);
            return  historyAccessPages;
        }
        historyAccessPages.remove(historyAccessPages.size() - 1);
        return historyAccessPages;
    }
    public ArrayList<CurrentPage> getHistoryAccessPage() {
        return historyAccessPage;
    }

    /**
     * @param currentPage accessed page for add in pages history
     */
    public void setHistoryAccessPage(final CurrentPage currentPage) {
        Back.historyAccessPage.add(currentPage);
    }

    /**
     * @param historyAccessPages array list for change historyAccessPages
     */
    public void resetHistoryAccessPage(final ArrayList<CurrentPage> historyAccessPages) {
        Back.historyAccessPage = historyAccessPages;
    }
}
