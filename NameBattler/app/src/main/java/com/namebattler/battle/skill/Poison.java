package com.namebattler.battle.skill;

import com.namebattler.battle.player.Player;

import com.namebattler.battle.battlelog.BattleLog;


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

        if (defender.checkSameAbnormal(this)) {
            BattleLog.addLog(defender.getName() + "はすでにかかっている！");

        } else {
            BattleLog.addLog(defender.getName() + "は毒にかかった！");
            defender.setAbnormalState(new StateEffect(this, this.skill.getEffectTurn(), stateChar));
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
    protected void initSkill() {
        skill = AllSkill.POISON;
    }
}


