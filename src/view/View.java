/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import controller.exceptions.IncorrectUserStateException;
import java.util.List;
import java.util.Scanner;
import model.models.Movie;

/**
 *
 * @author fabio teixeira ramos ID = 2020367
 */

public abstract class View {

    protected Scanner scanner;
    protected Controller controller;
    protected View currentView;
    protected StringBuilder builder;

    public View(Scanner scanner, Controller controller) {
        
        this.scanner = scanner;
        this.controller = controller;
    }

    public abstract boolean register(String email, String password);

    public abstract boolean login(String email, String password);

    public abstract void rentMovie(int movieIndex, long interval);

    public abstract void getCurrentlyRentedMovies();

    public abstract boolean logOut();

    protected abstract void chooseOption();

    public abstract void printMenu();

    public abstract void startProgram();


    public List<Movie> getMostRentedMovies() {
        
        List<Movie> movies = controller.getMostRentedMovies();
        if (movies.size() == 0) {
            System.out.println("There are no currently most rented movies available at the moment");
        } else {
            printMovies(movies);
        }
        return movies;
    }

    public List<Movie> getMovies() {
        List<Movie> movies;
        try {
            movies = controller.getMovies();
            printMovies(movies);
            return movies;
        } catch (IncorrectUserStateException e) {
            System.out.println("Sorry, it seems there are no movies available");
            return null;
        }
    }

    protected void printBanner() {
        
        builder = new StringBuilder();
        builder.append("Welcome to the RTE Movie Player \n");
        builder.append("Please Choose one of following options\n");
        builder.append("To interact with the system, enter whole numbers corresponding to the menu option\n");
        System.out.println(builder);
    }

    protected int getInput() {
        
        int input = -1;
        
        do {
            input = validateInput(scanner.nextLine());
            if (input < 0) System.out.println("Something went wrong, try again");
        } while (input < 0);
        return input;

    }

    protected String[] getEmailAndPassword() {
        
        System.out.println("Enter your email, please:");
        String email = getInputString();
        System.out.println("Enter your password, please:");
        String password = getInputString();
        return new String[]{email, password};
    }

    protected String getInputString() {
        
        String input;
        do {
            input = scanner.nextLine();
            if (input == null) System.out.println("Something went wrong");
        } while (input == null);
        return input;
    }

    protected int validateInput(String input) {
        
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            return -1;
        }
    }

    protected void printMovies(List<Movie> movies) {
        
        int number = 1;
        for (int i = 0; i < movies.size(); i++) {
            System.out.println("(" + number + ") " + movies.get(i).toString());
            number++;
        }
    }


}
