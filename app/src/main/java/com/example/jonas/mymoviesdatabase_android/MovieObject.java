package com.example.jonas.mymoviesdatabase_android;

/**
 * Created by Jonas on 22-06-2015.
 */
public class MovieObject {

    private String title;
    private String year;
    private String rating;
    private String release;
    private String runtime;
    private String genre;
    private String director;
    private String writers;
    private String actors;
    private String plot;
    private String country;
    private String posterImage;
    private String imdbRating;
    private String imdbID;

    public MovieObject(String title, String year, String rating, String release, String runtime,
                       String genre, String director, String writers, String actors, String plot,
                       String country, String posterImage, String imdbRating, String imdbID) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.release = release;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writers = writers;
        this.actors = actors;
        this.plot = plot;
        this.country = country;
        this.posterImage = posterImage;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getRelease() {
        return release;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriters() {
        return writers;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getCountry() {
        return country;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}
