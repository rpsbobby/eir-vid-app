package controller;

import controller.controllers.LoggedIn;
import controller.controllers.NotLoggedIn;
import controller.exceptions.IncorrectUserStateException;
import java.util.List;
import main.Constants;
import main.State;
import model.exceptions.MovieAlreadyRentedException;
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
public class MainController extends Controller implements ControllerState {

    private Controller currentController;
    private User user;
    private Rental rental;
    private MovieStorage movieStorage;
    private UserStorage userStorage;

    public MainController(Rental rental, UserStorage userStorage, MovieStorage movieStorage) {
        super(rental, movieStorage);
        this.rental = rental;
        this.userStorage = userStorage;
        this.movieStorage = movieStorage;
        this.currentController = new NotLoggedIn(rental, movieStorage, userStorage);
    }


    // change from logged out to logged in
    @Override
    public void changeState(State state) {
        switch (state) {
            case LOG_IN:
                currentController = new LoggedIn(rental, user, movieStorage);
                break;
            case LOG_OUT:
                currentController = new NotLoggedIn(rental, movieStorage, userStorage);
                break;
        }
    }
    // not logged in

    @Override
    public boolean register(String email, String password) throws IncorrectUserStateException, UserStorageException {
        return currentController.register(email, password);
    }

    @Override
    public User logIn(String email, String password) throws IncorrectUserStateException, UserStorageException {
        this.setUser(currentController.logIn(email, password));
        if(user != null) changeState(State.LOG_IN);
        return this.getUser();
    }

    // logged in

    @Override
    public boolean logOut() throws IncorrectUserStateException {
        if (currentController.logOut()) {
            setUser(null);
            changeState(State.LOG_OUT);
            return true;
        }
        return false;
    }

    @Override
    public Movie rentMovie(Movie movie, long interval) throws IncorrectUserStateException, MovieAlreadyRentedException {
        rental.rent(movie, Constants.MOST_RENTED_MOVIES_DURATION);
        return currentController.rentMovie(movie, interval);
    }

    @Override
    public List<Movie> getCurrentlyRentedMovies() throws IncorrectUserStateException {
        return currentController.getCurrentlyRentedMovies();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
