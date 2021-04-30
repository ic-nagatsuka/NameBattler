package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.skill.SkillBase;


public class DamagePriority extends Strategy {

    Player target;
    SkillBase selectSkill;

    /*=============
     * コンストラクタ
     =============*/
    public DamagePriority() {
        this.name = "ダメージ与えて";
    }

    @Override
    public void action(Player attacker, Party defenceParty) {
        if (isNormalAttack(attacker, defenceParty)) {
            attacker.normalAttack(target);
        } else {
            attacker.useSkill(selectSkill, target);
        }
    }

    private boolean isNormalAttack(Player attacker, Party defenceParty) {
        boolean normalAttack = true;
        target = randomSelectDefender(defenceParty);
        int highCalcDamage = 0;
        for (Player player : defenceParty.getAliveMenbers()) {
            if (highCalcDamage < attacker.calcDamage(player)) {
                target = player;
                highCalcDamage = player.calcDamage(player);
                normalAttack = true;
            }
            for (SkillBase skill : attacker.getNowUseSkillOnly()) {

                if (highCalcDamage < skill.calcDamage(player)) {
                    target = player;
                    selectSkill = skill;
                    normalAttack = false;
                }
            }
        }
        return normalAttack;
    }

    @Override
    public void initStrategy() {
        this.strategy = AllStrategy.DAMAGE_PRIORITY;
    }
}
