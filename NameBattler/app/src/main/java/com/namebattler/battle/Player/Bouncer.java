package com.namebattler.battle.player;

import com.namebattler.battle.skill.AllSkill;

import com.namebattler.battle.battlelog.BattleLog;

public class Bouncer extends Player{

	/*=============
	 * コンストラクタ
	 =============*/
	public Bouncer(String name)
	{
		super(name);
		this.setJob(AllJob.BOUNCER.getName());
		this.setCounter(true);
	}
	
	@Override
	public  void makeCharacter(){
		this.hp = getNumber(0, 50) + 100;
		this.mp = getNumber(1, 50) + 60;
		this.str = getNumber(2, 29) + 1;
		this.def = getNumber(3, 40) + 50;
		this.luck = getNumber(4, 50) + 50;
		this.agi = getNumber(5, 40) + 20;
	}
	@Override
	protected void makeSkill(){
		useSkill.add(AllSkill.fire);
		useSkill.add(AllSkill.heel);
	}
		
	@Override
	public void normalAttack(Player target)
	{
		if(rand.nextInt(2) == 0){
			BattleLog.addLog(this.getName() + "は飛び跳ねた！");
			super.normalAttack(target);
		}else{
			BattleLog.addLog(getName() + "は動かない！");
		}
	}
	
	@Override
	protected void counterAttack(Player defender){
		BattleLog.addLog(this.getName() + "が跳ね返ってきた！");
		//会心でなければ
		if( !checkLuckyHit(defender)){
			this.normalDamage(defender);
		}
		this.beforeHp = this.getHP();
	}
}
