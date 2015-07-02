package com.example.jonas.mymoviesdatabase_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
    private MovieObject mo;
    private boolean expanded = false;

    boolean fromSearch = false;

    public MovieDetailsAdapter(Context context, int resource, ArrayList<MovieObject> objects, boolean bo) {
        super(context, resource, objects);
        this.movieObjects = objects;
        fromSearch = bo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.movie_details_layout, parent, false);
        }

        mo = movieObjects.get(position);

        if(mo != null){
            Button button = (Button) v.findViewById(R.id.buttonADD);
            ImageView poster = (ImageView) v.findViewById(R.id.imageView_ADD_POSTER);
            TextView textViewTitle = (TextView) v.findViewById(R.id.textView_ADD_TITLE);
            TextView textViewYear = (TextView) v.findViewById(R.id.textView_ADD_YEAR);
            TextView textViewRuntime = (TextView) v.findViewById(R.id.textView_ADD_RUNTIME);
            TextView textViewRated = (TextView) v.findViewById(R.id.textView_ADD_RATED);
            TextView textViewDirector = (TextView) v.findViewById(R.id.textView_ADD_DIRECTOR);
            final TextView textViewPlot = (TextView) v.findViewById(R.id.textView_ADD_PLOT);
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

            try {
                textViewPlot.setText(mo.getPlot().substring(0, 60) + "... (click to expand)");
            } catch (Exception e){
                textViewPlot.setText(mo.getPlot());
            }

            textViewPlot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!expanded) {
                        textViewPlot.setText(mo.getPlot());
                        expanded = true;
                    }
                    else {
                        try {
                            textViewPlot.setText(mo.getPlot().substring(0, 60) + "... (click to expand)");
                            expanded = false;
                        } catch (Exception e){
                            textViewPlot.setText(mo.getPlot());
                        }
                    }
                }
            });

            textViewActors.setText("Starring " + mo.getActors());
            textViewWriters.setText("Written by " + mo.getWriter());
            textViewGenre.setText("Genre: " + mo.getGenre());
            textViewIMDBRating.setText("IMDB Rating: " + mo.getImdbRating());
            textViewCountry.setText("Country: " + mo.getCountry());

            if(fromSearch){
                button.setText("Add");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBHandler db = new DBHandler(v.getContext(), null, null, 1);
                        db.addMovie(mo);
                        Intent in = new Intent(v.getContext(), AllMoviesActivity.class);
                        v.getContext().startActivity(in);
                    }
                });
                //add button listener
            } else if(!fromSearch){
                button.setText("Delete");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBHandler db = new DBHandler(v.getContext(), null, null, 1);
                        db.deleteNote(mo.getImdbID());
                        Intent in = new Intent(v.getContext(), AllMoviesActivity.class);
                        v.getContext().startActivity(in);
                    }
                });
            }


        }

        return v;
    }
}
