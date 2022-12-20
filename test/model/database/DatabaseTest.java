/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.database;

import java.sql.Connection;

import jdk.jfr.Description;
import model.exceptions.UserStorageException;
import model.storage.database.DbConnection;
import model.storage.movies.DbMovieStorage;
import model.storage.movies.MovieStorage;
import model.storage.users.DbUserStorage;
import model.storage.users.UserStorage;
import java.sql.Statement;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author rober
 */
public class DatabaseTest {

    private DbConnection dbConnection = new DbConnection();
    private Connection connection = dbConnection.getConnection();
    private UserStorage userStorage = new DbUserStorage(dbConnection);
    private MovieStorage movieStorage = new DbMovieStorage(dbConnection);
    
    
    // test will be run in alphabetical order hence a_,b_,c_ in front of methiod name 


    @Test
    @Description("Should create connection to database")
    public void a_shouldCreateConnection() {
        assertNotNull(dbConnection);
    }

    @Test
    @Description("Should create statement to database")
    public void b_shouldCreateStatement() {
        Statement statement = new DbConnection().getStatement();
        assertNotNull(statement);
    }

    @Test
    @Description("Should register user")
    public void c_shouldRegisterUser() {
        try {
            assertEquals(true, userStorage.register("test", "test"));

        } catch (UserStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should throw an error when registering user")
    public void d_shouldNotRegisterUser() {
        try {
            userStorage.register("test", "test");
        } catch (UserStorageException e) {
            assertNotNull(e);
        }
    }

    @Test
    @Description("Should login user")
    public void e_shouldLogInUser() {
        try {
            assertNotNull(userStorage.logIn("test", "test"));
        } catch (UserStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should throw an error when login user, password incorrect")
    public void f_shouldNotLogInUser() {
        try {
            userStorage.logIn("test", "test2");
        } catch (UserStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should throw an error when login user, password incorrect")
    public void g_shouldReturnMapOfMovies() {
        try {
            assertNotNull(movieStorage.getAllMovies());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Description("Should return a single movie")
    public void h_shouldReturnMovie() {
        try {
            assertNotNull(movieStorage.getMovie(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
}
