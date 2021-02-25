package com.namebattler.battle.Skill;

import com.namebattler.battle.Player.Player;

import com.namebattler.battle.battlelog.BattleLog;

public class SkillOfAttackMagic extends Skill
{
	/*=============
	 * フィールド変数
	 =============*/
	private int minDama;//最小ダメージ
	private int maxDama;//最大ダメージ
	
	/*============
	 * コンストラクタ
	 ============*/
	protected SkillOfAttackMagic(SkillType type, String name, int useMp, int minDama, int maxDama){//名前　消費MP
		super(type, name, useMp);
		
		this.maxDama = maxDama;
		this.minDama = minDama;
	}
	
	/*============
	 * Getメソッド
	 ============*/
	public int getMinDama(){
		return this.minDama;
	}
	public int getMaxDama(){
		return this.maxDama;
	}
	
	public int getCalcDamage(){
		return this.minDama+this.maxDama;
	}
	/*
	 * メソッド
	 */
	@Override
	public void use(Player attacker, Player target)
	{
		int damage;
		BattleLog.addLog(attacker.getName() + "は" + this.name + "を唱えた！");
		usePlayerMp(attacker);
		
		for(Player defender : target.getParty().getmenbers()){
			damage= randomDamage(this.minDama, this.maxDama);
			BattleLog.addLog(defender.getName() + "に" + damage + "のダメージ！");
			defender.damage(damage);
			
		}
		
	}
	
	/**
	 * 指定した範囲の数字を返す
	 * @param min 最小値
	 * @param max 最大値
	 * @return 範囲内の数値
	 */
	public int randomDamage(int min, int max){
		int randNum = rand.nextInt(max - min) + min;
		return randNum;
	}
	
}
