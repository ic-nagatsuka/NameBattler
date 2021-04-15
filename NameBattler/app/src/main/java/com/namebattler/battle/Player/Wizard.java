package com.namebattler.battle.player;

import com.namebattler.battle.battlelog.BattleLog;


public class Wizard extends Player {
    /*=============
     * コンストラクタ
     =============*/
    public Wizard(String name) {
        super(name);
        this.setJob(AllJob.WIZARD.getName());

    }

    @Override
    public void makeCharacterStatus() {
        this.hp = getNumber(0, 100) + 50;
        this.mp = getNumber(1, 50) + 30;
        this.str = getNumber(2, 49) + 1;
        this.def = getNumber(3, 49) + 1;
        this.luck = getNumber(4, 99) + 1;
        this.agi = getNumber(5, 40) + 20;
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
        BattleLog.addLog(this.name + "魔法を唱えた");
        if (rand.nextInt(2) == 0) {
            BattleLog.addLog("が何も起こらなかった");
        } else {
            super.normalAttack(target);
        }
    }

}
