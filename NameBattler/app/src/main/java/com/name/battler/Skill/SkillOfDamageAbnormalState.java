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
	public int GetCalcDamage(){
		return this.turnDama;
	}
	
	
	@Override
	public void Use(Player attacker, Player defender){
		System.out.println(attacker.GetName() + "は" + this.GetName() + "を唱えた！");
		UsePlayerMp(attacker);
		
		if(defender.CheckSameAbnormal(AllSkill.poison)){
			System.out.println(defender.GetName() + "はすでにかかっている！");
			
		}else{
			System.out.println(defender.GetName() + "は毒にかかった！");
			defender.SetAbnormalState(new StateEffect(AllSkill.poison, this.effectTurn));
		}
		
	}
	
	@Override
	public void Effect(Player target, int turn){
		if(turn < 0){
			System.out.println(target.GetName() + "の毒が治った！");
		}else{
			System.out.println(target.GetName() + "は毒にかかっている！");
			System.out.println(target.GetName() + "は" + this.turnDama + "のダメージを受けた！");
			target.Damage(turnDama);
		}
	}
	
}


