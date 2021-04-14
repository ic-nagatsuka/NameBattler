package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

public class StrategyOfNoSkill extends Strategy {

    /*=============
     * コンストラクタ
     =============*/
    public StrategyOfNoSkill() {
        this.name = "スキル使わない";
    }

    @Override
    public void action(Player attacker, Party defenderParty) {
        Player defender = this.randomSelectDefender(defenderParty);
        attacker.normalAttack(defender);
    }

}
