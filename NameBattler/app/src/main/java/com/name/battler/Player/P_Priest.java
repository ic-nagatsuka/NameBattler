package com.name.battler.Player;

import com.name.battler.Skill.AllSkill;

import static com.name.battler.BattleLog.BattleLog.addLog;


public class P_Priest extends Player {

	/*=============
	 * コンストラクタ
	 =============*/
	public P_Priest(String name) {
		super(name);
		this.setJob(AllJob.Job.PRIEST.getName());
	}

	@Override
	public  void makeCharacter(){
		this.hp = getNumber(0, 120) + 80;
		this.mp = getNumber(1, 30) + 20;
		this.str = getNumber(2, 60) + 10;
		this.def = getNumber(3, 60) + 10;
		this.luck = getNumber(4, 99) + 1;
		this.agi = getNumber(5, 40) + 20;
	}

	@Override
	protected void makeSkill(){
		useSkill.add(AllSkill.paralysis);
		useSkill.add(AllSkill.poison);
		useSkill.add(AllSkill.heel);
	}
	
	@Override
	public void normalAttack(Player target){
		addLog(this.name + "は回復を唱えようとした");
	
//		if(rand.nextInt(2) == 0){
//			System.out.println("何も起こらなかった");
//		}else if(rand.nextInt(2) == 0){
//			System.out.println("");
//		}else{
//			super.NormalAttack(attacker, target);
//		}
		
		switch (rand.nextInt(3)) {
		case 0:
			addLog("何も起こらなかった");
			break;
		case 1:
			addLog("aaaaaaaa");
			break;
		case 2:
			super.normalAttack(target);
			break;
			

		default:
			break;
		}
		
	}

	
}
