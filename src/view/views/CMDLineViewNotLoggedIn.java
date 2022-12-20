/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.views;


import controller.Controller;
import controller.exceptions.IncorrectUserStateException;
import java.util.Scanner;
import main.State;
import model.exceptions.MovieAlreadyRentedException;
import model.exceptions.UserStorageException;
import view.MainView;
import view.View;
/**
 *
 * @author fabio Teixeira Ramos ID = 2020367
 */
public class CMDLineViewNotLoggedIn extends View {

    private StringBuilder builder;
    MainView mainView;

    public CMDLineViewNotLoggedIn(Scanner scanner, Controller controller, MainView mainView) {
        super(scanner, controller);
        this.builder = new StringBuilder();
        this.mainView = mainView;
    }

    @Override
    public boolean register(String email, String password) {
        try {
            return controller.register(email, password);
//            System.out.println("You were successfully registered. Thank you.");
        } catch (IncorrectUserStateException | UserStorageException e) {
            System.out.println("Seems like there already is a user with this email in our database. Try different email");
            return false;
        }
    }

    @Override
    public boolean login(String email, String password) {
        try {
            if (controller.logIn(email, password) != null) {
                mainView.changeState(State.LOG_IN);
                return true;
            }
            return false;
        } catch (IncorrectUserStateException | UserStorageException e) {
            System.out.println("Invalid user email or password");
            return false;
        }
    }

    @Override
    public void rentMovie(int movieIndex, long interval) {
        try {
            controller.rentMovie(null, 0);
        } catch (IncorrectUserStateException | MovieAlreadyRentedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getCurrentlyRentedMovies() {
        try {
            controller.getCurrentlyRentedMovies();
        } catch (IncorrectUserStateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean logOut() {
        try {
            controller.logOut();
        } catch (IncorrectUserStateException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    @Override
    public void printMenu() {
        builder = new StringBuilder();
        builder.append("Menu Options:\n");
        builder.append("1: Register\n");
        builder.append("2: Login\n");
        builder.append("3: Get Available Movies\n");
        builder.append("4: Get Most Rented Movies\n");
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
                System.out.println("Register");
                String[] reg = getEmailAndPassword();
                register(reg[0], reg[1]);
                break;
            case 2:
                System.out.println("Login");
                String[] log = getEmailAndPassword();
                login(log[0], log[1]);
                break;
            case 3:
                getMovies();
                break;
            case 4:
                getMostRentedMovies();
                break;
            default:
                System.out.println("Please, choose one of the following options");
        }
    }

    @Override
    public void startProgram() {
    }
}


