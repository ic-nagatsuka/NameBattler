package com.namebattler.battle.Player;

import java.util.ArrayList;
import java.util.List;

import com.namebattler.battle.battlelog.BattleLog;


public class P_Fighter extends Player{
	
	
	List<String> attText = new ArrayList<>();	//攻撃テキスト
	
	/*=============
	 * コンストラクタ
	 =============*/
	public P_Fighter(String name) {
		super(name);
		setJob(AllJob.Job.FIGHTER.getName());
		makeAttackText();
	}
	
	@Override
	public  void makeCharacter(){
		this.hp = getNumber(0, 200) + 100;
		this.mp = getNumber(1, 0) + 0;
		this.str = getNumber(2, 70) + 30;
		this.def = getNumber(3, 70) + 30;
		this.luck = getNumber(4, 99) + 1;
		this.agi = getNumber(5, 49) + 1;
	}
	
	@Override
	protected void makeSkill(){
	//	this.useSkill.add();
	}

	//攻撃テキスト
	protected void makeAttackText(){
		attText.add("パンチをした！");
		attText.add("キックをした！");
		attText.add("タックルをした！");
		attText.add("そこら辺の石を投げつけた!");
	}
	
	@Override
	public void normalAttack(Player target){
		BattleLog.addLog(this.name +"は" + attText.get( rand.nextInt(attText.size())));
		
		super.normalAttack(target);
	}
	
	
}
