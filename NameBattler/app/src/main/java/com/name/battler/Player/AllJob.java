package com.name.battler.Player;

import java.util.Scanner;


public class AllJob{
	
	/*=============
	 * フィールド変数
	 =============*/
	Scanner scan=new Scanner(System.in);
	
	
	//すべての職業
		static Job Fighter=new Job("Fighter");
		static final Job Wizard=new Job("Wizard");
		static final Job Priest=new Job("Priest");
		static final Job Bouncer=new Job("Bouncer");
		
//		public static Job[]allJob={
//			Fighter,
//			Wizard,
//			Priest,
//			Bouncer,
//			};
	
		public static String[]allJob = {
				"戦士",
				"魔法使い",
				"僧侶",
		};
		
}

class Job{
	/*============
	 * フィールド変数
	 ============*/
	private String name;
	/*=============
	 * コンストラクタ
	 =============*/
	Job(String name){
		this.name=name;
	}
	
	/*============
	 * Getメソッド
	 ============*/
	public String GetName(){
		return this.name;
	}
	
}
