package com.example.jonas.mymoviesdatabase_android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;

public class FrontActivityOLD extends ListActivity {

    public ArrayList<MovieObject> allMovies = new ArrayList<>();
    private Runnable listRunnable;
    private MovieListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        addTestData();
        initAdapter();
        initListViewListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_front_old, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent in = new Intent(getApplicationContext(), AllMoviesActivity.class);
                startActivity(in);
                break;
            case R.id.action_searchMovie:
                Intent in2 = new Intent(getApplicationContext(), SearchMovieActivity.class); //SearchMovieActivity
                startActivity(in2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initListViewListener(){
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieObject MOVIE_TO_VIEW = (MovieObject)parent.getAdapter().getItem(position);

                Intent i = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                i.putExtra("OBJECT_ZERO", MOVIE_TO_VIEW);
                startActivity(i);
            }
        });
    }

    public void initAdapter(){
        listAdapter = new MovieListAdapter(this, R.layout.movie_layout, allMovies);
        setListAdapter(listAdapter);

        Thread thread = new Thread(null, listRunnable, "initList");
        thread.start();
    }

    public void addTestData(){
        MovieObject mo1, mo2, mo3, mo4;

        mo1 = new MovieObject("Lord of The Rings: The Fellowship of The Ring And so on and on and on and on", "2001", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BNTEyMjAwMDU1OV5BMl5BanBnXkFtZTcwNDQyNTkxMw@@._V1_SX300.jpg", "test", "test");
        mo2 = new MovieObject("Something", "2010", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BMTY1NTY0NTg3Nl5BMl5BanBnXkFtZTgwNTUzOTk1MDE@._V1_SX300.jpg", "test", "test");
        mo3 = new MovieObject("Jurassic Park", "1993", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BMjM2MDgxMDg0Nl5BMl5BanBnXkFtZTgwNTM2OTM5NDE@._V1_SX300.jpg", "test", "test");
        mo4 = new MovieObject("Evil Dead", "2013", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BNTQ3OTkwNTgyN15BMl5BanBnXkFtZTcwNTAzOTAzOQ@@._V1_SX300.jpg", "test", "test");

        allMovies.add(mo1);
        allMovies.add(mo2);
        allMovies.add(mo3);
        allMovies.add(mo4);
    }
}
