package homepageautentificat.MoviesPage;
import json.Actions.Actions;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;

public final class Contains {
    private List<String> actors;
    private List<String> genre;
    public Contains() {
        actors = new ArrayList<>();
        genre = new ArrayList<>();
    }

    /**
     * @param moviesList list of movies
     * @param output output Array Node
     */
    public List<Movies> act(final Actions actionsNode,
                            final List<Movies> moviesList, final ArrayNode output) {
        ArrayList<Movies> moviesContains = new ArrayList<>();
        if (!actionsNode.getFilters().getContains().getActors()
                .equals(new ArrayList<String>())) {
            for (Movies movie : moviesList) {
                if (movie.getActors().containsAll(
                        actionsNode.getFilters().getContains().getActors())) {
                    moviesContains.add(movie);
                }
            }
        }
        if (!actionsNode.getFilters().getContains().getGenre()
                .equals(new ArrayList<String>())) {
            if (!moviesContains.equals(new ArrayList<Movies>())) {
                ArrayList<Movies> moviesContainsGenre = new ArrayList<Movies>();
                for (Movies movie : moviesContains) {
                    if (movie.getGenres().containsAll(
                            actionsNode.getFilters().getContains().getGenre())) {
                        moviesContainsGenre.add(movie);
                    }
                }
                return moviesContainsGenre;
            }
            for (Movies movie : moviesList) {
                if (movie.getGenres().containsAll(
                        actionsNode.getFilters().getContains().getGenre())) {
                    moviesContains.add(movie);
                }
            }
        }
        return moviesContains;
    }

    public List<String> getActors() {
        return actors;
    }
    public void setActors(final List<String> actors) {
        this.actors = actors;
    }
    public List<String> getGenre() {
        return genre;
    }
    public void setGenre(final List<String> genre) {
        this.genre = genre;
    }
}
