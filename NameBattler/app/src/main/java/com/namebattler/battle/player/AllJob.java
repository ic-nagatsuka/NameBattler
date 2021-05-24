package com.namebattler.battle.player;


public enum AllJob {
    FIGHTER("戦士", 100, 300, 0, 0, 30, 70, 30, 70, 1, 100, 1, 100, false),
    WIZARD("魔法使い", 50, 150, 30, 50, 1, 50, 1, 50, 1, 100, 1, 100, false),
    PRIEST("僧侶", 80, 200, 20, 50, 10, 70, 10, 70, 1, 100, 1, 100, false),
    BOUNCER("ボール", 50, 150, 50, 110, 1, 30, 40, 90, 50, 100, 1, 100, true),
    ;

    private String jobName;
    private int minHp, maxHp,
            minMp, maxMp,
            minStr, maxStr,
            minDef, maxDef,
            minLuck, maxLuck,
            minAgi, maxAgi;
    private boolean canCounter;

    AllJob(String jobName,
           int minHp, int maxHp,
           int minMp, int maxMp,
           int minStr, int maxStr,
           int minDef, int maxDef,
           int minLuck, int maxLuck,
           int minAgi, int maxAgi,
           boolean canCounter) {
        this.jobName = jobName;
        this.minHp = minHp;
        this.maxHp = maxHp;
        this.minMp = minMp;
        this.maxMp = maxMp;
        this.minStr = minStr;
        this.maxStr = maxStr;
        this.minDef = minDef;
        this.maxDef = maxDef;
        this.minLuck = minLuck;
        this.maxLuck = maxLuck;
        this.minAgi = minAgi;
        this.maxAgi = maxAgi;
        this.canCounter = canCounter;
    }

    public String getJobName() {
        return this.jobName;
    }

    public int getMinHp() {
        return this.minHp;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getMinMp() {
        return this.minMp;
    }

    public int getMaxMp() {
        return this.maxMp;
    }

    public int getMinStr() {
        return this.minStr;
    }

    public int getMaxStr() {
        return this.maxStr;
    }

    public int getMinDef() {
        return this.minDef;
    }

    public int getMaxDef() {
        return this.maxDef;
    }

    public int getMinLuck() {
        return this.minLuck;
    }

    public int getMaxLuck() {
        return this.maxLuck;
    }

    public int getMinAgi() {
        return this.minAgi;
    }

    public int getMaxAgi() {
        return this.maxAgi;
    }

    public boolean getCanCounter() {
        return this.canCounter;
    }

}

