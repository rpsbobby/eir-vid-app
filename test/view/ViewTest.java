/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MainController;
import java.util.Scanner;
import javax.swing.text.View;
import jdk.jfr.Description;
import main.Constants;
import model.services.Rental;
import model.storage.movies.MoviesStorageInMemory;
import model.storage.strategy.SortAlphabetically;
import model.storage.users.UserStorageInMemoryImpl;
import org.junit.Test;


import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author fabio Teixeira Ramos
 */
public class ViewTest {

    private MainView view = new MainView(new Scanner(System.in),
            new MainController(new Rental(Constants.MOST_RENTED_MOVIES_DURATION),
                    new UserStorageInMemoryImpl(),
                    new MoviesStorageInMemory(new SortAlphabetically())));

    @Test
    @Description("Validate int parsing, should return -1")
    public void a_shouldValidateIntInputAndReturnNegativeInt() {
        assertEquals(-1, view.validateInput("hello"));
    }

    @Test
    @Description("Validate int parsing, should return parsed int")
    public void b_shouldReturnParsedInt() {
        String input = "10";
        assertEquals(10, view.validateInput(input));
    }

    @Test
    @Description("Should register user")
    public void c_shouldRegisterUser() {
        assertEquals(true, view.register("test", "test"));
    }

    @Test
    @Description("Should log in user")
    public void d_shouldLogInUser() {
        view.register("test", "test");
        assertEquals(true, view.login("test", "test"));
    }

    @Test
    @Description("Should log out user")
    public void e_shouldLogOutUser() {
        view.register("test", "test");
        view.login("test", "test");
        assertEquals(true, view.logOut());

    }

    @Test
    @Description("Should not log in  user")
    public void f_shouldNotLogInUser() {
        assertEquals(false, view.login("test", "t"));

    }

   
}
