package com.example.kenneth.a2048game;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.renderscript.Sampler;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;
import static android.widget.GridLayout.spec;
import static java.security.AccessController.getContext;

public class BoardActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.myGridLayout)GridLayout myGridlayout;
    @BindView(R.id.scoreTxt)TextView scoreTextview;

    public static final String PREFS_NAME = "MyPrefsFile";

    float x1 = 0, x2 = 0, y1 = 0, y2 = 0, dx =0, dy = 0;

    int[][]playboard;

    int scoreTeller = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        switch (intent.getStringExtra("modus")){
            case "new" : playboard = vulBoard(); break;
            case "continue" : playboard = vulBoardMemory();break;
        }
        drawBoard(playboard);


    }



    public void drawBoard(int[][] board){
        myGridlayout.removeAllViews();
        for(int i = 0; i < 4; i++){
            GridLayout.Spec row = spec(i,1,1);
            for(int y = 0; y < 4;y++){
                GridLayout.Spec col = spec(y,1,1);
                TextView c = new TextView(this);
                c.setTextSize(25);
                if(board[i][y] == 0){
                    c.setText(" ");
                }
                else {
                    String text = Integer.toString(board[i][y]);
                    c.setText(text);
                }

                GradientDrawable gd = new GradientDrawable();
                gd.setColor(0xFF00FF00);
                gd.setCornerRadius(5);
                gd.setStroke(1, 0xFF000000);
                if(android.os.Build.VERSION.SDK_INT >= 16)
                c.setBackground(gd);
                else
                c.setBackgroundDrawable(gd);
                myGridlayout.addView(c, new GridLayout.LayoutParams(row, col));
            }
        }
    }

    public int[][] vulBoardTest(){
        int[][]board = new int[4][4];
        board[0][0] = 2;
        board[0][1] = 2;
        board[0][2] = 2;
        board[0][3] = 2;

        board[1][0] = 0;
        board[1][1] = 8;
        board[1][2] = 0;
        board[1][3] = 4;

        board[2][0] = 2;
        board[2][1] = 2;
        board[2][2] = 0;
        board[2][3] = 8;

        board[3][0] = 4;
        board[3][1] = 0;
        board[3][2] = 2;
        board[3][3] = 0;

        return board;
    }
    public int[][] vulBoard(){
        int[][]board = new int[4][4];
        for(int i = 0 ; i < 4;i++){
            for(int y = 0 ; y < 4;y++){
                board[i][y] = 0;
            }
        }
        Random random = new Random();
        int xwaarde1 = random.nextInt(4);
        int ywaarde1 = random.nextInt(4);
        int xwaarde2 = random.nextInt(4);
        int ywaarde2 = random.nextInt(4);
        while (xwaarde1 == xwaarde2 && ywaarde1 == ywaarde2){
            xwaarde2 = random.nextInt(4);
            ywaarde2 = random.nextInt(4);
        }
        board[xwaarde1][ywaarde1] = 2;
        board[xwaarde2][ywaarde2] = 2;

        return board;
    }
    public int[][] vulBoardMemory(){
        int[][]board = new int[4][4];
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if(!settings.contains("board")){
            board = vulBoard();
        } else {
            String boardString = settings.getString("board", "");
            String[] stap1 = boardString.split("\n");
            for (int i = 0; i < 4; i++) {
                String[] stap2 = stap1[i].split(" ");
                for (int y = 0; y < 4; y++) {
                    board[i][y] = Integer.parseInt(stap2[y]);
                }
            }
        }
        return board;
    }

    public int[][] boardTransformation(int[][]board){//dit wordt de hoofdklasse waar daarna op geroteerd word. dit is swipe naar beneden
        board = colomnTransformation(board,0);
        board = colomnTransformation(board,1);
        board = colomnTransformation(board,2);
        board = colomnTransformation(board,3);
        return board;
    }

    public int[][] colomnTransformation(int[][]board,int column){
        boolean transformatieKlaar = false;//om door te blijven loope zolang er iets kan veranderd worden
        //if een heel rij = 0 is moet hem niet die rij doen!!!!
        if(board[3][column]==0 && board[2][column]==0 && board[1][column]==0 && board[0][column]==0){
            transformatieKlaar = true;
        }
        while(transformatieKlaar == false ) {
            boolean check = false;
            if (board[3][column] == 0 && board[2][column]!=0) {
                board[3][column] = board[2][column];
                board[2][column] = 0;
                check = true;
            }

            if (board[2][column] == 0 && board[1][column]!=0) {
                board[2][column] = board[1][column];
                board[1][column] = 0;
                check = true;
            }

            if (board[1][column] == 0 && board[0][column]!=0) {
                board[1][column] = board[0][column];
                board[0][column] = 0;
                check = true;
            }

            if(check == false)
                transformatieKlaar = true;
        }
        if(board[3][column]==board[2][column]){
            board[3][column] = board[3][column]*2;
            board[2][column] = board[1][column];
            board[1][column] = board[0][column];
            board[0][column] = 0;
        }
        if(board[2][column]==board[1][column]){
            board[2][column] = board[2][column]*2;
            board[1][column] = board[0][column];
            board[0][column] = 0;
        }
        if(board[1][column]==board[0][column]){
            board[1][column] = board[1][column]*2;
            board[0][column] = 0;
        }
        return board;
    }

    public int[][]swipeLeftTransformatie(int[][]b){
        int[][]board = new int[4][4];

        board[0][0] = b[0][3];
        board[0][1] = b[1][3];
        board[0][2] = b[2][3];
        board[0][3] = b[3][3];

        board[1][0] = b[0][2];
        board[1][1] = b[1][2];
        board[1][2] = b[2][2];
        board[1][3] = b[3][2];

        board[2][0] = b[0][1];
        board[2][1] = b[1][1];
        board[2][2] = b[2][1];
        board[2][3] = b[3][1];

        board[3][0] = b[0][0];
        board[3][1] = b[1][0];
        board[3][2] = b[2][0];
        board[3][3] = b[3][0];
        return board;
    }

    public int[][]swiperightTransformatie(int[][]b){
        int[][]board = new int[4][4];

        board[0][0] = b[3][0];
        board[0][1] = b[2][0];
        board[0][2] = b[1][0];
        board[0][3] = b[0][0];

        board[1][0] = b[3][1];
        board[1][1] = b[2][1];
        board[1][2] = b[1][1];
        board[1][3] = b[0][1];

        board[2][0] = b[3][2];
        board[2][1] = b[2][2];
        board[2][2] = b[1][2];
        board[2][3] = b[0][2];

        board[3][0] = b[3][3];
        board[3][1] = b[2][3];
        board[3][2] = b[1][3];
        board[3][3] = b[0][3];
        return board;
    }
    public int[][]swipeTopTransformatie(int[][]b){
        int[][]board = new int[4][4];

        board[0][0] = b[3][0];
        board[0][1] = b[3][1];
        board[0][2] = b[3][2];
        board[0][3] = b[3][3];

        board[1][0] = b[2][0];
        board[1][1] = b[2][1];
        board[1][2] = b[2][2];
        board[1][3] = b[2][3];

        board[2][0] = b[1][0];
        board[2][1] = b[1][1];
        board[2][2] = b[1][2];
        board[2][3] = b[1][3];

        board[3][0] = b[0][0];
        board[3][1] = b[0][1];
        board[3][2] = b[0][2];
        board[3][3] = b[0][3];
        return board;
    }

    public int[][] addNumber(int[][]board){
        boolean check = false; //nakijken of er nog verder gespeeld kan worden
        List<String>lijst = new ArrayList<>();
        for(int i = 0 ; i < 4;i++){
            for(int y = 0 ; y < 4;y++){
                if(board[i][y]== 2048){
                    Bundle bundle = new Bundle();
                    bundle.putString("score", scoreTextview.getText().toString());
                    WonGameFragment wonGameFragment = new WonGameFragment();
                    wonGameFragment.setArguments(bundle);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.add(wonGameFragment, null);
                    ft.commit();
                }
            }
        }
        for(int i = 0 ; i < 4;i++){
            for(int y = 0 ; y < 4;y++){
                if(board[i][y]== 0){
                    check = true;
                    lijst.add(Integer.toString(i) + Integer.toString(y));
                }
            }
        }
        if(check == false){//betekent dat er geen vrije plekken zijn dus nakijken of er nog ne zet gedaan kan worden

            if(checkFailed(board)== true){//veloren
                LostGameFragment lostGameFragment = new LostGameFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(lostGameFragment, null);
                ft.commit();
            }
            else {
                return null;
            }
        }else {//betekend er is een vrije plek om nog een 2 aan toe te voegen
            Random random = new Random();
            int randomGetal = random.nextInt(lijst.size()-1);
            int xcordinaat = Integer.parseInt( Character.toString(lijst.get(randomGetal).charAt(0)));
            int ycordinaat = Integer.parseInt( Character.toString(lijst.get(randomGetal).charAt(1)));
            board[xcordinaat][ycordinaat] = 2;

        }
        Log.i("boardcheck ","addNumber doorlopen");
        return board;
    }

    public boolean checkFailed(int[][]board){//ga ik na of er iets veranderd kan worden. als in elke mogelijke richting geen zetten meer mogelijk zijn is het game over
        boolean check = false;
        int[][]kopieBoard = board;
        kopieBoard = boardTransformation(kopieBoard);
        if(Arrays.deepEquals(kopieBoard,board)){
            kopieBoard = swipeLeftTransformatie(kopieBoard);
            kopieBoard = boardTransformation(kopieBoard);
            kopieBoard = swiperightTransformatie(kopieBoard);
            if(Arrays.deepEquals(kopieBoard,board)){
                kopieBoard = swiperightTransformatie(kopieBoard);
                kopieBoard = boardTransformation(kopieBoard);
                kopieBoard = swipeLeftTransformatie(kopieBoard);
                if(Arrays.deepEquals(kopieBoard,board)){
                    kopieBoard = swipeTopTransformatie(kopieBoard);
                    kopieBoard = boardTransformation(kopieBoard);
                    kopieBoard = swipeTopTransformatie(kopieBoard);
                    if(Arrays.deepEquals(kopieBoard,board)){
                        check = true;
                    }
                    else {
                        check = false;
                    }
                } else {
                    check = false;
                }

            }else {
                check = false;
            }

        }else {
            check = false;
        }
        return check;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String direction;
        switch(event.getAction()) {
            case(MotionEvent.ACTION_DOWN):
                x1 = event.getX();
                y1 = event.getY();

                break;

            case(MotionEvent.ACTION_UP): {
                x2 = event.getX();
                y2 = event.getY();

                dx = x1 - x2;
                dy = y1 - y2;

                if((dy < -75)&&(-50 <= dx && dx <= 50)){
                    x1 = 0; x2 = 0; y1 = 0; y2 = 0; dx =0; dy = 0;
                    playboard = boardTransformation(playboard);
                    int[][]boardKopie = playboard;
                    playboard = addNumber(playboard);
                    if(playboard == null){
                        playboard = boardKopie;
                    }
                    scoreTeller++;
                    scoreTextview.setText("score : " + Integer.toString(scoreTeller));
                    drawBoard(playboard);
                    Log.i("swipe down ","swipe down doorlopen");
                }
                if((dy > 75)&&(-50 <= dx && dx <= 50)){
                    x1 = 0; x2 = 0; y1 = 0; y2 = 0; dx =0; dy = 0;
                    playboard = swipeTopTransformatie(playboard);
                    playboard = boardTransformation(playboard);
                    playboard = swipeTopTransformatie(playboard);
                    int[][]boardKopie = playboard;
                    playboard = addNumber(playboard);
                    if(playboard == null){
                        playboard = boardKopie;
                    }
                    drawBoard(playboard);
                    Log.i("swipe ","swipe up doorlopen");
                }

                if((dx < -75)&&(-50 <= dy && dy <= 50)){
                    x1 = 0; x2 = 0; y1 = 0; y2 = 0; dx =0; dy = 0;
                    playboard = swiperightTransformatie(playboard);
                    playboard = boardTransformation(playboard);
                    playboard = swipeLeftTransformatie(playboard);
                    int[][]boardKopie = playboard;
                    playboard = addNumber(playboard);
                    if(playboard == null){
                        playboard = boardKopie;
                    }
                    drawBoard(playboard);
                    Log.i("swipe ","swipe left doorlopen");

                }
                if((dx > 75)&&(-50 <= dy && dy <= 50)){
                    x1 = 0; x2 = 0; y1 = 0; y2 = 0; dx =0; dy = 0;
                    playboard = swipeLeftTransformatie(playboard);
                    playboard = boardTransformation(playboard);
                    playboard = swiperightTransformatie(playboard);
                    int[][]boardKopie = playboard;
                    playboard = addNumber(playboard);
                    if(playboard == null){
                        playboard = boardKopie;
                    }
                    drawBoard(playboard);
                    Log.i("swipe down ","swipe right doorlopen");
                }

            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        String board = "";
        for(int i = 0; i < 4; i++){
            for(int y = 0; y < 4;y++){
                board += Integer.toString(playboard[i][y]) + " ";
            }
            board.trim();
            board += "\n";
        }
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("board", board);
        editor.commit();
        super.onPause();
    }
}
