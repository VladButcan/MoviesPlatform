package homepageautentificat.MoviesPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Movies {
    private static Movies currentMovie;
    private String name;
    private String year;
    private Integer duration;
    private List<String> genres;
    private List<String> actors;
    private List<String> countriesBanned;
    private Integer numLikes;
    private Double rating;
    private HashMap<String, Double> allRates = new HashMap<String, Double>();
    private Integer numRatings;
    public Movies() {
        year = null;
        name = null;
        duration = 0;
        genres = new ArrayList<>();
        actors = new ArrayList<>();
        countriesBanned = new ArrayList<>();
        numLikes = 0;
        rating = 0.00;
        numRatings = 0;
    }

    /**
     * Function for return name of movie
     */
    public String getName() {
        return name;
    }

    /**
     * Function for set name for movie
     * @param name set new movie name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Function for return year of movies
     */
    public String getYear() {
        return year;
    }

    /**
     * Function for set year for movies
     * @param year new year for movie
     */
    public void setYear(final String year) {
        this.year = year;
    }

    /**
     * Function for return duration of movie
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Function for set duration for movie
     * @param duration new movie duration
     */
    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    /**
     * Function for return
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Function for set list of genres for movie
     * @param genres new list of genres
     */
    public void setGenres(final List<String> genres) {
        this.genres = genres;
    }

    /**
     * Function for return list of movie actors
     */
    public List<String> getActors() {
        return actors;
    }

    /**
     * Function for set list of movie actors
     * @param actors new list of movie actors
     */
    public void setActors(final List<String> actors) {
        this.actors = actors;
    }

    /**
     * Function for return list of movie countries banned
     */
    public List<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * Function for set list of movie countries banned
     * @param countriesBanned new list of countries banned
     */
    public void setCountriesBanned(final List<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    /**
     * Function for return number of movie likes
     */
    public Integer getNumLikes() {
        return numLikes;
    }

    /**
     * Function for set number of movie likes
     * @param numLikes new number of movie likes
     */
    public void setNumLikes(final Integer numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * Function for return movie rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * Function for set movie rating
     * @param rating new movie rating
     */
    public void setRating(final Double rating) {
        this.rating = rating;
    }

    /**
     * Function for return number of movie rating
     */
    public Integer getNumRatings() {
        return numRatings;
    }
    /**
     * @param numRatings number of all movie ratings
     */
    public void setNumRatings(final Integer numRatings) {
        this.numRatings = numRatings;
    }
    /**
     * @return list of all rates
     */
    public HashMap<String, Double> showMovieRate() {
        return allRates;
    }
    /**
     * @param rate rate that give current user after watch movie
     */
    public void setAllRates(final String userName, final Double rate) {
        this.allRates.put(userName, rate);
    }

    /**
     *
     * @param userName name of current user that rate the movie
     * @param rate rate what give the current user
     */
    public void changeRate(final String userName, final Double rate) {
        this.allRates.replace(userName, rate);
    }
    /**
     * @return showing current movie
     */
    public Movies showCurrentMovie() {
        return currentMovie;
    }
    /**
     * @param currentMovie for set current movie when access see details page
     */
    public void setCurrentMovie(final Movies currentMovie) {
        Movies.currentMovie = currentMovie;
    }
}
