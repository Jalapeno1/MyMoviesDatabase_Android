package com.example.jonas.mymoviesdatabase_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jonas on 22-06-2015.
 */
public class MovieListAdapter extends ArrayAdapter<MovieObject> {

    private ArrayList<MovieObject> movieObjects;

    public MovieListAdapter(Context context, int resource, ArrayList<MovieObject> objects) {
        super(context, resource, objects);
        this.movieObjects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.movie_layout, null);
        }

        MovieObject mo = movieObjects.get(position);

        if(mo != null){
            ImageView movie_poster = (ImageView) v.findViewById(R.id.imageView_MOVIE_POSTER);
            TextView movie_title = (TextView) v.findViewById(R.id.textView_MOVIE_TITLE);
            TextView movie_year = (TextView) v.findViewById(R.id.textView_MOVIE_YEAR);
            TextView movie_runtime = (TextView) v.findViewById(R.id.textView_MOVIE_RUNTIME);
            TextView movie_genre = (TextView) v.findViewById(R.id.textView_MOVIE_GENRE);
            TextView movie_director = (TextView) v.findViewById(R.id.textView_MOVIE_DIRECTOR);

            if(movie_poster != null){
                movie_poster.setImageResource(R.mipmap.ic_launcher);
            }

            if(movie_title != null){
                movie_title.setText(mo.getTitle());
            }

            if(movie_year != null){
                movie_year.setText(mo.getYear());
            }

            if(movie_runtime != null){
                movie_runtime.setText(mo.getRuntime());
            }

            if(movie_genre != null){
                movie_genre.setText(mo.getGenre());
            }

            if(movie_director != null){
                movie_director.setText(mo.getDirector());
            }

        }

        return v;
    }
}
