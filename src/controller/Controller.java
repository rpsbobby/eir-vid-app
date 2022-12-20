package controller;

import controller.exceptions.IncorrectUserStateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.exceptions.MovieAlreadyRentedException;
import model.exceptions.UserStorageException;
import model.models.Movie;
import model.models.User;
import model.services.Rental;
import model.storage.movies.MovieStorage;

/**
 *
 * @author Ingrid Castro 2020341
 */
public abstract class Controller {

    private Rental rentalService;
    MovieStorage movieStorage;
    User user;

    public Controller(Rental rental, MovieStorage movieStorage) {
        this.rentalService = rental;
        this.movieStorage = movieStorage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    // to be inherited
    public List<Movie> getMostRentedMovies() {
        return rentalService.getRentedMovies();
    }

    public List<Movie> getMovies() throws IncorrectUserStateException {
        List<Movie> list = new ArrayList<>();
        for (Map.Entry<Integer, Movie> entry : movieStorage.getAllMovies().entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    // user not logged in
    public abstract boolean register(String email, String password) throws IncorrectUserStateException, UserStorageException;

    public abstract User logIn(String email, String password) throws IncorrectUserStateException, UserStorageException;


    // user logged in
    public abstract Movie rentMovie(Movie movie, long interval) throws IncorrectUserStateException, MovieAlreadyRentedException;

    public abstract List<Movie> getCurrentlyRentedMovies() throws IncorrectUserStateException;

    public abstract boolean logOut() throws IncorrectUserStateException;
}
