package json.Actions;

import com.fasterxml.jackson.databind.node.ArrayNode;
import currentpageinterface.CurrentPage;
import homepageautentificat.HomePageAutentificat;
import homepageneautentificat.HomePageNeautentificat;
import json.JsonOut;
import java.util.ArrayList;

public final class Back implements CurrentPage {
    private static ArrayList<CurrentPage> historyAccessPage;

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
            jsonOut.errorNode(output);
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
