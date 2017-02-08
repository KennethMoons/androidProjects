package com.example.kenneth.movielistapplicatie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenneth on 25/01/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Movie_ViewHolder> {

    List<Movie>movies = new ArrayList<>();
    Context context;
    static int pos;

    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
    }

    @Override
    public Movie_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        Movie_ViewHolder movie_viewHolder = new Movie_ViewHolder(view);
        context = parent.getContext();
        return movie_viewHolder;
    }

    @Override
    public void onBindViewHolder(Movie_ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.movie_name.setText(movie.getTitle());
        holder.movie_votes.setText("uitgekomen op : " + movie.getReleaseDate());
        String uri = "http://image.tmdb.org/t/p/w185/";
        uri += movie.getPosterPath();
        Picasso.with(context).load(uri).into(holder.movie_img);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class Movie_ViewHolder extends RecyclerView.ViewHolder{

        ImageView movie_img;
        TextView movie_name,movie_votes;

        public Movie_ViewHolder(View itemView) {
            super(itemView);
            movie_img = (ImageView)itemView.findViewById(R.id.movie_image);
            movie_name = (TextView)itemView.findViewById(R.id.movie_titel);
            movie_votes = (TextView)itemView.findViewById(R.id.movie_votes);

        }
    }
}
