package com.namebattler.battle.skill;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.player.Player;

public class Thunder extends SkillBase {
    /*
     * メソッド
     */
    @Override
    public void use(Player attacker, Player target) {
        BattleLog.addLog(attacker.getName() + "は" + skill.getName() + "を唱えた！");
        usePlayerMp(attacker);

        int damage;
        for (Player defender : target.getParty().getmenbers()) {
            damage = getRandomDamage(skill.getminDama(), skill.getmaxDama());
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
        skill = AllSkill.THUNDER;
    }
}
