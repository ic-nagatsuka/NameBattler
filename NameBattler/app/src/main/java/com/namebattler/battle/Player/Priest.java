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
        this.setJob(AllJob.PRIEST.getName());
    }

    @Override
    public void makeCharacter() {
        this.hp = getNumber(0, 120) + 80;
        this.mp = getNumber(1, 30) + 20;
        this.str = getNumber(2, 60) + 10;
        this.def = getNumber(3, 60) + 10;
        this.luck = getNumber(4, 99) + 1;
        this.agi = getNumber(5, 40) + 20;
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


}
