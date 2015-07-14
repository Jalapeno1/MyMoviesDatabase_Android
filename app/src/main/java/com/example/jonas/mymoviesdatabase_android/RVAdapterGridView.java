package com.example.jonas.mymoviesdatabase_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Jonas on 09-07-2015.
 */
public class RVAdapterGridView extends RecyclerView.Adapter<RVAdapterGridView.MovieViewHolder> {

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rl;

        //ImageButton imgButton;

        ImageView movie_poster;
        TextView movie_title;
        TextView movie_year;

        MovieViewHolder(View itemView) {
            super(itemView);
            rl = (RelativeLayout)itemView.findViewById(R.id.rel_layout);

            //imgButton = (ImageButton) itemView.findViewById(R.id.imgButtonDelete);

//            imgButton.setVisibility(itemView.GONE);
//            rl.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    if (imgButton.getVisibility() == v.GONE) {
//                        imgButton.setVisibility(v.VISIBLE);
//                    } else {
//                        imgButton.setVisibility(v.GONE);
//                    }
//                    return true;
//                }
//            });

            movie_poster = (ImageView) itemView.findViewById(R.id.imageView_ADD_POSTER);
            movie_title = (TextView) itemView.findViewById(R.id.textView_ADD_TITLE);
            movie_year = (TextView) itemView.findViewById(R.id.textView_ADD_YEAR);

        }
    }

    List<MovieObject> allMovies;

    RVAdapterGridView(List<MovieObject> movies){
        this.allMovies = movies;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gridview, viewGroup, false);
        MovieViewHolder pvh = new MovieViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder movieViewHolder, final int i) {

        movieViewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieObject MOVIE_TO_VIEW = allMovies.get(i);

                Intent i = new Intent(v.getContext(), MovieDetailsActivity.class);
                i.putExtra("OBJECT_ZERO", MOVIE_TO_VIEW);
                v.getContext().startActivity(i);
            }
        });

        if(movieViewHolder.movie_poster != null){
            new DownloadRoundImageManager(movieViewHolder.movie_poster).execute(allMovies.get(i).getPoster());
        }

        if(movieViewHolder.movie_title != null){
            if(allMovies.get(i).getTitle().length() < 26)
                movieViewHolder.movie_title.setText(allMovies.get(i).getTitle());
            else
                movieViewHolder.movie_title.setText(allMovies.get(i).getTitle().substring(0, 23) + "...");
        }

        if(movieViewHolder.movie_year != null){
            movieViewHolder.movie_year.setText(allMovies.get(i).getYear());
        }

        movieViewHolder.rl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final View v = view;
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Delete?");
                builder.setMessage("Are you sure?") ;
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHandler db = new DBHandler(v.getContext(), null, null, 1);
                        db.deleteNote(allMovies.get(i).getImdbID());
                        allMovies.remove(allMovies.get(i));
                        notifyItemRemoved(i+1);
                        notifyDataSetChanged();
                        Toast.makeText(v.getContext(), "Movie deleted...",
                                Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog ad = builder.create();
                ad.setCanceledOnTouchOutside(true);
                ad.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return allMovies.size();
    }
}