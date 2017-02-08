package com.example.kenneth.movielistapplicatie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_page);
        Intent intent = getIntent();
        String titel = intent.getStringExtra("titel");
        String overview = intent.getStringExtra("overview");
        String taal = intent.getStringExtra("taal");
        String url = intent.getStringExtra("imageUrl");
        String releaseDate = intent.getStringExtra("releaseDate");
        TextView titelView = (TextView)findViewById(R.id.movieDetail_titel);
        TextView overviewTxt = (TextView)findViewById(R.id.movieDetail_overview);
        TextView taalTxt = (TextView)findViewById(R.id.movieDetail_taal);
        TextView releaseTxt = (TextView)findViewById(R.id.movieDetail_releaseDate);
        ImageView imageView = (ImageView)findViewById(R.id.movieDetail_image);
        titelView.setText(titel);
        overviewTxt.setText(overview);
        taalTxt.setText("taal " + taal);
        releaseTxt.setText("release date " + releaseDate);
        String uri = "http://image.tmdb.org/t/p/w185/";
        uri += url;
        Picasso.with(getApplicationContext()).load(uri).into(imageView);

    }
}
