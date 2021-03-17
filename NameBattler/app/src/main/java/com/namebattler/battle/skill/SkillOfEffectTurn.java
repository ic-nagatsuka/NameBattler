package com.namebattler.battle.skill;

import com.namebattler.battle.player.Player;

public class SkillOfEffectTurn extends Skill {

    public static class StateEffect {
        /*=============
         * フィールド変数
         =============*/
        SkillOfEffectTurn skill;
        int turn;
        String stateChar;

        /*=============
         * コンストラクタ
         =============*/
        StateEffect(SkillOfEffectTurn skill, int turn, String stateChar) {
            this.skill = skill;
            this.turn = turn;
            this.stateChar = stateChar;
        }

        /*============
         * Get
         ============*/
        public SkillOfEffectTurn getSkill() {
            return this.skill;
        }

        public int getTurn() {
            return this.turn;
        }

        public String getStateChar() {
            return this.stateChar;
        }

        /*============
         * Set
         ============*/
        public void setTurn(int turn) {
            this.turn = turn;
        }

    }

    /*================
     * フィールド変数
     ================*/
    int effectTurn;

    //===========
    //コンストラクタ
    //===========
    SkillOfEffectTurn(SkillType type, String name, int useMp, int effectTurn) {
        super(type, name, useMp);
        this.effectTurn = effectTurn;
    }

    /*============
     * Getメソッド
     ============*/
    public int getEffectTurn() {
        return this.effectTurn;
    }

    /**
     * 状態異常効果
     *
     * @param target 行動プレイヤー
     */
    public void effect(Player target, int turn) {
        //オーバーライド
    }

}

