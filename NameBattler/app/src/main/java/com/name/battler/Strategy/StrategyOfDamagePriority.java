package com.name.battler.Strategy;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;
import com.name.battler.Skill.AllSkill;
import com.name.battler.Skill.Skill;
import com.name.battler.Skill.SkillOfAttackMagic;
import com.name.battler.Skill.SkillOfDamageAbnormalState;
import com.name.battler.Skill.SkillOfEffectTurn;


public class StrategyOfDamagePriority extends Strategy{

	/*=============
	 * コンストラクタ
	 =============*/
	public StrategyOfDamagePriority(){
		this.name = "ダメージ与えて";
	}
	
	@Override
	public void Action(Player attacker, Party defenceParty){
		Player defender = RandomDefender(defenceParty.Getmenbers());
		int highDamage = 0;	//スキルの最大ダメージ計算値
		Skill useSkill;		//使用スキル
		int damage = 0;			//スキルのダメージ計算値
		
		boolean skillAttack = false;//スキルの使用
		
		//攻撃を一番与えそうな攻撃方法を選ぶ
		//スキルが使える場合
		if(attacker.CheckUseSkill()){
			//スキルがないプレイヤーだとエラーが出るのでここで初期化
			useSkill = attacker.GetUseSkill().get(0);
			//攻撃できるスキルを探す
			for(Skill skill : attacker.GetUseSkill()){
				
				//スキルの計算ダメージ
				damage = this.CheckSkillDamage(skill);
				try{
					//状態異常スキルの場合
					AllSkill.effectTurn = (SkillOfEffectTurn)skill;
					
					//同じ状態異常にかかっている場合
					if(defender.CheckSameAbnormal(AllSkill.effectTurn)){
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
			if(attacker.CalcDamage(defender)*2 > highDamage || highDamage == 0)
			{
				attacker.NormalAttack(defender);
			}else if(skillAttack){
				//選ばれたスキルを使う
				attacker.UseSkill(useSkill, defender);
			}else{
				//選ばれるスキルがなければ通常の流れ
				attacker.Action(defender);
			}
			
		}else{
			//使えるスキルがなければ通常攻撃
			attacker.NormalAttack(defender);	
		}
		
	}
	
	/**
	 * スキルのダメージ計算値を返す
	 * @param useSkill プレイヤーが使用できるスキル
	 * @return スキルダメージ計算値
	 */
	public int CheckSkillDamage(Skill useSkill){
		int i = 0;
		int damage = 0;//ダメージ値
		
		//スキルのクラスを見つける
		while(true){
			try{
				switch(i){
				case 0 :
					SkillOfAttackMagic skill1 = (SkillOfAttackMagic)useSkill;
					damage = skill1.GetCalcDamage();
					break;
					
				case 1 :
					SkillOfDamageAbnormalState skill2 = (SkillOfDamageAbnormalState)useSkill;
					damage = skill2.GetCalcDamage();
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
