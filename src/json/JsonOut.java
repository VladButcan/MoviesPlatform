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
    private String error;
    private ArrayNode moviesNode;
    private ObjectNode userNode;
    public static final class Builder {
        private String error;
        private ArrayNode moviesNode;
        private ObjectNode userNode;
        public Builder() {
            this.error = "Error";
        }

        /**
         * Basic constructor
         * @param errorSet set error value
         */
        public Builder error(final String errorSet) {
            this.error = errorSet;
            return this;
        }

        /**
         * Add movies output node
         * @param moviesNodeSet node of movies for output
         */
        public Builder moviesNode(final ArrayNode moviesNodeSet) {
            this.moviesNode = moviesNodeSet;
            return this;
        }

        /**
         * Add current user output node
         * @param userNodeSet nod of user for output
         */
        public Builder userNode(final ObjectNode userNodeSet) {
            this.userNode = userNodeSet;
            return this;
        }

        /**
         * Function for build output node
         */
        public JsonOut build() {
            return new JsonOut(this);
        }
        public String getError() {
            return error;
        }
        public void setError(final String error) {
            this.error = error;
        }
        public ArrayNode getMoviesNode() {
            return moviesNode;
        }
        public void setMoviesNode(final ArrayNode moviesNode) {
            this.moviesNode = moviesNode;
        }

        public ObjectNode getUserNode() {
            return userNode;
        }

        public void setUserNode(final ObjectNode userNode) {
            this.userNode = userNode;
        }
    }
    private JsonOut(final Builder builder) {
        this.userNode = builder.userNode;
        this.error = builder.error;
        this.moviesNode = builder.moviesNode;
    }
    public JsonOut() {
    }

    /**
     * Create general output array node with builder
     * @param output array node for output file
     */
    public void createOutputNode(final ArrayNode output) {
        user = mapper.createObjectNode();
        user.set("error", mapper.valueToTree(error));
        user.set("currentMoviesList", mapper.valueToTree(moviesNode));
        user.set("currentUser", mapper.valueToTree(userNode));
        output.add(user);
    }
    /**
     * Function for create a user node with current user credentials
     */
    public ObjectNode createUserNode() {
        ObjectNode currentUserNode = mapper.createObjectNode();
        ObjectNode credentials = mapper.createObjectNode();
        credentials.put("name", this.getCurrentUser().getCredentials().getName());
        credentials.put("password", this.getCurrentUser().getCredentials().getPassword());
        credentials.put("accountType", this.getCurrentUser().getCredentials().getAccountType());
        credentials.put("country", this.getCurrentUser().getCredentials().getCountry());
        credentials.put("balance", this.getCurrentUser().getCredentials().getBalance());
        currentUserNode.set("credentials", mapper.valueToTree(credentials));
        currentUserNode.put("tokensCount",
                this.getCurrentUser().getCredentials().getTokensCount());
        currentUserNode.put("numFreePremiumMovies",
                this.getCurrentUser().getCredentials().getNumFreePremiumMovies());
        currentUserNode.set("purchasedMovies",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getPurchasedMovies()));
        currentUserNode.set("watchedMovies",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getWatchedMovies()));
        currentUserNode.set("likedMovies",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getLikedMovies()));
        currentUserNode.set("ratedMovies",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getRatedMovies()));
        currentUserNode.set("notifications",
                mapper.valueToTree(this.getCurrentUser().getCredentials().getNotifications()));
        return currentUserNode;
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

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public ArrayNode getMoviesNode() {
        return moviesNode;
    }

    public void setMoviesNode(final ArrayNode moviesNode) {
        this.moviesNode = moviesNode;
    }

    public ObjectNode getUserNode() {
        return userNode;
    }

    public void setUserNode(final ObjectNode userNode) {
        this.userNode = userNode;
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
