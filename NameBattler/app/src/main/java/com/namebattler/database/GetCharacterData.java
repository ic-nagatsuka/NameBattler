package com.namebattler.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class GetCharacterData {

    SQLiteDatabase db;
    SQLiteOpenHelper helper;

    Context context;
    public GetCharacterData(Context context){
        this.context = context;
        helper = new CharacterInformation(context);
    }

    public Cursor getAllData(){
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    public Cursor getCharacter(String name){
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + " WHERE name = ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{name});

        return cursor;
    }

    public void deleteCharacter(String name){
        db = helper.getReadableDatabase();

        db.delete(
                CharacterInformation.TABLE_NAME,
                "name = ?",
                new String[] {name});
    }



}
