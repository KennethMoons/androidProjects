package com.example.kenneth.a2048game;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.continueBtn)
    public void continueClick(View view){
        Intent intent = new Intent(this,BoardActivity.class);
        intent.putExtra("modus","continue");
        startActivity(intent);
    }

    @OnClick(R.id.startNewBtn)
    public void newGameClick(View view){
        Intent intent = new Intent(this,BoardActivity.class);
        intent.putExtra("modus","new");
        startActivity(intent);
    }

    @OnClick(R.id.aboutBtn)
    public void aboutClick(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("2048 game");
        alertDialogBuilder.setMessage(R.string.aboutString);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @OnClick(R.id.exitBtn)
    public void exitClick(View view){
        finish();

    }


}
