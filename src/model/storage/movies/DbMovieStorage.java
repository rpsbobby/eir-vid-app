/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage.movies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import model.models.Movie;
import model.storage.database.DbConnection;
import model.storage.strategy.SortingStrategy;

/**
 *
 * @author Robert Szlufik #2020358
 */
public class DbMovieStorage implements MovieStorage {

    private DbConnection dbConnection;
    private Statement statement;
    private Map<Integer, Movie> movies;
    private SortingStrategy strategy;

    public DbMovieStorage(DbConnection dbConnection, SortingStrategy strategy) {
        this.dbConnection = dbConnection;
        this.statement = dbConnection.getStatement();
        this.strategy = strategy;

    }

    @Override
    public Map<Integer, Movie> getAllMovies() {
        movies = new HashMap<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movies;");
            while (resultSet.next()) {
                movies.put(Integer.parseInt(resultSet.getString("idmovies")), new Movie(resultSet.getString("title"),
                        Double.parseDouble(resultSet.getString("price"))));
            }
            return strategy.sort(movies);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Movie getMovie(int id) {
        return movies.get(id);
    }
}
