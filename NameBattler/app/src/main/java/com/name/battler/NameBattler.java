package com.name.battler;//2020/7/8


//攻撃メソッドをオーバーライドして職業ごとに違う文字を表示する

//優先スキルを探すときのｔｒｙを、instanceofを使ってクラスを探すようにした。　

//バトルの行動順Listを前でも後ろでも力尽きると、ずれてしまうのでバトル中にプレイヤーを除くことはしない

//優先スキルを使う作戦にスキル番号を送る。一つのクラスから複数のインスタンスを作る

/**
 * 自由に作るネームバトラー
 */
public class NameBattler {
	public static void main(String[] args) throws InterruptedException{
		
		GameManager gm=new GameManager();	
	
		gm.Start();
	
	}
}

