package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.skill.SkillBase;


public class StrategyOfDamagePriority extends Strategy {

    boolean normalAttack;

    Player target;
    SkillBase selectSkill;

    /*=============
     * コンストラクタ
     =============*/
    public StrategyOfDamagePriority() {
        this.name = "ダメージ与えて";
    }

    @Override
    public void action(Player attacker, Party defenceParty) {
        normalAttack = true;

        selectAction(attacker, defenceParty);


        if (normalAttack) {
            attacker.normalAttack(target);
        } else {
            attacker.useSkill(selectSkill, target);
        }

    }

    private void selectAction(Player attacker, Party defenceParty) {
        if (attacker.getUseSkillOnly().size() != 0) {
            selectSkill = attacker.getUseSkillOnly().get(0);
        }
        target = defenceParty.getAliveMenbers().get(0);
        int calcDamage = attacker.calcDamage(target);

        for (Player player : defenceParty.getAliveMenbers()) {

            if (calcDamage < attacker.calcDamage(player)) {
                target = player;
                calcDamage = player.calcDamage(player);
                normalAttack = true;
            }
            for (SkillBase skill : attacker.getUseSkillOnly()) {

                if (calcDamage < skill.calcDamage(player)) {
                    target = player;
                    selectSkill = skill;
                    normalAttack = false;
                }
            }
        }
    }

}
