package com.name.battler.Skill;

import com.name.battler.Player.Player;

public class SkillOfStateUp extends SkillOfEffectTurn{

	/*================
	 * フィールド変数
	 ================*/
	int upDamage;
	//===========
	//コンストラクタ
	//===========
	/**
	 * 
	 * @param type
	 * @param name
	 * @param useMp
	 * @param effectTurn
	 * @param upDamage
	 */
	SkillOfStateUp(SkillType type, String name, int useMp, int effectTurn, int upDamage){
		super(type, name, useMp, effectTurn);
		this.upDamage = upDamage;
	}

	/*============
	 * Getメソッド
	 ============*/
	public int getUpDamage(){
		return this.upDamage;
	}
	
	@Override
	public void use(Player attacker, Player target){
		System.out.println(attacker.getName() + "はきあいをためた！");
		usePlayerMp(attacker);
		
		target.setStr(target.getSTR() * upDamage);
		target.turnAbnormalState.add(upDamage, new StateEffect(AllSkill.power, this.effectTurn));
	}
	
	
	/**
	 * 状態異常効果
	 * @param target 行動プレイヤー
	 */
	public void effect(Player target, int turn){
		
		if(turn == 0){
			target.setStr(target.getSTR() / upDamage);
		}
		
	}
	
}
