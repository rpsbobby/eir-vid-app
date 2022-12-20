/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.views;

import controller.Controller;
import controller.exceptions.IncorrectUserStateException;
import java.util.List;
import java.util.Scanner;
import main.Constants;
import main.State;
import model.exceptions.MovieAlreadyRentedException;
import model.models.Movie;
import view.MainView;
import view.View;



/**
 *
 * @author fabio teixera ramos ID = 2020367
 */
public class CMDLineViewLoggedIn extends View {

    MainView mainView;

    public CMDLineViewLoggedIn(Scanner scanner, Controller controller, MainView mainView) {
        super(scanner, controller);
        this.mainView = mainView;
    }

    @Override
    public boolean register(String email, String password) {
        System.out.println("You are already registered");
        return false;
    }

    @Override
    public boolean login(String email, String password) {
        System.out.println("You are already logged in ");
        return false;
    }

    @Override
    public void rentMovie(int movieIndex, long interval) {
        movieIndex--;
        try {
            Movie movie = controller.rentMovie(controller.getMovies().get(movieIndex), interval);
            System.out.println(controller.getUser() + " rented " + movie.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Movie you selected is unavailable");
        } catch (IncorrectUserStateException | MovieAlreadyRentedException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void getCurrentlyRentedMovies() {
        List<Movie> movies;
        try {
            movies = controller.getCurrentlyRentedMovies();
            if (movies.size() == 0) System.out.println("There are no movies currently rented");
            printMovies(movies);
        } catch (IncorrectUserStateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean logOut() {
        try {
            controller.logOut();
            System.out.println("You were successfully logged out");
            return true;
        } catch (IncorrectUserStateException e) {
            System.out.println("Something went wrong");
            return false;
        }

    }

    @Override
    public void printMenu() {
        builder = new StringBuilder();
        builder.append("\nMenu Options:\n");
        builder.append("1: Get Available Movies\n");
        builder.append("2: Get Most Rented Movies\n");
        builder.append("3: Rent a Movie\n");
        builder.append("4: Get Currently Rented Movies\n");
        builder.append("5: Log Out\n");
        builder.append("0: Quit\n");
        System.out.println(builder);
    }

    @Override
    protected void chooseOption() {
        int option = getInput();
        switch (option) {
            case 0:
                System.out.println("Good bye");
                System.exit(0);
                break;
            case 1:
                getMovies();
                break;
            case 2:
                getMostRentedMovies();
                break;
            case 3:
                System.out.println("Which movie would you like to rent?");
                int movie = getInput();
                System.out.println("How long would you like to rent it for?");
                int duration;
                int i = 0;
                List<String[]> list = Constants.CUSTOM_RENT_DURATION;
                for (i = 0; i < list.size(); i++) {
                    System.out.println("(" + i + ")" + " " + list.get(i)[0]);
                }
                duration = getInput();
                try {
                    rentMovie(movie, Long.parseLong(Constants.CUSTOM_RENT_DURATION.get(duration)[1]));
                } catch (IndexOutOfBoundsException e ) {
                    System.out.println("This option doesn't exist");
                }
                break;
            case 4:
                getCurrentlyRentedMovies();
                break;
            case 5:
                logOut();
                mainView.changeState(State.LOG_OUT);
                break;
            default:
                System.out.println("It seems you chose wrong option");
        }
    }

    @Override
    public void startProgram() {
    }
}
