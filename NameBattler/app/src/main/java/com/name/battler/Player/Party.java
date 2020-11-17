package com.name.battler.Player;

import java.util.ArrayList;
import java.util.List;

public class Party {
	/*=============
	 * フィールド変数
	 =============*/
	private List<Player>menbers = new ArrayList<>();
	
	/*=============
	 * コンストラクタ
	 =============*/
	private String name;
	public Party(String name){
		this.name = name;
	}
	
	/*============
	 * Getメソッド
	 ============*/
	public String getName(){
		return name;
	}
	
	public List<Player> getmenbers(){
		return menbers;
	}
	
	
	/*
	 * メソッド
	 */
	public void appendPlayer(Player player)
	{
		System.out.println("パーティー"+ getName() + "に追加されました");
		this.menbers.add(player);
		
	}
	
	public void removePlayer(Player player)
	{
		this.menbers.remove(player);
	}
	
	
}
