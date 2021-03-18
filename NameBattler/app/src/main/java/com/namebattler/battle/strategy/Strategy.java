package com.namebattler.battle.strategy;

import java.util.List;
import java.util.Random;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

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
    protected Player randomDefender(List<Player> defenceParty) {
        Player defender;
        while (true) {
            defender = defenceParty.get(rand.nextInt(defenceParty.size()));
            if (defender.getHP() != 0) {
                break;
            }
        }

        return defender;
    }

}
