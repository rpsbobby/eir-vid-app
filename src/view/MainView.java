/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.Scanner;
import main.State;
import view.views.CMDLineViewLoggedIn;
import view.views.CMDLineViewNotLoggedIn;

/**
 *
 * @author fabio Teixeira Ramos ID = 2020367
 */
public class MainView extends View implements ViewState {

    protected StringBuilder builder;
    protected View currentView;

    public MainView(Scanner scanner, Controller controller) {
        super(scanner, controller);
        this.currentView = new CMDLineViewNotLoggedIn(scanner, controller, this);

        this.builder = new StringBuilder();
    }


    @Override
    public void changeState(State state) {
        switch (state) {
            case LOG_IN:
                this.currentView = new CMDLineViewLoggedIn(scanner, controller, this);
                break;
            case LOG_OUT:
                this.currentView = new CMDLineViewNotLoggedIn(scanner, controller, this);
                break;
        }

    }

    @Override
    public void startProgram() {
        printBanner();
        while (true) {
            printMenu();
            chooseOption();
        }
    }

    @Override
    public boolean register(String email, String password) {
        return currentView.register(email, password);
    }

    @Override
    public boolean login(String email, String password) {
        boolean verified = currentView.login(email, password);
        if (verified) {
            System.out.println("You were success  fully logged in");
            return true;
        }
        return false;
    }

    @Override
    public void rentMovie(int movieIndex, long interval) {
        currentView.rentMovie(movieIndex, interval);
    }

    @Override
    public void getCurrentlyRentedMovies() {
        currentView.getCurrentlyRentedMovies();
    }

    @Override
    public boolean logOut() {
        if (currentView.logOut()) changeState(State.LOG_OUT);
        return true;
    }

    @Override
    public void printMenu() {
        currentView.printMenu();
    }

    @Override
    protected void chooseOption() {
        currentView.chooseOption();
    }


}
