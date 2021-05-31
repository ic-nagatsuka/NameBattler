package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.skill.SkillBase;


public class DamagePriority extends BaseStrategy {

    Player target;
    SkillBase selectSkill;
    int highCalcDamage = 0;

    /*=============
     * コンストラクタ
     =============*/
    public DamagePriority() {
    }

    @Override
    public void action(Player attacker, Party defenceParty) {
        if (isNormalAttack(attacker, defenceParty)) {
            attacker.normalAttack(this.target);
        } else {
            attacker.useSkill(this.selectSkill, this.target);
        }
    }

    private boolean isNormalAttack(Player attacker, Party defenceParty) {
        boolean normalAttack = true;
        this.target = randomSelectDefender(defenceParty);

        calcNormalAttack(attacker, defenceParty);

        calcSkillAttack(attacker, defenceParty);

        return normalAttack;
    }

    private void calcNormalAttack(Player attacker, Party defenseParty) {
        for (Player player : defenseParty.getAliveMenbers()) {
            int damage = attacker.calcDamage(player);
            if (this.highCalcDamage < damage) {
                this.highCalcDamage = damage;
                this.target = player;
            }
        }
    }

    private void calcSkillAttack(Player attacker, Party defenseParty) {
        for (Player player : defenseParty.getAliveMenbers()) {
            for (SkillBase skill : attacker.getNowUseSkillOnly()) {

                int damage = skill.calcDamage(player);
                if (this.highCalcDamage < damage) {
                    this.highCalcDamage = damage;
                    this.target = player;
                    this.selectSkill = skill;
                }
            }
        }
    }


    @Override
    public void initStrategy() {
        this.strategy = AllStrategy.EStrategy.DAMAGE_PRIORITY;
    }
}
