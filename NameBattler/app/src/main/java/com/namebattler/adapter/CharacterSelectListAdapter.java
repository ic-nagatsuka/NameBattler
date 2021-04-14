package com.namebattler.adapter;

import android.content.Context;
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
import com.namebattler.battle.GameManager;
import com.namebattler.battle.player.Player;
import com.namebattler.option.Option;

import java.util.List;


public class CharacterSelectListAdapter extends BaseAdapter {

    Context context;
    View startButton;
    List<CharacterOrganization.Status> status;

    LayoutInflater inflater;

    public CharacterSelectListAdapter(Context context, View v, List<CharacterOrganization.Status> status) {
        this.context = context;
        this.startButton = v;
        this.status = status;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Option.MAX_MAKE_PLAYER_NUM;
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

        if (view == null) {
            view = inflater.inflate(R.layout.listview_character_organization, null);
        }
        if (status.size() - 1 < i) {
            return view;
        }

        final CharacterOrganization.Status player = status.get(i);
        TextView textView = view.findViewById(R.id.character_organization_listView_status_name);
        textView.setText(player.getName());

        textView = view.findViewById(R.id.character_organization_listView_status_job);
        textView.setText(player.getJob());

        textView = view.findViewById(R.id.character_organization_listView_status);
        textView.setText(player.getStatus());


        final RadioButton radioButton = view.findViewById(R.id.character_organization_listView_radioButton);
        radioButton.setVisibility(View.VISIBLE);

        if (player.getIsClick()) {
            radioButton.setChecked(true);
        } else {
            radioButton.setChecked(false);
        }

        final View convertView = view;
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = convertView.findViewById(R.id.character_organization_listView_status_name);
                String name = textView.getText().toString();

                if (!hasName(name)) {
                    if (GameManager.myParty.getmenbers().size() < Option.PARTY_PLAYER_NUM) {
                        player.setClick(true);
                        textView = convertView.findViewById(R.id.character_organization_listView_status_job);
                        String job = textView.getText().toString();
                        GameManager.myParty.appendPlayer(
                                GameManager.makePlayer(name, job, GameManager.myParty));
                    } else {
                        Toast.makeText(context, "最大数に達しました", Toast.LENGTH_SHORT).show();
                        radioButton.setChecked(false);
                    }
                } else {
                    player.setClick(false);
                    radioButton.setChecked(false);

                    removeSelectPlayer(name);
                }
                Button btn = startButton.findViewById(R.id.character_organization_start);
                btn.setText("このパーティーで開始(" + GameManager.myParty.getmenbers().size() + "/3)");
            }

            private void removeSelectPlayer(String name) {
                for (int i = 0; i < GameManager.myParty.getmenbers().size(); i++) {
                    Player player = GameManager.myParty.getmenbers().get(i);
                    if (player.getName().equals(name)) {
                        GameManager.myParty.removePlayer(player);
                    }
                }
            }
        });
        return view;
    }

    public boolean hasName(String name) {
        for (Player player : GameManager.myParty.getmenbers()) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
