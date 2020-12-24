package com.name.battler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.name.battler.Player.P_Bouncer;
import com.name.battler.Player.P_Fighter;
import com.name.battler.Player.P_Priest;
import com.name.battler.Player.P_Wizard;
import com.name.battler.Player.Party;
import com.name.battler.Player.Player;
import com.name.battler.Strategy.AllStrategy;


public class GameManager{
	/*============
	 *フィールド変数
	 ============*/
	public static Party myParty = new Party("味方");
	public static Party enemyParty = new Party("敵");

	//すべてのプレイヤーを入れる
	List<Player> allPlayer = new ArrayList<>();
	//バトルに参加しているプレイヤー
	List<Player> battlePlayer = new ArrayList<>();

	//全てのパーティーを入れる
	List<Party> allParty = new ArrayList<>();
//	//バトルに参加しているパーティー
//	List<Party> battleParty = new ArrayList<>();

	public static Party win;
	int turnCount = 1;	//ターン数

	Random rand = new Random();
	/*============
	 * 設定
	 ============*/
	private int gameTextSpeed = 500;	//ゲームスピード

	/*=============
	 * コンストラクタ
	 =============*/
	public GameManager() {
		prepare();

	}


	/**
	 * 戦闘準備
	 * @throws InterruptedException
	 */
	public void prepare() {

		allParty.add(myParty);
		allParty.add(enemyParty);

		if(myParty.getStrategy() == null || enemyParty.getStrategy() == null){
			myParty.setStrategy(AllStrategy.Strategies.values()[0].getStrategy());
			enemyParty.setStrategy(AllStrategy.Strategies.values()[0].getStrategy());
		}

		addAllPlayer();

		//素早さが高い順に並べる
		highSpeedSort(allPlayer);

	}

	public void addAllPlayer(){
	    for(Party party: allParty){
	        for(Player player: party.getmenbers()){
                allPlayer.add(player);
            }
        }

    }


	/**
	 * 素早さが大きい順に並べる
	 * @param playerList すべてのプレイヤー情報
	 */
	private void highSpeedSort(List<Player> playerList){

		for(int i = 0; i < playerList.size() - 1; i++){
			for(int j = 0; j < playerList.size() - 1; j++){
				if(playerList.get(j).getAGI() > playerList.get(j+1).getAGI()){
					//場所を入れ替える
					Player saveValue = playerList.get(j);
					playerList.set(j, playerList.get(j + 1));
					playerList.set(j + 1, saveValue);
				}
			}
		}
	}
	/**
	 * バトル
	 */
	public boolean battle()
	{
		//ターン数の表示
		System.out.println("=====ターン"+turnCount+"=====");

		//行動
		for(int i = allPlayer.size() -1; 0 <= i; i--){
			Player attacker = allPlayer.get(i);//攻撃するプレイヤー
			Party defenseParty;	//攻撃を受けるパーティー

			//状態異常の確認
			attacker.abnormalEffect(attacker);
			//行動不能ではなく、状態異常で倒れていない
			if(!attacker.getInaction() && attacker.getHP() > 0){
				//攻撃されるパーティー
				defenseParty = selectDefenseParty(attacker);
				//作戦に沿って行動をする
				attacker.getParty().getStrategy().action(attacker, defenseParty);
			}


			if(isEnd()){
				return false;
			}

		}
		turnCount++;	//ターン経過

		return true;
	}

	public boolean isEnd(){
		boolean isEnd = true;
		for(Party party: allParty){
			isEnd = true;

			for(Player player: party.getmenbers()){
				if(player.getHP() != 0){
					win = party;
					isEnd = false;
				}
			}
			if(isEnd == true){
				return isEnd;
			}
		}

		return isEnd;
	}
//	/**
//	 防衛側パーティーを返す
//	  @param attacker 攻撃プレイヤー
//	  @return 防衛側パーティー
//	 */
	private Party selectDefenseParty(Player attacker){
		Party defenseParty;
		while(true){
			//パーティーをランダムで選ぶ
			defenseParty = allParty.get( rand.nextInt(allParty.size()) );
			//同じパーティーでなければ
			if(attacker.getParty() != defenseParty){
				return defenseParty;
			}
		}
	}

	public static Player makePlayer(String name, String job, Party party){
		Player player = null;
		switch(job){
			case "戦士"   : player = new P_Fighter(name);break;
			case "魔法使い" :player = new P_Wizard(name); break;
			case "僧侶"   : player = new P_Priest(name); break;
			case "ボール"  : player = new P_Bouncer(name); break;
		}

		System.out.println("プレイヤー作成パーティー" + party);

		player.setParty(party);
		player.setStrategy(AllStrategy.Strategies.values()[0].getStrategy());

		return player;
	}

}
