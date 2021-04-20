package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

public class Balance extends Strategy {

    /*=============
     * コンストラクタ
     =============*/
    public Balance() {
        this.name = "状況に応じて";
    }

    @Override
    public void action(Player attacker, Party defenceParty) {
        Player target = randomDefender(defenceParty.getmenbers());


        attacker.action(target);
    }

}
