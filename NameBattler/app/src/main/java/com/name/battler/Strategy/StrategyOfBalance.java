package com.name.battler.Strategy;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

public class StrategyOfBalance extends Strategy{

	/*=============
	 * コンストラクタ
	 =============*/
	public StrategyOfBalance(){
		this.name = "状況に応じて";
	}
	
	@Override
	public void Action(Player attacker, Party defenceParty){
		Player target = RandomDefender(defenceParty.Getmenbers());
		
		
		
		attacker.Action(target);
	}
	
}
