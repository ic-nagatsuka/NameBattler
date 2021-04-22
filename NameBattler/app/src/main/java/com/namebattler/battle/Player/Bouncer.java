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
        this.setJob(AllJob.BOUNCER.getName());
        this.setCounter(true);
    }

    @Override
    public void makeCharacterStatus() {
        this.hp = getNumber(0, 50) + 100;
        this.mp = getNumber(1, 50) + 60;
        this.str = getNumber(2, 29) + 1;
        this.def = getNumber(3, 40) + 50;
        this.luck = getNumber(4, 50) + 50;
        this.agi = getNumber(5, 40) + 20;
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
}
