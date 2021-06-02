package com.namebattler.battle.player;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.skill.Fire;
import com.namebattler.battle.skill.Heal;

import java.util.Random;

public class Bouncer extends Player {

    int attackPercent = 50;

    /*=============
     * コンストラクタ
     =============*/
    public Bouncer(String name) {
        super(name);
        this.setCanCounter(true);
    }

    @Override
    protected void makeSkill() {
        super.useSkill.add(new Fire());
        super.useSkill.add(new Heal());
    }

    @Override
    public void normalAttack(Player target) {
        Random rand = new Random();
        if (rand.nextInt(100) < this.attackPercent) {
            BattleLog.addLog(this.getName() + "は飛び跳ねた！");
            super.normalAttack(target);
        } else {
            BattleLog.addLog(getName() + "は動かない！");
        }
    }

    @Override
    protected void counterAttack(Player defender) {
        BattleLog.addLog(this.getName() + "が跳ね返ってきた！");
        //会心でなければ
        if (!isLuckyHit()) {
            this.normalDamage(defender);
        }
        this.beforeHp = this.getHp();
    }

    @Override
    protected void initJobData() {
        super.jobData = AllJob.JobData.BOUNCER;
    }
}
