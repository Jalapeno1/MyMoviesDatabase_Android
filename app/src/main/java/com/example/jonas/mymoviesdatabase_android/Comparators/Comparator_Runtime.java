package com.example.jonas.mymoviesdatabase_android.Comparators;

import com.example.jonas.mymoviesdatabase_android.MovieObject;

import java.util.Comparator;

/**
 * Created by Jonas on 03-07-2015.
 */
public class Comparator_Runtime implements Comparator<MovieObject> {
    @Override
    public int compare(MovieObject lhs, MovieObject rhs) {
        return lhs.getRuntime().compareTo(rhs.getRuntime());
    }
}
