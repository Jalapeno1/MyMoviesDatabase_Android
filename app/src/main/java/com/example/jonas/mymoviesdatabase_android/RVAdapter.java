package com.example.jonas.mymoviesdatabase_android;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MovieViewHolder> {

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        CardView cv;

        ImageView movie_poster;
        TextView movie_title;
        TextView movie_year;
        TextView movie_runtime;
        TextView movie_genre;
        TextView movie_director;

        MovieViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);

            movie_poster = (ImageView) itemView.findViewById(R.id.imageView_ADD_POSTER);
            movie_title = (TextView) itemView.findViewById(R.id.textView_ADD_TITLE);
            movie_year = (TextView) itemView.findViewById(R.id.textView_ADD_YEAR);
            movie_runtime = (TextView) itemView.findViewById(R.id.textView_ADD_RUNTIME);
            movie_genre = (TextView) itemView.findViewById(R.id.textView_MOVIE_GENRE);
            movie_director = (TextView) itemView.findViewById(R.id.textView_ADD_DIRECTOR);

        }
    }

    List<MovieObject> allMovies;

    RVAdapter(List<MovieObject> movies){
        this.allMovies = movies;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        MovieViewHolder pvh = new MovieViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {

        final int positionToClick = i;
        movieViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieObject MOVIE_TO_VIEW = allMovies.get(positionToClick);

                Intent i = new Intent(v.getContext(), MovieDetailsActivity_CardView.class);
                i.putExtra("OBJECT_ZERO", MOVIE_TO_VIEW);
                v.getContext().startActivity(i);
            }
        });

        if(movieViewHolder.movie_poster != null){
            new DownloadImageManager(movieViewHolder.movie_poster).execute(allMovies.get(i).getPoster());
        }

        if(movieViewHolder.movie_title != null){
            if(allMovies.get(i).getTitle().length() < 46)
                movieViewHolder.movie_title.setText(allMovies.get(i).getTitle());
            else
                movieViewHolder.movie_title.setText(allMovies.get(i).getTitle().substring(0, 43) + "...");
        }

        if(movieViewHolder.movie_year != null){
            if(allMovies.get(i).getYear().length() < 25)
                movieViewHolder.movie_year.setText("Release: " + allMovies.get(i).getYear());
            else
                movieViewHolder.movie_year.setText("Release: " + allMovies.get(i).getYear().substring(0, 22) + "...");
        }

        if(movieViewHolder.movie_runtime != null){
            if(allMovies.get(i).getRuntime().length() < 25)
                movieViewHolder.movie_runtime.setText("Runtime: " + allMovies.get(i).getRuntime());
            else
                movieViewHolder.movie_runtime.setText("Runtime: " + allMovies.get(i).getRuntime().substring(0, 22) + "...");
        }

        if(movieViewHolder.movie_genre != null){
            if(allMovies.get(i).getGenre().length() < 25)
                movieViewHolder.movie_genre.setText("Genre: " + allMovies.get(i).getGenre());
            else
                movieViewHolder.movie_genre.setText("Genre: " + allMovies.get(i).getGenre().substring(0, 22) + "...");
        }

        if(movieViewHolder.movie_director != null){
            if(allMovies.get(i).getDirector().length() < 25)
                movieViewHolder.movie_director.setText("Directed by " + allMovies.get(i).getDirector());
            else
                movieViewHolder.movie_director.setText("Directed by " + allMovies.get(i).getDirector().substring(0, 22) + "...");
        }
    }

    @Override
    public int getItemCount() {
        return allMovies.size();
    }
}