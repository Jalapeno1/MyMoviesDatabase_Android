package com.example.jonas.mymoviesdatabase_android;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class AddMovieActivity extends Activity {

    private EditText editText;
    private Button button;

    private final String TAG = "AddMovieActivity";

    private String mSearchTitle = "";
    private String mSearchYear = "";

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        Intent intentRes = getIntent();
        mSearchTitle = intentRes.getStringExtra("SEARCH_TITLE");
        mSearchYear = intentRes.getStringExtra("SEARCH_YEAR");
        Log.d(TAG, mSearchTitle + ", " + mSearchYear);

        editText = (EditText) findViewById(R.id.editTextTEST);
        button = (Button) findViewById(R.id.buttonTEST);

        Log.d(TAG, "Before Try-Catch");

        try {
            MovieObject mov = gson.fromJson(new APIConnectionManager().execute("http://www.omdbapi.com/?t=" + mSearchTitle + "&y=" + mSearchYear + "&plot=full&r=json")
                    .get(), MovieObject.class);
            Log.d(TAG, mov.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        editText.setText("something");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = "";
                try {
                    text = new APIConnectionManager().execute("http://www.omdbapi.com/?t=evil+dead&y=2013&plot=full&r=json").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                editText.setText(text);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_movie, menu);
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


