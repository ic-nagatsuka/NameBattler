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
import com.namebattler.battle.player.AllJob;
import com.namebattler.battle.player.Player;
import com.namebattler.option.Option;

import java.util.List;


public class CharacterSelectListAdapter extends BaseAdapter {

    private Context context;
    private View startButton;
    private List<CharacterOrganization.Status> status;

    private LayoutInflater inflater;

    public CharacterSelectListAdapter(Context context, View v, List<CharacterOrganization.Status> status) {
        this.context = context;
        this.startButton = v;
        this.status = status;
        this.inflater = LayoutInflater.from(context);
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
            view = this.inflater.inflate(R.layout.listview_character_organization, null);
        }

        if (this.status.size() - 1 < i) {
            view = this.inflater.inflate(R.layout.listview_character_organization, null);
            return view;
        }

        final RadioButton radioButton = view.findViewById(R.id.character_organization_listView_radioButton);
        radioButton.setVisibility(View.VISIBLE);

        final CharacterOrganization.Status player = this.status.get(i);
        setPlayerStatusLayout(view, player);

        if (player.getIsClicked()) {
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
                    if (GameManager.myParty.getAllMenbers().size() < Option.PARTY_PLAYER_NUM) {
                        player.setIsClicked(true);
                        GameManager.myParty.appendPlayer(
                                AllJob.makePlayer(name, player.getJob()));
                    } else {
                        Toast.makeText(context, "最大数に達しました", Toast.LENGTH_SHORT).show();
                        radioButton.setChecked(false);
                    }
                } else {
                    player.setIsClicked(false);
                    radioButton.setChecked(false);

                    removeSelectPlayer(name);
                }
                Button btn = startButton.findViewById(R.id.character_organization_start);
                btn.setText("このパーティーで開始(" + GameManager.myParty.getAllMenbers().size() + "/3)");
            }

            private void removeSelectPlayer(String name) {
                for (int i = 0; i < GameManager.myParty.getAllMenbers().size(); i++) {
                    Player player = GameManager.myParty.getAllMenbers().get(i);
                    if (player.getName().equals(name)) {
                        GameManager.myParty.removePlayer(player);
                    }
                }
            }
        });
        return view;
    }

    private void setPlayerStatusLayout(View view, CharacterOrganization.Status player) {
        TextView textView = view.findViewById(R.id.character_organization_listView_status_name);
        textView.setText(player.getName());

        textView = view.findViewById(R.id.character_organization_listView_status_job);
        textView.setText(player.getJob().getJobName());

        textView = view.findViewById(R.id.character_organization_listView_status);
        textView.setText(player.getStatus());
    }

    private boolean hasName(String name) {
        for (Player player : GameManager.myParty.getAllMenbers()) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
