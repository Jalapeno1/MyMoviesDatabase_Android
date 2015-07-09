package com.example.jonas.mymoviesdatabase_android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchMovieActivity extends Activity {

    private EditText editTextSearch;
    private Button buttonSearch;

    private final String TAG = "SearchMovieActivity";

    private String mSearchTitle = "";
    private String mSearchYear = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] splitString = editTextSearch.getText().toString().split(",");
                mSearchTitle = splitString[0].trim();
                mSearchTitle = mSearchTitle.replaceAll("\\s", "+");
                Log.d(TAG, mSearchTitle);
                mSearchYear = splitString[1].trim();
                Log.d(TAG, mSearchYear);

                //Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);
                Intent intent = new Intent(getApplicationContext(), AddMovieActivityNEW.class); //AddMovieActivityNEW
                intent.putExtra("SEARCH_TITLE", mSearchTitle);
                intent.putExtra("SEARCH_YEAR", mSearchYear);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_movie, menu);
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
}
