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
import com.name.battler.Player.P_Fighter;
import com.name.battler.Player.P_Priest;
import com.name.battler.Player.P_Wizard;
import com.name.battler.Player.Player;

import java.util.List;

import static com.example.namebattler.CharacterMake.makePlayer;
import static com.example.namebattler.CharacterOrganization.party;

public class BaseAdapter_CharacterOrganization extends BaseAdapter {



    Context context;
    View startButton;
    List<Status> status;

    LayoutInflater inflater;
    SQLiteOpenHelper helper ;
    SQLiteDatabase db;
    Cursor cursor ;
    String sql = "SELECT * FROM " + CharacterInformation.TABLE_NAME + ";";
    int count = 0;
    int id ;

    BaseAdapter_CharacterOrganization(Context context, View v, List<Status> status){
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
        return cursor.getCount();
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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.listview_character_organization, null);
        }

        TextView text = convertView.findViewById(R.id.character_organization_listView_status_name);
        text.setText(status.get(i).getName());

        text = convertView.findViewById(R.id.character_organization_listView_status_job);
        text.setText(status.get(i).getJob());

        text = convertView.findViewById(R.id.character_organization_listView_status);
        text.setText(status.get(i).getStatus());




        final CheckBox checkBox = convertView.findViewById(R.id.character_organization_listView_checkBox);
        final View finalConvertView = convertView;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = checkBox.isChecked();
                checkBox.setChecked(false);
                if(count <= 2 && isChecked){
                    checkBox.setChecked(true);
                    count++;

                    TextView textView = finalConvertView.findViewById(R.id.character_organization_listView_status_name);
                    String name = textView.getText().toString();
                    System.out.println(name);

                    textView = finalConvertView.findViewById(R.id.character_organization_listView_status_job);
                    String job = textView.getText().toString();
                    System.out.println(job);
                    party.appendPlayer(makePlayer(name, job));

                    if(party.getmenbers().size() > 3){
                        System.exit(0);
                    }

                }else if(!isChecked){
                    count--;

                    TextView textView = finalConvertView.findViewById(R.id.character_organization_listView_status_name);
                    String name = textView.getText().toString();

                    for(Player player: party.getmenbers()){
                        if(player.getName().equals(name)){
                            party.removePlayer(player);
                            break;
                        }
                    }

                }else{
                    Toast.makeText(context, "最大数に達しました", Toast.LENGTH_SHORT).show();
                }

                Button btn = startButton.findViewById(R.id.character_organization_start);

                btn.setText("このパーティーで開始(" + count + "/3)");

            }
        });

        return convertView;
    }


}
