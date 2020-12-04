package com.example.namebattler;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

public class BaseAdapter_BattleMain extends BaseAdapter {

    LayoutInflater inflater;
    Party party;

    public BaseAdapter_BattleMain(Context context, Party party){
        this.inflater = LayoutInflater.from(context);
        this.party = party;
    }


    @Override
    public int getCount() {
        return party.getmenbers().size();
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
            view = this.inflater.inflate(R.layout.gridview_battle_main, null);
        }

        Player player = party.getmenbers().get(i);

        TextView textView;
        textView = view.findViewById(R.id.gridview_name);
        textView.setText(player.getName());
        if(player.getHP() == 0){
            textView.setTextColor(Color.RED);
        }

        textView = view.findViewById(R.id.gridview_hp);
        textView.setText("HP" + player.getHP() + "/" + player.getMaxHp());

        textView = view.findViewById(R.id.gridview_mp);
        textView.setText("MP" + player.getMP() + "/" + player.getMaxMp());

//        textView = view.findViewById(R.id.gridview_status_abnormal);
//        textView.setText(player.getName());








        return view;
    }
}
