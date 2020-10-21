package com.name.battler.Item;

import com.name.battler.Player.Player;

public abstract class Item {
	/*===========
	 * フィールド変数
	 ===========*/
	String name;
	
	/*==========
	 * コンストラクタ
	 ==========*/
	Item(String name){
		this.name = name;
	}
	
	/*==========
	 * Get
	 ==========*/
	public String GetName(){
		return this.name;
	}

	/*==========
	 * public 
	 ==========*/
	public abstract void Use(Player attacker, Player target);
	
}
