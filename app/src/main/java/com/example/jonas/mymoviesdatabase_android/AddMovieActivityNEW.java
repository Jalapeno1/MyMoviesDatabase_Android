package com.example.jonas.mymoviesdatabase_android;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class AddMovieActivityNEW extends ListActivity {

    public ArrayList<MovieObject> movie = new ArrayList<>();
    private Runnable listRunnable;
    private MovieDetailsAdapter movieAdapter;

    private final String TAG = "AddMovieActivity";

    private String mSearchTitle = "";
    private String mSearchYear = "";

    private Gson gson = new Gson();

    private MovieObject mov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie_activity_new);

        Intent intentRes = getIntent();
        mSearchTitle = intentRes.getStringExtra("SEARCH_TITLE");
        mSearchYear = intentRes.getStringExtra("SEARCH_YEAR");
        Log.d(TAG, mSearchTitle + ", " + mSearchYear);

        Log.d(TAG, "Before Try-Catch");

        try {
            mov = gson.fromJson(new APIConnectionManager().execute("http://www.omdbapi.com/?t=" + mSearchTitle + "&y=" + mSearchYear + "&plot=full&r=json")
                    .get(), MovieObject.class);
            Log.d(TAG, mov.toString());
            movie.add(mov);
            initAdapter();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_movie_activity_new, menu);
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

    public void initAdapter(){
        movieAdapter = new MovieDetailsAdapter(this, R.layout.movie_details_layout, movie, true);
        setListAdapter(movieAdapter);

        Thread thread = new Thread(null, listRunnable, "initList");
        thread.start();
    }
}
