package com.namebattler.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CharacterInformation extends SQLiteOpenHelper {

    static final String DB_NAME = "nameBattler.character";
    public static final String TABLE_NAME = "CHARACTERS";
    static final int DB_VERSION = 1;

    public CharacterInformation(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                "NAME TEXT PRIMARY KEY DEFAULT '' NOT NULL, " +
                "JOB INTEGER DEFAULT 0 NOT NULL," +
                "HP  INTEGER DEFAULT 0 NOT NULL," +
                "MP  INTEGER DEFAULT 0 NOT NULL," +
                "STR INTEGER DEFAULT 0 NOT NULL," +
                "DEF INTEGER DEFAULT 0 NOT NULL, " +
                "LUCK INTEGER DEFAULT 0 NOT NULL," +
                "AGI INTEGER DEFAULT 0 NOT NULL," +
                "CREATE_AT TEXT DEFAULT NULL" +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TABLE_NAME + ";");
        onCreate(db);
    }
}
