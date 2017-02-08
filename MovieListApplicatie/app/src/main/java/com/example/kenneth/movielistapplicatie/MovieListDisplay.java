package com.example.kenneth.movielistapplicatie;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListDisplay extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "b2b5187c2ee9192c060fe37beaa8dd3e";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    int startPositiePopular = 0;
    int startPositieToprated = 0;
    int startPositieCinema = 0;
    int startPositieFuture = 0;
    int tellerPopular = 1;
    int tellerTopRated = 1;
    int tellerCinema = 1;
    int tellerFuture = 1;
    LinearLayoutManager mLayoutManager;
    MyDbHandler myDbHandler;

    List<Movie> moviesMostPopular = new ArrayList<>();
    List<Movie>moviesTopRated = new ArrayList<>();
    List<Movie>moviesCinema = new ArrayList<>();
    List<Movie>moviesFutre = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_display);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String s = intent.getStringExtra("typeList");
        myDbHandler = new MyDbHandler(this,null,null,1);
        switch (s){
            case "cinema" : showCinemaMoviesDb(); getSupportActionBar().setTitle("nu in de bioscoop");
                break;
            case "rating" : showTopRatedMoviesDb();getSupportActionBar().setTitle("beste rating");break;
            case "populairste" : showPopularMoviesDb();getSupportActionBar().setTitle("meest populaire");break;
            case "binnenkort" : showFutureMoviesDb();getSupportActionBar().setTitle("binnenkort");break;
        }

    }

    public void showTopRatedMovies(){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        recyclerView =(RecyclerView) findViewById(R.id.reclycer_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY,tellerTopRated);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                moviesTopRated.addAll(movies);
                Log.d(TAG, "Number of movies received show top rated: " + movies.size());
                adapter = new MovieAdapter(moviesTopRated);
                recyclerView.setAdapter(adapter);
                mLayoutManager.scrollToPosition(startPositieToprated);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if(dy > 0) //check for scroll down
                        {
                            int last = mLayoutManager.findLastCompletelyVisibleItemPosition();
                            int totalcount = mLayoutManager.getItemCount();
                            if(last + 1 == totalcount){
                                tellerTopRated++;
                                startPositieToprated = last;
                                showTopRatedMovies();
                            }

                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(),MovieDetailPage.class);
                        int positie = recyclerView.getChildAdapterPosition(view);
                        Movie movie = moviesTopRated.get(positie);
                        String titel = movie.getTitle();
                        String overview = movie.getOverview();
                        String taal = movie.getOriginalLanguage();
                        String imageUrl = movie.getPosterPath();
                        String releaseDate = movie.getReleaseDate();
                        intent.putExtra("titel",titel);
                        intent.putExtra("overview",overview);
                        intent.putExtra("taal",taal);
                        intent.putExtra("imageUrl",imageUrl);
                        intent.putExtra("releaseDate",releaseDate);
                        startActivity(intent);
                    }
                })
        );
    }

    public void showCinemaMovies(){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        recyclerView =(RecyclerView) findViewById(R.id.reclycer_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        Call<MovieResponse> call = apiService.getNowPlayingMovies(API_KEY,tellerCinema);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                moviesCinema.addAll(movies);

                Log.d(TAG, "Number of movies received in cinemas: " + movies.size());
                adapter = new MovieAdapter(moviesCinema);
                recyclerView.setAdapter(adapter);
                mLayoutManager.scrollToPosition(startPositieCinema);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if(dy > 0) //check for scroll down
                        {
                            int last = mLayoutManager.findLastCompletelyVisibleItemPosition();
                            int totalcount = mLayoutManager.getItemCount();
                            if(last + 1 == totalcount){
                                tellerCinema++;
                                startPositieCinema = last;
                                showCinemaMovies();

                            }

                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(),MovieDetailPage.class);
                        int positie = recyclerView.getChildAdapterPosition(view);
                        Movie movie = moviesCinema.get(positie);
                        String titel = movie.getTitle();
                        String overview = movie.getOverview();
                        String taal = movie.getOriginalLanguage();
                        String imageUrl = movie.getPosterPath();
                        String releaseDate = movie.getReleaseDate();
                        intent.putExtra("titel",titel);
                        intent.putExtra("overview",overview);
                        intent.putExtra("taal",taal);
                        intent.putExtra("imageUrl",imageUrl);
                        intent.putExtra("releaseDate",releaseDate);
                        startActivity(intent);
                    }
                })
        );
    }

    public void showMostPopular(){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        recyclerView =(RecyclerView) findViewById(R.id.reclycer_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        Call<MovieResponse> call1 = apiService.getPopularMovies(API_KEY,tellerPopular);

        call1.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                moviesMostPopular.addAll(movies);
                Log.d(TAG, "Number of movies received in most popular: " + movies.size());
                adapter = new MovieAdapter(moviesMostPopular);
                recyclerView.setAdapter(adapter);
                mLayoutManager.scrollToPosition(startPositiePopular);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if(dy > 0) //check for scroll down
                        {
                            int last = mLayoutManager.findLastCompletelyVisibleItemPosition();
                            int totalcount = mLayoutManager.getItemCount();
                            if(last + 1 == totalcount){
                                tellerPopular++;
                                startPositiePopular = last;
                                showMostPopular();

                            }

                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(),MovieDetailPage.class);
                        int positie = recyclerView.getChildAdapterPosition(view);
                        Movie movie = moviesMostPopular.get(positie);
                        String titel = movie.getTitle();
                        String overview = movie.getOverview();
                        String taal = movie.getOriginalLanguage();
                        String imageUrl = movie.getPosterPath();
                        String releaseDate = movie.getReleaseDate();
                        intent.putExtra("titel",titel);
                        intent.putExtra("overview",overview);
                        intent.putExtra("taal",taal);
                        intent.putExtra("imageUrl",imageUrl);
                        intent.putExtra("releaseDate",releaseDate);
                        startActivity(intent);
                    }
                })
        );

    }

    public void showFutureMovies(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        recyclerView =(RecyclerView) findViewById(R.id.reclycer_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        Call<MovieResponse> call = apiService.getUpcomingMovies(API_KEY,tellerFuture);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                moviesFutre.addAll(movies);
                Log.d(TAG, "Number of movies received in future movies: " + movies.size());
                adapter = new MovieAdapter(moviesFutre);
                recyclerView.setAdapter(adapter);
                mLayoutManager.scrollToPosition(startPositieFuture);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if(dy > 0) //check for scroll down
                        {
                            int last = mLayoutManager.findLastCompletelyVisibleItemPosition();
                            int totalcount = mLayoutManager.getItemCount();
                            if(last + 1 == totalcount){
                                tellerFuture++;
                                startPositieFuture = last;
                                showFutureMovies();

                            }

                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(),MovieDetailPage.class);
                        int positie = recyclerView.getChildAdapterPosition(view);
                        Movie movie = moviesFutre.get(positie);
                        String titel = movie.getTitle();
                        String overview = movie.getOverview();
                        String taal = movie.getOriginalLanguage();
                        String imageUrl = movie.getPosterPath();
                        String releaseDate = movie.getReleaseDate();
                        intent.putExtra("titel",titel);
                        intent.putExtra("overview",overview);
                        intent.putExtra("taal",taal);
                        intent.putExtra("imageUrl",imageUrl);
                        intent.putExtra("releaseDate",releaseDate);
                        startActivity(intent);
                    }
                })
        );
    }
    public void showFutureMoviesDb(){
        recyclerView =(RecyclerView) findViewById(R.id.reclycer_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        if(myDbHandler.databaseToMovies("moviesFuture").isEmpty()){
            showFutureMovies();
        }else {
            final List<Movie> movies = myDbHandler.databaseToMovies("moviesFuture");
            adapter = new MovieAdapter(movies);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getApplicationContext(), MovieDetailPage.class);
                            int positie = recyclerView.getChildAdapterPosition(view);
                            Movie movie = movies.get(positie);
                            String titel = movie.getTitle();
                            String overview = movie.getOverview();
                            String taal = movie.getOriginalLanguage();
                            String imageUrl = movie.getPosterPath();
                            String releaseDate = movie.getReleaseDate();
                            intent.putExtra("titel", titel);
                            intent.putExtra("overview", overview);
                            intent.putExtra("taal", taal);
                            intent.putExtra("imageUrl", imageUrl);
                            intent.putExtra("releaseDate", releaseDate);
                            startActivity(intent);
                        }
                    })
            );
        }
    }
    public void showCinemaMoviesDb(){
        recyclerView =(RecyclerView) findViewById(R.id.reclycer_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        if(myDbHandler.databaseToMovies("moviesCinema").isEmpty()){
            showCinemaMovies();
        }else {
            final List<Movie> movies = myDbHandler.databaseToMovies("moviesCinema");
            adapter = new MovieAdapter(movies);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getApplicationContext(), MovieDetailPage.class);
                            int positie = recyclerView.getChildAdapterPosition(view);
                            Movie movie = movies.get(positie);
                            String titel = movie.getTitle();
                            String overview = movie.getOverview();
                            String taal = movie.getOriginalLanguage();
                            String imageUrl = movie.getPosterPath();
                            String releaseDate = movie.getReleaseDate();
                            intent.putExtra("titel", titel);
                            intent.putExtra("overview", overview);
                            intent.putExtra("taal", taal);
                            intent.putExtra("imageUrl", imageUrl);
                            intent.putExtra("releaseDate", releaseDate);
                            startActivity(intent);
                        }
                    })
            );
        }

    }

    public void showTopRatedMoviesDb(){

        recyclerView =(RecyclerView) findViewById(R.id.reclycer_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        if(myDbHandler.databaseToMovies("moviesTopRated").isEmpty()){
            showTopRatedMovies();
        }else {
            final List<Movie> movies = myDbHandler.databaseToMovies("moviesTopRated");
            adapter = new MovieAdapter(movies);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getApplicationContext(), MovieDetailPage.class);
                            int positie = recyclerView.getChildAdapterPosition(view);
                            Movie movie = movies.get(positie);
                            String titel = movie.getTitle();
                            String overview = movie.getOverview();
                            String taal = movie.getOriginalLanguage();
                            String imageUrl = movie.getPosterPath();
                            String releaseDate = movie.getReleaseDate();
                            intent.putExtra("titel", titel);
                            intent.putExtra("overview", overview);
                            intent.putExtra("taal", taal);
                            intent.putExtra("imageUrl", imageUrl);
                            intent.putExtra("releaseDate", releaseDate);
                            startActivity(intent);
                        }
                    })
            );
        }

    }

    public void showPopularMoviesDb(){
        recyclerView =(RecyclerView) findViewById(R.id.reclycer_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        if(myDbHandler.databaseToMovies("moviesPopular").isEmpty()){
            showMostPopular();
        }else {
            final List<Movie> movies = myDbHandler.databaseToMovies("moviesPopular");
            adapter = new MovieAdapter(movies);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getApplicationContext(), MovieDetailPage.class);
                            int positie = recyclerView.getChildAdapterPosition(view);
                            Movie movie = movies.get(positie);
                            String titel = movie.getTitle();
                            String overview = movie.getOverview();
                            String taal = movie.getOriginalLanguage();
                            String imageUrl = movie.getPosterPath();
                            String releaseDate = movie.getReleaseDate();
                            intent.putExtra("titel", titel);
                            intent.putExtra("overview", overview);
                            intent.putExtra("taal", taal);
                            intent.putExtra("imageUrl", imageUrl);
                            intent.putExtra("releaseDate", releaseDate);
                            startActivity(intent);
                        }
                    })
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (getSupportActionBar().getTitle().toString()){
            case "nu in de bioscoop" : showCinemaMovies();break;
            case "beste rating" : showTopRatedMovies();break;
            case "meest populaire" : showMostPopular();break;
            case "binnenkort" : showFutureMovies();break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!moviesMostPopular.isEmpty()){
            myDbHandler.deleteMovieList("moviesPopular");
            myDbHandler.addProduct(moviesMostPopular,"moviesPopular");
        }
        if(!moviesTopRated.isEmpty()){
            myDbHandler.deleteMovieList("moviesTopRated");
            myDbHandler.addProduct(moviesTopRated,"moviesTopRated");
        }
        if(!moviesCinema.isEmpty()){
            myDbHandler.deleteMovieList("moviesCinema");
            myDbHandler.addProduct(moviesCinema,"moviesCinema");
        }
        if(!moviesFutre.isEmpty()){
            myDbHandler.deleteMovieList("moviesFuture");
            myDbHandler.addProduct(moviesFutre,"moviesFuture");
        }
    }
}
