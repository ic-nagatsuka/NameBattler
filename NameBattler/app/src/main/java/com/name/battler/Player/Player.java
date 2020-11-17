package com.name.battler.Player;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.name.battler.Item.AllItem;
import com.name.battler.Item.Item;
import com.name.battler.Item.ItemHeel;
import com.name.battler.Skill.AllSkill;
import com.name.battler.Skill.Skill;
import com.name.battler.Skill.SkillOfEffectTurn.StateEffect;
import com.name.battler.Strategy.Strategy;

public abstract class Player{
	
	private int itemNum = 3;
	
	// =====================
	// フィールド変数
	// =====================
	protected int level = 1;					//レベル
	

	protected Party party;						//パーティー
	protected String partyName;					//パーティー名
	protected Strategy strategy;				//作戦
	protected String name;						//名前
	protected Job job;							//職業
	protected int hp, mp, str, def, luck, agi;	//能力
	protected int maxHp;						//HPの最大値
	protected int maxMp;						//MPの最大値
	protected int beforeHp;						//行動前のHP
	protected boolean inaction ;				//行動不能
	protected boolean heelSkill;				//回復スキルの使用
	protected boolean counter;					//カウンター使用
	
	AllItem allItem = new AllItem();;
	
	//使用スキル
	protected List <Skill> useSkill = new ArrayList <>();
	//消費アイテム
	protected List<Item> useItem = new ArrayList<>();
	//かかっている状態異常
	public List <StateEffect> turnAbnormalState = new ArrayList <>();
	protected List <StateEffect> upState = new ArrayList <>();

	
	
	Random rand = new Random();
	
	/*=============
	 * コンストラクタ
	 =============*/
	public Player(String name) {
		this.name = name;

		// キャラクターのパラメータ生成
		makeCharacter();
		//職業のスキル作成
		makeSkill();
		//アイテムを渡す
		makeItem();
		
		this.maxHp = this.getHP();
		this.beforeHp = this.getHP();
	}
	
	/**
	 * パラメーター生成
	 */
	abstract void makeCharacter();
	
	/**
	 * スキル設定
	 * List<Skill>.add(AllSkill.name)
	 */
	abstract void makeSkill();
	
	/*============
	 * Getメソッド 
	 ============*/
	public int getLevel() {
		return this.level;
	}
	public Party getParty(){
		return this.party;
	}
	public String getPartyName(){
		return this.partyName;
	}
	public Job getJob(){
		return this.job;
	}
	public String getName(){
		return this.name;
	}
	public int getHP(){
		return this.hp;
	}
	public int getMP(){
		return this.mp;
	}
	public int getSTR(){
		return this.str;
	}
	public int getDEF(){
		return this.def;
	}
	public int getLUCK(){
		return this.luck;
	}
	public int getAGI() {
		return this.agi;
	}
	
	public int getMaxHp(){
		return this.maxHp;
	}
	public int getMaxMp(){
		return this.maxMp;
	}
	public int getBeforeHP(){
		return this.beforeHp;
	}
	public Strategy getStrategy(){
		return this.strategy;
	}
	
	public List<Item> getUseItem(){
		return this.useItem;
	}
	
	public boolean getInaction(){
		return this.inaction;
	}
	public boolean getCounter(){
		return this.counter;
	}
	public List<Skill> getUseSkill(){
		return this.useSkill;
	}
	
	/*============
	 *Setメソッド 
	 ============*/
	public void setLevel(int level){
		this.level = level;
	}
	public void setParty(Party party){
		this.party = party;
	}
	public void setPartyName(String partyName){
		this.partyName = partyName;
	}
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	public void setJob(Job job){
		this.job = job;
	}
	public void setHP(int hp){
		this.hp = hp;
	}
	public void setMP(int mp){
		this.mp = mp;
	}
	public void setStr(int str){
		this.str = str;
	}
	
	public void setInaction(boolean inaction){
		this.inaction = inaction;
	}
	
	public void setAbnormalState(StateEffect skill){
		this.turnAbnormalState.add(skill);
	}
	
	public void setCounter(boolean counter){
		this.counter = counter;
	}
	
	public void setBeforHP(int beforeHp){
		this.beforeHp = beforeHp;
	}
	
