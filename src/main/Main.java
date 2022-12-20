package main;

import controller.Controller;
import controller.MainController;

import java.util.Scanner;
import model.exceptions.UserStorageException;
import model.services.Rental;
import model.storage.database.DbConnection;
import model.storage.movies.DbMovieStorage;
import model.storage.movies.MovieStorage;
import model.storage.strategy.SortAlphabetically;
import model.storage.strategy.SortByPrice;
import model.storage.strategy.SortingStrategy;
import model.storage.users.DbUserStorage;
import model.storage.users.UserStorage;
import view.MainView;
import view.View;

public class Main {

    /*

        add jbcd driver to the project root
        add hamcrest .jar to test libraries 
        

        you need to add user to mySql database with credentials with admin privileges

        password: 1234RteMoviePlayer
        username: EirVid

        alternatively change values in Constants class



     */

    public static void main(String[] args) throws UserStorageException {


        DbConnection connection = new DbConnection();
        // create global rental with 5min interval 
        Rental rental = new Rental(Constants.MOST_RENTED_MOVIES_DURATION);
        // create sorting strategies
        SortingStrategy sortByPrice = new SortByPrice();
        SortingStrategy sortAlphabetically = new SortAlphabetically();
        
        // in memory implmentation 
//        MovieStorage movieStorage = new MoviesStorageInMemory(sortAlphabetically);
//        UserStorage userStorage = new UserStorageInMemoryImpl(new UserLoader());

        // Database implementation - you can choose sorting strategy here 
        MovieStorage movieStorage = new DbMovieStorage(connection, sortByPrice);
//        MovieStorage movieStorage = new DbMovieStorage(connection, sortAlphabetically);
        UserStorage userStorage = new DbUserStorage(connection);
        // main controller
        Controller controller = new MainController(rental, userStorage, movieStorage);
        Scanner scanner = new Scanner(System.in);
        // main view 
        View view = new MainView(scanner, controller);
//
//
        view.startProgram();

    }


}