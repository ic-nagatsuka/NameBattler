package com.name.battler.Strategy;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

public class StrategyOfLeastStates extends StrategyOfLeastHP{
	/*=============
	 * フィールド変数
	 =============*/
	
	/*=============
	 * コンストラクタ
	 =============*/
	StrategyOfLeastStates() {
		this.name = "防御力少ない";
	}
	
	@Override
	protected Player selectDefender(Party defenderParty) {
		Player defender = defenderParty.getmenbers().get(0);
		
		for(Player player : defenderParty.getmenbers()){
			
			if(defender.getDEF() > player.getDEF()){
				defender = player;
			}
		}
		return defender;
	}
}
