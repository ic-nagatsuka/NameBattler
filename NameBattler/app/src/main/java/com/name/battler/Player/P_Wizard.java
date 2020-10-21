package com.name.battler.Player;

import com.name.battler.Skill.AllSkill;


public class P_Wizard extends Player{
	/*=============
	 * コンストラクタ
	 =============*/
	public P_Wizard(String name) {
		super(name);
		this.SetJob(AllJob.Wizard);

	}
	
	protected void MakeCharacter(){
		this.hp = GetNumber(0, 100) + 50;
		this.mp = GetNumber(1, 50) + 30;
		this.str = GetNumber(2, 49) + 1;
		this.def = GetNumber(3, 49) + 1;
		this.luck = GetNumber(4, 99) + 1;
		this.agi = GetNumber(5, 40) + 20;
	}
	//this.useSkill.add(AllSkill.);
	protected void MakeSkill(){
		this.useSkill.add(AllSkill.fire);
		this.useSkill.add(AllSkill.thunder);
//		this.useSkill.add(AllSkill.);
//		this.useSkill.add(AllSkill.);
	}
		
	@Override
	public void NormalAttack(Player target){
		System.out.println(this.name + "魔法を唱えた");
		if(rand.nextInt(2) == 0){
			System.out.println("が何も起こらなかった");
		}else{
			super.NormalAttack(target);
		}
	}
	
}
