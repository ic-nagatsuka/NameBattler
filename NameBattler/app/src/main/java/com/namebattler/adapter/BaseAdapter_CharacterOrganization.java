package com.namebattler.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.namebattler.R;
import com.namebattler.activity.CharacterOrganization;
import com.namebattler.database.CharacterInformation;
import com.namebattler.battle.Player.Player;

import java.util.List;


import static com.namebattler.Option.Option.makePlayerNum;
import static com.namebattler.battle.GameManager.makePlayer;
import static com.namebattler.battle.GameManager.myParty;

public class BaseAdapter_CharacterOrganization extends BaseAdapter {

    Context context;
    View startButton;
    List<CharacterOrganization.Status> status;

    LayoutInflater inflater;
    SQLiteOpenHelper helper ;
    SQLiteDatabase db;
    Cursor cursor ;
    String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";
    int count = 0;

    public BaseAdapter_CharacterOrganization(Context context, View v, List<CharacterOrganization.Status> status){
        this.context = context;
        this.startButton = v;
        this.status = status;
        inflater = LayoutInflater.from(context);

        helper = new CharacterInformation(context);
        db = helper.getReadableDatabase();
        cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
    }

    @Override
    public int getCount() {
        return makePlayerNum;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = inflater.inflate(R.layout.listview_character_organization, null);
        }
        if(status.size() -1 < i){
            return view;
        }

        TextView text = view.findViewById(R.id.character_organization_listView_status_name);
        text.setText(status.get(i).getName());

        text = view.findViewById(R.id.character_organization_listView_status_job);
        text.setText(status.get(i).getJob());

        text = view.findViewById(R.id.character_organization_listView_status);
        text.setText(status.get(i).getStatus());




        final RadioButton radioButton = view.findViewById(R.id.character_organization_listView_radioButton);
        radioButton.setVisibility(View.VISIBLE);

        final View convertView = view;
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = radioButton.isChecked();
                System.out.println("isChecked" + isChecked);


                TextView textView = convertView.findViewById(R.id.character_organization_listView_status_name);
                String name = textView.getText().toString();

                if(!hasName(name)){
                    if(count < 3){
                        count++;
                        textView = convertView.findViewById(R.id.character_organization_listView_status_job);
                        String job = textView.getText().toString();
                        myParty.appendPlayer(
                                makePlayer(name, job, myParty));
                    }else{
                        Toast.makeText(context, "最大数に達しました", Toast.LENGTH_SHORT).show();
                        radioButton.setChecked(false);
                    }
                }else{
                    count--;
                    radioButton.setChecked(false);

                    for(int i = 0; i < myParty.getmenbers().size(); i++){
                        Player player = myParty.getmenbers().get(i);
                        if(player.getName().equals(name)){
                            myParty.removePlayer(player);
                        }
                    }
                }

                Button btn = startButton.findViewById(R.id.character_organization_start);

                btn.setText("このパーティーで開始(" + count + "/3)");

            }
        });

        return view;
    }



    public boolean hasName(String name){
        boolean haveName = false;
        for(int i = 0; i < myParty.getmenbers().size() ; i++){
            Player player = myParty.getmenbers().get(i);
            if(player.getName().equals(name)){
                haveName = true;
            }
        }

        return haveName;
    }


}
