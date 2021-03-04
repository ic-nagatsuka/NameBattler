package com.namebattler.battle.skill;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.player.Player;

public class Fire extends SkillBase{

    public void use(Player attacker, Player defender){
        BattleLog.addLog(attacker.getName() + "は" + skill.getName() + "を唱えた！");
        attacker.setMP(attacker.getMP() - skill.getUseMp());

        int damage = getRandomDamage(skill.getminDama(), skill.getmaxDama());
        defender.damage(damage);
        BattleLog.addLog(defender.getName() + "に" + damage + "のダメージ！");
    }


    @Override
    protected void initSkill() {
        this.skill = AllSkill.Skills.FIRE;
    }
}
