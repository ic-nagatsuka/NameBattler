package com.namebattler.battle.skill;

import com.namebattler.battle.player.Player;

import com.namebattler.battle.battlelog.BattleLog;

public class Heal extends SkillBase{

	
	/*=============
	 * コンストラクタ
	 =============*/
	Heal(){
	}
	
	@Override
	public void use(Player attacker, Player target){
		BattleLog.addLog(attacker.getName() + "は" + this.getName() + "を唱えた！");
		usePlayerMp(attacker);//mpを消費する

		BattleLog.addLog(target.getName() + "は" + skill.getHealPoint() + "回復した！");
		//スキル効果
		target.setHP(Math.min(target.getHP() + skill.getHealPoint(), target.getMaxHp()));
	}

	@Override
	protected void initSkill() {
		skill = AllSkill.HEAL;
	}
}
