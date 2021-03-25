package com.namebattler.battle.skill;


public class StateEffect {
    /*=============
     * フィールド変数
     =============*/
    AbnormalState skill;
    int turn;
    String stateChar;

    /*=============
     * コンストラクタ
     =============*/
    StateEffect(AbnormalState skill, int turn, String stateChar) {
        this.skill = skill;
        this.turn = turn;
        this.stateChar = stateChar;
    }

    /*============
     * Get
     ============*/
    public AbnormalState getSkill() {
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


