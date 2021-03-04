package com.namebattler.battle.skill;

import java.util.Random;

import com.namebattler.battle.player.Player;

import com.namebattler.battle.battlelog.BattleLog;

public class Paralysis extends SkillBase{
	/*=============
	 * フィールド変数
	 =============*/
	Random rand = new Random();


	/*=============
	 * コンストラクタ
	 =============*/
	Paralysis(){
	}

	@Override
	public void use(Player attacker, Player defender){
		BattleLog.addLog(attacker.getName() + "は" + this.getName() + "を唱えた！");
		usePlayerMp(attacker);

		//成功
		if(this.probability > rand.nextInt(100)){
			//同じ状態異常にかかっている
			if(defender.checkSameAbnormal(AllSkill.paralysis)){
				BattleLog.addLog(defender.getName() + "はすでにかかっている!");
			}else{
				BattleLog.addLog(defender.getName() + "はしびれた！");
				//相手に状態異常をつける
				defender.setAbnormalState(new StateEffect(AllSkill.paralysis, this.effectTurn, stateChar));
				defender.setInaction(true);
			}
		}else{
			//失敗
			BattleLog.addLog(defender.getName() + "はかからなかった！");
		}

	}

	@Override
	public void effect(Player target, int turn){

		if(turn < 0){
			BattleLog.addLog(target.getName() + "のしびれが治った！");
			target.setInaction(false);
		}else{
			BattleLog.addLog(target.getName() + "はしびれていて動けない！\n");
		}
	}


}