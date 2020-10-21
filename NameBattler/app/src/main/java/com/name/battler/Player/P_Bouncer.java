package com.name.battler.Player;

import com.name.battler.Skill.AllSkill;

public class P_Bouncer extends Player{

	/*=============
	 * コンストラクタ
	 =============*/
	public P_Bouncer(String name)
	{
		super(name);
		this.SetJob(AllJob.Bouncer);
		this.SetCounter(true);
	}
	
	@Override
	protected void MakeCharacter(){
		this.hp = GetNumber(0, 50) + 100;
		this.mp = GetNumber(1, 50) + 60;
		this.str = GetNumber(2, 29) + 1;
		this.def = GetNumber(3, 40) + 50;
		this.luck = GetNumber(4, 50) + 50;
		this.agi = GetNumber(5, 40) + 20;
	}
	@Override
	protected void MakeSkill(){
		useSkill.add(AllSkill.fire);
		useSkill.add(AllSkill.heel);
	}
		
	@Override
	public void NormalAttack(Player target)
	{
		if(rand.nextInt(2) == 0){
			System.out.println(this.GetName() + "は飛び跳ねた！");
			super.NormalAttack(target);
		}else{
			System.out.println(GetName() + "は動かない！");
		}
	}
	
	@Override
	protected void CounterAttack(Player defender){
		System.out.println(this.GetName() + "が跳ね返ってきた！");
		//会心でなければ
		if( !CheckLuckyHit(defender)){
			this.NormalDamage(defender);
		}
		this.beforeHp = this.GetHP();
	}
}
