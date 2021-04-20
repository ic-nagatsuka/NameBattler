package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

public class LeastStates extends LeastHP {
	/*=============
	 * フィールド変数
	 =============*/

    /*=============
     * コンストラクタ
     =============*/
    LeastStates() {
        this.name = "防御力少ない";
    }

    @Override
    protected Player selectDefender(Party defenderParty) {
        Player defender = defenderParty.getmenbers().get(0);

        for (Player player : defenderParty.getmenbers()) {

            if (defender.getDef() > player.getDef()) {
                defender = player;
            }
        }
        return defender;
    }
}
