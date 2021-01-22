package com.name.battler.Player;

import com.name.battler.Skill.AllSkill;

import static com.name.battler.BattleLog.BattleLog.addLog;


public class P_Wizard extends Player{
	/*=============
	 * コンストラクタ
	 =============*/
	public P_Wizard(String name) {
		super(name);
		this.setJob(AllJob.Job.WIZARD.getName());

	}

	public void makeCharacter(){
		this.hp = getNumber(0, 100) + 50;
		this.mp = getNumber(1, 50) + 30;
		this.str = getNumber(2, 49) + 1;
		this.def = getNumber(3, 49) + 1;
		this.luck = getNumber(4, 99) + 1;
		this.agi = getNumber(5, 40) + 20;
	}
	//this.useSkill.add(AllSkill.);
	protected void makeSkill(){
		this.useSkill.add(AllSkill.fire);
		this.useSkill.add(AllSkill.thunder);
//		this.useSkill.add(AllSkill.);
//		this.useSkill.add(AllSkill.);
	}
		
	@Override
	public void normalAttack(Player target){
		addLog(this.name + "魔法を唱えた");
		if(rand.nextInt(2) == 0){
			addLog("が何も起こらなかった");
		}else{
			super.normalAttack(target);
		}
	}
	
}
