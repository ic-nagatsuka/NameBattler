package com.namebattler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.namebattler.battle.player.Player;


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

    public long setCharacter(Player player, int job, String dateTime ){
        db = helper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put("NAME", player.getName());
        values.put("JOB", job);
        values.put("HP", player.getHP());
        values.put("MP", player.getMP());
        values.put("STR", player.getSTR());
        values.put("DEF", player.getDEF());
        values.put("LUCK", player.getAGI());
        values.put("AGI", player.getLUCK());
        values.put("CREATE_AT", dateTime);

        return db.insert(CharacterInformation.TABLE_NAME, null, values);
    }


    public void deleteCharacter(String name){
        db = helper.getReadableDatabase();

        db.delete(
                CharacterInformation.TABLE_NAME,
                "name = ?",
                new String[] {name});
    }



}
