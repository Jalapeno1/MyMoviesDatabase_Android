package com.example.jonas.mymoviesdatabase_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MovieDetailsActivity extends Activity  {

    private ImageView imageView_POSTER;
    private TextView textView_YEAR;
    private TextView textView_RUNTIME;
    private TextView textView_RATED;
    private TextView textView_DIRECTOR;
    private TextView textView_PLOT;
    private TextView textView_ACTORS;
    private TextView textView_WRITERS;
    private TextView textView_GENRE;
    private TextView textView_IMDB_RATING;
    private TextView textView_COUNTRY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initUI();
        fillUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initUI(){
        imageView_POSTER = (ImageView) findViewById(R.id.imageView_MOVIE_POSTER);
        textView_YEAR = (TextView) findViewById(R.id.textView_MOVIE_YEAR);
        textView_RUNTIME = (TextView) findViewById(R.id.textView_MOVIE_RUNTIME);
        textView_RATED = (TextView) findViewById(R.id.textView_MOVIE_RATED);
        textView_DIRECTOR = (TextView) findViewById(R.id.textView_MOVIE_DIRECTOR);
        textView_PLOT = (TextView) findViewById(R.id.textView_PLOT);
        textView_ACTORS = (TextView) findViewById(R.id.textView_ACTORS);
        textView_WRITERS = (TextView) findViewById(R.id.textView_WRITERS);
        textView_GENRE = (TextView) findViewById(R.id.textView_GENRE);
        textView_IMDB_RATING = (TextView) findViewById(R.id.textView_IMDB_RATING);
        textView_COUNTRY = (TextView) findViewById(R.id.textView_COUNTRY);
    }

    public void fillUI(){

        Intent i = getIntent();

        MovieObject mo_DETAILS = i.getParcelableExtra("OBJECT_ZERO");
        Log.d("IN MOVIE DETAILS", mo_DETAILS.toString());

        new DownloadImageManager(imageView_POSTER).execute(mo_DETAILS.getPoster());
        textView_YEAR.setText("Year: " + mo_DETAILS.getYear());
        textView_RUNTIME.setText("Runtime: " + mo_DETAILS.getRuntime());
        textView_RATED.setText("Rated " + mo_DETAILS.getRated());
        textView_DIRECTOR.setText("Director: " + mo_DETAILS.getDirector());
        textView_PLOT.setText(mo_DETAILS.getPlot());
        textView_ACTORS.setText("Starring " + mo_DETAILS.getActors());
        textView_WRITERS.setText("Written by " + mo_DETAILS.getWriter());
        textView_GENRE.setText("Genre: " + mo_DETAILS.getGenre());
        textView_IMDB_RATING.setText("IMDB-rating: " + mo_DETAILS.getImdbRating());
        textView_COUNTRY.setText("Produced in " + mo_DETAILS.getCountry());

    }
}
