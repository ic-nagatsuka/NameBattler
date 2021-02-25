package com.namebattler.battle.Skill;

import com.namebattler.battle.Player.Player;

import com.namebattler.battle.battlelog.BattleLog;

public class SkillOfHeelHP extends SkillOfHeel{

	
	/*=============
	 * コンストラクタ
	 =============*/
	SkillOfHeelHP(SkillType type, String name, int useMp, int heelPoint){
		super(type, name, useMp, heelPoint);
	}
	
	@Override
	public void use(Player attacker, Player target){
		BattleLog.addLog(attacker.getName() + "は" + this.getName() + "を唱えた！");
		usePlayerMp(attacker);//mpを消費する

		BattleLog.addLog(target.getName() + "は" + this.heelPoint + "回復した！");
		//スキル効果
		target.setHP(Math.min(target.getHP() + this.heelPoint, target.getMaxHp()));
	}
}
