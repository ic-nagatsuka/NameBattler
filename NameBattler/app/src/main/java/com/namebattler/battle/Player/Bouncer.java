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
        this.hp = getNumber(0, super.jobData.getMaxHp() - super.jobData.getMinHp()) + super.jobData.getMinHp();
        this.mp = getNumber(1, super.jobData.getMaxMp() - super.jobData.getMinMp()) + super.jobData.getMinMp();
        this.str = getNumber(2, super.jobData.getMaxStr() - super.jobData.getMinStr()) + super.jobData.getMinStr();
        this.def = getNumber(3, super.jobData.getMaxDef() - super.jobData.getMinDef()) + super.jobData.getMinDef();
        this.luck = getNumber(4, super.jobData.getMaxLuck() - super.jobData.getMinLuck()) + super.jobData.getMinLuck();
        this.agi = getNumber(5, super.jobData.getMaxAgi() - super.jobData.getMinAgi()) + super.jobData.getMinAgi();
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
