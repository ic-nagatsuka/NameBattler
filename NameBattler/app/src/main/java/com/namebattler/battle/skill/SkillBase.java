package com.namebattler.battle.skill;

import com.namebattler.battle.player.Player;

public abstract class SkillBase {

    AllSkill.Skills skill;

    SkillBase(){
        initSkill();
    }

    public abstract void use(Player attacker, Player defender);

    protected abstract void initSkill();

    public String getName(){
        return this.skill.getName();
    }
    public int getUseMp(){
        return this.skill.getUseMp();
    }

    public int getminDama(){
        return this.skill.getminDama();
    }

    public int getmaxDama(){
        return this.skill.getminDama();
    }

    public int getTurnDama(){
        return this.skill.getmaxDama();
    }




}
