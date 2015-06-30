package com.example.jonas.mymoviesdatabase_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;


public class MovieDetailsActivity_CardView extends Activity {

    private static final String TAG = "CARD_VIEW_ACTIVITY";
    CardView cardview;
    Button button;
    ImageView poster;
    TextView textViewTitle;
    TextView textViewYear;
    TextView textViewRuntime;
    TextView textViewRated;
    TextView textViewDirector;
    TextView textViewPlot;
    TextView textViewActors;
    TextView textViewWriters;
    TextView textViewGenre;
    TextView textViewIMDBRating;
    TextView textViewCountry;

    public MovieObject mo = new MovieObject();
    private boolean expanded = false;
    private boolean fromSearch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviedetails_cardview_activity);
        initUI();
        fillUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_view, menu);
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

    private void fillUI(){
        Intent i = getIntent();

        mo = i.getParcelableExtra("OBJECT_ZERO");
        Log.d("PARCEL!!!!", mo.toString());


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
            //add button listener
        } else if(!fromSearch){
            button.setText("Delete");
            //add button listener
        }
    }

    private void initUI(){
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
}
