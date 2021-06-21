package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.skill.SkillBase;

public class LeastDef extends BaseStrategy {
    /*=============
     * コンストラクタ
     =============*/
    LeastDef() {
    }

    @Override
    public void action(Player attacker, Party defenderParty) {
        Player target = selectDefender(defenderParty);

        if (attacker.canSkill()) {
            SkillBase skill = attacker.getRandomSkill();
            attacker.useSkill(skill, target);
        } else {
            attacker.normalAttack(target);
        }
    }

    private Player selectDefender(Party defenderParty) {
        Player defender = defenderParty.getAliveMenbers().get(0);

        for (Player player : defenderParty.getAliveMenbers()) {
            if (defender.getDef() > player.getDef()) {
                defender = player;
            }
        }
        return defender;
    }

    @Override
    public void initStrategy() {
        super.strategy = AllStrategy.EStrategy.LEAST_DEF;
    }
}
