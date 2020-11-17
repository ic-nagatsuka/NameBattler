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
		System.out.println(attacker.getName() + "は" + this.name + "を使った！");
		System.out.println(target.getName() + "のMPが" + this.heelPoint + "回復した！");
		target.setHP(Math.min(target.getMaxHp(), target.getMP() + this.heelPoint));
	}
}
