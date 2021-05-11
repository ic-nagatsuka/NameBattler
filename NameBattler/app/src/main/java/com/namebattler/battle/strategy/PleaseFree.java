package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.skill.SkillBase;

public class PleaseFree extends BaseStrategy {

    /*=============
     * コンストラクタ
     =============*/
    public PleaseFree() {
    }

    @Override
    public void action(Player attacker, Party defenceParty) {
        Player target = randomSelectDefender(defenceParty);
        if (attacker.canSkill()) {
            SkillBase skill = attacker.getNowUseSkillOnly().get(
                    rand.nextInt(attacker.getNowUseSkillOnly().size()));
            attacker.useSkill(skill, target);
        } else {
            attacker.normalAttack(target);
        }
    }

    @Override
    public void initStrategy() {
        this.strategy = AllStrategy.EStrategy.PLEASE_FREE;
    }

}