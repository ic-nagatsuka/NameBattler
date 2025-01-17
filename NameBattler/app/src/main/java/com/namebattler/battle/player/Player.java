package com.namebattler.battle.player;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.party.Party;
import com.namebattler.battle.skill.AbnormalState;
import com.namebattler.battle.skill.IHeal;
import com.namebattler.battle.skill.SkillBase;
import com.namebattler.battle.skill.StateEffect;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Player {

    // =====================
    // フィールド変数
    // =====================
    protected Party party;                        //パーティー
    protected String name;                        //名前
    protected int hp, mp, str, def, luck, agi;    //能力
    protected AllJob.JobData jobData;                      //職業情報
    protected int maxHp;                        //HPの最大値
    protected int maxMp;                        //MPの最大値
    protected int beforeHp;                        //行動前のHP
    protected boolean isDeath;                    //戦闘不能
    protected boolean isInaction;                //行動不能
    protected boolean canCounter;                    //カウンター使用

    //使用スキル
    protected List<SkillBase> useSkill = new ArrayList<>();
    //かかっている状態異常
    protected List<StateEffect> turnAbnormalState = new ArrayList<>();

    /*=============
     * コンストラクタ
     =============*/
    public Player(String name) {
        this.name = name;

        initJobData();
        // キャラクターのパラメータ生成
        makeCharacterStatus();
        //職業のスキル作成
        makeSkill();

        this.maxHp = this.getHp();
        this.maxMp = this.getMp();
        this.beforeHp = this.getHp();
        this.canCounter = this.jobData.getCanCounter();
    }

    /*====================================================================================
     * abstract
     * ====================================================================================*/


    /**
     * 使用するスキルを作成
     */
    protected abstract void makeSkill();

    protected abstract void initJobData();

    /*============
     * Getメソッド
     ============*/
    public Party getParty() {
        return this.party;
    }

    public String getJobName() {
        return this.jobData.getJobName();
    }

    public AllJob.JobData getJobData() {
        return this.jobData;
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMp() {
        return this.mp;
    }

    public int getStr() {
        return this.str;
    }

    public int getDef() {
        return this.def;
    }

    public int getLuck() {
        return this.luck;
    }

    public int getAgi() {
        return this.agi;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getMaxMp() {
        return this.maxMp;
    }

    public int getBeforeHP() {
        return this.beforeHp;
    }

    public boolean getIsDeath() {
        return this.isDeath;
    }

    public boolean getIsInaction() {
        return this.isInaction;
    }

    public boolean getCanCounter() {
        return this.canCounter;
    }

    public List<SkillBase> getUseSkill() {
        return this.useSkill;
    }

    /**
     * キャラクターステータス作成
     */
    public void makeCharacterStatus() {
        this.hp = getNumber(0, this.jobData.getMaxHp() - this.jobData.getMinHp()) + this.jobData.getMinHp();
        this.mp = getNumber(1, this.jobData.getMaxMp() - this.jobData.getMinMp()) + this.jobData.getMinMp();
        this.str = getNumber(2, this.jobData.getMaxStr() - this.jobData.getMinStr()) + this.jobData.getMinStr();
        this.def = getNumber(3, this.jobData.getMaxDef() - this.jobData.getMinDef()) + this.jobData.getMinDef();
        this.luck = getNumber(4, this.jobData.getMaxLuck() - this.jobData.getMinLuck()) + this.jobData.getMinLuck();
        this.agi = getNumber(5, this.jobData.getMaxAgi() - this.jobData.getMinAgi()) + this.jobData.getMinAgi();
    }

    /**
     * かかっているすべての状態異常の画面表示文字をまとめて返す
     *
     * @return かかっているすべての状態異常の画面表示文字
     */
    public String getAllAbnormalStateChar() {
        StringBuilder sb = new StringBuilder();
        for (StateEffect abnormalState : this.turnAbnormalState) {
            sb.append(abnormalState.getStateChar());
        }
        return sb.toString();
    }

    public SkillBase getRandomSkill() {
        ArrayList<SkillBase> canSkill = getNowUseSkillOnly();
        return canSkill.get(new Random().nextInt(canSkill.size()));
    }

    /**
     * 現在使用できるスキルを返す
     *
     * @return 現在使用できるスキル
     */
    public ArrayList<SkillBase> getNowUseSkillOnly() {
        ArrayList<SkillBase> useSkill = new ArrayList<>();
        for (SkillBase skill : this.getUseSkill()) {
            if (skill.getUseMp() <= this.getMp()) {
                if (skill instanceof IHeal && isDicreasePartyMenberHp(this.getParty())
                        || skill instanceof IHeal == false
                ) {
                    useSkill.add(skill);
                }
            }
        }
        return useSkill;
    }

    /*============
     *Setメソッド
     ============*/
    public void setParty(Party party) {
        this.party = party;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setIsDeath(boolean isDeath) {
        this.isDeath = isDeath;
    }

    public void setIsInaction(boolean inaction) {
        this.isInaction = inaction;
    }

    public void setAbnormalState(StateEffect skill) {
        this.turnAbnormalState.add(skill);
    }

    public void setCanCounter(boolean canCounter) {
        this.canCounter = canCounter;
    }

    public void setBeforHP(int beforeHp) {
        this.beforeHp = beforeHp;
    }

    protected void setUseSkill(SkillBase skill) {
        this.useSkill.add(skill);
    }


    /**
     * 通常攻撃の流れ
     *
     * @param target 攻撃されるプレイヤー
     */
    public void normalAttack(Player target) {
        this.readyCounter(target);

        //通常のダメージ計算
        normalDamage(target);
        //戦闘不能判定
        checkDeath(target.getParty());
//        //カウンター攻撃確認
//        target.canCounter(this);
    }

    /**
     * 通常攻撃ダメージを与える
     *
     * @param target 攻撃されるプレイヤー
     */
    protected void normalDamage(Player target) {
        int damage = calcDamage(target);
        if (isLuckyHit()) {
            damage = this.getStr();
        }

        if (damage == 0) {
            BattleLog.addLog(this.getName() + "はダメージを与えられなかった！");
        } else {
            BattleLog.addLog(target.getName() + "に" + damage + "のダメージ！");
            target.damage(damage);
        }
    }

    /**
     * 会心の一撃判定
     *
     * @return true 会心の一撃
     * false 会心の一撃ではない
     */
    protected boolean isLuckyHit() {
        Random rand = new Random();
        if (this.getLuck() > rand.nextInt(1000)) {
            BattleLog.addLog("会心の一撃！！");
            return true;
        }
        return false;
    }

    /**
     * スキルを使用する
     *
     * @param skill  使用スキル
     * @param target ターゲットプレイヤー
     */
    public void useSkill(SkillBase skill, Player target) {
        //スキルを使用する
        skill.use(this, target);
        //戦闘不能判定
        checkDeath(target.getParty());
//        //カウンター攻撃
//        target.canCounter(this);
    }

    /**
     * スキルをランダムで選ぶ
     *
     * @param useSkill 使用出来るスキル
     * @return 使用するスキル
     */
    public SkillBase randomSelectSkill(ArrayList<SkillBase> useSkill) {
        Random rand = new Random();
        return useSkill.get(rand.nextInt(useSkill.size()));
    }

    /**
     * スキルが使用可能か
     *
     * @return true:	使える
     * false:	使えない
     */
    public boolean canSkill() {
        return getNowUseSkillOnly().size() != 0;
    }

    /**
     * HPが減少しているパーティーメンバーを探す
     *
     * @param party 　調べるパーティー
     * @return true:	減少している
     * false: 	減少していない
     */
    public boolean isDicreasePartyMenberHp(Party party) {
        for (Player player : party.getAllMenbers()) {
            //HPが減っている場合
            if (player.getMaxHp() - player.getHp() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param target 行動攻撃をしたプレイヤー
     * @param attackerParty 攻撃を受けたパーティー
     */
    public void canCounter(Player target, Party attackerParty) {
        for(Player player : attackerParty.getAliveMenbers()){
            if (player.getBeforeHP() != player.getHp()
                    && player.getCanCounter()) {
                //カウンター攻撃
                player.counterAttack(target);
            }
        }
    }

    /**
     * カウンター攻撃
     *
     * @param target 　ターゲットプレイヤー
     */
    protected void counterAttack(Player target) {
        BattleLog.addLog(this.getName() + "は反撃した！！");
        normalDamage(target);
    }

    /**
     * 通常攻撃ダメージの計算
     *
     * @param target ターゲットプレイヤー
     * @return 与えるダメージ
     */
    public int calcDamage(Player target) {
        int damage = getStr() - target.getDef();
        if (damage < 0) {
            damage = 0;
        }
        return damage;
    }

    /**
     * ダメージを与える
     *
     * @param damage 与えるダメージ
     */
    public void damage(int damage) {
        // ダメージ値分、HPを減少させる
        this.hp = Math.max(this.getHp() - damage, 0);
    }

    /**
     * 死亡判定
     *
     * @param party パーティー
     */
    public void checkDeath(Party party) {
        for (Player player : party.getAliveMenbers()) {
            if (player.getHp() <= 0) {
                BattleLog.addLog(player.getName() + "は力尽きた...\n");
                player.setIsDeath(true);
            }
        }
    }

    /**
     * 行動の流れ
     *
     * @param target 攻撃されるプレイヤー
     */
//    public void action(Player target) {
//        this.readyCounter(target);
//        if (isDicreasePartyMenberHp(this.getParty())) {
//            if (canSkill()) {
//                //ランダムでスキルを使用する
//                useSkill(randomSelectSkill(this.getNowUseSkillOnly()), target);
//            } else {
//                //通常攻撃
//                normalAttack(target);
//            }
//        }
//    }

    /**
     * 状態異常効果を動かす
     */
    public void abnormalEffect() {
        for (int i = this.turnAbnormalState.size() - 1; 0 <= i; i--) {
            StateEffect abnormal = this.turnAbnormalState.get(i);
            //効果ターン経過
            abnormal.setTurn(abnormal.getTurn() - 1);
            //状態異常の効果
            abnormal.getSkill().effect(this, abnormal.getTurn());
            //効果ターン経過すれば削除する
            if (abnormal.getTurn() < 0) {
                this.turnAbnormalState.remove(i);
            }
            checkDeath(this.getParty());
            if (this.getIsDeath()) {
                break;
            }
        }
    }

    /**
     * 同じ状態異常スキルを探す
     *
     * @param skill 比較するスキル
     * @return true : あり	false : なし
     */
    public boolean haveSameAbnormal(AbnormalState skill) {
        for (StateEffect abnormal : this.turnAbnormalState) {
            if (abnormal.getSkill().getClass().equals(skill.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * ステータス文字列をまとめて返す
     *
     * @return ステータスをまとめてた文字列
     */
    public String getStatus() {
        return "HP:" + getHp() +
                " MP:" + getMp() +
                " STR:" + getStr() +
                " DEF:" + getDef() +
                " LUCK:" + getLuck() +
                " AGI:" + getAgi();
    }

    /**
     * 能力値を返す
     *
     * @param index 参照する場所
     * @param max   最大値
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


    /**
     * HPを上書きする
     *
     * @param target 　プレイヤー
     */
    private void readyCounter(Player target) {
        for (Player player : target.getParty().getAllMenbers()) {
            player.setBeforHP(player.getHp());
        }
    }

}