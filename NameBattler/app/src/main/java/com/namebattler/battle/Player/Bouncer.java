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
    public void makeCharacterStatus() {
        this.hp = getNumber(0, jobData.getMaxHp() - jobData.getMinHp()) + jobData.getMinHp();
        this.mp = getNumber(1, jobData.getMaxMp() - jobData.getMinMp()) + jobData.getMinMp();
        this.str = getNumber(2, jobData.getMaxStr() - jobData.getMinStr()) + jobData.getMinStr();
        this.def = getNumber(3, jobData.getMaxDef() - jobData.getMinDef()) + jobData.getMinDef();
        this.luck = getNumber(4, jobData.getMaxLuck() - jobData.getMinLuck()) + jobData.getMinLuck();
        this.agi = getNumber(5, jobData.getMaxAgi() - jobData.getMinAgi()) + jobData.getMinAgi();
    }

    @Override
    protected void makeSkill() {
        useSkill.add(new Fire());
        useSkill.add(new Heal());
    }

    @Override
    public void normalAttack(Player target) {
        Random rand = new Random();
        if (rand.nextInt(100) < attackPercent) {
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
        jobData = AllJob.JobData.BOUNCER;
    }
}
