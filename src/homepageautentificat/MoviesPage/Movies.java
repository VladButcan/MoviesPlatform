package homepageautentificat.MoviesPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Movies {
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

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(final List<String> genres) {
        this.genres = genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(final List<String> actors) {
        this.actors = actors;
    }

    public List<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final List<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final Integer numLikes) {
        this.numLikes = numLikes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(final Double rating) {
        this.rating = rating;
    }

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
