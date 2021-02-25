package com.namebattler.battle.Skill;

import com.namebattler.battle.Player.Player;

import com.namebattler.battle.battlelog.BattleLog;


public class SkillOfDamageAbnormalState extends SkillOfEffectTurn{
	/*=============
	 * フィールド変数
	 =============*/
	int turnDama;
	String stateChar = "毒";

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
		BattleLog.addLog(attacker.getName() + "は" + this.getName() + "を唱えた！");
		usePlayerMp(attacker);
		
		if(defender.checkSameAbnormal(AllSkill.poison)){
			BattleLog.addLog(defender.getName() + "はすでにかかっている！");
			
		}else{
			BattleLog.addLog(defender.getName() + "は毒にかかった！");
			defender.setAbnormalState(new StateEffect(AllSkill.poison, this.effectTurn, stateChar));
		}
		
	}
	
	@Override
	public void effect(Player target, int turn){
		if(turn < 0){
			BattleLog.addLog(target.getName() + "の毒が治った！");
		}else{
			BattleLog.addLog(target.getName() + "は毒にかかっている！");
			BattleLog.addLog(target.getName() + "は" + this.turnDama + "のダメージを受けた！");
			target.damage(turnDama);
		}
	}
	
}


