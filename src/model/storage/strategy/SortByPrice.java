/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.models.Movie;

/**
 * @author Robert Szlufik #2020358

 */
public class SortByPrice implements SortingStrategy {

    @Override
    public Map<Integer, Movie> sort(Map<Integer, Movie> map) {
        List<Movie> list = new ArrayList<>(map.values());
        map.clear();
        list.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        for (int i = 0; i < list.size(); i++) {
            map.put(i, list.get(i));
        }
        return map;
    }
}
