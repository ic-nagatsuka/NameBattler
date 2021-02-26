package com.namebattler.battle.skill;

public class AllSkill {
	/*=============
	 * フィールド変数
	 =============*/

	/*=============
	 * コンストラクタ
	 =============*/
	public AllSkill(){

	}
	
	/*
	 * =============スキルタイプ一覧=============
	 */
	
	public static SkillType ATTACKMAGIC=new SkillType(0);
	public static SkillType HEEL=new SkillType(1);
	public static SkillType ABNORMALSTATE=new SkillType(2);
	
//	static SkillType =new SkillType();
	
	/*
	 * ===========スキルインスタンス一覧===========
	 */
	public static SkillOfAttackMagic magicSkill;
	public static SkillOfHeel heelSkill;
	public static SkillOfHeel heelHpSkill;
	public static SkillOfEffectTurn effectTurn;
	public static SkillOfDamageAbnormalState danageState;
	public static SkillOfAbnormalState_Inaction inactionState;
	
	/*
	 * ========スキル一覧===========
	 */
	public enum Skills{
		FIRE(new Fire(), "ファイア", 10, 10, 30,0,false),
		THUNDER(new Fire(),"サンダー", 20, 10, 30, 0, false),
		;

		private SkillBase skill;
		private String name;
		private int useMp;
		private int minDama;
		private int maxDama;
		private int turnDama;
		private boolean inaction;

		Skills(SkillBase skill, String name, int useMp, int minDama, int maxDama, int turnDama, boolean inaction){
			this.skill = skill;
			this.name = name;
			this.useMp = useMp;
			this.minDama = minDama;
			this.maxDama = maxDama;
			this.turnDama = turnDama;
			this.inaction = inaction;
		}

		public SkillBase getSkill(){
			return this.skill;
		}

		public String getName(){
			return this.name;
		}

		public int getUseMp(){
			return this.useMp;
		}

		public int getminDama(){
			return this.minDama;
		}

		public int getmaxDama(){
			return this.maxDama;
		}

		public int getTurnDama(){
			return this.turnDama;
		}

	}

	public static SkillOfAttackMagic fire = new SkillOfAttackMagic
			(ATTACKMAGIC, "ファイア", 10, 10, 30);
	public static SkillOfAttackMagic thunder = new SkillOfAttackMagic
			(ATTACKMAGIC, "サンダー", 20, 10, 30);
	public static SkillOfAbnormalState_Inaction paralysis = new SkillOfAbnormalState_Inaction
			(ABNORMALSTATE, "パライズ", 10 , 3, 20);
	public static SkillOfDamageAbnormalState poison = new SkillOfDamageAbnormalState
			(ABNORMALSTATE, "ポイズン", 10, 3, 30);
	public static SkillOfHeelHP heel = new SkillOfHeelHP
			(HEEL, "ヒール", 20, 50);

}

