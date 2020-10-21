package com.name.battler.Skill;

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
	public int GetType(){
		return this.type;
	}
}
