package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_start);

//        List<Map<String, String>> list1 = makeList();
//        List<Map<String, String>> list2 = makeList();
//        List<Map<String, String>> list = new ArrayList<>();
//        for(int i = 0; i < 3; i++){
//            Map<String, String> map = new HashMap();
//            map.put("name", "");
//            map.put("job", "");
//            map.put("status", "");
//            list.add(map);
//        }
//
//        SimpleAdapter adapter1 = new SimpleAdapter(
//                this,
//                list,
//                R.layout.activity_character_list_listview,
//                new String[]{},
//                new int []{}
//        );



//        ListView listview = findViewById(R.id.battle_start_listView_top);
//        listview.setAdapter(adapter1);

        makeList(R.id.battle_start_listView_top);
        makeList(R.id.battle_start_listView_bottom);



    }

    public List<Map<String, String>> makeList(int listviewId){
        List<Map<String, String>> list = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Map<String, String> map = new HashMap();
            map.put("name", "");
            map.put("job", "");
            map.put("status", "");
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.activity_character_list_listview,
                new String[]{"name", "job", "status"},
                new int []{
                        R.id.character_list_name,
                        R.id.character_list_job,
                        R.id.character_list_status
                }
        );

        ListView listview = findViewById(listviewId);
        return list;
    }
}