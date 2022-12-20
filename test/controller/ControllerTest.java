/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mysql.cj.Constants;
import controller.exceptions.IncorrectUserStateException;
import java.util.ArrayList;
import java.util.List;
import jdk.jfr.Description;
import model.models.Movie;
import model.services.Rental;
import model.storage.movies.MovieStorage;
import model.storage.movies.MoviesStorageInMemory;
import model.storage.strategy.SortAlphabetically;
import model.storage.users.UserStorage;
import model.storage.users.UserStorageInMemoryImpl;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertEquals;
import model.exceptions.MovieAlreadyRentedException;
import model.exceptions.UserStorageException;

/**
 *
 * @author rober
 */
public class ControllerTest {

    private MovieStorage movieStorage = new MoviesStorageInMemory(new SortAlphabetically());
    private UserStorage userStorage = new UserStorageInMemoryImpl();
    private Rental rental = new Rental(60000);
    private MainController mainController = new MainController(rental, userStorage, movieStorage);
    private List<Movie> movies;

    public ControllerTest() throws IncorrectUserStateException {
        this.movies = mainController.getMovies();
    }

    @Test
    @Description("Should return list of movies")
    public void a_shouldReturnListOfMovies() {
        try {
            assertNotNull(mainController.getMovies().get(1).getTitle(), "The list should not be null");
            movies = mainController.getMovies();
        } catch (IncorrectUserStateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should register user")
    public void b_shouldRegisterUser() throws UserStorageException {
        try {
            assertEquals(true, mainController.register("test", "test"));
        } catch (IncorrectUserStateException | UserStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should throw exception")
    public void c_shouldThrowUserAlreadyExists() {
        try {
            mainController.register("test", "test");
            mainController.register("test", "test");
        } catch (IncorrectUserStateException | UserStorageException e) {
            assertNotNull(e.getMessage());
            assertEquals("User Already Exists in Database. Please Choose Different Email", e.getMessage());
        }
    }

    @Test
    @Description("Should log in user")
    public void d_shouldLogInUser() {
        try {
            mainController.register("test", "test");

            assertNotNull(mainController.logIn("test", "test"));
        } catch (IncorrectUserStateException | UserStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should return list of movies")
    public void e_shouldReturnListOfMovies() {
        try {
            assertNotNull(mainController.getMovies());
        } catch (IncorrectUserStateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should return null")
    public void f_shouldReturnNullWhenGettingCurrentlyRentedBeforeRentingAnything() throws UserStorageException {
        try {
            mainController.register("test", "test");
            mainController.logIn("test", "test");
            assertEquals(new ArrayList<>(), mainController.getCurrentlyRentedMovies());
        } catch (IncorrectUserStateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should rent a movie")
    public void g_shouldRentAMovie() throws MovieAlreadyRentedException, UserStorageException {
        try {
            mainController.register("test", "test");
            mainController.logIn("test", "test");
            assertNotNull(mainController.rentMovie(movies.get(0), 10));
        } catch (IncorrectUserStateException | MovieAlreadyRentedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should return list of currently movies")
    public void h_shouldReturnListOfCurrentlyRentedMovies() {
        try {
            mainController.register("test", "test");
            mainController.logIn("test", "test");

            mainController.rentMovie(movies.get(4), 10);

            assertNotNull(mainController.getCurrentlyRentedMovies());
        } catch (IncorrectUserStateException | MovieAlreadyRentedException | UserStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should return list of most rented movies")
    public void i_shouldReturnListOfMostRentedMovies() throws IncorrectUserStateException, UserStorageException {
        try {
            mainController.register("test", "test");
            mainController.logIn("test", "test");

            mainController.rentMovie(movies.get(4), 10);
            assertNotNull(mainController.getMostRentedMovies());

        } catch (IncorrectUserStateException | MovieAlreadyRentedException | UserStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should log out")
    public void j_shouldSuccessfullyLogOut() {
        try {
            mainController.logOut();
        } catch (IncorrectUserStateException e) {
            assertNotNull(e.getMessage());
        }
    }
}
