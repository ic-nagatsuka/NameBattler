package com.namebattler.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class GetDataBase {

    SQLiteDatabase db;
    SQLiteOpenHelper helper;

    Context context;
    public GetDataBase(Context context){
        this.context = context;
    }

    public Cursor getAllData(){
        helper = new CharacterInformation(context);
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    public Cursor getCharacter(String name){
        helper = new CharacterInformation(context);
        db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + " WHERE name = ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{name});

        return cursor;
    }




}
