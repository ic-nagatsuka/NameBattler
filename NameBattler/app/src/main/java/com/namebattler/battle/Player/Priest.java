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
        this.hp = getNumber(0, jobData.getMaxHp() - jobData.getMinHp()) + jobData.getMinHp();
        this.mp = getNumber(1, jobData.getMaxMp() - jobData.getMinMp()) + jobData.getMinMp();
        this.str = getNumber(2, jobData.getMaxStr() - jobData.getMinStr()) + jobData.getMinStr();
        this.def = getNumber(3, jobData.getMaxDef() - jobData.getMinDef()) + jobData.getMinDef();
        this.luck = getNumber(4, jobData.getMaxLuck() - jobData.getMinLuck()) + jobData.getMinLuck();
        this.agi = getNumber(5, jobData.getMaxAgi() - jobData.getMinAgi()) + jobData.getMinAgi();
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
        jobData = AllJob.PRIEST;
    }
}
