import json.Actions.Actions;
import homepageneautentificat.HomePageNeautentificat;
import currentpageinterface.CurrentPage;
import json.Actions.AddedMovie;
import json.Actions.Back;
import json.Actions.DeleteMovie;
import json.Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import json.JsonOut;

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
        objectMapper = new ObjectMapper();
        Json json = objectMapper.readValue(new File(args[0]), Json.class);
        CurrentPage currentPage = new HomePageNeautentificat();
        AddedMovie addedMovie = new AddedMovie();
        DeleteMovie deleteMovie = new DeleteMovie();
        Back back = new Back();
        back.resetHistoryAccessPage(new ArrayList<CurrentPage>());
        back.setHistoryAccessPage(currentPage);
        for (Actions actions: json.getActions()) {
            switch (actions.getType()) {
                case "change page":
                    currentPage = actions.changeCurrentPage(currentPage, actions, json.getUsers(),
                            json.getMovies(), output);
                    if (!back.getHistoryAccessPage().get(
                            back.getHistoryAccessPage().size() - 1).
                            getClass().getName().equals(currentPage.getClass().getName())) {
                        back.getHistoryAccessPage().add(currentPage);
                    }
                    break;
                case "on page":
                    currentPage = actions.onPage(currentPage, actions, json.getUsers(),
                            json.getMovies(), output);
                    if (!back.getHistoryAccessPage().get(
                                    back.getHistoryAccessPage().size() - 1).
                            getClass().getName().equals(currentPage.getClass().getName())) {
                        back.getHistoryAccessPage().add(currentPage);
                    }
                    break;
                case "database":
                    switch (actions.getFeature()) {
                        case "add":
                            json.setMovies(addedMovie.add(
                                    json.getUsers(), json.getMovies(), actions, output));
                            break;
                        case "delete":
                            json.setMovies(deleteMovie.delete(
                                    json.getUsers(), json.getMovies(), actions, output));
                            break;
                        default:
                            break;
                    }
                    break;
                case "back":
                    back.resetHistoryAccessPage(back.act(back.getHistoryAccessPage(), output));
                    currentPage = back.getHistoryAccessPage().get(
                            back.getHistoryAccessPage().size() - 1);
                    actions.goBack(currentPage, json.getMovies(), output);
                    break;
                default:
                    break;
            }
        }
        JsonOut jsonOut = new JsonOut();
        if (!jsonOut.getUserName().equals("No user")) {
            if (jsonOut.getUserAccountType().equals("premium")) {
                Recommendations recommendations = new Recommendations();
                recommendations.act(json.getUsers(), json.getMovies(), output);
            }
        }
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
        objectWriter.writeValue(new File("checker/\\"
                + args[0].substring(args[0].lastIndexOf("\\") + 1)), output);
    }
}
