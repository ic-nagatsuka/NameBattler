package com.name.battler.Skill;


public class SkillOfHeel extends Skill{
	/*=============
	 * フィールド変数
	 =============*/
	int heelPoint;	//回復力
	
	/*=============
	 * コンストラクタ
	 =============*/
	SkillOfHeel(SkillType type, String name, int useMp, int heelPoint){
		super(type, name, useMp);
		this.heelPoint = heelPoint;
	}
	
	/*============
	 *Get 
	 ============*/
	public int getHeelPoint(){
		return this.heelPoint;
	}
	
}
