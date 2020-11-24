package com.name.battler.Player;

import java.util.Scanner;


public class AllJob{
	
	/*=============
	 * フィールド変数
	 =============*/
	Scanner scan=new Scanner(System.in);

	public static String[]allJob = {
			"戦士",
			"魔法使い",
			"僧侶",
			"ボール"
	};

	//すべての職業
		static final String Fighter = allJob[0];
		static final String Wizard = allJob[1];
		static final String Priest = allJob[2];
		static final String Bouncer = allJob[3];

//		public static Job[]allJob={
//			Fighter,
//			Wizard,
//			Priest,
//			Bouncer,
//			};
	
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
	public String getName(){
		return this.name;
	}
	
}
