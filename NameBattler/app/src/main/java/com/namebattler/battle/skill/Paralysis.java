package com.namebattler.battle.skill;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.player.Player;

import java.util.Random;

public class Paralysis extends SkillBase implements AbnormalState {
    /*=============
     * フィールド変数
     =============*/
    Random rand = new Random();


    /*=============
     * コンストラクタ
     =============*/
    public Paralysis() {
    }

    @Override
    public void use(Player attacker, Player defender) {
        BattleLog.addLog(attacker.getName() + "は" + this.getName() + "を唱えた！");
        usePlayerMp(attacker);

        //成功
        if (this.getSuccessRate() > rand.nextInt(100)) {
            //同じ状態異常にかかっている
            if (defender.checkSameAbnormal(this)) {
                BattleLog.addLog(defender.getName() + "はすでにかかっている!");
            } else {
                BattleLog.addLog(defender.getName() + "はしびれた！");
                //相手に状態異常をつける
                defender.setAbnormalState(new StateEffect(this, skill.getEffectTurn(), "⚡"));
                defender.setInaction(true);
            }
        } else {
            //失敗
            BattleLog.addLog(defender.getName() + "はかからなかった！");
        }

    }

    @Override
    public void effect(Player target, int turn) {

        if (turn < 0) {
            BattleLog.addLog(target.getName() + "のしびれが治った！");
            target.setInaction(false);
        } else {
            BattleLog.addLog(target.getName() + "はしびれていて動けない！\n");
        }
    }

    @Override
    public int calcDamage(Player target) {
        return 0;
    }

    @Override
    public void initSkill() {
        this.skill = AllSkill.PARALYSIS;
    }

}