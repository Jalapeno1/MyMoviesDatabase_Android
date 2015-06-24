package com.example.jonas.mymoviesdatabase_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import java.util.concurrent.ExecutionException;


public class AddMovieActivity extends Activity {

    private ImageView poster;
    private Button button;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewRuntime;
    private TextView textViewRated;
    private TextView textViewDirector;
    private TextView textViewPlot;
    private TextView textViewActors;
    private TextView textViewWriters;
    private TextView textViewGenre;
    private TextView textViewIMDBRating;
    private TextView textViewCountry;

    private final String TAG = "AddMovieActivity";

    private String mSearchTitle = "";
    private String mSearchYear = "";

    private Gson gson = new Gson();

    private MovieObject mov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        Intent intentRes = getIntent();
        mSearchTitle = intentRes.getStringExtra("SEARCH_TITLE");
        mSearchYear = intentRes.getStringExtra("SEARCH_YEAR");
        Log.d(TAG, mSearchTitle + ", " + mSearchYear);

        initUI();

        Log.d(TAG, "Before Try-Catch");

        try {
            mov = gson.fromJson(new APIConnectionManager().execute("http://www.omdbapi.com/?t=" + mSearchTitle + "&y=" + mSearchYear + "&plot=short&r=json")
                    .get(), MovieObject.class);
            Log.d(TAG, mov.toString());
            fillUI(mov);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initUI(){
        button = (Button) findViewById(R.id.buttonADD);
        poster = (ImageView) findViewById(R.id.imageView_ADD_POSTER);
        textViewTitle = (TextView) findViewById(R.id.textView_ADD_TITLE);
        textViewYear = (TextView) findViewById(R.id.textView_ADD_YEAR);
        textViewRuntime = (TextView) findViewById(R.id.textView_ADD_RUNTIME);
        textViewRated = (TextView) findViewById(R.id.textView_ADD_RATED);
        textViewDirector = (TextView) findViewById(R.id.textView_ADD_DIRECTOR);
        textViewPlot = (TextView) findViewById(R.id.textView_ADD_PLOT);
        textViewActors = (TextView) findViewById(R.id.textView_ADD_ACTORS);
        textViewWriters = (TextView) findViewById(R.id.textView_ADD_WRITERS);
        textViewGenre = (TextView) findViewById(R.id.textView_ADD_GENRE);
        textViewIMDBRating = (TextView) findViewById(R.id.textView_ADD_IMDB_RATING);
        textViewCountry = (TextView) findViewById(R.id.textView_ADD_COUNTRY);
    }

    public void fillUI(final MovieObject movieObject){
        new DownloadImageManager(poster).execute(movieObject.getPoster());
        textViewTitle.setText(movieObject.getTitle());
        textViewYear.setText("Release: " + movieObject.getYear());
        textViewRuntime.setText("Runtime: " + movieObject.getRuntime());
        textViewRated.setText("Rated " + movieObject.getRated());
        textViewDirector.setText("Directed by " + movieObject.getDirector());
        textViewPlot.setText(movieObject.getPlot());
        textViewActors.setText("Starring " + movieObject.getActors());
        textViewWriters.setText("Written by " + movieObject.getWriter());
        textViewGenre.setText("Genre: " + movieObject.getGenre());
        textViewIMDBRating.setText("IMDB Rating: " + movieObject.getImdbRating());
        textViewCountry.setText("Country: " + movieObject.getCountry());
    }

}


