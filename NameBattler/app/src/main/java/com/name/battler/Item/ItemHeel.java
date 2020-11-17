package com.name.battler.Item;

import com.name.battler.Player.Player;

public class ItemHeel extends Item{
	/*===========
	 * フィールド変数
	 ===========*/
	int heelPoint;
	
	/*==========
	 * コンストラクタ
	 ==========*/
	ItemHeel(String name, int heelPoint){
		super(name);
		this.heelPoint = heelPoint;
	}
	
	/*==========
	 * Get
	 ==========*/

	/*==========
	 * public 
	 ==========*/
	@Override
	public void Use(Player attacker, Player target){
		System.out.println(attacker.getName() + "は" + this.name + "を使った！");
		System.out.println(target.getName() + "のHPが" + this.heelPoint + "回復した！");
		target.setHP(Math.min(target.getMaxHp(), target.getHP() + this.heelPoint));
	}
}
