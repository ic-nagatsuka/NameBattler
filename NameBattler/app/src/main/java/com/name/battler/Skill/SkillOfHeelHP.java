package com.name.battler.Skill;

import com.name.battler.Player.Player;

public class SkillOfHeelHP extends SkillOfHeel{

	
	/*=============
	 * コンストラクタ
	 =============*/
	SkillOfHeelHP(SkillType type, String name, int useMp, int heelPoint){
		super(type, name, useMp, heelPoint);
	}
	
	@Override
	public void Use(Player attacker, Player target){
		System.out.println(attacker.GetName() + "は" + this.GetName() + "を唱えた！");
		UsePlayerMp(attacker);//mpを消費する
		
		System.out.println(target.GetName() + "は" + this.heelPoint + "回復した！");
		//スキル効果
		target.SetHP(Math.min(target.GetHP() + this.heelPoint, target.GetMaxHp()));
	}
}
