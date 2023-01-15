import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import homepageautentificat.MoviesPage.Movies;
import json.Credentials.Notifications;
import json.JsonOut;
import json.Users.Users;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Recommendations {
    public boolean act(final List<Users> usersList, final List<Movies> moviesList,
                       final ArrayNode output) {
        HashMap<String, Integer> genresHashMap = new HashMap<String, Integer>();
        List<String> genresList = new ArrayList<String>();
        JsonOut jsonOut = new JsonOut();
        for (Movies movie: moviesList) {
            for (String genre: movie.getGenres()) {
                if (!genresList.contains(genre)) {
                    genresList.add(genre);
                    genresHashMap.put(genre, 0);
                }
            }
        }
        for (String genre: genresList) {
            for (Movies movie: moviesList) {
                if (movie.getGenres().contains(genre)) {
                    if (jsonOut.getUserLikedMovies().contains(movie)) {
                        genresHashMap.replace(genre, genresHashMap.get(genre) + 1);
                    }
                }
            }
        }
        for (String genre: genresList) {
            if (genresHashMap.get(genre).equals(0)) {
                genresHashMap.remove(genre);
            }
        }
        moviesList.sort(((o1, o2) -> {
            return o2.getNumLikes().compareTo(o1.getNumLikes());
        }));
        Notifications notifications = new Notifications();
        for (Movies movie: moviesList) {
            for (Map.Entry<String, Integer> entry: genresHashMap.entrySet()) {
                if (!jsonOut.getUserWatchedMovies().contains(movie)) {
                    if (movie.getGenres().contains(entry.getKey())) {
                        notifications.setMessage("Recommendation");
                        notifications.setMovieName(movie.getName());
                        jsonOut.setUserNotifications(notifications);
                        ObjectNode userNode = jsonOut.createUserNode();
                        jsonOut.recommendNode(output, userNode);
                        return true;
                    }
                }
            }
        }
        notifications.setMessage("Recommendation");
        notifications.setMovieName("No recommendation");
        jsonOut.setUserNotifications(notifications);
        ObjectNode userNode = jsonOut.createUserNode();
        jsonOut.recommendNode(output, userNode);
        return false;
    }
}
