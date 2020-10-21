package com.name.battler.Skill;

import com.name.battler.Player.Player;

public class SkillOfEffectTurn extends Skill{

	public static class StateEffect{
		/*=============
		 * フィールド変数
		 =============*/
		SkillOfEffectTurn skill;
		int turn;
		/*=============
		 * コンストラクタ
		 =============*/	
		StateEffect(SkillOfEffectTurn skill, int turn){
			this.skill = skill;
			this.turn = turn;
		}
		/*============
		 * Get
		 ============*/
		public SkillOfEffectTurn GetSkill(){
			return this.skill;
		}
		
		public int GetTurn(){
			return this.turn;
		}
		/*============
		 * Set
		 ============*/
		public void SetTurn(int turn){
			this.turn = turn;
		}
		
	}
	/*================
	 * フィールド変数
	 ================*/
	int effectTurn;
	
	//===========
	//コンストラクタ
	//===========
	SkillOfEffectTurn(SkillType type, String name, int useMp, int effectTurn){
		super(type, name, useMp);
		this.effectTurn = effectTurn;
	}

	/*============
	 * Getメソッド
	 ============*/
	public int GetEffectTurn(){
		return this.effectTurn;
	}
	
	/**
	 * 状態異常効果
	 * @param target 行動プレイヤー
	 */
	public void Effect(Player target, int turn){
		//オーバーライド
	}
	
}


