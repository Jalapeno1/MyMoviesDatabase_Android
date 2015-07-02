package com.example.jonas.mymoviesdatabase_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jonas on 02-07-2015.
 */
public class DBHandler  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movieDB.db";
    private static final String TABLE_MOVIES = "movies";

    public static final String COLUMN_TITLE = "movieTitle";
    public static final String COLUMN_YEAR = "movieYear";
    public static final String COLUMN_RATED = "movieRating";
    public static final String COLUMN_RELEASED = "movieRelease";
    public static final String COLUMN_RUNTIME = "movieRuntime";
    public static final String COLUMN_GENRE = "movieGenre";
    public static final String COLUMN_DIRECTOR = "movieDirector";
    public static final String COLUMN_WRITER = "movieWriter";
    public static final String COLUMN_ACTORS = "movieActors";
    public static final String COLUMN_PLOT = "moviePlot";
    public static final String COLUMN_COUNTRY = "movieCountry";
    public static final String COLUMN_POSTER = "moviePoster";
    public static final String COLUMN_IMDB_RATING = "movieImdbRating";
    public static final String COLUMN_IMDB_ID = "movieImdbID";

    public DBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " +
                TABLE_MOVIES + "("
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_YEAR + " TEXT,"
                + COLUMN_RATED + " TEXT,"
                + COLUMN_RELEASED + " TEXT,"
                + COLUMN_RUNTIME + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_DIRECTOR + " TEXT,"
                + COLUMN_WRITER + " TEXT,"
                + COLUMN_ACTORS + " TEXT,"
                + COLUMN_PLOT + " TEXT,"
                + COLUMN_COUNTRY + " TEXT,"
                + COLUMN_POSTER + " TEXT,"
                + COLUMN_IMDB_RATING + " TEXT,"
                + COLUMN_IMDB_ID + " TEXT"
                + ")";
        db.execSQL(CREATE_MOVIES_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);

    }
    //Saves new notes
    public void addMovie(MovieObject movieObject) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movieObject.getTitle());
        values.put(COLUMN_YEAR, movieObject.getYear());
        values.put(COLUMN_RATED, movieObject.getRated());
        values.put(COLUMN_RELEASED, movieObject.getReleased());
        values.put(COLUMN_RUNTIME, movieObject.getRuntime());
        values.put(COLUMN_GENRE, movieObject.getGenre());
        values.put(COLUMN_DIRECTOR, movieObject.getDirector());
        values.put(COLUMN_WRITER, movieObject.getWriter());
        values.put(COLUMN_ACTORS, movieObject.getActors());
        values.put(COLUMN_PLOT, movieObject.getPlot());
        values.put(COLUMN_COUNTRY, movieObject.getCountry());
        values.put(COLUMN_POSTER, movieObject.getPoster());
        values.put(COLUMN_IMDB_RATING, movieObject.getImdbRating());
        values.put(COLUMN_IMDB_ID, movieObject.getImdbID());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

    //Read all notes from DB!
    public ArrayList<MovieObject> getAll() {
        ArrayList<MovieObject> list = new ArrayList<MovieObject>();
        String query = "Select * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        try{
            Cursor cursor = db.rawQuery(query, null);
            try{
                if (cursor.moveToFirst()) {
                    do {
                        MovieObject movieObject = new MovieObject();
                        movieObject.setTitle(cursor.getString(0));
                        movieObject.setYear(cursor.getString(1));
                        movieObject.setRated(cursor.getString(2));
                        movieObject.setReleased(cursor.getString(3));
                        movieObject.setRuntime(cursor.getString(4));
                        movieObject.setGenre(cursor.getString(5));
                        movieObject.setDirector(cursor.getString(6));
                        movieObject.setWriter(cursor.getString(7));
                        movieObject.setActors(cursor.getString(8));
                        movieObject.setPlot(cursor.getString(9));
                        movieObject.setCountry(cursor.getString(10));
                        movieObject.setPoster(cursor.getString(11));
                        movieObject.setImdbRating(cursor.getString(12));
                        movieObject.setImdbID(cursor.getString(13));

                        //Adds all objects to list
                        list.add(movieObject);

                    } while (cursor.moveToNext());
                }
            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }
        }finally {
            try { db.close(); } catch (Exception ignore) {}
        }
        return list;
    }

    //Delete note (Not finish)
    public boolean deleteNote(String imdbID) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_MOVIES + " WHERE " + COLUMN_IMDB_ID + " =  \"" + imdbID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        MovieObject movieObject = new MovieObject();

        if (cursor.moveToFirst()) {
            movieObject.setImdbID(cursor.getString(13));
            db.delete(TABLE_MOVIES, COLUMN_IMDB_ID + " = ?",
                    new String[]{String.valueOf(movieObject.getImdbID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_MOVIES);
    }
}
