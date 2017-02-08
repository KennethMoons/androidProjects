package com.example.kenneth.movielistapplicatie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String PREFS_NAME = "MyPrefsFile";

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(PREFS_NAME, 0);


    }

    public void cinemaClick(View view) {
        Intent intent = new Intent(this,MovieListDisplay.class);
        intent.putExtra("typeList","cinema");
        startActivity(intent);
    }

    public void ratingClick(View view) {
        Intent intent = new Intent(this,MovieListDisplay.class);
        intent.putExtra("typeList","rating");
        startActivity(intent);
    }


    public void populairsteClick(View view) {
        Intent intent = new Intent(this,MovieListDisplay.class);
        intent.putExtra("typeList","populairste");
        startActivity(intent);
    }

    public void binnenkortClick(View view) {
        Intent intent = new Intent(this,MovieListDisplay.class);
        intent.putExtra("typeList","binnenkort");
        startActivity(intent);
    }
}
