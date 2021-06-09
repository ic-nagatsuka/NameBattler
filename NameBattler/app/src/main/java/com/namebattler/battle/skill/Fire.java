package com.namebattler.battle.skill;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.player.Player;

public class Fire extends SkillBase {

    @Override
    public void use(Player attacker, Player defender) {
        BattleLog.addLog(attacker.getName() + "は" + super.skill.getName() + "を唱えた！");
        usePlayerMp(attacker);

        int damage = getRandomDamage(super.skill.getMinDama(), super.skill.getMaxDama());
        defender.damage(damage);
        BattleLog.addLog(defender.getName() + "に" + damage + "のダメージ！");
    }

    @Override
    public int calcDamage(Player target) {
        int damage = this.getRandomDamage(this.getMinDama(), this.getMaxDama());
        return damage;
    }

    @Override
    protected void initSkill() {
        super.skill = AllSkill.FIRE;
    }
}
