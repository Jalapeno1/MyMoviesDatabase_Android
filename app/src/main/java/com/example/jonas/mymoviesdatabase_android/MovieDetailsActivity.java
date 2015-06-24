package com.example.jonas.mymoviesdatabase_android;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;


public class MovieDetailsActivity extends ListActivity {

    public ArrayList<MovieObject> movie = new ArrayList<>();
    private Runnable listRunnable;
    private MovieDetailsAdapter movieAdapter;

    private final String TAG = "MovieDetailsActivity";

    private MovieObject mov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initAdapter();
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
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fillUI(){

        Intent i = getIntent();

        mov = i.getParcelableExtra("OBJECT_ZERO");
        Log.d(TAG, mov.toString());

        movie.add(mov);

    }

    public void initAdapter(){
        movieAdapter = new MovieDetailsAdapter(this, R.layout.movie_details_layout, movie, false);
        setListAdapter(movieAdapter);

        Thread thread = new Thread(null, listRunnable, "initList");
        thread.start();
    }
}
