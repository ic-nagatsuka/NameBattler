package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

import java.util.Random;

public abstract class Strategy {

    /*=============
     * フィールド変数
     =============*/
    protected String name;


    Random rand = new Random();

    /*=============
     * コンストラクタ
     =============*/
    Strategy() {
    }

    /*============
     * Getメソッド
     ============*/
    public String getName() {
        return this.name;
    }

    /**
     * 作戦の動き
     *
     * @param attacker     攻撃するプレイヤー
     * @param defnderParty 攻撃されるパーティー
     */
    public abstract void action(Player attacker, Party defnderParty);

    /**
     * ランダムにプレイヤーを選ぶ
     *
     * @param defenceParty 攻撃されるパーティー
     * @return 攻撃されるプレイヤー
     */
    protected Player randomSelectDefender(Party defenceParty) {
        return defenceParty.getAliveMenbers()
                .get(rand.nextInt(defenceParty.getAliveMenbers().size()));
    }

}
