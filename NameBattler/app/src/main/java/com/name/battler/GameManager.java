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
	Scanner scanStr = new Scanner( System.in);
	Scanner scanInt = new Scanner( System.in);
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
	private int playerNum = 2;		//プレイヤー数
	private int partyNuma = 2;			//パーティー数
	private int gameTextSpeed = 500;	//ゲームスピード
	
	/*=============
	 * コンストラクタ
	 =============*/
	GameManager() {
		//パーティーを作成
		MakeParty();
		//作戦を作成
		MakeStrategy();
	}
	
	/**
	 * パーティーを作成する
	 */
	private void MakeParty(){
		for(int i = 0; i < partyNuma; i++){
			allParty.add( new Party("パーティー"+(i + 1) ));
		}
	}
	
	/**
	 * 作戦を作成する
	 */
	private void MakeStrategy(){
		strategy.add( new StrategyOfLeastHP());
		strategy.add( new StrategyOfNoSkill());
		strategy.add( new StrategyOfAttackMagicPriority("魔法優先", new SkillType []{
				AllSkill.ATTACKMAGIC}));
		strategy.add( new StrategyOfAttackMagicPriority("状態異常優先", new SkillType []{
				AllSkill.ABNORMALSTATE}));
		strategy.add( new StrategyOfDamagePriority());
		strategy.add( new StrategyOfBalance());
	}
	
	/**
	 * 戦闘準備
	 * @throws InterruptedException
	 */
	public void Start() throws InterruptedException{
			String nameKey;	//名前
			int jobKey;		//職業
			
			//プレイヤーの設定
			for(int i = 0; i < playerNum; i++){
				System.out.println("プレイヤー"+(i + 1)+"の名前を入力してください");
				//プレイヤーの名前を入力
				nameKey = scanStr.nextLine();
				//職業を選択する
				jobKey = 3;//SelectJob(i);
				
				//プレイヤーを作成する
				Player player=MakeChar(nameKey, jobKey);
				//作成プレイヤーを入れる
				allPlayer.add(player);
				//作戦を選択する
				SelectStrategy(player, i);
				//パーティーを選択する
				SelectAddParty(player, i);
			}
			
			//パーティーの人数表示
			for(Party party : allParty){
				System.out.println(party.GetName() + ":" + party.Getmenbers().size());
			}
			//すべてのプレイヤーステータスを表示する
			PrintAllPlayerStatus();
			
			//元の状態を残すためバトル用を作る
			battlePlayer.addAll(allPlayer);	//バトルで使うプレイヤー
			battleParty.addAll(allParty);	//バトルで使うパーティー
			
			//素早さが高い順に並べる
			HighSpeedSort(battlePlayer);
			
			Battle();//バトル開始
			
			scanStr.close();
			scanInt.close();
	}
	
	/**
	 * プレイヤー作成
	 * @param name 名前
	 * @param jobKey 職業番号
	 * @return プレイヤー
	 */
	private Player MakeChar(String name, int jobKey){

		switch(jobKey)
		{
		case 0: return new P_Fighter(name);
		
		case 1: return new P_Wizard(name);
		
		case 2: return new P_Priest(name);
		
		case 3: return new P_Bouncer(name);
		
		default:
			System.out.println("見つかりませんでした");
			System.out.println("Fighterで始めます");
			return new P_Fighter(name);
		}
	}
	
	/**
	 * 職業を選択する
	 * @param i for文の変数i
	 * @return 作成済みのプレイヤー数
	 */
	private int SelectJob(int i){
		//プレイヤーの職業を選択
		System.out.println("プレイヤー"+(i + 1)+"の職業を選択してください");
		
		//職業名の出力
		for(int j = 0; j < AllJob.allJob.length; j++){
			System.out.print(AllJob.allJob[j].GetName() + "「" + j + "」 : ");
		}
		//職業を選択する
		int jobKey = InputExceptionCheck(0, AllJob.allJob.length -1);
		
		return jobKey;
	}
	
	
	/**
	 * 作戦を選択する
	 * @param player 作成しているプレイヤー
	 * @param i 作成済みのプレイヤー数
	 */
	private void SelectStrategy(Player player, int i){

		//作戦を決める
		System.out.println("プレイヤー"+ (i + 1) +"の作戦を選択して下さい");
		
		//作戦を表示する
		for(int j = 0; j < strategy.size(); j++){
			System.out.print(strategy.get(j).GetName()+"[" + j + "] : ");
			//改行
			if(j % 5 == 0 && j != 0){
				System.out.println();
			}
		}
		//作戦を選択する
		int strategyKey = InputExceptionCheck(0, strategy.size()-1);
		
		//プレイヤーに作戦の番号を与える
		player.SetStrategy(strategy.get(strategyKey));
	}
	
	/**
	 * パーティーを選択する
	 * @param player　作成中のプレイヤー
	 * @param i 作成済みのプレイヤー数
	 */
	private void SelectAddParty(Player player, int i){
		//入れるパーティーを決める
		boolean err = true;
		while(err == true){
			
			System.out.println("プレイヤー"+(i + 1)+"の参加パーティーを選択してください");
			//選択するパーティーを表示する
			for(int j = 0; j < allParty.size(); j++){
				System.out.println(allParty.get(j). GetName()+"[" +j+ "]");
			}
			//パーティーを選択する
			int partyKey = InputExceptionCheck(0, allParty.size() - 1);
			
			int inPlayer = playerNum - i;//パーティーに入る残りの人数
			int emptyParty = 0;//プレイヤーがいないパーティー数
			//プレイヤーがいないパーティーを数える
			for(Party party : allParty){
				if(party.Getmenbers().size() == 0){
					emptyParty++;	
				}
			}
			
			//パーティー選択  
			//プレイヤー数と空のパーティー数が同じではない＿パーティー人数が０
			if((emptyParty != inPlayer || allParty.get(partyKey).Getmenbers().size() == 0))
			{
				//パーティーに追加
				allParty.get(partyKey).AppendPlayer(player);
				//プレイヤーにパーティー情報を送る
				player.SetParty(allParty.get(partyKey));
				//条件から抜ける
				err = false;
			}else{
				//エラー
				System.err.println("\n人数が足りないパーティーが存在してしまいます");
			}
		}
		//改行
		System.out.println();
	}
	
	
	
	/**
	 * すべてのプレイヤーステータスを表示する
	 * @throws InterruptedException
	 */
	private void PrintAllPlayerStatus() throws InterruptedException{
		for(Party party : allParty){
			System.out.println("======"+ party.GetName() +"======");
			Thread.sleep(300);
			for(Player player : party.Getmenbers())
			{
				player.PrintStatus();
				Thread.sleep(500);
			}
		}
		System.out.println();
	}
	
	/**
	 * 素早さが大きい順に並べる
	 * @param playerList すべてのプレイヤー情報
	 */
	private void HighSpeedSort (List<Player> playerList){
		
		for(int i = 0; i < playerList.size() - 1; i++){
			for(int j = 0; j < playerList.size() - 1; j++){
				if(playerList.get(j).GetAGI() > playerList.get(j+1).GetAGI()){
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
	private void Battle() throws InterruptedException
	{
		//残りのパーティーが1つでバトル終了
		while(battleParty.size() != 1){
			//ターン数の表示
			System.out.println("=====ターン"+turnCount+"=====");
			Thread.sleep(1000);
			
			//バトル途中のステータスを表示
			PrintPlayerBattleStatus();
			Thread.sleep(900);
			System.out.println("");
			
			//行動
			for(int i = battlePlayer.size() -1; 0 <= i; i--){
				Player attacker = battlePlayer.get(i);//攻撃するプレイヤー
				Party defenseParty;	//攻撃を受けるパーティー
				
				if(attacker.GetHP() > 0){
					//状態異常の確認
					attacker.AbnormalEffect(attacker);
					//戦闘不能判定
					attacker.DeathJudgeAbnormal(attacker.GetParty().Getmenbers());
					//状態異常での終了判定
					if(CheckEnd()){
						break;
					}
				}
				
				//行動不能ではなく、状態異常で倒れていない
				if(!attacker.GetInaction() && attacker.GetHP() > 0){
					//攻撃されるパーティー
					defenseParty = SelectDefenseParty(attacker);
					//作戦に沿って行動をする
					attacker.GetStrategy().Action(attacker, defenseParty);
				}
				
				Thread.sleep(gameTextSpeed);
				
				//行動後の終了判定
				if(CheckEnd()){
					break;
				}
			}
//			CheckDead();//HPがあるプレイヤーだけにする
			
			turnCount++;	//ターン経過
		}
	}
	
	/**
	 * バトル途中のステータス表示
	 */
	private void PrintPlayerBattleStatus(){
		for(Party party : battleParty){
			//パーティ名を表示
			System.out.println(party.GetName());
			for(Player player : party.Getmenbers()){
				//ステータス表示
				player.PrintBattleStatus();
			}
		}
	}
	
	/**
	 防衛側パーティーを返す 
	  @param attacker 攻撃プレイヤー
	  @return 防衛側パーティー
	 */
	private Party SelectDefenseParty(Player attacker){
		Party defenseParty;
		while(true){
			//パーティーをランダムで選ぶ
			defenseParty = battleParty.get( rand.nextInt(battleParty.size()) );
			//同じパーティーでなければ
			if(attacker.GetParty() != defenseParty){
				return defenseParty;
			}
		}
	}
	
	/**
	 *バトル終了判定 
	 * @return true:バトル終了, 
	 * 			  false:バトル継続
	 */
	private boolean CheckEnd(){
		CheckDead();
		//全滅判定
		CheckEmptyParty();
		//残りのパーティーが1グループの場合
		if(battleParty.size() == 1){
			System.out.println(battleParty.get(0).GetName() + "の勝利！！");

			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * HPがあるプレイヤーだけにする
	 */
	private void CheckDead(){
		Player player;
		for(int i = battlePlayer.size() -1; 0 <= i; i--){
			player = battlePlayer.get(i);
			if(player.GetHP() <= 0){
				battlePlayer.remove(player);
			}
		}
	}
	
	/**
	 * プレイヤーがいないパーティーを除く
	 */
	private void CheckEmptyParty(){
		Party party;
		for(int i = battleParty.size() -1; 0 <= i; i--){
			party = battleParty.get(i);
			if(party.Getmenbers().size() == 0){
				battleParty.remove(party);
			}
		}
	}
	
	/**
	 * 入力チェック
	 * @param min 入力できる最小値
	 * @param max 入力できる最大値
	 * @return 入力した値
	 */
	public int InputExceptionCheck(int min, int max){
		int key;
		while(true){
			try{
				//キーボード入力
				key = Integer.parseInt( scanStr.nextLine() );
				//最大値と最小値の範囲以外は再入力
				if(key < min || key > max){
					throw new Exception();
				}else{
					break;
				}
			}catch(Exception e){
				System.out.println("正しく入力されませんでした。");
			}
		}
		System.out.println();

		return key;
	}

	
}
