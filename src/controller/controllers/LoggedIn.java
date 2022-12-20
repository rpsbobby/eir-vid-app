package controller.controllers;

import controller.Controller;
import controller.exceptions.IncorrectUserStateException;
import java.util.List;
import model.exceptions.MovieAlreadyRentedException;
import model.models.Movie;
import model.models.User;
import model.services.Rental;
import model.storage.movies.MovieStorage;

/**
 *
 * @author Ingrid Castro 2020341
 */
public class LoggedIn extends Controller {

    private String exceptionMessage = "You Are Already Logged In";
    private User user;
    MovieStorage movieStorage;

    public LoggedIn(Rental rental, User user, MovieStorage movieStorage) {
        super(rental,movieStorage);
        this.user = user;
        this.movieStorage = movieStorage;
    }

    @Override
    public boolean register(String email, String password) throws IncorrectUserStateException {
        throw new IncorrectUserStateException(exceptionMessage);
    }

    @Override
    public User logIn(String email, String password) throws IncorrectUserStateException {
        throw new IncorrectUserStateException(exceptionMessage);
    }

    @Override
    public Movie rentMovie(Movie movie, long interval) throws MovieAlreadyRentedException {
        return user.rentMovie(movie, interval);
    }

    @Override
    public List<Movie> getCurrentlyRentedMovies() {
        return user.getRentedMovies();
    }

    @Override
    public boolean logOut() {
        if(user != null){
            user = null;
            return true;
        }
        return false;
    }
}