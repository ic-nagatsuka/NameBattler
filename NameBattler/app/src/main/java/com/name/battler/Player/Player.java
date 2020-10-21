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
		MakeCharacter();
		//職業のスキル作成
		MakeSkill();
		//アイテムを渡す
		MakeItem();
		
		this.maxHp = this.GetHP();
		this.beforeHp = this.GetHP();
	}
	
	/**
	 * パラメーター生成
	 */
	abstract void MakeCharacter();
	
	/**
	 * スキル設定
	 * List<Skill>.add(AllSkill.name)
	 */
	abstract void MakeSkill();
	
	/**
	 * レベルアップ効果
	 */
	protected void UpLevelEffect(){
		
	}
	
	
	
	
	/*============
	 * Getメソッド 
	 ============*/
	public int GetLevel() {
		return this.level;
	}
	public Party GetParty(){
		return this.party;
	}
	public String GetPartyName(){
		return this.partyName;
	}
	public Job GetJob(){
		return this.job;
	}
	public String GetName(){
		return this.name;
	}
	public int GetHP(){ 
		return this.hp;
	}
	public int GetMP(){
		return this.mp;
	}
	public int GetSTR(){
		return this.str;
	}
	public int GetDEF(){
		return this.def;
	}
	public int GetLUCK(){
		return this.luck;
	}
	public int GetAGI() {
		return this.agi;
	}
	
	public int GetMaxHp(){
		return this.maxHp;
	}
	public int GetMaxMp(){
		return this.maxMp;
	}
	public int GetBeforeHP(){
		return this.beforeHp;
	}
	public Strategy GetStrategy(){
		return this.strategy;
	}
	
	public List<Item> GetUseItem(){
		return this.useItem;
	}
	
	public boolean GetInaction(){
		return this.inaction;
	}
	public boolean GetCounter(){
		return this.counter;
	}
	public List<Skill>GetUseSkill(){
		return this.useSkill;
	}
	
	/*============
	 *Setメソッド 
	 ============*/
	public void SetLevel (int level){
		this.level = level;
	}
	public void SetParty(Party party){
		this.party = party;
	}
	public void SetPartyName(String partyName){
		this.partyName = partyName;
	}
	public void SetStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	public void SetJob(Job job){
		this.job = job;
	}
	public void SetHP(int hp){ 
		this.hp = hp;
	}
	public void SetMP(int mp){
		this.mp = mp;
	}
	public void SetStr(int str){
		this.str = str;
	}
	
	public void SetInaction(boolean inaction){
		this.inaction = inaction;
	}
	
	public void SetAbnormalState(StateEffect skill){
		this.turnAbnormalState.add(skill);
	}
	
	public void SetCounter(boolean counter){
		this.counter = counter;
	}
	
	public void SetBeforHP(int beforeHp){
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
	public void NormalAttack(Player target){
		
		this.ReadyCounter(target);
//		System.out.println(attacker.GetName() + "の攻撃！");
		
		//通常のダメージ計算
		NormalDamage(target);
		//戦闘不能判定
		DeathJudge(target.GetParty().Getmenbers());
		//カウンター攻撃確認
		target.CheckCounter(this);
	}
	
	/**
	 * 通常攻撃ダメージを与える
	 * @param attacker 攻撃するプレイヤー
	 * @param target 攻撃されるプレイヤー
	 */
	protected void NormalDamage(Player target){
		int damage = CalcDamage(target);
		if(CheckLuckyHit(target)){
			damage = this.GetSTR();
		}
		
		if(damage == 0){
			System.out.println(this.GetName() + "はダメージを与えられなかった！\n");
		}else{
			System.out.println(target.GetName() + "に" + damage + "のダメージ！\n");
			target.Damage(damage);
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
	protected boolean CheckLuckyHit(Player target){
		int luckyHit = rand.nextInt(1000);
		//会心の一撃が出た場合
		if(this.GetLUCK() > luckyHit){
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
	private void UseItem(Player target){
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
	public void UseSkill(Skill skill, Player target){
		//回復スキルだった場合回復するプレイヤーを選択する
		if(skill.GetType() == AllSkill.HEEL){
			target = this.HeelTargetHP(this.GetParty().Getmenbers());
		}
		//スキルを使用する
		skill.Use(this, target);
		//戦闘不能判定
		DeathJudge(target.GetParty().Getmenbers());
		//カウンター攻撃
		target.CheckCounter(this);
		
		System.out.println();
	}
	
	/**
	 * スキルをランダムで選ぶ
	 * @param attacker 攻撃するプレイヤー
	 * @return　使用するスキル
	 */
	private Skill RandomSelectSkill(){
		Skill skill;
		while(true){
			//スキルをランダムで選ぶ
			skill = useSkill.get( rand.nextInt( useSkill.size()));
			//MPの確認
			if(skill.GetUseMP() <= this.GetMP()){
				//回復スキルが使えるか確認
				if(skill.GetType() == AllSkill.HEEL && !CheckDicreasePlayerHp(this.GetParty())){
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
	public boolean CheckUseSkill(){
		while(true){
			if(useSkill.size() == 0 ||//使うスキルがない
					SkillMinUseMp() > this.GetMP() ||//スキルを使うMPがない 
					//回復スキルはあるが使えない
						useSkill.size() == 1 && 
						useSkill.get(0).GetType() == AllSkill.HEEL &&
						!CheckDicreasePlayerHp(this.GetParty()))
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
	protected boolean CheckDicreasePlayerHp(Party party){
		heelSkill = false;
		for(Player player : party.Getmenbers()){
			//HPが減っている場合
			if(player.GetMaxHp() - player.GetHP() > 0){
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
	protected boolean CheckDicreasePlayerMP(Party party){
		for(Player player : party.Getmenbers())
		{
			//MPが減っている場合
			if((player.GetMaxMp() - player.GetMP()) > 0){
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
	protected Player HeelTargetHP(List<Player> party){
		double percent;
		double minPercent = party.get(0).GetHP() * party.get(0).GetMaxHp();//HPの割合
		
		Player target = party.get(0);
		
		//HPの割合が一番少ないプレイヤーにする
		for(Player player : party){
			percent=(double)player.GetHP() / (double)player.GetMaxHp() * 100;
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
	private Player HeelTargetMP(List<Player> party){
		double percent;	//MPの割合
		double minPercent = party.get(0).GetMP() * party.get(0).GetMaxMp();
		
		Player target = party.get(0);
		
		//MPの割合が一番少ないプレイヤーにする
		for(Player player : party){
			percent=(double)player.GetMP() / (double)player.GetMaxHp() * 100;
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
	protected void CheckCounter(Player target){
		//HPが減っていて、カウンターができる状態で、戦闘不能ではなく、相手が同じパーティーではない場合
		if(this.GetBeforeHP() != this.GetHP() && 
				this.GetCounter() && 
				this.GetHP() != 0 && this.GetParty() != target.GetParty()){
			//カウンター攻撃
			this.CounterAttack(target);
		}
	}
	
	/**
	 * カウンター攻撃
	 * @param attacker 攻撃するプレイヤー
	 * @param defender 攻撃されるプレイヤー
	 */
	protected void CounterAttack(Player target){
		System.out.println(this.GetName() + "は反撃した！！");
		
		NormalDamage(target);
	}
	
	/**
	 * 通常ダメージの計算
	 * @param target 攻撃されるプレイヤー
	 * @return 与えるダメージ
	 */
	public int CalcDamage(Player target)
	{
        int damage = GetSTR() - target.GetDEF();
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
	public void Damage(int damage){
        // ダメージ値分、HPを減少させる
        this.hp = Math.max(this.GetHP() - damage, 0);
    }
	
	 /**
	  * 死亡判定
	  * @param party 攻撃を受けたパーティー
	  */
	protected void DeathJudge(List<Player> party){
		for(int i = party.size() -1 ; 0 <= i; i--){
			Player player = party.get(i);
			//HPが０以下
			if(player.GetHP() <= 0){
				System.out.println(player.GetName() + "は力尽きた...\n");
				//パーティーから除く
				player.GetParty().RemovePlayer(player);
				
				this.SetLevel(this.GetLevel() + 1 );
				//レベルアップの処理==================================
			}
		}
	}
	
	/**
	 * 状態異常での戦闘不能
	 * @param party
	 */
	public void DeathJudgeAbnormal(List<Player> party){
		for(int i = party.size() -1 ; 0 <= i; i--){
			Player player = party.get(i);
			//HPが０以下
			if(player.GetHP() <= 0){
				System.out.println(player.GetName() + "は力尽きた...\n");
				//パーティーから除く
				player.GetParty().RemovePlayer(player);
			}
		}
	}
	
	/*
	 * public
	 */
	/**
	 * 行動選択
	 * @param attacker	攻撃するプレイヤー
	 * @param target	攻撃されるプレイヤー
	 */
	public void Action(Player target){
		this.ReadyCounter(target);
		
		if( (CheckDicreasePlayerHp(this.GetParty()) || CheckDicreasePlayerMP(this.GetParty()))
				&& this.GetUseItem().size() != 0){
			//アイテムを使う
			Item item;
			if(CheckDicreasePlayerHp(this.GetParty())){
				//HPが減っている場合
				while(true){
					item = useItem.get(rand.nextInt(useItem.size()));
					//アイテムを使用する
					if(item instanceof ItemHeel){
						this.UseItem(HeelTargetHP(this.GetParty().Getmenbers()));
						break;
					}
				}
			}else if(CheckDicreasePlayerMP(this.GetParty())){
				//MPが減っている場合
				while(true){
					item = useItem.get(rand.nextInt(useItem.size()));
					//アイテムを使用する
					if(item instanceof ItemHeel){
						this.UseItem(HeelTargetMP(this.GetParty().Getmenbers()));
						break;
					}
				}
			}
		}else if(CheckUseSkill()){
			//ランダムでスキルを使用する
			UseSkill(RandomSelectSkill(), target);
		}else{
			//通常攻撃
			NormalAttack(target);
		}	
	}
	
	/**
	 * 状態異常の効果
	 * @param attacker 攻撃するプレイヤー
	 */
	public void AbnormalEffect(Player attacker){
		//すべての状態異常を動かす
		for(int i = turnAbnormalState.size() -1; 0 <= i; i--){
			StateEffect abnormal = turnAbnormalState.get(i);
			//効果ターン経過
			abnormal.SetTurn(abnormal.GetTurn() - 1);
			//状態異常の効果
			abnormal.GetSkill().Effect(attacker, abnormal.GetTurn());
			//効果ターン経過すれば削除する
			if(abnormal.GetTurn() < 0){
				turnAbnormalState.remove(i);
			}
		}
	}
	
	/**
	 * 同じ状態効果があるか確認 
	 * @param skill 使用するスキル
	 * @return true : あり	false : なし
	 */
	public boolean CheckSameAbnormal(Skill skill){
		for(StateEffect abnormal : turnAbnormalState){
			if(abnormal.GetSkill().equals(skill)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ステータス表示
	 */
	public void PrintStatus()
	{
		System.out.printf("%s: %s (HP %3d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d)\n",
				job.GetName(), GetName(), GetHP(), GetMP(), GetSTR(), GetDEF(), GetLUCK(), GetAGI());
	}	
	
	/**
	 * バトル中のステータス表示
	 */
	public void PrintBattleStatus(){
		System.out.printf("%s %s HP %3d : MP %3d アイテム ",
				this.GetJob().GetName(), this.GetName(), this.GetHP(), this.GetMP(),
				this.useItem.size());
		PrintItem();
	}
	
	
	
	/**
	 * 
	 * @param index 参照する場所
	 * @param max 最大値
	 * @return 参照場所の数値
	 */
	protected int GetNumber(int index, int max) {
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
	private void ReadyCounter(Player target){
		for(Player player : target.GetParty().Getmenbers()){
			player.SetBeforHP(player.GetHP());
		}
	}
	
	/**
	 * スキルの最小消費MPの数値を返す
	 * @return スキルの最小消費MP
	 */
	private int SkillMinUseMp(){
		int minMp = useSkill.get(0).GetUseMP();
		for(Skill skill : useSkill){
			if(skill.GetUseMP() < minMp){
				minMp = skill.GetUseMP();
			}
		}
		return minMp;
	}
	
	/**
	 * 所持アイテムを表示
	 */
	private void PrintItem(){
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
	private void MakeItem(){
		for(int i = 0; i < itemNum; i++){
			this.useItem.add(
					allItem.GetItemList().get(rand.nextInt(allItem.GetItemList().size())));
		}
	}	
	
	
}

