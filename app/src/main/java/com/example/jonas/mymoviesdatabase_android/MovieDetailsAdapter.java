package com.example.jonas.mymoviesdatabase_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jonas on 24-06-2015.
 */
public class MovieDetailsAdapter extends ArrayAdapter<MovieObject> {
    private ArrayList<MovieObject> movieObjects;

    public MovieDetailsAdapter(Context context, int resource, ArrayList<MovieObject> objects) {
        super(context, resource, objects);
        this.movieObjects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.movie_details_layout, null);
        }

        MovieObject mo = movieObjects.get(position);

        if(mo != null){
            Button button = (Button) v.findViewById(R.id.buttonADD);
            ImageView poster = (ImageView) v.findViewById(R.id.imageView_ADD_POSTER);
            TextView textViewTitle = (TextView) v.findViewById(R.id.textView_ADD_TITLE);
            TextView textViewYear = (TextView) v.findViewById(R.id.textView_ADD_YEAR);
            TextView textViewRuntime = (TextView) v.findViewById(R.id.textView_ADD_RUNTIME);
            TextView textViewRated = (TextView) v.findViewById(R.id.textView_ADD_RATED);
            TextView textViewDirector = (TextView) v.findViewById(R.id.textView_ADD_DIRECTOR);
            TextView textViewPlot = (TextView) v.findViewById(R.id.textView_ADD_PLOT);
            TextView textViewActors = (TextView) v.findViewById(R.id.textView_ADD_ACTORS);
            TextView textViewWriters = (TextView) v.findViewById(R.id.textView_ADD_WRITERS);
            TextView textViewGenre = (TextView) v.findViewById(R.id.textView_ADD_GENRE);
            TextView textViewIMDBRating = (TextView) v.findViewById(R.id.textView_ADD_IMDB_RATING);
            TextView textViewCountry = (TextView) v.findViewById(R.id.textView_ADD_COUNTRY);

            if(poster != null){
                new DownloadImageManager(poster).execute(mo.getPoster());
            }

            textViewTitle.setText(mo.getTitle());
            textViewYear.setText("Release: " + mo.getYear());
            textViewRuntime.setText("Runtime: " + mo.getRuntime());
            textViewRated.setText("Rated " + mo.getRated());
            textViewDirector.setText("Directed by " + mo.getDirector());
            textViewPlot.setText(mo.getPlot());
            textViewActors.setText("Starring " + mo.getActors());
            textViewWriters.setText("Written by " + mo.getWriter());
            textViewGenre.setText("Genre: " + mo.getGenre());
            textViewIMDBRating.setText("IMDB Rating: " + mo.getImdbRating());
            textViewCountry.setText("Country: " + mo.getCountry());
        }

        return v;
    }
}
