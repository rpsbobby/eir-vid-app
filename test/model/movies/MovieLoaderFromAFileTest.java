/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.movies;

import jdk.jfr.Description;
import model.storage.movies.MoviesStorageInMemory;
import model.storage.strategy.SortAlphabetically;
import org.junit.Test;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;


/**
 *
 * @author rober
 */
public class MovieLoaderFromAFileTest {

    @Test
    @Description("Should load map of movies")
    public void shouldLoadFileIn() {
        assertNotNull(new MoviesStorageInMemory(new SortAlphabetically()).getAllMovies().get(1).getTitle(), "The map should be not null");
    }
}
