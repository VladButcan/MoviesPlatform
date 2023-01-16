package json;
import json.Users.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import homepageautentificat.MoviesPage.Movies;

public final class JsonOut {
    private static Users currentUser = new Users();
    public Users getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(final Users currentUser) {
        this.currentUser = currentUser;
    }
    private ObjectMapper mapper = new ObjectMapper();
    private ObjectNode user;
    private static String activeUser = "No user";
    private static HashMap<String, ArrayList<Integer>> allRatings = new HashMap<>();
    /**
     * Function for create a error node in json output node
     * @param output output file
     */
    public void errorNode(final ArrayNode output) {
        user = mapper.createObjectNode();
        user.put("error", "Error");
        user.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
        user.set("currentUser", mapper.valueToTree(null));
        output.add(user);
    }
    /**
     * Function for create a node without error, with current movie list and current user
     * @param output output Array Node
     * @param moviesNode Array Node of movies
     * @param userNode Array Node of users
     */
    public void createNode(final ArrayNode output, final ArrayNode moviesNode,
                           final ObjectNode userNode) {
        user = mapper.createObjectNode();
        user.set("error", mapper.valueToTree(null));
        user.set("currentMoviesList", moviesNode);
        user.set("currentUser", userNode);
        output.add(user);
    }
    /**
     * Function for create a node without error and with current user
     * @param output output Array Node
     * @param userNode Array Node of users
     */
    public void createNode(final ArrayNode output, final ObjectNode userNode) {
        user = mapper.createObjectNode();
        user.set("error", mapper.valueToTree(null));
        user.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
        user.set("currentUser", userNode);
        output.add(user);
    }

    /**
     * Function for create a recommendation node
     * @param output output array node
     * @param userNode output user array node
     */
    public void recommendNode(final ArrayNode output, final ObjectNode userNode) {
        user = mapper.createObjectNode();
        user.set("error", mapper.valueToTree(null));
        user.set("currentMoviesList", mapper.valueToTree(null));
        user.set("currentUser", userNode);
        output.add(user);
    }
    /**
     * Function for create a user node with current user credentials
     */
    public ObjectNode createUserNode() {
        ObjectNode currentUser = mapper.createObjectNode();
        ObjectNode credentials = mapper.createObjectNode();
        credentials.put("name", this.getCurrentUser().getCredentials().getName());
        credentials.put("password", this.getCurrentUser().getCredentials().getPassword());
        credentials.put("accountType", this.getCurrentUser().getCredentials().getAccountType());
        credentials.put("country", this.getCurrentUser().getCredentials().getCountry());
        credentials.put("balance", this.getCurrentUser().getCredentials().getBalance());
        currentUser.set("credentials", mapper.valueToTree(credentials));
        currentUser.put("tokensCount", this.getCurrentUser().getCredentials().getTokensCount());
        currentUser.put("numFreePremiumMovies",
                this.getCurrentUser().getCredentials().getNumFreePremiumMovies());
        currentUser.set("purchasedMovies",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getPurchasedMovies()));
        currentUser.set("watchedMovies",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getWatchedMovies()));
        currentUser.set("likedMovies",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getLikedMovies()));
        currentUser.set("ratedMovies",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getRatedMovies()));
        currentUser.set("notifications",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getNotifications()));
        return currentUser;
    }

    /**
     * Function for create a node with movie list
     * @param movies list of Movies from json file
     * @return return node with movies
     */
    public ArrayNode moviesList(final List<Movies> movies) {
        ArrayList<Movies> moviesArrayList = new ArrayList<>();
        for (Movies movie: movies) {
            if (!movie.getCountriesBanned().contains(currentUser.getCredentials().getCountry())) {
                moviesArrayList.add(movie);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.valueToTree(moviesArrayList);
    }

    /**
     * Function for create the Array node out of movie object
     * @param movie for create Array Node with movie
     * @return return the movies Array Node
     */
    public ArrayNode createMovieNode(final Movies movie) {
        ArrayList<Movies> moviesArrayList = new ArrayList<>();
        moviesArrayList.add(movie);
        return moviesList(moviesArrayList);
    }
    public ObjectMapper getMapper() {
        return mapper;
    }
    public void setMapper(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ObjectNode getUser() {
        return user;
    }

    public void setUser(final ObjectNode user) {
        this.user = user;
    }
    public String getActiveUser() {
        return activeUser;
    }
    public void setActiveUser(final String userName) {
        JsonOut.activeUser = userName;
    }
    public HashMap<String, ArrayList<Integer>> getAllRatings() {
        return allRatings;
    }
    /**
     * @param allRatings hash map of all rating for current movie
     */
    public void setAllRatings(final HashMap<String, ArrayList<Integer>> allRatings) {
        JsonOut.allRatings = allRatings;
    }
}
