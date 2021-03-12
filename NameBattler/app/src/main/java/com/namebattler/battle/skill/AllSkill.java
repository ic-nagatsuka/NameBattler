package com.namebattler.battle.skill;

public class AllSkill {
	/*=============
	 * フィールド変数
	 =============*/

    /*=============
     * コンストラクタ
     =============*/
    public AllSkill() {

    }

    /*
     * =============スキルタイプ一覧=============
     */

    public static SkillType ATTACKMAGIC = new SkillType(0);
    public static SkillType HEEL = new SkillType(1);
    public static SkillType ABNORMALSTATE = new SkillType(2);

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
    public static SkillOfAttackMagic fire = new SkillOfAttackMagic
            (ATTACKMAGIC, "ファイア", 10, 10, 30);
    public static SkillOfAttackMagic thunder = new SkillOfAttackMagic
            (ATTACKMAGIC, "サンダー", 20, 10, 30);
    public static SkillOfAbnormalState_Inaction paralysis = new SkillOfAbnormalState_Inaction
            (ABNORMALSTATE, "パライズ", 10, 3, 20);
    public static SkillOfDamageAbnormalState poison = new SkillOfDamageAbnormalState
            (ABNORMALSTATE, "ポイズン", 10, 3, 30);
    public static SkillOfHeelHP heel = new SkillOfHeelHP
            (HEEL, "ヒール", 20, 50);

}

