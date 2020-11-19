package com.example.namebattler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.name.battler.Player.AllJob;
import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.example.namebattler.CharacterMake.makePlayer;
import static com.example.namebattler.CharacterOrganization.party;

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

        Random rand = new Random();

        Party enemyParty= new Party("敵");

        EnemyNameData nameData = new EnemyNameData();
        for(int i = 0; i < 3; i++){
            enemyParty.appendPlayer(makePlayer(nameData.getEnemyName(), AllJob.allJob[rand.nextInt(AllJob.allJob.length)]));
        }


        makeList(R.id.battle_start_listView_top, party);
        makeList(R.id.battle_start_listView_bottom, enemyParty );



    }

//    public Player amakePlayer(){
//
////        switch(){
////            case
////        }
//
//
//    }

    public List<Map<String, String>> makeList(int listviewId, Party party){
        List<Map<String, String>> list = new ArrayList<>();
        for(Player player: party.getmenbers()){
            Map<String, String> map = new HashMap();
            map.put("name", player.getName());
            map.put("job", player.getJob());
            map.put("status", player.getstatus());
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.activity_battle_start_listview,
                new String[]{"name", "job", "status"},
                new int []{
                        R.id.character_list_name,
                        R.id.character_list_job,
                        R.id.character_list_status
                }
        );

        ListView listview = findViewById(listviewId);
        listview.setAdapter(adapter);
        return list;
    }
}

class EnemyNameData{

    static Random rand = new Random();
    static ArrayList<String> enemyNames = new ArrayList<>();

    EnemyNameData(){
        resetName();
    }

    public String getEnemyName(){

        return enemyNames.remove(rand.nextInt(enemyNames.size()));
    }

    public void resetName(){
        enemyNames.clear();

        enemyNames.add("a");
        enemyNames.add("i");
        enemyNames.add("u");
        enemyNames.add("e");
        enemyNames.add("o");
    }

}