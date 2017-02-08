package com.example.kenneth.whoisitgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenneth on 23/01/2017.
 */

public class MyDbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5 ;
    private static final String DATABASE_NAME = "personen.db";
    private static final String TABLE_PERSONEN = "personen";
    private static final String COLUMN_ID = "_id";

    private static final String COLUMN_NAAM = "_naam";
    private static final String COLUMN_GESLACHT = "_geslacht";
    private static final String COLUMN_KLEURHAAR = "_kleurhaar";
    private static final String COLUMN_KAAL = "_kaal";
    private static final String COLUMN_HOOFDDEKSEL = "_hoofddeksel";

    private static final String COLUMN_BRIL = "_bril";
    private static final String COLUMN_HUIDSKLEUR = "_huidskleur";
    private static final String COLUMN_BAARD = "_baard";
    private static final String COLUMN_SNOR = "_snor";

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_PERSONEN + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAAM + " TEXT,  " +
                COLUMN_GESLACHT + " INTEGER, " + COLUMN_KLEURHAAR + " TEXT, " + COLUMN_KAAL + " INTEGER, " + COLUMN_HOOFDDEKSEL + " INTEGER, " +
                COLUMN_BRIL + " INTEGER, " + COLUMN_HUIDSKLEUR + " TEXT, " + COLUMN_BAARD  + " INTEGER, " + COLUMN_SNOR + " INTEGER " +
                ");" ;
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONEN);
        onCreate(sqLiteDatabase);
    }
    public void delete(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query2 = "DELETE FROM " + TABLE_PERSONEN + ";";
        sqLiteDatabase.execSQL(query2);
    }

    public void addPersonenBulk(){

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAAM,"alex");
        values.put(COLUMN_GESLACHT,0);
        values.put(COLUMN_KLEURHAAR,"zwart");
        values.put(COLUMN_KAAL,0);
        values.put(COLUMN_HOOFDDEKSEL,0);
        values.put(COLUMN_BRIL,0);
        values.put(COLUMN_HUIDSKLEUR,"bruin");
        values.put(COLUMN_BAARD,0);
        values.put(COLUMN_SNOR,1);

        ContentValues values1 = new ContentValues();
        values1.put(COLUMN_NAAM,"alfred");
        values1.put(COLUMN_GESLACHT,0);
        values1.put(COLUMN_KLEURHAAR,"ros");
        values1.put(COLUMN_KAAL,0);
        values1.put(COLUMN_HOOFDDEKSEL,0);
        values1.put(COLUMN_BRIL,0);
        values1.put(COLUMN_HUIDSKLEUR,"blank");
        values1.put(COLUMN_BAARD,0);
        values1.put(COLUMN_SNOR,1);

        ContentValues values2 = new ContentValues();
        values2.put(COLUMN_NAAM,"anita");
        values2.put(COLUMN_GESLACHT,1);
        values2.put(COLUMN_KLEURHAAR,"blond");
        values2.put(COLUMN_KAAL,0);
        values2.put(COLUMN_HOOFDDEKSEL,0);
        values2.put(COLUMN_BRIL,0);
        values2.put(COLUMN_HUIDSKLEUR,"blank");
        values2.put(COLUMN_BAARD,0);
        values2.put(COLUMN_SNOR,0);

        ContentValues values3 = new ContentValues();

        values3.put(COLUMN_NAAM,"anne");
        values3.put(COLUMN_GESLACHT,1);
        values3.put(COLUMN_KLEURHAAR,"zwart");
        values3.put(COLUMN_KAAL,0);
        values3.put(COLUMN_HOOFDDEKSEL,0);
        values3.put(COLUMN_BRIL,0);
        values3.put(COLUMN_HUIDSKLEUR,"bruin");
        values3.put(COLUMN_BAARD,0);
        values3.put(COLUMN_SNOR,0);

        ContentValues values4 = new ContentValues();

        values4.put(COLUMN_NAAM,"bernard");
        values4.put(COLUMN_GESLACHT,0);
        values4.put(COLUMN_KLEURHAAR,"bruin");
        values4.put(COLUMN_KAAL,0);
        values4.put(COLUMN_HOOFDDEKSEL,1);
        values4.put(COLUMN_BRIL,0);
        values4.put(COLUMN_HUIDSKLEUR,"blank");
        values4.put(COLUMN_BAARD,0);
        values4.put(COLUMN_SNOR,0);

        ContentValues values5 = new ContentValues();

        values5.put(COLUMN_NAAM,"bill");
        values5.put(COLUMN_GESLACHT,0);
        values5.put(COLUMN_KLEURHAAR,"ros");
        values5.put(COLUMN_KAAL,1);
        values5.put(COLUMN_HOOFDDEKSEL,0);
        values5.put(COLUMN_BRIL,0);
        values5.put(COLUMN_HUIDSKLEUR,"blank");
        values5.put(COLUMN_BAARD,1);
        values5.put(COLUMN_SNOR,0);

        ContentValues values6 = new ContentValues();

        values6.put(COLUMN_NAAM,"charles");
        values6.put(COLUMN_GESLACHT,0);
        values6.put(COLUMN_KLEURHAAR,"blond");
        values6.put(COLUMN_KAAL,0);
        values6.put(COLUMN_HOOFDDEKSEL,0);
        values6.put(COLUMN_BRIL,0);
        values6.put(COLUMN_HUIDSKLEUR,"blank");
        values6.put(COLUMN_BAARD,0);
        values6.put(COLUMN_SNOR,1);

        ContentValues values7 = new ContentValues();

        values7.put(COLUMN_NAAM,"claire");
        values7.put(COLUMN_GESLACHT,1);
        values7.put(COLUMN_KLEURHAAR,"ros");
        values7.put(COLUMN_KAAL,0);
        values7.put(COLUMN_HOOFDDEKSEL,1);
        values7.put(COLUMN_BRIL,1);
        values7.put(COLUMN_HUIDSKLEUR,"blank");
        values7.put(COLUMN_BAARD,0);
        values7.put(COLUMN_SNOR,0);

        ContentValues values8 = new ContentValues();

        values8.put(COLUMN_NAAM,"David");
        values8.put(COLUMN_GESLACHT,0);
        values8.put(COLUMN_KLEURHAAR,"blond");
        values8.put(COLUMN_KAAL,0);
        values8.put(COLUMN_HOOFDDEKSEL,0);
        values8.put(COLUMN_BRIL,0);
        values8.put(COLUMN_HUIDSKLEUR,"blank");
        values8.put(COLUMN_BAARD,1);
        values8.put(COLUMN_SNOR,0);

        ContentValues values9 = new ContentValues();

        values9.put(COLUMN_NAAM,"eric");
        values9.put(COLUMN_GESLACHT,0);
        values9.put(COLUMN_KLEURHAAR,"blond");
        values9.put(COLUMN_KAAL,0);
        values9.put(COLUMN_HOOFDDEKSEL,1);
        values9.put(COLUMN_BRIL,0);
        values9.put(COLUMN_HUIDSKLEUR,"blank");
        values9.put(COLUMN_BAARD,0);
        values9.put(COLUMN_SNOR,0);

        ContentValues values10 = new ContentValues();

        values10.put(COLUMN_NAAM,"frans");
        values10.put(COLUMN_GESLACHT,0);
        values10.put(COLUMN_KLEURHAAR,"ros");
        values10.put(COLUMN_KAAL,0);
        values10.put(COLUMN_HOOFDDEKSEL,0);
        values10.put(COLUMN_BRIL,0);
        values10.put(COLUMN_HUIDSKLEUR,"blank");
        values10.put(COLUMN_BAARD,0);
        values10.put(COLUMN_SNOR,0);

        ContentValues values11 = new ContentValues();

        values11.put(COLUMN_NAAM,"george");
        values11.put(COLUMN_GESLACHT,0);
        values11.put(COLUMN_KLEURHAAR,"wit");
        values11.put(COLUMN_KAAL,0);
        values11.put(COLUMN_HOOFDDEKSEL,1);
        values11.put(COLUMN_BRIL,0);
        values11.put(COLUMN_HUIDSKLEUR,"blank");
        values11.put(COLUMN_BAARD,0);
        values11.put(COLUMN_SNOR,0);

        ContentValues values12 = new ContentValues();

        values12.put(COLUMN_NAAM,"herman");
        values12.put(COLUMN_GESLACHT,0);
        values12.put(COLUMN_KLEURHAAR,"ros");
        values12.put(COLUMN_KAAL,1);
        values12.put(COLUMN_HOOFDDEKSEL,0);
        values12.put(COLUMN_BRIL,0);
        values12.put(COLUMN_HUIDSKLEUR,"blank");
        values12.put(COLUMN_BAARD,1);
        values12.put(COLUMN_SNOR,0);

        ContentValues values13 = new ContentValues();

        values13.put(COLUMN_NAAM,"joe");
        values13.put(COLUMN_GESLACHT,0);
        values13.put(COLUMN_KLEURHAAR,"blond");
        values13.put(COLUMN_KAAL,0);
        values13.put(COLUMN_HOOFDDEKSEL,0);
        values13.put(COLUMN_BRIL,1);
        values13.put(COLUMN_HUIDSKLEUR,"blank");
        values13.put(COLUMN_BAARD,0);
        values13.put(COLUMN_SNOR,0);

        ContentValues values14 = new ContentValues();

        values14.put(COLUMN_NAAM,"maria");
        values14.put(COLUMN_GESLACHT,1);
        values14.put(COLUMN_KLEURHAAR,"bruin");
        values14.put(COLUMN_KAAL,0);
        values14.put(COLUMN_HOOFDDEKSEL,1);
        values14.put(COLUMN_BRIL,0);
        values14.put(COLUMN_HUIDSKLEUR,"blank");
        values14.put(COLUMN_BAARD,0);
        values14.put(COLUMN_SNOR,0);

        ContentValues values15 = new ContentValues();

        values15.put(COLUMN_NAAM,"max");
        values15.put(COLUMN_GESLACHT,0);
        values15.put(COLUMN_KLEURHAAR,"zwart");
        values15.put(COLUMN_KAAL,0);
        values15.put(COLUMN_HOOFDDEKSEL,0);
        values15.put(COLUMN_BRIL,0);
        values15.put(COLUMN_HUIDSKLEUR,"bruin");
        values15.put(COLUMN_BAARD,0);
        values15.put(COLUMN_SNOR,1);

        ContentValues values16 = new ContentValues();

        values16.put(COLUMN_NAAM,"paul");
        values16.put(COLUMN_GESLACHT,0);
        values16.put(COLUMN_KLEURHAAR,"wit");
        values16.put(COLUMN_KAAL,0);
        values16.put(COLUMN_HOOFDDEKSEL,0);
        values16.put(COLUMN_BRIL,1);
        values16.put(COLUMN_HUIDSKLEUR,"blank");
        values16.put(COLUMN_BAARD,0);
        values16.put(COLUMN_SNOR,0);

        ContentValues values17 = new ContentValues();

        values17.put(COLUMN_NAAM,"peter");
        values17.put(COLUMN_GESLACHT,0);
        values17.put(COLUMN_KLEURHAAR,"wit");
        values17.put(COLUMN_KAAL,0);
        values17.put(COLUMN_HOOFDDEKSEL,0);
        values17.put(COLUMN_BRIL,0);
        values17.put(COLUMN_HUIDSKLEUR,"blank");
        values17.put(COLUMN_BAARD,0);
        values17.put(COLUMN_SNOR,0);

        ContentValues values18 = new ContentValues();

        values18.put(COLUMN_NAAM,"philip");
        values18.put(COLUMN_GESLACHT,0);
        values18.put(COLUMN_KLEURHAAR,"zwart");
        values18.put(COLUMN_KAAL,0);
        values18.put(COLUMN_HOOFDDEKSEL,0);
        values18.put(COLUMN_BRIL,0);
        values18.put(COLUMN_HUIDSKLEUR,"bruin");
        values18.put(COLUMN_BAARD,1);
        values18.put(COLUMN_SNOR,0);

        ContentValues values19 = new ContentValues();

        values19.put(COLUMN_NAAM,"richard");
        values19.put(COLUMN_GESLACHT,0);
        values19.put(COLUMN_KLEURHAAR,"bruin");
        values19.put(COLUMN_KAAL,1);
        values19.put(COLUMN_HOOFDDEKSEL,0);
        values19.put(COLUMN_BRIL,0);
        values19.put(COLUMN_HUIDSKLEUR,"blank");
        values19.put(COLUMN_BAARD,1);
        values19.put(COLUMN_SNOR,1);

        ContentValues values20 = new ContentValues();

        values20.put(COLUMN_NAAM,"robert");
        values20.put(COLUMN_GESLACHT,0);
        values20.put(COLUMN_KLEURHAAR,"bruin");
        values20.put(COLUMN_KAAL,0);
        values20.put(COLUMN_HOOFDDEKSEL,0);
        values20.put(COLUMN_BRIL,0);
        values20.put(COLUMN_HUIDSKLEUR,"blank");
        values20.put(COLUMN_BAARD,0);
        values20.put(COLUMN_SNOR,0);

        ContentValues values21 = new ContentValues();

        values21.put(COLUMN_NAAM,"sam");
        values21.put(COLUMN_GESLACHT,0);
        values21.put(COLUMN_KLEURHAAR,"wit");
        values21.put(COLUMN_KAAL,1);
        values21.put(COLUMN_HOOFDDEKSEL,0);
        values21.put(COLUMN_BRIL,1);
        values21.put(COLUMN_HUIDSKLEUR,"blank");
        values21.put(COLUMN_BAARD,1);
        values21.put(COLUMN_SNOR,0);

        ContentValues values22 = new ContentValues();

        values22.put(COLUMN_NAAM,"Susan");
        values22.put(COLUMN_GESLACHT,1);
        values22.put(COLUMN_KLEURHAAR,"wit");
        values22.put(COLUMN_KAAL,0);
        values22.put(COLUMN_HOOFDDEKSEL,0);
        values22.put(COLUMN_BRIL,0);
        values22.put(COLUMN_HUIDSKLEUR,"blank");
        values22.put(COLUMN_BAARD,0);
        values22.put(COLUMN_SNOR,0);

        ContentValues values23 = new ContentValues();

        values23.put(COLUMN_NAAM,"tom");
        values23.put(COLUMN_GESLACHT,0);
        values23.put(COLUMN_KLEURHAAR,"zwart");
        values23.put(COLUMN_KAAL,1);
        values23.put(COLUMN_HOOFDDEKSEL,0);
        values23.put(COLUMN_BRIL,1);
        values23.put(COLUMN_HUIDSKLEUR,"blank");
        values23.put(COLUMN_BAARD,1);
        values23.put(COLUMN_SNOR,0);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values1);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values2);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values3);

        sqLiteDatabase.insert(TABLE_PERSONEN,null,values4);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values5);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values6);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values7);

        sqLiteDatabase.insert(TABLE_PERSONEN,null,values8);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values9);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values10);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values11);

        sqLiteDatabase.insert(TABLE_PERSONEN,null,values12);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values13);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values14);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values15);

        sqLiteDatabase.insert(TABLE_PERSONEN,null,values16);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values17);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values18);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values19);

        sqLiteDatabase.insert(TABLE_PERSONEN,null,values20);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values21);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values22);
        sqLiteDatabase.insert(TABLE_PERSONEN,null,values23);
        sqLiteDatabase.close();
    }

    public ArrayList<Persoon> databaseToList(){
        ArrayList<Persoon>personen = new ArrayList<>();
        String dbString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PERSONEN + " WHERE 1";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Persoon persoon = new Persoon();
                persoon.set_naam(cursor.getString(1));
                persoon.set_geslacht(Integer.parseInt(cursor.getString(2)));
                persoon.set_kleurHaar(cursor.getString(3));
                persoon.set_kaal(Integer.parseInt(cursor.getString(4)));
                persoon.set_hoofddeksel(Integer.parseInt(cursor.getString(5)));
                persoon.set_bril(Integer.parseInt(cursor.getString(6)));
                persoon.set_huidskleur(cursor.getString(7));
                persoon.set_baard(Integer.parseInt(cursor.getString(8)));
                persoon.set_snor(Integer.parseInt(cursor.getString(9)));
                personen.add(persoon);

            }while(cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return personen;
    }
}
