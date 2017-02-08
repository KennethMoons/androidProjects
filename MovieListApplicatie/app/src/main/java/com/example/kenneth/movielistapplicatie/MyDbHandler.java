package com.example.kenneth.movielistapplicatie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenneth on 27/01/2017.
 */

public class MyDbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1 ;
    private static final String DATABASE_NAME = "movies.db";
    private static final String TABLE_MOVIESTOPRATED = "moviesTopRated";
    private static final String TABLE_MOVIESPOPULAR = "moviesPopular";
    private static final String TABLE_MOVIESCINEMA = "moviesCinema";
    private static final String TABLE_MOVIESFUTURE = "moviesFuture";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITEL = "_titel";
    private static final String COLUMN_RELEASE = "_releaseDate";
    private static final String COLUMN_PATH = "_path";
    private static final String COLUMN_TAAL = "_taal";
    private static final String COLUMN_OVERVIEW = "_overview";

    Context context;

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_MOVIESTOPRATED + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITEL + " TEXT, " +
                COLUMN_RELEASE + " TEXT, " +
                COLUMN_PATH + " TEXT, " +
                COLUMN_TAAL + " TEXT, " +
                COLUMN_OVERVIEW + " TEXT " +
                ");" ;
        sqLiteDatabase.execSQL(query);
        String query1 = "CREATE TABLE " + TABLE_MOVIESPOPULAR + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITEL + " TEXT, " +
                COLUMN_RELEASE + " TEXT, " +
                COLUMN_PATH + " TEXT, " +
                COLUMN_TAAL + " TEXT, " +
                COLUMN_OVERVIEW + " TEXT " +
                ");" ;
        sqLiteDatabase.execSQL(query1);
        String query2 = "CREATE TABLE " + TABLE_MOVIESCINEMA + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITEL + " TEXT, " +
                COLUMN_RELEASE + " TEXT, " +
                COLUMN_PATH + " TEXT, " +
                COLUMN_TAAL + " TEXT, " +
                COLUMN_OVERVIEW + " TEXT " +
                ");" ;
        sqLiteDatabase.execSQL(query2);
        String query3 = "CREATE TABLE " + TABLE_MOVIESFUTURE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITEL + " TEXT, " +
                COLUMN_RELEASE + " TEXT, " +
                COLUMN_PATH + " TEXT, " +
                COLUMN_TAAL + " TEXT, " +
                COLUMN_OVERVIEW + " TEXT " +
                ");" ;
        sqLiteDatabase.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIESTOPRATED);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIESPOPULAR);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIESCINEMA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIESFUTURE);
        onCreate(sqLiteDatabase);
    }

    public void deleteMovieList(String movieList){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + movieList +" ;");

    }
    public void addProduct(List<Movie> movies,String tabel){
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        for (Movie m:movies) {
            values.put(COLUMN_TITEL,m.getTitle());
            values.put(COLUMN_RELEASE,m.getReleaseDate());
            values.put(COLUMN_PATH,m.getPosterPath());
            values.put(COLUMN_TAAL,m.getOriginalLanguage());
            values.put(COLUMN_OVERVIEW,m.getOverview());
            sqLiteDatabase.insert(tabel,null,values);
        }
        sqLiteDatabase.close();
    }

    public List<Movie> databaseToMovies(String tabel){
        List<Movie>movies = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + tabel + " WHERE 1";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Movie movie = new Movie();
                movie.setTitle(cursor.getString(1));
                movie.setReleaseDate(cursor.getString(2));
                movie.setPosterPath(cursor.getString(3));
                movie.setOriginalLanguage(cursor.getString(4));
                movie.setOverview(cursor.getString(5));
                movies.add(movie);

            }while(cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return movies;

    }
}
