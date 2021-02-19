package com.namebattler.battle.Strategy;

import com.namebattler.battle.Player.Party;
import com.namebattler.battle.Player.Player;
import com.namebattler.battle.Skill.AllSkill;
import com.namebattler.battle.Skill.Skill;
import com.namebattler.battle.Skill.SkillOfAttackMagic;
import com.namebattler.battle.Skill.SkillOfDamageAbnormalState;
import com.namebattler.battle.Skill.SkillOfEffectTurn;


public class StrategyOfDamagePriority extends Strategy{

	/*=============
	 * コンストラクタ
	 =============*/
	public StrategyOfDamagePriority(){
		this.name = "ダメージ与えて";
	}
	
	@Override
	public void action(Player attacker, Party defenceParty){
		Player defender = randomDefender(defenceParty.getmenbers());
		int highDamage = 0;	//スキルの最大ダメージ計算値
		Skill useSkill;		//使用スキル
		int damage = 0;			//スキルのダメージ計算値
		
		boolean skillAttack = false;//スキルの使用
		
		//攻撃を一番与えそうな攻撃方法を選ぶ
		//スキルが使える場合
		if(attacker.checkUseSkill()){
			//スキルがないプレイヤーだとエラーが出るのでここで初期化
			useSkill = attacker.getUseSkill().get(0);
			//攻撃できるスキルを探す
			for(Skill skill : attacker.getUseSkill()){
				
				//スキルの計算ダメージ
				damage = this.checkSkillDamage(skill);
				try{
					//状態異常スキルの場合
					AllSkill.effectTurn = (SkillOfEffectTurn)skill;
					
					//同じ状態異常にかかっている場合
					if(defender.checkSameAbnormal(AllSkill.effectTurn)){
						damage = 0;
					}
				}catch(Exception e){}
				
				//ダメージが高い場合
				if(damage > highDamage){
					//スキルをセットする
					useSkill = skill;
					//ダメージを更新する
					highDamage = damage;
					//スキルを使える条件に変える
					skillAttack = true;
				}
			}
			
			//通常攻撃のダメージがスキルダメージより多ければ使う
			if(attacker.calcDamage(defender)*2 > highDamage || highDamage == 0)
			{
				attacker.normalAttack(defender);
			}else if(skillAttack){
				//選ばれたスキルを使う
				attacker.useSkill(useSkill, defender);
			}else{
				//選ばれるスキルがなければ通常の流れ
				attacker.action(defender);
			}
			
		}else{
			//使えるスキルがなければ通常攻撃
			attacker.normalAttack(defender);
		}
		
	}
	
	/**
	 * スキルのダメージ計算値を返す
	 * @param useSkill プレイヤーが使用できるスキル
	 * @return スキルダメージ計算値
	 */
	public int checkSkillDamage(Skill useSkill){
		int i = 0;
		int damage = 0;//ダメージ値
		
		//スキルのクラスを見つける
		while(true){
			try{
				switch(i){
				case 0 :
					SkillOfAttackMagic skill1 = (SkillOfAttackMagic)useSkill;
					damage = skill1.getCalcDamage();
					break;
					
				case 1 :
					SkillOfDamageAbnormalState skill2 = (SkillOfDamageAbnormalState)useSkill;
					damage = skill2.getCalcDamage();
					break;
					
				default:
					//当てはまらない
				}
				break;//処理を抜ける
				
			}catch(Exception e){
				//次の番号にする
				i++;
			}
			
		}
		return damage;
	}
	
	
}
