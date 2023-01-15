package json.Actions;
import json.Credentials.Credentials;
import homepageautentificat.MoviesPage.Movies;
import homepageautentificat.MoviesPage.Filters;
import json.Users.Users;
import currentpageinterface.CurrentPage;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;

public final class Actions {
    private String type;
    private String page;
    private String feature;
    private String movie;
    private String startsWith;
    private String count;
    private Double rate;
    private String subscribedGenre;
    private Credentials credentials;
    private Filters filters;
    private AddedMovie addedMovie;
    private String  deletedMovie;

    public Actions() {
        type = null;
        page = null;
        feature = null;
        movie = null;
        startsWith = null;
        count = null;
        addedMovie = null;
        deletedMovie = null;
        rate = 0.0;
    }
    /**
     * Function for access another page
     * @param currentPage current HomePage Autentificat / Neautentificat
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     * @return return new HomePage
     */
    public CurrentPage changeCurrentPage(final CurrentPage currentPage, final Actions actionsNode,
                                         final List<Users> usersList, final List<Movies> moviesList,
                                         final ArrayNode output) {
        return currentPage.changePage(currentPage, actionsNode, usersList, moviesList, output);
    }

    /**
     * Function for act back action
     * @param currentPage current user page
     * @param moviesList list of movies
     * @param output array node for json output
     */
    public void goBack(final CurrentPage currentPage,
                       final List<Movies> moviesList, final ArrayNode output) {
        currentPage.goBack(currentPage, moviesList, output);
    }
    /**
     * Function for do something on current page
     * @param currentPage current HomePage Autentificat / Neautentificat
     * @param actionsNode each actions node from json file
     * @param usersList list of users from json file
     * @param moviesList list of movies from json file
     * @param output output Array Node
     * @return return new HomePage
     */
    public CurrentPage onPage(final CurrentPage currentPage, final Actions actionsNode,
                              final List<Users> usersList, final List<Movies> moviesList,
                              final ArrayNode output) {
        return currentPage.onPage(currentPage, actionsNode, usersList, moviesList, output);
    }
    public String getType() {
        return type;
    }
    public void setType(final String type) {
        this.type = type;
    }
    public String getPage() {
        return page;
    }
    public void setPage(final String page) {
        this.page = page;
    }
    public String getFeature() {
        return feature;
    }
    public void setFeature(final String feature) {
        this.feature = feature;
    }
    public String getMovie() {
        return movie;
    }
    public void setMovie(final String movie) {
        this.movie = movie;
    }
    public String getStartsWith() {
        return startsWith;
    }
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }
    public String getCount() {
        return count;
    }
    public void setCount(final String count) {
        this.count = count;
    }
    public Double getRate() {
        return rate;
    }
    public void setRate(final Double rate) {
        this.rate = rate;
    }

    public Credentials getCredentials() {
        return credentials;
    }
    public Filters getFilters() {
        return filters;
    }

    public AddedMovie getAddedMovie() {
        return addedMovie;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    public void setAddedMovie(final AddedMovie addedMovie) {
        this.addedMovie = addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }
}
