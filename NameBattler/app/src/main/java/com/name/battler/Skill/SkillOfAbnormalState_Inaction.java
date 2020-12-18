package com.name.battler.Skill;

import java.util.Random;

import com.name.battler.Player.Player;

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
		System.out.println(attacker.getName() + "は" + this.name + "を唱えた！");
		usePlayerMp(attacker);

		//成功
		if(this.probability > rand.nextInt(100)){
			//同じ状態異常にかかっている
			if(defender.checkSameAbnormal(AllSkill.paralysis)){
				System.out.println(defender.getName() + "はすでにかかっている!");
			}else{
				System.out.println(defender.getName() + "はしびれた！");
				//相手に状態異常をつける
				defender.setAbnormalState(new StateEffect(AllSkill.paralysis, this.effectTurn, stateChar));
				defender.setInaction(true);
			}
		}else{
			//失敗
			System.out.println(defender.getName() + "はかからなかった！");
		}
		
	}

	@Override
	public void effect(Player target, int turn){
		
		if(turn < 0){
			System.out.println(target.getName() + "のしびれが治った！");
			target.setInaction(false);
		}else{
			System.out.println(target.getName() + "はしびれていて動けない！\n");
		}
	}

	
}