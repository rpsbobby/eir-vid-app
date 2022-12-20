package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */


public class Constants {
    
    public static final long USER_RENTAL_DURATION = 60000; // 1 min in milliseconds
    public static final long MOST_RENTED_MOVIES_DURATION = 300000; // 5 min in milliseconds
    public static final List<String[]> CUSTOM_RENT_DURATION = new ArrayList<>() {
        {
            add(new String[]{"1 min", "60000"});
            add(new String[]{"1h", "3600000"});
            add(new String[]{"24h", "86400000"});
            add(new String[]{"1 week", "6040800000"});
        }
    };    
    public static final String DB_SERVER = "jdbc:mysql://localhost:3306/eir_vid";
    public static final String DB_USER = "EirVid";
    public static final String DB_PASSWORD = "1234RteMoviePlayer";
    
}
