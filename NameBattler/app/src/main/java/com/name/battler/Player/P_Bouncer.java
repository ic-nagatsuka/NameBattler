package com.name.battler.Player;

import com.name.battler.Skill.AllSkill;

import static com.name.battler.BattleLog.BattleLog.addLog;

public class P_Bouncer extends Player{

	/*=============
	 * コンストラクタ
	 =============*/
	public P_Bouncer(String name)
	{
		super(name);
		this.setJob(AllJob.Bouncer);
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
			addLog(this.getName() + "は飛び跳ねた！");
			super.normalAttack(target);
		}else{
			addLog(getName() + "は動かない！");
		}
	}
	
	@Override
	protected void counterAttack(Player defender){
		addLog(this.getName() + "が跳ね返ってきた！");
		//会心でなければ
		if( !checkLuckyHit(defender)){
			this.normalDamage(defender);
		}
		this.beforeHp = this.getHP();
	}
}
