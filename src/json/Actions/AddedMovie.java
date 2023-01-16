package json.Actions;
import com.fasterxml.jackson.databind.node.ArrayNode;
import homepageautentificat.MoviesPage.Movies;
import json.Credentials.Notifications;
import json.JsonOut;
import json.Users.Users;

import java.util.ArrayList;
import java.util.List;

public final class AddedMovie extends Movies {
    private String name;
    private String year;
    private Integer duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    public AddedMovie() {
        name = null;
        year = null;
        duration = 0;
        genres = new ArrayList<String>();
        actors = new ArrayList<String>();
        countriesBanned = new ArrayList<String>();

    }
    /**
     * Function for create and return a nea movie for add in movie database
     * @param usersList list of users from json input
     * @param moviesList list of movies from json input
     * @param output array node for output
     * @param actionsNode node of actions from json input
     */
    public List<Movies> add(final List<Users> usersList, final List<Movies> moviesList,
                            final Actions actionsNode, final ArrayNode output) {
        JsonOut jsonOut = new JsonOut();
        for (Movies movie: moviesList) {
            if (movie.getName().equals(actionsNode.getAddedMovie().getName())) {
                jsonOut = new JsonOut.Builder()
                        .error("Error")
                        .moviesNode(jsonOut.moviesList(new ArrayList<>()))
                        .userNode(null).build();
                jsonOut.createOutputNode(output);
                return moviesList;
            }
        }
        Movies newMovie = actionsNode.getAddedMovie();
        moviesList.add(newMovie);
        Notifications notifications = new Notifications();
        notifications.setMovieName(newMovie.getName());
        notifications.setMessage("ADD");
        new Users().setNotify(notifications, newMovie);
        return moviesList;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(final String year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
}
