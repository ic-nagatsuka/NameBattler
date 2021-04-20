package com.namebattler.battle.strategy;

import java.util.Random;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

public class NoSkill extends Strategy {
    /*=============
     * フィールド変数
     =============*/
    Random rand = new Random();

    /*=============
     * コンストラクタ
     =============*/
    public NoSkill() {
        this.name = "スキル使わない";
    }


    @Override
    public void action(Player attacker, Party defenderParty) {
        Player defender;
        //ランダムで選ぶ
        defender = this.randomDefender(defenderParty.getmenbers());

        attacker.normalAttack(defender);
    }


}
