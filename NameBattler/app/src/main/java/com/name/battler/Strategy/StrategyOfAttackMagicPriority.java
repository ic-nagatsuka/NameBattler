package com.name.battler.Strategy;

import java.util.ArrayList;
import java.util.List;

import com.name.battler.Player.Party;
import com.name.battler.Player.Player;
import com.name.battler.Skill.Skill;
import com.name.battler.Skill.SkillType;

public class StrategyOfAttackMagicPriority extends Strategy {
	/*=============
	 * フィールド変数
	 =============*/
	SkillType [] skillNum;	//優先するスキルタイプ番号
	/*=============
	 * コンストラクタ
	 =============*/
//	ArrayList<Integer>
	public StrategyOfAttackMagicPriority(String name, SkillType [] skillNum){
		this.name = name;
		this.skillNum = skillNum;
	}
	
	@Override
	public void Action(Player attacker, Party defenceParty){
		Skill skill;
		Player target = RandomDefender(defenceParty.Getmenbers());
		List<Skill> prioritySkill;//優先スキル

		//優先スキルを探す
		prioritySkill = CheckPrioritySkill(attacker.GetUseSkill());
		//優先スキルがあり, mpがある
		if(prioritySkill.size() != 0 && attacker.CheckUseSkill()){
			//優先スキルをランダムで選ぶ
			skill = prioritySkill.get(rand.nextInt(prioritySkill.size()));
			//スキルを使用する
			attacker.UseSkill(skill, target);
		}else{
			//他のスキルを使用する
			attacker.Action(target);
		}
	}		
		
	/**
	 * 優先スキルを探す
	 * @param useSkill プレイヤーの使用スキル
	 */
	protected List<Skill> CheckPrioritySkill(List<Skill> useSkill){
		List<Skill> prioritySkill= new ArrayList<>();
			
			for(int i = 0; i < skillNum.length; i++){
				for(Skill skill : useSkill){
					if(skillNum[i] == skill.GetType()){
						prioritySkill.add(skill);
					}
				}
//				if(prioritySkill.size() != 0){
//					return prioritySkill;
//				}
			}
			
//			if(skill instanceof SkillOfAttackMagic){
//				prioritySkill.add(skill);
//			}
			
			System.out.println(prioritySkill.size());
		return prioritySkill;
	}
}
