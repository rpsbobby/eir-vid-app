package controller.controllers;

import controller.Controller;
import controller.exceptions.IncorrectUserStateException;
import java.util.List;
import model.exceptions.UserStorageException;
import model.models.Movie;
import model.models.User;
import model.services.Rental;
import model.storage.movies.MovieStorage;
import model.storage.users.UserStorage;

/**
 *
 * @author Ingrid Castro 2020341
 */
public class NotLoggedIn extends Controller {

    private String exceptionMessage = "You Need To Log In First";
    private Rental rental;
    private MovieStorage movieStorage;
    private UserStorage userStorage;

    public NotLoggedIn(Rental rental, MovieStorage movieStorage, UserStorage userStorage) {
        super(rental, movieStorage);
        this.rental = rental;
        this.movieStorage = movieStorage;
        this.userStorage = userStorage;
    }

    @Override
    public boolean register(String email, String password) throws UserStorageException {
        return userStorage.register(email, password);
    }

    @Override
    public User logIn(String email, String password) throws UserStorageException {
        return userStorage.logIn(email, password);
    }


    @Override
    public Movie rentMovie(Movie movie, long interval) throws IncorrectUserStateException {
        throw new IncorrectUserStateException(exceptionMessage);

    }

    @Override
    public List<Movie> getCurrentlyRentedMovies() throws IncorrectUserStateException {
        throw new IncorrectUserStateException(exceptionMessage);
    }

    @Override
    public boolean logOut() throws IncorrectUserStateException {
        throw new IncorrectUserStateException(exceptionMessage);
    }
}

