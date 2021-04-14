package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

import java.util.Random;

public class StrategyOfNoSkill extends Strategy {
    /*=============
     * フィールド変数
     =============*/
    Random rand = new Random();

    /*=============
     * コンストラクタ
     =============*/
    public StrategyOfNoSkill() {
        this.name = "スキル使わない";
    }


    @Override
    public void action(Player attacker, Party defenderParty) {
        Player defender;
        //ランダムで選ぶ
        defender = this.randomSelectDefender(defenderParty);

        attacker.normalAttack(defender);
    }


}