	/*
	 * protected
	 */
	/**
	 *通常攻撃の流れ
	 * @param attacker 行動プレイヤー
	 * @param target 対象プレイヤー
	 */
	public void normalAttack(Player target){
		
		this.readyCounter(target);
//		System.out.println(attacker.GetName() + "の攻撃！");
		
		//通常のダメージ計算
		normalDamage(target);
		//戦闘不能判定
		deathJudge(target.getParty().getmenbers());
		//カウンター攻撃確認
		target.checkCounter(this);
	}
	
	/**
	 * 通常攻撃ダメージを与える
	 * @param attacker 攻撃するプレイヤー
	 * @param target 攻撃されるプレイヤー
	 */
	protected void normalDamage(Player target){
		int damage = calcDamage(target);
		if(checkLuckyHit(target)){
			damage = this.getSTR();
		}
		
		if(damage == 0){
			System.out.println(this.getName() + "はダメージを与えられなかった！\n");
		}else{
			System.out.println(target.getName() + "に" + damage + "のダメージ！\n");
			target.damage(damage);
		}
	}
	
	/**
	 * 会心の一撃の判定
	 * @param attacker 攻撃するプレイヤー
	 * @param target 攻撃されるプレイヤー
	 * @return 
	 * true:	会心の一撃
	 * false: 	通常ダメージ
	 */
	protected boolean checkLuckyHit(Player target){
		int luckyHit = rand.nextInt(1000);
		//会心の一撃が出た場合
		if(this.getLUCK() > luckyHit){
			System.out.println("会心の一撃！！");
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * アイテムを使用する
	 * @param attacker	使用するプレイヤー 
	 * @param target 	対象プレイヤー
	 */
	private void useItem(Player target){
		Item randItem = useItem.get(rand.nextInt(useItem.size()));
		randItem.Use(this, target);
		useItem.remove(randItem);
	}
	
	/**
	 * スキルを使用する
	 * @param skill 使用スキル
	 * @param attacker 攻撃するプレイヤー
	 * @param target 攻撃されるプレイヤー
	 */
	public void useSkill(Skill skill, Player target){
		//回復スキルだった場合回復するプレイヤーを選択する
		if(skill.getType() == AllSkill.HEEL){
			target = this.heelTargetHP(this.getParty().getmenbers());
		}
		//スキルを使用する
		skill.use(this, target);
		//戦闘不能判定
		deathJudge(target.getParty().getmenbers());
		//カウンター攻撃
		target.checkCounter(this);
		
		System.out.println();
	}
	
	/**
	 * スキルをランダムで選ぶ
	 * @param attacker 攻撃するプレイヤー
	 * @return　使用するスキル
	 */
	private Skill randomSelectSkill(){
		Skill skill;
		while(true){
			//スキルをランダムで選ぶ
			skill = useSkill.get( rand.nextInt( useSkill.size()));
			//MPの確認
			if(skill.getUseMP() <= this.getMP()){
				//回復スキルが使えるか確認
				if(skill.getType() == AllSkill.HEEL && !checkDicreasePlayerHp(this.getParty())){
					continue;
				}
				return skill;
			}
		}
	}
	
	
	/**
	 * スキルの使用可能を確認	
	 * @return
	 * true:	使える
	 * false:	使えない
	 */
	public boolean checkUseSkill(){
		while(true){
			if(useSkill.size() == 0 ||//使うスキルがない
					skillMinUseMp() > this.getMP() ||//スキルを使うMPがない
					//回復スキルはあるが使えない
						useSkill.size() == 1 && 
						useSkill.get(0).getType() == AllSkill.HEEL &&
						!checkDicreasePlayerHp(this.getParty()))
			{
				return false;
			}else{
				return true;
			}
		}
	}
	
	
	/**
	 * HPが減少しているパーティーメンバーを探す
	 * @param party　攻撃側パーティー
	 * @return
	 * true:	減少している
	 * false: 	減少していない
	 */
	protected boolean checkDicreasePlayerHp(Party party){
		heelSkill = false;
		for(Player player : party.getmenbers()){
			//HPが減っている場合
			if(player.getMaxHp() - player.getHP() > 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * MPが減少しているパーティーメンバーを探す
	 * @param party　攻撃側パーティー
	 * @return
	 * true:	減少している
	 * false: 	減少していない
	 */
	protected boolean checkDicreasePlayerMP(Party party){
		for(Player player : party.getmenbers())
		{
			//MPが減っている場合
			if((player.getMaxMp() - player.getMP()) > 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * HPの割合が一番少ないプレイヤーを選ぶ
	 * @param party 攻撃側パーティー
	 * @return 回復されるプレイヤー
	 */
	protected Player heelTargetHP(List<Player> party){
		double percent;
		double minPercent = party.get(0).getHP() * party.get(0).getMaxHp();//HPの割合
		
		Player target = party.get(0);
		
		//HPの割合が一番少ないプレイヤーにする
		for(Player player : party){
			percent=(double)player.getHP() / (double)player.getMaxHp() * 100;
			if(percent < minPercent){
				target = player;
				minPercent=percent;
			}
		}
			
		return target;
	}
	
	/**
	 * MPの割合が一番少ないプレイヤーを選ぶ
	 * @param party 攻撃側パーティー
	 * @return 回復されるプレイヤー
	 */
	private Player heelTargetMP(List<Player> party){
		double percent;	//MPの割合
		double minPercent = party.get(0).getMP() * party.get(0).getMaxMp();
		
		Player target = party.get(0);
		
		//MPの割合が一番少ないプレイヤーにする
		for(Player player : party){
			percent=(double)player.getMP() / (double)player.getMaxHp() * 100;
			if(percent < minPercent){
				target = player;
				minPercent=percent;
			}
		}
		return target;
	}
	
	/**
	 * カウンター攻撃の確認
	 * @param attacker 攻撃されたプレイヤー
	 * @param target 攻撃したプレイヤー
	 */
	protected void checkCounter(Player target){
		//HPが減っていて、カウンターができる状態で、戦闘不能ではなく、相手が同じパーティーではない場合
		if(this.getBeforeHP() != this.getHP() &&
				this.getCounter() &&
				this.getHP() != 0 && this.getParty() != target.getParty()){
			//カウンター攻撃
			this.counterAttack(target);
		}
	}
	
	/**
	 * カウンター攻撃
	 * @param attacker 攻撃するプレイヤー
	 * @param defender 攻撃されるプレイヤー
	 */
	protected void counterAttack(Player target){
		System.out.println(this.getName() + "は反撃した！！");
		
		normalDamage(target);
	}
	
	/**
	 * 通常ダメージの計算
	 * @param target 攻撃されるプレイヤー
	 * @return 与えるダメージ
	 */
	public int calcDamage(Player target)
	{
        int damage = getSTR() - target.getDEF();
        if (damage < 0)
        {
            damage = 0;
        }
        return damage;
    }
	    
	/**
	 * ダメージを与える
	 * @param damage 与えるダメージ
	 */
	public void damage(int damage){
        // ダメージ値分、HPを減少させる
        this.hp = Math.max(this.getHP() - damage, 0);
    }
	
	 /**
	  * 死亡判定
	  * @param party 攻撃を受けたパーティー
	  */
	protected void deathJudge(List<Player> party){
		for(int i = party.size() -1 ; 0 <= i; i--){
			Player player = party.get(i);
			//HPが０以下
			if(player.getHP() <= 0){
				System.out.println(player.getName() + "は力尽きた...\n");
				//パーティーから除く
				player.getParty().removePlayer(player);
				
				this.setLevel(this.getLevel() + 1 );
				//レベルアップの処理==================================
			}
		}
	}
	
	/**
	 * 状態異常での戦闘不能
	 * @param party
	 */
	public void deathJudgeAbnormal(List<Player> party){
		for(int i = party.size() -1 ; 0 <= i; i--){
			Player player = party.get(i);
			//HPが０以下
			if(player.getHP() <= 0){
				System.out.println(player.getName() + "は力尽きた...\n");
				//パーティーから除く
				player.getParty().removePlayer(player);
			}
		}
	}
	
	/*
	 * public
	 */
	/**
	 * 行動選択
	 * @param target	攻撃されるプレイヤー
	 */
	public void action(Player target){
		this.readyCounter(target);
		
		if( (checkDicreasePlayerHp(this.getParty()) || checkDicreasePlayerMP(this.getParty()))
				&& this.getUseItem().size() != 0){
			//アイテムを使う
			Item item;
			if(checkDicreasePlayerHp(this.getParty())){
				//HPが減っている場合
				while(true){
					item = useItem.get(rand.nextInt(useItem.size()));
					//アイテムを使用する
					if(item instanceof ItemHeel){
						this.useItem(heelTargetHP(this.getParty().getmenbers()));
						break;
					}
				}
			}else if(checkDicreasePlayerMP(this.getParty())){
				//MPが減っている場合
				while(true){
					item = useItem.get(rand.nextInt(useItem.size()));
					//アイテムを使用する
					if(item instanceof ItemHeel){
						this.useItem(heelTargetMP(this.getParty().getmenbers()));
						break;
					}
				}
			}
		}else if(checkUseSkill()){
			//ランダムでスキルを使用する
			useSkill(randomSelectSkill(), target);
		}else{
			//通常攻撃
			normalAttack(target);
		}	
	}
	
	/**
	 * 状態異常の効果
	 * @param attacker 攻撃するプレイヤー
	 */
	public void abnormalEffect(Player attacker){
		//すべての状態異常を動かす
		for(int i = turnAbnormalState.size() -1; 0 <= i; i--){
			StateEffect abnormal = turnAbnormalState.get(i);
			//効果ターン経過
			abnormal.setTurn(abnormal.getTurn() - 1);
			//状態異常の効果
			abnormal.getSkill().effect(attacker, abnormal.getTurn());
			//効果ターン経過すれば削除する
			if(abnormal.getTurn() < 0){
				turnAbnormalState.remove(i);
			}
		}
	}
	
	/**
	 * 同じ状態効果があるか確認 
	 * @param skill 使用するスキル
	 * @return true : あり	false : なし
	 */
	public boolean checkSameAbnormal(Skill skill){
		for(StateEffect abnormal : turnAbnormalState){
			if(abnormal.getSkill().equals(skill)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ステータス表示
	 */
	public void printStatus()
	{
		System.out.printf("%s: %s (HP %3d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d)\n",
				job.getName(), getName(), getHP(), getMP(), getSTR(), getDEF(), getLUCK(), getAGI());
	}

	public String getstatus()	{
		return "HP:" + getHP() +
				" MP:" + getMP() +
				" STR:" + getSTR() +
				" DEF:" + getDEF() +
				" LUCK:" + getLUCK() +
				" AGI:" + getAGI();
	}

	/**
	 * バトル中のステータス表示
	 */
	public void printBattleStatus(){
		System.out.printf("%s %s HP %3d : MP %3d アイテム ",
				this.getJob().getName(), this.getName(), this.getHP(), this.getMP(),
				this.useItem.size());
		printItem();
	}
	
	
	
	/**
	 * 
	 * @param index 参照する場所
	 * @param max 最大値
	 * @return 参照場所の数値
	 */
	protected int getNumber(int index, int max) {
		try {
			// 名前からハッシュ値を生成する
			byte[] result = MessageDigest.getInstance("SHA-1").digest(this.name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));

			// ハッシュ値から指定された位置の文字列を取り出す（２文字分）
			String hex = digest.substring(index * 2, index * 2 + 2);

			// 取り出した文字列（16進数）を数値に変換する
			int val = Integer.parseInt(hex, 16);
			return val * max / 255;
		} catch (Exception e) {
			// エラー
			e.printStackTrace();
		}
		return 0;
	}
	/*===========
	 * private
	 ===========*/
	/**
	 * HPを上書きする
	 * @param target
	 */
	private void readyCounter(Player target){
		for(Player player : target.getParty().getmenbers()){
			player.setBeforHP(player.getHP());
		}
	}
	
	/**
	 * スキルの最小消費MPの数値を返す
	 * @return スキルの最小消費MP
	 */
	private int skillMinUseMp(){
		int minMp = useSkill.get(0).getUseMP();
		for(Skill skill : useSkill){
			if(skill.getUseMP() < minMp){
				minMp = skill.getUseMP();
			}
		}
		return minMp;
	}
	
	/**
	 * 所持アイテムを表示
	 */
	private void printItem(){
		System.out.print("[");
		if(this.useItem.size() == 0){
			System.out.print("なし");
		}
		
		for(int i = 0; i < useItem.size(); i++){
			String name = useItem.get(i).GetName();
			if(i != useItem.size() -1){
				System.out.print(name + ", ");
			}else{
				System.out.print(name);
			}
		}
		System.out.print("]\n");
	}
	
	/**
	 * アイテムをセットする
	 */
	private void makeItem(){
		for(int i = 0; i < itemNum; i++){
			this.useItem.add(
					allItem.GetItemList().get(rand.nextInt(allItem.GetItemList().size())));
		}
	}	
	
	
}

