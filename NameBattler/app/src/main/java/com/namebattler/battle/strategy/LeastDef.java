package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.skill.SkillBase;

public class LeastDef extends Strategy {
	/*=============
	 * フィールド変数
	 =============*/

    /*=============
     * コンストラクタ
     =============*/
    LeastDef() {
        this.name = "柔らか狙い";
    }

    @Override
    public void action(Player attacker, Party defenderParty) {
        Player target = selectDefender(defenderParty);

        if(attacker.canSkill()){
            SkillBase skill = attacker.getRandomSkill();
            attacker.useSkill(skill, target);
        }else{
            attacker.normalAttack(target);
        }
    }

    protected Player selectDefender(Party defenderParty) {
        Player defender = defenderParty.getmenbers().get(0);

        for (Player player : defenderParty.getAliveMenbers()) {
            if (defender.getDef() > player.getDef()) {
                defender = player;
            }
        }
        return defender;
    }

    @Override
    public void initStrategy() {
        this.strategy = AllStrategy.LEAST_DEF;
    }
}
