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
	public void use(Player attacker, Player target){
		System.out.println(attacker.getName() + "は" + this.getName() + "を唱えた！");
		usePlayerMp(attacker);//mpを消費する
		
		System.out.println(target.getName() + "は" + this.heelPoint + "回復した！");
		//スキル効果
		target.setHP(Math.min(target.getHP() + this.heelPoint, target.getMaxHp()));
	}
}
