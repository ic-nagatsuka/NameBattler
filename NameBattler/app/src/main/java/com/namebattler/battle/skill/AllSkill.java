package com.namebattler.battle.skill;

public enum AllSkill {
		FIRE(new Fire(), "ファイア", 10, 10, 30,0,false),
		THUNDER(new Fire(),"サンダー", 20, 10, 30, 0, false),
		;

		private SkillBase skill;
		private String name;
		private int useMp;
		private int minDama;
		private int maxDama;
		private int turnDama;
		private boolean inaction;

		AllSkill(SkillBase skill, String name, int useMp, int minDama, int maxDama, int turnDama, boolean inaction){
			this.skill = skill;
			this.name = name;
			this.useMp = useMp;
			this.minDama = minDama;
			this.maxDama = maxDama;
			this.turnDama = turnDama;
			this.inaction = inaction;
		}

		public SkillBase getSkill(){
			return this.skill;
		}

		public String getName(){
			return this.name;
		}

		public int getUseMp(){
			return this.useMp;
		}

		public int getminDama(){
			return this.minDama;
		}

		public int getmaxDama(){
			return this.maxDama;
		}

		public int getTurnDama(){
			return this.turnDama;
		}

}

