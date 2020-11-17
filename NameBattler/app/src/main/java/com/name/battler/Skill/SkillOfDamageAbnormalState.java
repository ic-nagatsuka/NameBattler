package com.name.battler.Skill;

import com.name.battler.Player.Player;


public class SkillOfDamageAbnormalState extends SkillOfEffectTurn{
	/*=============
	 * フィールド変数
	 =============*/
	int turnDama;
	
	/*=============
	 * コンストラクタ
	 =============*/
	SkillOfDamageAbnormalState(SkillType type, String name, int useMp, int effectTurn, int turnDama){
		super(type, name, useMp, effectTurn);
		this.turnDama = turnDama;
	}

	
	/*============
	 * Get
	 ============*/
	public int getCalcDamage(){
		return this.turnDama;
	}
	
	
	@Override
	public void use(Player attacker, Player defender){
		System.out.println(attacker.getName() + "は" + this.getName() + "を唱えた！");
		usePlayerMp(attacker);
		
		if(defender.checkSameAbnormal(AllSkill.poison)){
			System.out.println(defender.getName() + "はすでにかかっている！");
			
		}else{
			System.out.println(defender.getName() + "は毒にかかった！");
			defender.setAbnormalState(new StateEffect(AllSkill.poison, this.effectTurn));
		}
		
	}
	
	@Override
	public void effect(Player target, int turn){
		if(turn < 0){
			System.out.println(target.getName() + "の毒が治った！");
		}else{
			System.out.println(target.getName() + "は毒にかかっている！");
			System.out.println(target.getName() + "は" + this.turnDama + "のダメージを受けた！");
			target.damage(turnDama);
		}
	}
	
}


