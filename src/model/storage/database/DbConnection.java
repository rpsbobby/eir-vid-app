/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage.database;

/**
 *
 * @author rober
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import main.Constants;
import model.models.Movie;
import model.storage.movies.MoviesStorageInMemory;
import model.storage.strategy.SortAlphabetically;

public class DbConnection {
    private Connection connection;

    public DbConnection() {
        this.connection = init();
        initDatabaseIfNotExists();
    }

    private Connection init() {
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            String dbServer = Constants.DB_SERVER; //"jdbc:mysql://localhost:3306/eir_vid";
            String user = Constants.DB_USER; //"EirVid";
            String password = Constants.DB_PASSWORD;//"1234RteMoviePlayer";
            return DriverManager.getConnection(dbServer, user, password);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
            return null;

        }

    }

    public Connection getConnection() {
        return this.connection;
    }

    public Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private  void initDatabaseIfNotExists() {
        Map<Integer, Movie> movieStorage = new MoviesStorageInMemory(new SortAlphabetically()).getAllMovies();
        Statement statement = getStatement();
        try {
            // create schema
            statement.execute("CREATE SCHEMA IF NOT EXISTS eir_vid");
            //create movie table
            statement.execute("CREATE TABLE IF NOT EXISTS `eir_vid`.`movies` (" +
                    "`idmovies` INT NOT NULL AUTO_INCREMENT," +
                    "`title` VARCHAR(255) NOT NULL," +
                    "  `price` FLOAT(45) NOT NULL," +
                    "PRIMARY KEY (`idmovies`)," +
                    "UNIQUE INDEX `moviescol_UNIQUE` (`title` ASC) VISIBLE," +
                    "UNIQUE INDEX `idmovies_UNIQUE` (`idmovies` ASC) VISIBLE);");
            // create users table
            statement.execute("CREATE TABLE IF NOT EXISTS `eir_vid`.`users` (" +
                    "  `idusers` INT NOT NULL AUTO_INCREMENT,"+
                    "`email` VARCHAR(45) NOT NULL," +
                    "  `password` VARCHAR(45) NOT NULL," +
                    " PRIMARY KEY (`idusers`)," +
                    " UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            // populate db if empty
            statement.execute("select count(1) where exists (select * from movies)");
            for (Map.Entry<Integer, Movie> m : movieStorage.entrySet()) {
                statement.execute("INSERT INTO movies  (title, price)" +
                        "VALUES (\"" + m.getValue().getTitle() + "\", " + m.getValue().getPrice() + ");");
            }

        } catch (SQLException e) {
            e.getMessage();
        }


    }

}