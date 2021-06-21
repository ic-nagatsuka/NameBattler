package com.namebattler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.namebattler.battle.player.Player;


public class OperationCharacterData {

    SQLiteDatabase db;
    SQLiteOpenHelper helper;

    Context context;

    public OperationCharacterData(Context context) {
        this.context = context;
        this.helper = new CharacterInformationHelper(context);
    }

    public int getCharacterCount() {
        return getAllData().getCount();
    }

    public Cursor getAllData() {
        this.db = this.helper.getReadableDatabase();
        String sql = "SELECT * FROM " + CharacterInformationHelper.TABLE_NAME + ";";
        Cursor cursor = this.db.rawQuery(sql, null);

        return cursor;
    }

    public Cursor getCharacter(String name) {
        this.db = this.helper.getReadableDatabase();
        String sql = "SELECT * FROM " + CharacterInformationHelper.TABLE_NAME + " WHERE name = ?;";
        Cursor cursor = this.db.rawQuery(sql, new String[]{name});

        return cursor;
    }

    public long setCharacter(Player player, int job, String dateTime) {
        this.db = this.helper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put("NAME", player.getName());
        values.put("JOB", job);
        values.put("HP", player.getHp());
        values.put("MP", player.getMp());
        values.put("STR", player.getStr());
        values.put("DEF", player.getDef());
        values.put("LUCK", player.getAgi());
        values.put("AGI", player.getLuck());
        values.put("CREATE_AT", dateTime);

        return this.db.insert(CharacterInformationHelper.TABLE_NAME, null, values);
    }


    public void deleteCharacter(String name) {
        this.db = this.helper.getReadableDatabase();

        this.db.delete(
                CharacterInformationHelper.TABLE_NAME,
                "name = ?",
                new String[]{name});
    }


}
