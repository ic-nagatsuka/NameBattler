package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.skill.SkillBase;

public class LeastHP extends BaseStrategy {

    /*=============
     * コンストラクタ
     =============*/
    public LeastHP() {
        this.name = "体力少ない";
    }

    @Override
    public void action(Player attacker, Party defenderParty) {
        Player defender = selectDefender(defenderParty);

        if (attacker.canSkill()) {
            SkillBase skill = attacker.randomSelectSkill(attacker.getNowUseSkillOnly());
            skill.use(attacker, defender);
        } else {
            attacker.normalAttack(defender);
        }
    }

    /**
     * HPが一番低いプレイヤーを選ぶ
     *
     * @param defenderParty 敵パーティー
     * @return　HPが一番低いプレイヤー
     */
    protected Player selectDefender(Party defenderParty) {
        Player defender = defenderParty.getAliveMenbers().get(0);
        for (Player player : defenderParty.getAliveMenbers()) {
            if (defender.getHp() > player.getHp()) {
                defender = player;
            }
        }
        return defender;
    }

    @Override
    public void initStrategy() {
        this.strategy = AllStrategy.LEAST_HP;
    }
}
