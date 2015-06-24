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
                new DownloadImageManager(movie_poster).execute(mo.getPoster());
            }

            if(movie_title != null){
                if(mo.getTitle().length() < 46)
                    movie_title.setText(mo.getTitle());
                else
                    movie_title.setText(mo.getTitle().substring(0, 43) + "...");
            }

            if(movie_year != null){
                if(mo.getYear().length() < 25)
                    movie_year.setText("Year: " + mo.getYear());
                else
                    movie_year.setText("Year: " + mo.getYear().substring(0, 22) + "...");
            }

            if(movie_runtime != null){
                if(mo.getRuntime().length() < 25)
                    movie_runtime.setText("Runtime: " + mo.getRuntime());
                else
                    movie_runtime.setText("Runtime: " + mo.getRuntime().substring(0, 22) + "...");
            }

            if(movie_genre != null){
                if(mo.getGenre().length() < 25)
                    movie_genre.setText("Genre: " + mo.getGenre());
                else
                    movie_genre.setText("Genre: " + mo.getGenre().substring(0, 22) + "...");
            }

            if(movie_director != null){
                if(mo.getDirector().length() < 25)
                    movie_director.setText("Director: " + mo.getDirector());
                else
                    movie_director.setText("Director: " + mo.getDirector().substring(0, 22) + "...");
            }

        }

        return v;
    }
}
