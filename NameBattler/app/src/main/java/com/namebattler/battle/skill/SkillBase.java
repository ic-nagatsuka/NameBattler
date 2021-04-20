package com.namebattler.battle.skill;

import com.namebattler.battle.player.Player;

import java.util.Random;

public abstract class SkillBase {

    AllSkill skill;

    SkillBase() {
        initSkill();
    }

    public abstract void use(Player attacker, Player defender);

    public abstract int calcDamage(Player target);

    protected abstract void initSkill();

    public String getName() {
        return this.skill.getName();
    }

    public int getUseMp() {
        return this.skill.getUseMp();
    }

    public int getminDama() {
        return this.skill.getminDama();
    }

    public int getmaxDama() {
        return this.skill.getminDama();
    }

    public int getHealPoint() {
        return this.skill.getHealPoint();
    }

    public int getTurnDama() {
        return this.skill.getmaxDama();
    }

    public int getSuccessRate() {
        return this.skill.getSuccessRate();
    }

    public boolean getInaction() {
        return this.skill.getInaction();
    }


    protected int getRandomDamage(int minDama, int maxDama) {
        Random rand = new Random();

        int damage = rand.nextInt(maxDama - minDama + 1) + minDama;
        return damage;
    }

    protected void usePlayerMp(Player attacker) {
        attacker.setMp(attacker.getMp() - skill.getUseMp());
    }

}
