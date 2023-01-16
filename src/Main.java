import json.Actions.Actions;
import homepageneautentificat.HomePageNeautentificat;
import currentpageinterface.CurrentPage;
import json.Actions.actionsType.TypeOfActions;
import json.Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import json.JsonOut;
import json.Actions.actionsType.Back;
import json.Actions.actionsType.ActionsType;

public class Main {
    /**
     * @param args argument from input or for output
     * @throws IOException signature exception
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode output = objectMapper.createArrayNode();

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Homepage neautentificat");

        Json json = objectMapper.readValue(new File(args[0]), Json.class);

        CurrentPage currentPage = new HomePageNeautentificat();

        Back back = new Back();
        back.resetHistoryAccessPage(new ArrayList<CurrentPage>());
        back.setHistoryAccessPage(currentPage);

        JsonOut jsonOut = new JsonOut();
        ActionsType actionsType = new ActionsType();
        TypeOfActions typeOfActions;

        for (Actions actions: json.getActions()) {
            typeOfActions = actionsType.returnType(actions);
            currentPage = typeOfActions
                    .act(currentPage, json.getUsers(), actions, json.getMovies(), output);
        }
        /**
         * Recommend the best movie for user if they are premium
         */
        if (!jsonOut.getActiveUser().equals("No user")) {
            if (jsonOut.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
                Recommendations recommendations = new Recommendations();
                recommendations.act(json.getMovies(), output);
            }
        }
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
