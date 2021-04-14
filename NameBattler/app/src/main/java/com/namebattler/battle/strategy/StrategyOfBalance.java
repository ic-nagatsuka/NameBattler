package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

public class StrategyOfBalance extends Strategy {

    /*=============
     * コンストラクタ
     =============*/
    public StrategyOfBalance() {
        this.name = "状況に応じて";
    }

    @Override
    public void action(Player attacker, Party defenceParty) {
        Player target = randomSelectDefender(defenceParty);


        attacker.action(target);
    }

}
