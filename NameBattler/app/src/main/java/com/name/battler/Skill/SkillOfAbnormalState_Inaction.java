package com.name.battler.Skill;

import java.util.Random;

import com.name.battler.Player.Player;

public class SkillOfAbnormalState_Inaction extends SkillOfEffectTurn{
	/*=============
	 * フィールド変数
	 =============*/
	Random rand = new Random();
	
	int probability;//効果を与える確率
	
	/*=============
	 * コンストラクタ
	 =============*/
	SkillOfAbnormalState_Inaction(SkillType type, String name, int useMp, int effectTurn, int probability){
		super(type, name, useMp, effectTurn);
		this.probability = probability;
	}
	
	@Override
	public void Use(Player attacker, Player defender){
		System.out.println(attacker.GetName() + "は" + this.name + "を唱えた！");
		UsePlayerMp(attacker);
		
		//成功
		if(this.probability > rand.nextInt(100)){
			//同じ状態異常にかかっている
			if(defender.CheckSameAbnormal(AllSkill.paralysis)){
				System.out.println(defender.GetName() + "はすでにかかっている!");
			}else{
				System.out.println(defender.GetName() + "はしびれた！");
				//相手に状態異常をつける
				defender.SetAbnormalState(new StateEffect(AllSkill.paralysis, this.effectTurn));
				defender.SetInaction(true);
			}
		}else{
			//失敗
			System.out.println(defender.GetName() + "はかからなかった！");
		}
		
	}
	
	@Override
	public void Effect(Player target, int turn){
		
		if(turn < 0){
			System.out.println(target.GetName() + "のしびれが治った！");
			target.SetInaction(false);
		}else{
			System.out.println(target.GetName() + "はしびれていて動けない！\n");
		}
	}
	
	
}
