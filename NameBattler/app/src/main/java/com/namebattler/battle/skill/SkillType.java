package com.namebattler.battle.skill;

public class SkillType{
	/*=============
	 * フィールド変数
	 =============*/
	int type;
	
	/*=============
	 * コンストラクタ
	 =============*/
	SkillType(int type){
		this.type=type;
	}
	
	/*============
	 * Getメソッド
	 ============*/
	public int getType(){
		return this.type;
	}
}
