package com.name.battler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.name.battler.Player.AllJob;
import com.name.battler.Player.P_Bouncer;
import com.name.battler.Player.P_Fighter;
import com.name.battler.Player.P_Priest;
import com.name.battler.Player.P_Wizard;
import com.name.battler.Player.Party;
import com.name.battler.Player.Player;
import com.name.battler.Skill.AllSkill;
import com.name.battler.Skill.SkillType;
import com.name.battler.Strategy.Strategy;
import com.name.battler.Strategy.StrategyOfAttackMagicPriority;
import com.name.battler.Strategy.StrategyOfBalance;
import com.name.battler.Strategy.StrategyOfDamagePriority;
import com.name.battler.Strategy.StrategyOfLeastHP;
import com.name.battler.Strategy.StrategyOfNoSkill;

public class GameManager{
	/*============
	 *フィールド変数
	 ============*/
	Random rand = new Random();

	//すべてのプレイヤーを入れる
	List<Player> allPlayer = new ArrayList<>();
	//バトルに参加しているプレイヤー
	List<Player> battlePlayer = new ArrayList<>();

	//作成したキャラクターを入れる
	List<Party> allParty = new ArrayList<>();
	//バトルに参加しているパーティー
	List<Party> battleParty = new ArrayList<>();

	//すべての作戦
	List<Strategy> strategy = new ArrayList<>();

	int turnCount = 1;	//ターン数

	/*============
	 * 設定
	 ============*/
	private int gameTextSpeed = 500;	//ゲームスピード

	/*=============
	 * コンストラクタ
	 =============*/
	GameManager() {


	}


	/**
	 * 戦闘準備
	 * @throws InterruptedException
	 */
	public void Start() throws InterruptedException{


            addAllPlayer();

			//素早さが高い順に並べる
			HighSpeedSort(battlePlayer);

//			Battle();//バトル開始

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
	private void HighSpeedSort (List<Player> playerList){

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
	 * @throws InterruptedException
	 */
//	private void Battle() throws InterruptedException
//	{
//		//残りのパーティーが1つでバトル終了
//		while(battleParty.size() != 1){
//			//ターン数の表示
//			System.out.println("=====ターン"+turnCount+"=====");
//			Thread.sleep(1000);
//
//			//バトル途中のステータスを表示
////			PrintPlayerBattleStatus();
//			Thread.sleep(900);
//			System.out.println("");
//
//			//行動
//			for(int i = battlePlayer.size() -1; 0 <= i; i--){
//				Player attacker = battlePlayer.get(i);//攻撃するプレイヤー
//				Party defenseParty;	//攻撃を受けるパーティー
//
//				if(attacker.getHP() > 0){
//					//状態異常の確認
//					attacker.abnormalEffect(attacker);
//					//戦闘不能判定
//					attacker.deathJudgeAbnormal(attacker.getParty().Getmenbers());
//					//状態異常での終了判定
//					if(CheckEnd()){
//						break;
//					}
//				}
//
//				//行動不能ではなく、状態異常で倒れていない
//				if(!attacker.getInaction() && attacker.GetHP() > 0){
//					//攻撃されるパーティー
//					defenseParty = SelectDefenseParty(attacker);
//					//作戦に沿って行動をする
//					attacker.GetStrategy().Action(attacker, defenseParty);
//				}
//
//				Thread.sleep(gameTextSpeed);
//
//				//行動後の終了判定
//				if(CheckEnd()){
//					break;
//				}
//			}
////			CheckDead();//HPがあるプレイヤーだけにする
//
//			turnCount++;	//ターン経過
//		}
//	}

//	/**
//	 防衛側パーティーを返す
//	  @param attacker 攻撃プレイヤー
//	  @return 防衛側パーティー
//	 */
//	private Party SelectDefenseParty(Player attacker){
//		Party defenseParty;
//		while(true){
//			//パーティーをランダムで選ぶ
//			defenseParty = battleParty.get( rand.nextInt(battleParty.size()) );
//			//同じパーティーでなければ
//			if(attacker.GetParty() != defenseParty){
//				return defenseParty;
//			}
//		}
//	}
//
//	/**
//	 *バトル終了判定
//	 * @return true:バトル終了,
//	 * 			  false:バトル継続
//	 */
//	private boolean CheckEnd(){
//		CheckDead();
//		//全滅判定
//		CheckEmptyParty();
//		//残りのパーティーが1グループの場合
//		if(battleParty.size() == 1){
//			System.out.println(battleParty.get(0).GetName() + "の勝利！！");
//
//			return true;
//		}else{
//			return false;
//		}
//	}
//
//	/**
//	 * HPがあるプレイヤーだけにする
//	 */
//	private void CheckDead(){
//		Player player;
//		for(int i = battlePlayer.size() -1; 0 <= i; i--){
//			player = battlePlayer.get(i);
//			if(player.GetHP() <= 0){
//				battlePlayer.remove(player);
//			}
//		}
//	}
//
//	/**
//	 * プレイヤーがいないパーティーを除く
//	 */
//	private void CheckEmptyParty(){
//		Party party;
//		for(int i = battleParty.size() -1; 0 <= i; i--){
//			party = battleParty.get(i);
//			if(party.Getmenbers().size() == 0){
//				battleParty.remove(party);
//			}
//		}
//	}

}
