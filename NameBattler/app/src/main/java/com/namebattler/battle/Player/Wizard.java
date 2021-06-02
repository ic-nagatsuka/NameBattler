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
