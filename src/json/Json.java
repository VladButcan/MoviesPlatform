package json;
import json.Users.Users;
import json.Actions.Actions;
import homepageautentificat.MoviesPage.Movies;
import java.util.List;

public final class Json {
    private List<Users> users;
    private List<Actions> actions;
    private List<Movies> movies;

    public List<Users> getUsers() {
        return users;
    }

    public List<Actions> getActions() {
        return actions;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(final List<Movies> movies) {
        this.movies = movies;
    }
}
