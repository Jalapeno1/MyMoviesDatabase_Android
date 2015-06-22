package com.example.jonas.mymoviesdatabase_android;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;


public class FrontActivity extends ListActivity {

    private ArrayList<MovieObject> allMovies = new ArrayList<>();
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
        getMenuInflater().inflate(R.menu.menu_front, menu);
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

        mo1 = new MovieObject("title", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
        mo2 = new MovieObject("title2", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
        mo3 = new MovieObject("title3", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
        mo4 = new MovieObject("title4", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");

        allMovies.add(mo1);
        allMovies.add(mo2);
        allMovies.add(mo3);
        allMovies.add(mo4);
    }
}
