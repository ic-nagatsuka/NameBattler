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
        this.hp = getNumber(0, super.jobData.getMaxHp() - super.jobData.getMinHp()) + super.jobData.getMinHp();
        this.mp = getNumber(1, super.jobData.getMaxMp() - super.jobData.getMinMp()) + super.jobData.getMinMp();
        this.str = getNumber(2, super.jobData.getMaxStr() - super.jobData.getMinStr()) + super.jobData.getMinStr();
        this.def = getNumber(3, super.jobData.getMaxDef() - super.jobData.getMinDef()) + super.jobData.getMinDef();
        this.luck = getNumber(4, super.jobData.getMaxLuck() - super.jobData.getMinLuck()) + super.jobData.getMinLuck();
        this.agi = getNumber(5, super.jobData.getMaxAgi() - super.jobData.getMinAgi()) + super.jobData.getMinAgi();
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
        super.jobData = AllJob.JobData.WIZARD;
    }
}
