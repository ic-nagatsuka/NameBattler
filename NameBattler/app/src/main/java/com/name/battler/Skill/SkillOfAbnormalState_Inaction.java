package com.name.battler.Skill;

import java.util.Random;

import com.name.battler.Player.Player;

import static com.name.battler.BattleLog.BattleLog.addLog;

public class SkillOfAbnormalState_Inaction extends SkillOfEffectTurn{
	/*=============
	 * フィールド変数
	 =============*/
	Random rand = new Random();
	
	int probability;//効果を与える確率
	String stateChar = "痺";

	/*=============
	 * コンストラクタ
	 =============*/
	SkillOfAbnormalState_Inaction(SkillType type, String name, int useMp, int effectTurn, int probability){
		super(type, name, useMp, effectTurn);
		this.probability = probability;
	}
	
	@Override
	public void use(Player attacker, Player defender){
		addLog(attacker.getName() + "は" + this.name + "を唱えた！");
		usePlayerMp(attacker);

		//成功
		if(this.probability > rand.nextInt(100)){
			//同じ状態異常にかかっている
			if(defender.checkSameAbnormal(AllSkill.paralysis)){
				addLog(defender.getName() + "はすでにかかっている!");
			}else{
				addLog(defender.getName() + "はしびれた！");
				//相手に状態異常をつける
				defender.setAbnormalState(new StateEffect(AllSkill.paralysis, this.effectTurn, stateChar));
				defender.setInaction(true);
			}
		}else{
			//失敗
			addLog(defender.getName() + "はかからなかった！");
		}
		
	}

	@Override
	public void effect(Player target, int turn){
		
		if(turn < 0){
			addLog(target.getName() + "のしびれが治った！");
			target.setInaction(false);
		}else{
			addLog(target.getName() + "はしびれていて動けない！\n");
		}
	}

	
}