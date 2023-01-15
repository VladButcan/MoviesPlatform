package json.Actions;

import currentpageinterface.CurrentPage;
import homepageneautentificat.HomePageNeautentificat;
import json.JsonOut;

import java.util.ArrayList;

public final class Logout {
    /**
     * Function for reset history access pages after logout of system
     */
    public void act() {
        JsonOut jsonOut = new JsonOut();
        Back back = new Back();
        ArrayList<CurrentPage> historyAccessPage = new ArrayList<CurrentPage>();
        historyAccessPage.add(new HomePageNeautentificat());
        back.resetHistoryAccessPage(historyAccessPage);
        jsonOut.setUserName("No user");
    }
}
