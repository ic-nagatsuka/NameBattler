package com.name.battler.Player;

import java.util.ArrayList;
import java.util.List;

import com.name.battler.Skill.AllSkill;


public class P_Fighter extends Player{
	
	
	List<String> attText = new ArrayList<>();
	
	/*=============
	 * コンストラクタ
	 =============*/
	public P_Fighter(String name) {
		super(name);
		SetJob(AllJob.Fighter);
		MakeAttackText();
	}
	
	@Override
	protected void MakeCharacter(){
		this.hp = GetNumber(0, 200) + 100;
		this.mp = GetNumber(1, 0) + 0;
		this.str = GetNumber(2, 70) + 30;
		this.def = GetNumber(3, 70) + 30;
		this.luck = GetNumber(4, 99) + 1;
		this.agi = GetNumber(5, 49) + 1;
	}
	
	@Override
	protected void MakeSkill(){
	//	this.useSkill.add();
	}
	
	@Override
	protected void UpLevelEffect(){
		
		switch(this.GetLevel()){
		case 2 :
			this.useSkill.add(AllSkill.fire);
			this.maxMp = 30;
			break;
		case 3 : 
			break;
		case 4 : 
			break;
		}
		
		this.SetMP(this.maxMp);
	}
	
	protected void MakeAttackText(){
		attText.add("パンチをした！");
		attText.add("キックをした！");
		attText.add("タックルをした！");
		attText.add("そこら辺の石を投げつけた!");
	}
	
	@Override
	public void NormalAttack(Player target){
		System.out.println(this.name +"は" + attText.get( rand.nextInt(attText.size())));
		
		super.NormalAttack(target);
	}
	
	
}
