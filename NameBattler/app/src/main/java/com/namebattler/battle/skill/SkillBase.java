package com.namebattler.battle.skill;

import com.namebattler.battle.player.Player;

import java.util.Random;

public abstract class SkillBase {

    protected AllSkill skill;

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

    public int getMinDama() {
        return this.skill.getMinDama();
    }

    public int getMaxDama() {
        return this.skill.getMaxDama();
    }

    public int getHealPoint() {
        return this.skill.getHealPoint();
    }

    public int getTurnDama() {
        return this.skill.getTurnDama();
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
        attacker.setMp(attacker.getMp() - this.skill.getUseMp());
    }

}
