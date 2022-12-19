package model.models;

import java.util.List;
import main.Constants;
import model.exceptions.MovieAlreadyRentedException;
import model.services.Rental;

/**
 *
 * @author Ingrid Castro 2020341
 */

public class User {

    private String email;
    private String password;
    private Rental rentedMovies;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        // every user has distinct rental service
        this.rentedMovies = new Rental(Constants.USER_RENTAL_DURATION);
    }

    public Movie rentMovie(Movie movie, long interval) throws MovieAlreadyRentedException {
        if(rentedMovies.getRentedMovies() != null){
            if(rentedMovies.getRentedMovies().contains(movie)) throw new MovieAlreadyRentedException("This movie is already rented");
        }
        return rentedMovies.rent(movie, interval);
    }

    public List<Movie> getRentedMovies() {
         return rentedMovies.getRentedMovies();
    }

    public boolean validatePassword(String input) {
        return password.equals(input);
    }

    public Rental getRental() {
        return rentedMovies;
    }

    @Override
    public String toString() {
        return email;
    }
}
