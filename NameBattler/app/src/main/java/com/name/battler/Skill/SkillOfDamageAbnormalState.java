package com.name.battler.Skill;

import com.name.battler.Player.Player;

import static com.name.battler.BattleLog.BattleLog.addLog;


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

	public String getstateChara(){
		return this.stateChar;
	}
	
	@Override
	public void use(Player attacker, Player defender){
		addLog(attacker.getName() + "は" + this.getName() + "を唱えた！");
		usePlayerMp(attacker);
		
		if(defender.checkSameAbnormal(AllSkill.poison)){
			addLog(defender.getName() + "はすでにかかっている！");
			
		}else{
			addLog(defender.getName() + "は毒にかかった！");
			defender.setAbnormalState(new StateEffect(AllSkill.poison, this.effectTurn, stateChar));
		}
		
	}
	
	@Override
	public void effect(Player target, int turn){
		if(turn < 0){
			addLog(target.getName() + "の毒が治った！");
		}else{
			addLog(target.getName() + "は毒にかかっている！");
			addLog(target.getName() + "は" + this.turnDama + "のダメージを受けた！");
			target.damage(turnDama);
		}
	}
	
}


