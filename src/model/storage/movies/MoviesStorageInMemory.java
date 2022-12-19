/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage.movies;

import java.util.Map;
import model.models.Movie;
import model.storage.strategy.SortingStrategy;

/**
 *
 * @author Robert Szlufik #2020358

 */
public class MoviesStorageInMemory  implements MovieStorage{

    private Map<Integer, Movie> movies;
    private MovieLoaderFromAFile movieLoader;
    private int lastIndex;

    public MoviesStorageInMemory(SortingStrategy strategy) {
        this.movieLoader = new MovieLoaderFromAFile(strategy);
        movies = movieLoader.getAllMovies();
        lastIndex = movies.size();
    }

    @Override
    public Map<Integer, Movie> getAllMovies() {
        return movies;
    }

    @Override
    public Movie getMovie(int id) {
        return movies.get(id);
    }

}