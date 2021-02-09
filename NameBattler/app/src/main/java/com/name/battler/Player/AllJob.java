package com.name.battler.Player;


public class AllJob{
	
	/*=============
	 * フィールド変数
	 =============*/

	//職業一覧
	public enum Job {
		FIGHTER("戦士"),
		WIZARD("魔法使い"),
		PRIEST("僧侶"),
		BOUNCER("ボール");


		private String name;
		Job(String name){
			this.name = name;
		}

		public String getName(){
			return this.name;
		}
	}

	
}

