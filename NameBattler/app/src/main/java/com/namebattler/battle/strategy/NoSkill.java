package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

public class NoSkill extends BaseStrategy {

    /*=============
     * コンストラクタ
     =============*/
    public NoSkill() {
    }

    @Override
    public void action(Player attacker, Party defenderParty) {
        Player defender = this.randomSelectDefender(defenderParty);
        attacker.normalAttack(defender);
    }

    @Override
    public void initStrategy() {
        this.strategy = AllStrategy.EStrategy.NO_SKILL;
    }
}
