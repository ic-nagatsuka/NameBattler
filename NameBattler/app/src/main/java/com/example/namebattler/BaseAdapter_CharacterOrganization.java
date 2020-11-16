package com.example.namebattler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namebattler.database.CharacterInformation;

public class BaseAdapter_CharacterOrganization extends BaseAdapter {


    LayoutInflater inflater;

    Context context;

    SQLiteOpenHelper helper ;
    SQLiteDatabase db;
    String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";
//    Cursor cursor = db.rawQuery(sql,null,null);
    static int count = 0;
    int id ;
    View v;
    BaseAdapter_CharacterOrganization(Context context, View v){
        this.context = context;
        this.v = v;
        inflater = LayoutInflater.from(context);
        helper = new CharacterInformation(context);
        db = helper.getWritableDatabase();


        System.out.println(helper + "コンストラクタ");
    }

    @Override
    public int getCount() {
//        return cursor.getCount();
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.listview_character_organization, null);
        }

        final CheckBox checkBox = view.findViewById(R.id.character_organization_listView_checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TextView textView = view.findViewById(R.id.character_organization_listView_status_name);

                boolean isChecked = checkBox.isChecked();
                checkBox.setChecked(false);
                if(count <= 2 && isChecked){
                    checkBox.setChecked(true);
                    count++;
                }else if(!isChecked){
                    count--;
                }else{
                    Toast.makeText(context, "最大数に達しました", Toast.LENGTH_SHORT).show();
                }

                Button btn = v.findViewById(R.id.character_organization_start);

                btn.setText("このパーティーで開始(" + count + "/3)");

            }
        });

        return view;
    }
}
