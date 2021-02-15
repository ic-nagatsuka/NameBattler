package com.namebattler.battle.Strategy;

import java.util.ArrayList;
import java.util.List;

import com.namebattler.battle.Player.Party;
import com.namebattler.battle.Player.Player;
import com.namebattler.battle.Skill.Skill;
import com.namebattler.battle.Skill.SkillType;

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
	public void action(Player attacker, Party defenceParty){
		Skill skill;
		Player target = randomDefender(defenceParty.getmenbers());
		List<Skill> prioritySkill;//優先スキル

		//優先スキルを探す
		prioritySkill = checkPrioritySkill(attacker.getUseSkill());
		//優先スキルがあり, mpがある
		if(prioritySkill.size() != 0 && attacker.checkUseSkill()){
			//優先スキルをランダムで選ぶ
			skill = prioritySkill.get(rand.nextInt(prioritySkill.size()));
			//スキルを使用する
			attacker.useSkill(skill, target);
		}else{
			//他のスキルを使用する
			attacker.action(target);
		}
	}		
		
	/**
	 * 優先スキルを探す
	 * @param useSkill プレイヤーの使用スキル
	 */
	protected List<Skill> checkPrioritySkill(List<Skill> useSkill){
		List<Skill> prioritySkill= new ArrayList<>();
			
			for(int i = 0; i < skillNum.length; i++){
				for(Skill skill : useSkill){
					if(skillNum[i] == skill.getType()){
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
