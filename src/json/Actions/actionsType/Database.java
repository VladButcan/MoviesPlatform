package json.Actions.actionsType;

import com.fasterxml.jackson.databind.node.ArrayNode;
import currentpageinterface.CurrentPage;
import homepageautentificat.MoviesPage.Movies;
import json.Actions.Actions;
import json.Actions.AddedMovie;
import json.Actions.DeleteMovie;
import json.Json;
import json.Users.Users;

import java.util.List;

public final class Database implements TypeOfActions {
    @Override
    public CurrentPage act(final CurrentPage currentPage,
                    final List<Users> usersList, final Actions actions,
                    final List<Movies> moviesList, final ArrayNode output) {
        AddedMovie addedMovie = new AddedMovie();
        DeleteMovie deleteMovie = new DeleteMovie();

        switch (actions.getFeature()) {
            case "add":
                new Json().setMovies(addedMovie
                        .add(usersList, moviesList, actions, output));
                break;
            case "delete":
                new Json().setMovies(deleteMovie
                        .delete(usersList, moviesList, actions, output));
                break;
            default:
                break;
        }
        return currentPage;
    }
}
