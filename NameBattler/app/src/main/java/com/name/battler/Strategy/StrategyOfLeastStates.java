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
	protected Player SelectDefender(Party defenderParty) {
		Player defender = defenderParty.Getmenbers().get(0);
		
		for(Player player : defenderParty.Getmenbers()){
			
			if(defender.GetDEF() > player.GetDEF()){
				defender = player;
			}
		}
		return defender;
	}
}
