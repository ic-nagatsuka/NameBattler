package com.namebattler.battle.skill;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.player.Player;


public class Poison extends SkillBase implements AbnormalState {
    /*=============
     * フィールド変数
     =============*/
    String stateChar = "毒";

    /*=============
     * コンストラクタ
     =============*/
    public Poison() {
    }


    @Override
    public void use(Player attacker, Player defender) {
        BattleLog.addLog(attacker.getName() + "は" + this.getName() + "を唱えた！");
        usePlayerMp(attacker);

        if (defender.haveSameAbnormal(this)) {
            BattleLog.addLog(defender.getName() + "はすでにかかっている！");

        } else {
            BattleLog.addLog(defender.getName() + "は毒にかかった！");
            defender.setAbnormalState(new StateEffect(this, super.skill.getEffectTurn(), this.stateChar));
        }

    }

    @Override
    public void effect(Player target, int turn) {
        if (turn < 0) {
            BattleLog.addLog(target.getName() + "の毒が治った！");
        } else {
            BattleLog.addLog(target.getName() + "は毒にかかっている！");

            int damage = this.skill.getTurnDama();
            BattleLog.addLog(target.getName() + "は" + damage + "のダメージを受けた！");
            target.damage(damage);
        }
    }

    @Override
    public int calcDamage(Player target) {
        return 0;
    }

    @Override
    protected void initSkill() {
        super.skill = AllSkill.POISON;
    }
}


