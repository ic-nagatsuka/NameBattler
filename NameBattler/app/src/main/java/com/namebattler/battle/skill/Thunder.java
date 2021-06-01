package com.namebattler.battle.skill;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.player.Player;

public class Thunder extends SkillBase {

    @Override
    public void use(Player attacker, Player target) {
        BattleLog.addLog(attacker.getName() + "は" + super.skill.getName() + "を唱えた！");
        usePlayerMp(attacker);

        int damage;
        for (Player defender : target.getParty().getAliveMenbers()) {
            damage = getRandomDamage(super.skill.getminDama(), super.skill.getmaxDama());
            BattleLog.addLog(defender.getName() + "に" + damage + "のダメージ！");
            defender.damage(damage);
        }
    }

    @Override
    public int calcDamage(Player target) {
        int damage = this.getRandomDamage(this.getminDama(), this.getmaxDama());
        return damage;
    }

    @Override
    protected void initSkill() {
        super.skill = AllSkill.THUNDER;
    }
}
