package model.models;

import main.Constants;
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
}
