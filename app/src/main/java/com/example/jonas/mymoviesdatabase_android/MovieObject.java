package com.example.jonas.mymoviesdatabase_android;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jonas on 22-06-2015.
 */
public class MovieObject implements Parcelable{

    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Country;
    private String Poster;
    private String imdbRating;
    private String imdbID;

    public MovieObject(String title, String year, String Rated, String release, String runtime,
                       String genre, String director, String writers, String actors, String plot,
                       String country, String posterImage, String imdbRating, String imdbID) {
        this.Title = title;
        this.Year = year;
        this.Rated = Rated;
        this.Released = release;
        this.Runtime = runtime;
        this.Genre = genre;
        this.Director = director;
        this.Writer = writers;
        this.Actors = actors;
        this.Plot = plot;
        this.Country = country;
        this.Poster = posterImage;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
    }

    public MovieObject(){}

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getCountry() {
        return Country;
    }

    public String getPoster() {
        return Poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    public void setRated(String rated) {
        this.Rated = rated;
    }

    public void setReleased(String released) {
        this.Released = released;
    }

    public void setRuntime(String runtime) {
        this.Runtime = runtime;
    }

    public void setGenre(String genre) {
        this.Genre = genre;
    }

    public void setDirector(String director) {
        this.Director = director;
    }

    public void setWriter(String writer) {
        this.Writer = writer;
    }

    public void setActors(String actors) {
        this.Actors = actors;
    }

    public void setPlot(String plot) {
        this.Plot = plot;
    }

    public void setCountry(String country) {
        this.Country = country;
    }

    public void setPoster(String poster) {
        this.Poster = poster;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
        dest.writeString(this.Year);
        dest.writeString(this.Rated);
        dest.writeString(this.Released);
        dest.writeString(this.Runtime);
        dest.writeString(this.Genre);
        dest.writeString(this.Director);
        dest.writeString(this.Writer);
        dest.writeString(this.Actors);
        dest.writeString(this.Plot);
        dest.writeString(this.Country);
        dest.writeString(this.Poster);
        dest.writeString(this.imdbRating);
        dest.writeString(this.imdbID);
    }

    public static final Parcelable.Creator<MovieObject> CREATOR = new Parcelable.Creator<MovieObject>(){

        @Override
        public MovieObject createFromParcel(Parcel source) {
            return new MovieObject(source);
        }

        @Override
        public MovieObject[] newArray(int size) {
            return new MovieObject[size];
        }
    };

    public MovieObject(Parcel in){
        Title = in.readString();
        Year = in.readString();
        Rated = in.readString();
        Released = in.readString();
        Runtime = in.readString();
        Genre = in.readString();
        Director = in.readString();
        Writer = in.readString();
        Actors = in.readString();
        Plot = in.readString();
        Country = in.readString();
        Poster = in.readString();
        imdbRating = in.readString();
        imdbID = in.readString();
    }

    @Override
    public String toString() {
        return "MovieObject{" +
                "Title='" + Title + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                '}';
    }
}
