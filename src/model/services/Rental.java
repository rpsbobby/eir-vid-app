/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.services;

/**
 *
 * @author Robert Szlufik #2020358
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import model.models.Movie;

public class Rental {
    
    private List<Movie> rentedMovies;
    private Timer timer;
    private long interval;

    public Rental(long interval) {
        this.rentedMovies = new ArrayList<>();
        this.timer = new Timer();
        this.interval = interval;
    }

    public List<Movie> getRentedMovies() {
        if (rentedMovies.size() == 0) {
            return new ArrayList<>();
        }
        return rentedMovies;
    }

    public Movie rent(Movie movie, long interval) {
        return startRental(movie, interval);
    }

    private Movie startRental(Movie movie, long interval) {
        Movie temp = null;
        if (addToRented(movie)) {
            temp = movie;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    removeFromRented(movie);
                }
            }, interval);
        }
        return temp;
    }

    private boolean addToRented(Movie movie) {
        if (rentedMovies.contains(movie)) {
            return false;
        }
        return rentedMovies.add(movie);
    }

    private boolean removeFromRented(Movie movie) {
        return rentedMovies.remove(movie);
    }

}
