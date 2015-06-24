package com.example.jonas.mymoviesdatabase_android;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jonas on 23-06-2015.
 */

public class APIConnectionManager extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(String... params) {

        String strTemp = "";

        try {
            URL url = new URL(params[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            strTemp = br.readLine();
            Log.d("STRING IN PROGRESS", strTemp);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        return strTemp;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
