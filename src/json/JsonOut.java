package json;
import json.Actions.Actions;
import json.Credentials.Notifications;
import json.Users.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import homepageautentificat.MoviesPage.Movies;

public final class JsonOut {
//    private static Users currentUser = new Users();
//
//    public Users getCurrentUser() {
//        return currentUser;
//    }
//
//    public void setCurrentUser(Users currentUser) {
//        this.currentUser = currentUser;
//    }

    private static ArrayList<String> userSubscribedGenres;
    private ObjectMapper mapper = new ObjectMapper();
    private ObjectNode user;
    private static String userName;
    private static String userPassword;
    private static String userAccountType;
    private static String userCountry;
    private static String userBalance;
    private static Integer userTokensCount;
    private static Integer userNumFreePremiumMovies;
    private static ArrayList<Movies> userPurchasedMovies;
    private static ArrayList<Movies> userWatchedMovies;
    private static ArrayList<Movies> userLikedMovies;
    private static ArrayList<Movies> userRatedMovies;
    private static ArrayList<Notifications> userNotifications;
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
     * Function for set credentials for current user
     * @param userCredentials actions node from json file
     */
    public void createUser(final UserCredentials userCredentials) {
        userName = userCredentials.getCredentials().getName();
        userCountry = userCredentials.getCredentials().getCountry();
        userBalance = userCredentials.getCredentials().getBalance();
        userAccountType = userCredentials.getCredentials().getAccountType();
        userPassword = userCredentials.getCredentials().getPassword();
        userTokensCount = userCredentials.getCredentials().getTokensCount();
        userNumFreePremiumMovies = userCredentials.getCredentials().getNumFreePremiumMovies();
        userPurchasedMovies = userCredentials.getCredentials().getPurchasedMovies();
        userWatchedMovies = userCredentials.getCredentials().getWatchedMovies();
        userLikedMovies = userCredentials.getCredentials().getLikedMovies();
        userRatedMovies = userCredentials.getCredentials().getRatedMovies();
        userSubscribedGenres = userCredentials.getCredentials().getSubscribeGenres();
        userNotifications = userCredentials.getCredentials().getNotifications();
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
        credentials.put("name", userName);
        credentials.put("password", userPassword);
        credentials.put("accountType", userAccountType);
        credentials.put("country", userCountry);
        credentials.put("balance", userBalance);
        currentUser.set("credentials", mapper.valueToTree(credentials));
        currentUser.put("tokensCount", userTokensCount);
        currentUser.put("numFreePremiumMovies", userNumFreePremiumMovies);
        currentUser.set("purchasedMovies", mapper.valueToTree(userPurchasedMovies));
        currentUser.set("watchedMovies", mapper.valueToTree(userWatchedMovies));
        currentUser.set("likedMovies", mapper.valueToTree(userLikedMovies));
        currentUser.set("ratedMovies", mapper.valueToTree(userRatedMovies));
        currentUser.set("notifications", mapper.valueToTree(userNotifications));
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
            if (!movie.getCountriesBanned().contains(userCountry)) {
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
    public String getUserName() {
        return userName;
    }
    public void setUserName(final String userName) {
        JsonOut.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(final String userPassword) {
        JsonOut.userPassword = userPassword;
    }
    public String getUserAccountType() {
        return userAccountType;
    }
    public void setUserAccountType(final String userAccountType) {
        JsonOut.userAccountType = userAccountType;
    }
    public String getUserCountry() {
        return userCountry;
    }
    public void setUserCountry(final String userCountry) {
        JsonOut.userCountry = userCountry;
    }
    public String getUserBalance() {
        return userBalance;
    }
    public void setUserBalance(final String userBalance) {
        JsonOut.userBalance = userBalance;
    }
    public Integer getUserTokensCount() {
        return userTokensCount;
    }
    public void setUserTokensCount(final Integer userTokensCount) {
        JsonOut.userTokensCount = userTokensCount;
    }
    public Integer getUserNumFreePremiumMovies() {
        return userNumFreePremiumMovies;
    }
    public void setUserNumFreePremiumMovies(final Integer userNumFreePremiumMovies) {
        JsonOut.userNumFreePremiumMovies = userNumFreePremiumMovies;
    }
    public ArrayList<Movies> getUserPurchasedMovies() {
        return userPurchasedMovies;
    }
    /**
     * @param userPurchasedMovies list of current user purchase movies
     */
    public void setUserPurchasedMovies(final Movies userPurchasedMovies) {
        JsonOut.userPurchasedMovies.add(userPurchasedMovies);
    }
    public ArrayList<Movies> getUserWatchedMovies() {
        return userWatchedMovies;
    }
    /**
     * @param userWatchedMovies list of current user watch movies
     */
    public void setUserWatchedMovies(final Movies userWatchedMovies) {
        JsonOut.userWatchedMovies.add(userWatchedMovies);
    }
    public ArrayList<Movies> getUserLikedMovies() {
        return userLikedMovies;
    }
    /**
     * @param userLikedMovies list of current user liked movies
     */
    public void setUserLikedMovies(final Movies userLikedMovies) {
        JsonOut.userLikedMovies.add(userLikedMovies);
    }
    public ArrayList<Movies> getUserRatedMovies() {
        return userRatedMovies;
    }
    /**
     * @param userRatedMovies list of current user rated movies
     */
    public void setUserRatedMovies(final Movies userRatedMovies) {
        JsonOut.userRatedMovies.add(userRatedMovies);
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
    public static ArrayList<String> getUserSubscribedGenres() {
        return userSubscribedGenres;
    }
    /**
     * @param userSubscribedGenres array list with user subscribe genres
     */
    public static void setUserSubscribedGenres(final String userSubscribedGenres) {
        JsonOut.userSubscribedGenres.add(userSubscribedGenres);
    }

    public ArrayList<Notifications> getUserNotifications() {
        return userNotifications;
    }

    /**
     * @param userNotifications notification for add in notifications array list
     */
    public void setUserNotifications(final Notifications userNotifications) {
        JsonOut.userNotifications.add(userNotifications);
    }
}
