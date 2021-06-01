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
