package com.name.battler.Strategy;

import java.util.Random;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

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
	public void Action(Player attacker, Party defenderParty){
		Player defender;
		//ランダムで選ぶ
		defender = this.RandomDefender(defenderParty.Getmenbers());
		
		attacker.NormalAttack(defender);
	}
	
	
}
