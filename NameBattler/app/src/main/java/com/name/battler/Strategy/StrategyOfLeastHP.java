package com.name.battler.Strategy;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

public class StrategyOfLeastHP extends Strategy{
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
	public void Action(Player attacker, Party defenderParty){
		Player defender ;
		defender = SelectDefender(defenderParty);
		//通常の作戦に戻る
		attacker.Action(defender);
	}

	/**
	 * 探したステータスが一番低いプレイヤーを返す
	 * 継承したクラスは探すステータス項目を設定する
	 * @param defenderParty
	 * @return　探したステータス項目が一番低いプレイヤー
	 */
	protected Player SelectDefender(Party defenderParty) {
		Player defender = defenderParty.Getmenbers().get(0);
		//目的のステータスが一番低いプレイヤーを選ぶ
		for(Player player : defenderParty.Getmenbers()){
			
			if(defender.GetHP() > player.GetHP()){
				defender = player;
			}
		}
		return defender;
	}
	
}
