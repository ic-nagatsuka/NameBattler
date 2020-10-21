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
	  	
	protected boolean 
	  	noAction,		//行動不能
	  	possible = true;	//使用可能
		
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
	public SkillType GetType(){
		return this.type;
	}
	public String GetName(){
		return this.name;
	}
	public int GetUseMP(){
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
	public void Use(Player attacker, Player defender){
		
	}
	
	//MPを消費する
	protected void UsePlayerMp(Player attacker){
		attacker.SetMP( Math.max(0, attacker.GetMP()-this.useMp));
	}
	
}
