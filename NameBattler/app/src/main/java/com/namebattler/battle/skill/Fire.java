package com.namebattler.battle.skill;

import com.namebattler.battle.player.Player;

public class Fire extends SkillBase{

    public void use(Player attacker, Player defender){

    }


    @Override
    protected void initSkill() {
        this.skill = AllSkill.Skills.FIRE;
    }
}
