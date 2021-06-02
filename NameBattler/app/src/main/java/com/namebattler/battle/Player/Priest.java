package com.namebattler.battle.player;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.skill.Paralysis;
import com.namebattler.battle.skill.Poison;


public class Priest extends Player {

    /*=============
     * コンストラクタ
     =============*/
    public Priest(String name) {
        super(name);
    }

    @Override
    protected void makeSkill() {
        useSkill.add(new Paralysis());
        useSkill.add(new Poison());
    }

    @Override
    public void normalAttack(Player target) {
        BattleLog.addLog(this.name + "は回復を唱えようとした");
        super.normalAttack(target);
    }

    @Override
    protected void initJobData() {
        super.jobData = AllJob.JobData.PRIEST;
    }
}
