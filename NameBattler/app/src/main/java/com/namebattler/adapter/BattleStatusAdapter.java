package com.namebattler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.namebattler.R;
import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

public class BattleStatusAdapter extends BaseAdapter {

    LayoutInflater inflater;
    Party party;

    public BattleStatusAdapter(Context context, Party party) {
        this.inflater = LayoutInflater.from(context);
        this.party = party;
    }


    @Override
    public int getCount() {
        return party.getAllMenbers().size();
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

        if (view == null) {
            view = this.inflater.inflate(R.layout.gridview_battle_main, null);
        }

        Player player = party.getAllMenbers().get(i);
        //名前
        TextView textView = view.findViewById(R.id.gridview_name);
        textView.setText(player.getName());
        //体力が０のキャラクターは赤色文字
        if (player.getHp() == 0) {
            textView.setTextColor(Color.RED);
        }
        //HP
        textView = view.findViewById(R.id.gridview_hp);
        textView.setText("HP" + player.getHp() + "/" + player.getMaxHp());
        //MP
        textView = view.findViewById(R.id.gridview_mp);
        textView.setText("MP" + player.getMp() + "/" + player.getMaxMp());
        //状態異常
        textView = view.findViewById(R.id.gridview_status_abnormal);
        textView.setText(player.getAllAbnormalStateChar());

        return view;
    }
}
