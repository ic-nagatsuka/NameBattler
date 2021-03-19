package com.namebattler.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class GetDataBase {

    SQLiteDatabase db;

    public Cursor getAllData(Context context) {
        SQLiteOpenHelper helper = new CharacterInformation(context);
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }


}
