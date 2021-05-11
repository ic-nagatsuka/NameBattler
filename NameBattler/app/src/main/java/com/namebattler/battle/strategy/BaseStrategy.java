package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

import java.util.Random;

public abstract class BaseStrategy implements IStrategy {

    /*=============
     * フィールド変数
     =============*/
    protected AllStrategy.EStrategy strategy;

    Random rand = new Random();

    /*=============
     * コンストラクタ
     =============*/
    BaseStrategy() {
        initStrategy();
    }

    /*============
     * Getメソッド
     ============*/
    public String getName() {
        return this.strategy.getName();
    }

    public AllStrategy.EStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * 作戦の動き
     *
     * @param attacker     攻撃するプレイヤー
     * @param defnderParty 攻撃されるパーティー
     */
    public abstract void action(Player attacker, Party defnderParty);

    protected abstract void initStrategy();

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
