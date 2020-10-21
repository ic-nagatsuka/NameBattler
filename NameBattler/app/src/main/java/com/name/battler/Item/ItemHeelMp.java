package com.name.battler.Item;

import com.name.battler.Player.Player;

public class ItemHeelMp extends ItemHeel{
	/*===========
	 * フィールド変数
	 ===========*/
	
	/*==========
	 * コンストラクタ
	 ==========*/
	ItemHeelMp(String name, int heelPoint){
		super(name, heelPoint);
	}
	
	/*==========
	 * Get
	 ==========*/

	/*==========
	 *  
	 ==========*/
	@Override
	public void Use(Player attacker, Player target){
		System.out.println(attacker.GetName() + "は" + this.name + "を使った！");
		System.out.println(target.GetName() + "のMPが" + this.heelPoint + "回復した！");
		target.SetHP(Math.min(target.GetMaxHp(), target.GetMP() + this.heelPoint));
	}
}
