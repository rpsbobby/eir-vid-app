/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage.movies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.models.Movie;
import model.storage.strategy.SortingStrategy;

/**
 *
* @author Robert Szlufik #2020358
 */
public class MovieLoaderFromAFile {

    SortingStrategy strategy;

    public MovieLoaderFromAFile(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public MovieLoaderFromAFile() {
    }

    public Map<Integer, Movie> getAllMovies() {
        Map<Integer, Movie> movies = new HashMap<>();
        int index = 1;
        try {
            String entry;
            String[] info;

            BufferedReader bufferedReader = new BufferedReader(new FileReader("src".concat("\\").concat("Movie_Metadata_Edited.csv")));
            // skip first line
            entry = bufferedReader.readLine();
            while((entry = bufferedReader.readLine() )!= null) {
                info = entry.split(",");
                movies.put(index, new Movie(info[0],Double.parseDouble(info[1])));
                index++;
                // allow 300 movies to be fetched from the file, comas in those titles were removed
                if (movies.size() > 300) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strategy.sort(movies);
    }


}