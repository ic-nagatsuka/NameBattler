package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.skill.SkillBase;

public class StrategyOfLeastHP extends Strategy {
	/*=============
	 * フィールド変数
	 =============*/

    /*=============
     * コンストラクタ
     =============*/
    public StrategyOfLeastHP() {
        this.name = "体力少ない";
    }

    @Override
    public void action(Player attacker, Party defenderParty) {
        Player defender = selectDefender(defenderParty);

        if (attacker.checkUseSkill()) {
            SkillBase skill = attacker.randomSelectSkill(attacker.getUseSkillOnly());

            skill.use(attacker, defender);

        } else {
            attacker.normalAttack(defender);
        }

//		//通常の作戦に戻る
//		attacker.action(defender);
    }

    /**
     * 探したステータスが一番低いプレイヤーを返す
     * 継承したクラスは探すステータス項目を設定する
     *
     * @param defenderParty
     * @return　探したステータス項目が一番低いプレイヤー
     */
    protected Player selectDefender(Party defenderParty) {
        Player defender = defenderParty.getmenbers().get(0);
        //目的のステータスが一番低いプレイヤーを選ぶ
        for (Player player : defenderParty.getmenbers()) {

            if (defender.getHP() > player.getHP() && !player.getIsDeath()
                    || defender.getIsDeath()) {
                defender = player;
            }
        }
        return defender;
    }

}
