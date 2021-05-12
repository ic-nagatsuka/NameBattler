package com.namebattler.battle.party;

import com.namebattler.battle.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Party {
    /*=============
     * フィールド変数
     =============*/
    private List<Player> menbers = new ArrayList<>();
    private int strategyKey;

    /*=============
     * コンストラクタ
     =============*/
    private String name;

    public Party(String name) {
        this.name = name;
    }

    /*============
     * getメソッド
     ============*/
    public String getName() {
        return this.name;
    }

    public int getStrategyKey() {
        return this.strategyKey;
    }

    public List<Player> getAllMenbers() {
        return this.menbers;
    }

    public List<Player> getAliveMenbers() {
        ArrayList<Player> aliveMenbers = new ArrayList<>();
        for (Player player : this.menbers) {
            if (!player.getIsDeath()) {
                aliveMenbers.add(player);
            }
        }
        return aliveMenbers;
    }

    /*============
     * setメソッド
     ============*/
    public void setStrategyKey(int strategyKey) {
        this.strategyKey = strategyKey;
    }


    /*
     * メソッド
     */
    public void appendPlayer(Player player) {
        this.menbers.add(player);
    }

    public void removePlayer(Player player) {
        this.menbers.remove(player);
    }


}
