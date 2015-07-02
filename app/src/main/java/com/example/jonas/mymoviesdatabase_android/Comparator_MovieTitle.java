package com.example.jonas.mymoviesdatabase_android;

import java.util.Comparator;

/**
 * Created by Jonas on 02-07-2015.
 */
public class Comparator_MovieTitle implements Comparator<MovieObject> {
    @Override
    public int compare(MovieObject lhs, MovieObject rhs) {
        return lhs.getTitle().compareTo(rhs.getTitle());
    }
}
