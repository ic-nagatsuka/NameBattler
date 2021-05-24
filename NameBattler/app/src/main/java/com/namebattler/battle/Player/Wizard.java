package com.namebattler.battle.player;

import com.namebattler.battle.battlelog.BattleLog;

import java.util.Random;


public class Wizard extends Player {
    /*=============
     * コンストラクタ
     =============*/
    public Wizard(String name) {
        super(name);
    }

    @Override
    public void makeCharacterStatus() {
        this.hp = getNumber(0, jobData.getMaxHp() - jobData.getMinHp()) + jobData.getMinHp();
        this.mp = getNumber(1, jobData.getMaxMp() - jobData.getMinMp()) + jobData.getMinMp();
        this.str = getNumber(2, jobData.getMaxStr() - jobData.getMinStr()) + jobData.getMinStr();
        this.def = getNumber(3, jobData.getMaxDef() - jobData.getMinDef()) + jobData.getMinDef();
        this.luck = getNumber(4, jobData.getMaxLuck() - jobData.getMinLuck()) + jobData.getMinLuck();
        this.agi = getNumber(5, jobData.getMaxAgi() - jobData.getMinAgi()) + jobData.getMinAgi();
    }

    //this.useSkill.add(AllSkill.);
    @Override
    protected void makeSkill() {
//		this.useSkill.add(AllSkill.fire);
//		this.useSkill.add(AllSkill.thunder);
//		this.useSkill.add(AllSkill.);
//		this.useSkill.add(AllSkill.);
    }

    @Override
    public void normalAttack(Player target) {
        Random rand = new Random();
        BattleLog.addLog(this.name + "魔法を唱えた");
        if (rand.nextInt(2) == 0) {
            BattleLog.addLog("が何も起こらなかった");
        } else {
            super.normalAttack(target);
        }
    }

    @Override
    protected void initJobData() {
        jobData = AllJob.WIZARD;
    }
}
