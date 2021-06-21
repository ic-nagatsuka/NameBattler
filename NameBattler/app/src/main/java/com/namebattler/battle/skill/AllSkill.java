package com.namebattler.battle.skill;

public enum AllSkill {
    FIRE("ファイア", 10, 10, 30, 0, 0, 0, 0, false),
    THUNDER("サンダー", 20, 10, 30, 0, 0, 0, 0, false),
    HEAL("ヒール", 30, 0, 0, 50, 0, 0, 0, false),
    PARALYSIS("パライズ", 20, 0, 0, 0, 3, 0, 30, true),
    POISON("ポイズン", 10, 0, 0, 0, 3, 30, 100, false),
    ;

    private String name;
    private int useMp;
    private int minDama;
    private int maxDama;
    private int healPoint;
    private int effectTurn;
    private int turnDama;
    private int successRate;
    private boolean inaction;

    AllSkill(String name, int useMp, int minDama, int maxDama, int healPoint, int effectTurn, int turnDama, int successRate, boolean inaction) {
        this.name = name;
        this.useMp = useMp;
        this.minDama = minDama;
        this.maxDama = maxDama;
        this.healPoint = healPoint;
        this.effectTurn = effectTurn;
        this.turnDama = turnDama;
        this.successRate = successRate;
        this.inaction = inaction;
    }

    public String getName() {
        return this.name;
    }

    public int getUseMp() {
        return this.useMp;
    }

    public int getMinDama() {
        return this.minDama;
    }

    public int getMaxDama() {
        return this.maxDama;
    }

    public int getHealPoint() {
        return this.healPoint;
    }

    public int getEffectTurn() {
        return this.effectTurn;
    }

    public int getTurnDama() {
        return this.turnDama;
    }

    public int getSuccessRate() {
        return this.successRate;
    }

    public Boolean getInaction() {
        return this.inaction;
    }

}

