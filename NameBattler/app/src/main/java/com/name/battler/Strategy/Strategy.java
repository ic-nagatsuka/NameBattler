package com.name.battler.Strategy;

import java.util.List;
import java.util.Random;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;

public abstract class Strategy {

	/*=============
	 * フィールド変数
	 =============*/
	protected String name;
	
	
	Random rand=new Random();
	
	/*=============
	 * コンストラクタ
	 =============*/
	Strategy(){
	}
	
	/*============
	 * Getメソッド
	 ============*/
	public String GetName(){
		return this.name;
	}
	
	/**
	 * 作戦の動き
	 * @param attacker 攻撃するプレイヤー
	 * @param defnderParty 攻撃されるパーティー
	 */
	public abstract void Action(Player attacker, Party defnderParty );
	
	/**
	 *ランダムにプレイヤーを選ぶ
	 * @param defenceParty 攻撃されるパーティー
	 * @return 攻撃されるプレイヤー
	 */
	protected Player RandomDefender(List<Player> defenceParty){
		return defenceParty.get(rand.nextInt(defenceParty.size()));
	}
	
}