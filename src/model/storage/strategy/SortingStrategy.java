/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage.strategy;

import java.util.Map;
import model.models.Movie;

/**
 *
 * @author Robert Szlufik #2020358
 */
public interface SortingStrategy {

    Map<Integer, Movie> sort(Map<Integer, Movie> listOfMovies);

}
