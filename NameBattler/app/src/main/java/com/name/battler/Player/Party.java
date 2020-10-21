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
	public String GetName(){
		return name;
	}
	
	public List<Player> Getmenbers(){
		return menbers;
	}
	
	
	/*
	 * メソッド
	 */
	public void AppendPlayer(Player player)
	{
		System.out.println("パーティー"+GetName() + "に追加されました");
		this.menbers.add(player);
		
	}
	
	public void RemovePlayer(Player player)
	{
		this.menbers.remove(player);
	}
	
	
}
