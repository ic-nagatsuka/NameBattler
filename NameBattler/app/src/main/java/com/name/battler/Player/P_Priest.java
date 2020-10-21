package com.name.battler.Player;

import com.name.battler.Skill.AllSkill;


public class P_Priest extends Player {

	/*=============
	 * コンストラクタ
	 =============*/
	public P_Priest(String name) {
		super(name);
		this.SetJob(AllJob.Priest);
	}
	
	protected void MakeCharacter(){
		this.hp = GetNumber(0, 120) + 80;
		this.mp = GetNumber(1, 30) + 20;
		this.str = GetNumber(2, 60) + 10;
		this.def = GetNumber(3, 60) + 10;
		this.luck = GetNumber(4, 99) + 1;
		this.agi = GetNumber(5, 40) + 20;
	}
	protected void MakeSkill(){
		useSkill.add(AllSkill.paralysis);
		useSkill.add(AllSkill.poison);
		useSkill.add(AllSkill.heel);
	}
	
	@Override
	public void NormalAttack(Player target){
		System.out.println(this.name + "は回復を唱えようとした");
	
//		if(rand.nextInt(2) == 0){
//			System.out.println("何も起こらなかった");
//		}else if(rand.nextInt(2) == 0){
//			System.out.println("");
//		}else{
//			super.NormalAttack(attacker, target);
//		}
		
		switch (rand.nextInt(3)) {
		case 0:
			System.out.println("何も起こらなかった");
			break;
		case 1:
			System.out.println("aaaaaaaa");
			break;
		case 2:
			super.NormalAttack(target);
			break;
			

		default:
			break;
		}
		
	}

	
}
