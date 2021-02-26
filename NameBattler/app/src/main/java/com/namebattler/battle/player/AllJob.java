package com.namebattler.battle.player;


public enum AllJob {
	FIGHTER("戦士"),
	WIZARD("魔法使い"),
	PRIEST("僧侶"),
	BOUNCER("ボール");


	private String name;
	AllJob(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
}

