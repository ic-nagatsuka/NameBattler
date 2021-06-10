package com.namebattler.battle.player;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.skill.AllSkill;
import com.namebattler.battle.skill.Fire;
import com.namebattler.battle.skill.Thunder;

import java.util.Random;


public class Wizard extends Player {
    /*=============
     * コンストラクタ
     =============*/
    public Wizard(String name) {
        super(name);
    }

    @Override
    protected void makeSkill() {
		super.useSkill.add(new Fire());
		super.useSkill.add(new Thunder());
    }

    @Override
    public void normalAttack(Player target) {
        Random rand = new Random();
        BattleLog.addLog(this.name + "は魔法を唱えた");
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
