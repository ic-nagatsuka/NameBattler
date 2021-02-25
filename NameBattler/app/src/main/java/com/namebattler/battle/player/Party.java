package com.namebattler.battle.player;

import com.namebattler.battle.Strategy.Strategy;

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
	private Strategy strategy;

	public Party(String name){
		this.name = name;
	}
	
	/*============
	 * getメソッド
	 ============*/
	public String getName(){
		return name;
	}

	public Strategy getStrategy(){
		return strategy;
	}
	
	public List<Player> getmenbers(){
		return menbers;
	}
	/*============
	 * setメソッド
	 ============*/
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}



	/*
	 * メソッド
	 */
	public void appendPlayer(Player player)
	{
		this.menbers.add(player);
	}
	
	public void removePlayer(Player player)
	{
		this.menbers.remove(player);
	}

	
}
