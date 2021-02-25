package com.namebattler.battle.Strategy;

import java.util.Random;

import com.namebattler.battle.Player.Party;
import com.namebattler.battle.Player.Player;

public class StrategyOfNoSkill extends Strategy{
	/*=============
	 * フィールド変数
	 =============*/
	Random rand = new Random();
	
	/*=============
	 * コンストラクタ
	 =============*/
	public StrategyOfNoSkill(){
		this.name = "スキル使わない";
	}
	
	
	@Override
	public void action(Player attacker, Party defenderParty){
		Player defender;
		//ランダムで選ぶ
		defender = this.randomDefender(defenderParty.getmenbers());
		
		attacker.normalAttack(defender);
	}
	
	
}
