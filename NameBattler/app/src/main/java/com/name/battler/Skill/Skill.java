package com.name.battler.Skill;

import java.util.Random;

import com.name.battler.Player.Player;

public class Skill
{
	/*=============
	 * フィールド変数
	 =============*/
	protected SkillType type;		//種類
	protected String name;			//名前
	protected int useMp;			//消費MP
	  	
	Random rand = new Random();
	
	/*=============
	 * コンストラクタ
	 =============*/
	protected Skill(SkillType type, String name, int useMp){//名前　消費MP
		this.type = type;
		this.name = name;
		this.useMp = useMp;
	}
	
	/*============
	 * Getメソッド
	 ============*/
	public SkillType getType(){
		return this.type;
	}
	public String getName(){
		return this.name;
	}
	public int getUseMP(){
		return this.useMp;
	}
	
	/*============
	 * protected
	 ============*/
	/**
	 * スキル使用
	 * @param attacker 攻撃するプレイヤー
	 * @param defender 攻撃されるプレイヤー
	 */
	public void use(Player attacker, Player defender){
		
	}
	
	//MPを消費する
	protected void usePlayerMp(Player attacker){
		attacker.setMP( Math.max(0, attacker.getMP()-this.useMp));
	}
	
}
