package com.namebattler.battle.skill;

import com.namebattler.battle.player.Player;

import com.namebattler.battle.battlelog.BattleLog;

public class Heal extends SkillBase implements IHeal{

	
	/*=============
	 * コンストラクタ
	 =============*/
	public Heal(){
	}

	public boolean terms(Player target){
		return target.checkDicreasePlayerHp(target.getParty());
	}

	@Override
	public void use(Player attacker, Player target){
		target = selectTarget(attacker);
		BattleLog.addLog(attacker.getName() + "は" + this.getName() + "を唱えた！");
		usePlayerMp(attacker);

		int calcHealPoint = Math.min(this.getHealPoint(), target.getMaxHp() - target.getHP());

		BattleLog.addLog(target.getName() + "は" + calcHealPoint + "回復した！");
		//スキル効果
		target.setHP(Math.min(target.getHP() + skill.getHealPoint(), target.getMaxHp()));
	}

	public Player selectTarget(Player attacker){
		Player target = attacker.getParty().getmenbers().get(0);

		for(Player player: attacker.getParty().getmenbers()){
			if(playerHpPercentage(player) < playerHpPercentage(target)){
				target = player;
			}
		}

		return target;
	}

	private double playerHpPercentage(Player player){
		return (double)player.getHP()/(double)player.getMaxHp();
	}

	@Override
	protected void initSkill() {
		skill = AllSkill.HEAL;
	}
}
