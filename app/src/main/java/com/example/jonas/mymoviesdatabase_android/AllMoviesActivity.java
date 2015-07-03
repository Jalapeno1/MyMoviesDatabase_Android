package com.example.jonas.mymoviesdatabase_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jonas.mymoviesdatabase_android.Comparators.Comparator_IMDB_Rating;
import com.example.jonas.mymoviesdatabase_android.Comparators.Comparator_MovieTitle;
import com.example.jonas.mymoviesdatabase_android.Comparators.Comparator_Release;
import com.example.jonas.mymoviesdatabase_android.Comparators.Comparator_Runtime;

import java.util.ArrayList;
import java.util.Collections;

public class AllMoviesActivity extends Activity {

    private ArrayList<MovieObject> movies;
    private RecyclerView rv;

    private static boolean DEVELOPER_MODE = false; //select true to clear DB and add test data on startup

    private static int sortID = 1; //default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);

        DBHandler db = new DBHandler(this, null, null, 1);
        if(DEVELOPER_MODE){
            db.deleteAll();
            initializeTestData();
            for (int i = 0; movies.size() > i; i++){
                db.addMovie(movies.get(i));
            }
            movies.clear();
            DEVELOPER_MODE = false;
        }
        movies = db.getAll();

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_allmovies_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent in = new Intent(getApplicationContext(), AllMoviesActivity.class);
                startActivity(in);
                break;
            case R.id.action_searchmov:
                Intent in2 = new Intent(getApplicationContext(), SearchMovieActivity.class);
                startActivity(in2);
                break;
            case R.id.action_addmovieactivity:
                Intent in3 = new Intent(getApplicationContext(), AddMovieActivityNEW.class);
                startActivity(in3);
                break;
            case R.id.menuSortAlph:
                sortID = 1;
                initializeAdapter();
                break;
            case R.id.menuSortRating:
                sortID = 2;
                initializeAdapter();
                break;
            case R.id.menuSortReleaseNew:
                sortID = 3;
                initializeAdapter();
                break;
            case R.id.menuSortReleaseOld:
                sortID = 4;
                initializeAdapter();
                break;
            case R.id.menuSortRuntime:
                sortID = 5;
                initializeAdapter();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getComparator(int num){

        switch (num) {
            case 1:
                Collections.sort(movies, new Comparator_MovieTitle());
                break;
            case 2:
                Collections.sort(movies, Collections.reverseOrder(new Comparator_IMDB_Rating()));
                break;
            case 3:
                Collections.sort(movies, Collections.reverseOrder(new Comparator_Release())); //Newest first
                break;
            case 4:
                Collections.sort(movies, new Comparator_Release()); //Oldest first
                break;
            case 5:
                Collections.sort(movies, new Comparator_Runtime());
                break;
        }
    }

    private void initializeTestData(){
        movies = new ArrayList<>();
        MovieObject mo1, mo2, mo3, mo4, mo5, mo6, mo7, mo8;

        mo1 = new MovieObject("Lord of The Rings: The Fellowship of The Ring And so on and on and on and on", "2001", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BNTEyMjAwMDU1OV5BMl5BanBnXkFtZTcwNDQyNTkxMw@@._V1_SX300.jpg", "test", "test");
        mo2 = new MovieObject("Something", "2010", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BMTY1NTY0NTg3Nl5BMl5BanBnXkFtZTgwNTUzOTk1MDE@._V1_SX300.jpg", "test", "test");
        mo3 = new MovieObject("Jurassic Park", "1993", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BMjM2MDgxMDg0Nl5BMl5BanBnXkFtZTgwNTM2OTM5NDE@._V1_SX300.jpg", "test", "test");
        mo4 = new MovieObject("Evil Dead", "2013", "test", "test", "test", "test", "test", "test", "test", "lowship of The Ring And so on and on and on and on", "lowship of The Ring And so on and on and on and on", "http://ia.media-imdb.com/images/M/MV5BNTQ3OTkwNTgyN15BMl5BanBnXkFtZTcwNTAzOTAzOQ@@._V1_SX300.jpg", "lowship of The Ring And so on and on and on and on", "lowship of The Ring And so on and on and on and on \n lowship of The Ring And so on and on and on and on \n lowship of The Ring And so on and on and on and on");
        mo5 = new MovieObject("Lord of The Rings: The Fellowship of The Ring And so on and on and on and on", "2001", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BNTEyMjAwMDU1OV5BMl5BanBnXkFtZTcwNDQyNTkxMw@@._V1_SX300.jpg", "test", "test");
        mo6 = new MovieObject("Something", "2010", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BMTY1NTY0NTg3Nl5BMl5BanBnXkFtZTgwNTUzOTk1MDE@._V1_SX300.jpg", "test", "test");
        mo7 = new MovieObject("Jurassic Park", "1993", "test", "test", "test", "test", "test", "test", "test", "test", "test", "http://ia.media-imdb.com/images/M/MV5BMjM2MDgxMDg0Nl5BMl5BanBnXkFtZTgwNTM2OTM5NDE@._V1_SX300.jpg", "test", "test");
        mo8 = new MovieObject("Evil Dead", "2013", "test", "test", "test", "test", "test", "test", "test", "lowship of The Ring And so on and on and on and on", "lowship of The Ring And so on and on and on and on", "http://ia.media-imdb.com/images/M/MV5BNTQ3OTkwNTgyN15BMl5BanBnXkFtZTcwNTAzOTAzOQ@@._V1_SX300.jpg", "lowship of The Ring And so on and on and on and on", "lowship of The Ring And so on and on and on and on \n lowship of The Ring And so on and on and on and on \n lowship of The Ring And so on and on and on and on");


        movies.add(mo1);
        movies.add(mo2);
        movies.add(mo3);
        movies.add(mo4);
        movies.add(mo5);
        movies.add(mo6);
        movies.add(mo7);
        movies.add(mo8);
    }

    private void initializeAdapter(){
        getComparator(sortID);
        RVAdapter adapter = new RVAdapter(movies);
        rv.setAdapter(adapter);
    }
}
