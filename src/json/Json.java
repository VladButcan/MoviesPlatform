package json;
import json.Users.Users;
import json.Actions.Actions;
import homepageautentificat.MoviesPage.Movies;
import java.util.List;

public final class Json {
    private static List<Users> users;
    private static List<Actions> actions;
    private static List<Movies> movies;

    public List<Users> getUsers() {
        return users;
    }

    public List<Actions> getActions() {
        return actions;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    /**
     * Set users list
     * @param users new list of users
     */
    public void setUsers(final List<Users> users) {
        Json.users = users;
    }

    /**
     * Set actions list
     * @param actions new list of users
     */
    public void setActions(final List<Actions> actions) {
        Json.actions = actions;
    }

    public void setMovies(final List<Movies> movies) {
        Json.movies = movies;
    }
}
