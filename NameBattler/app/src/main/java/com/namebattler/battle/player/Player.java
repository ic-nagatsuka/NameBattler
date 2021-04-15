package com.namebattler.battle.player;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.party.Party;
import com.namebattler.battle.skill.AbnormalState;
import com.namebattler.battle.skill.AllSkill;
import com.namebattler.battle.skill.IHeal;
import com.namebattler.battle.skill.SkillBase;
import com.namebattler.battle.skill.StateEffect;
import com.namebattler.battle.strategy.Strategy;

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
    protected String partyName;                    //パーティー名
    protected Strategy strategy;                //作戦
    protected String name;                        //名前
    protected String job;                            //職業
    protected int hp, mp, str, def, luck, agi;    //能力
    protected int maxHp;                        //HPの最大値
    protected int maxMp;                        //MPの最大値
    protected int beforeHp;                        //行動前のHP
    protected boolean isDeath;                    //戦闘不能
    protected boolean inaction;                //行動不能
    protected boolean heelSkill;                //回復スキルの使用
    protected boolean counter;                    //カウンター使用

    //使用スキル
    protected List<SkillBase> useSkill = new ArrayList<>();
    //かかっている状態異常
    public List<StateEffect> turnAbnormalState = new ArrayList<>();


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

        this.maxHp = this.getHP();
        this.maxMp = this.getMP();
        this.beforeHp = this.getHP();
    }

    /*====================================================================================
     * abstract
     * ====================================================================================*/
    /**
     * キャラクターステータス作成
     */
    protected abstract void makeCharacter();

    /**
     * 使用するスキルを作成
     */
    protected abstract void makeSkill();

    /*============
     * Getメソッド
     ============*/
    public Party getParty() {
        return this.party;
    }

    public String getPartyName() {
        return this.partyName;
    }

    public String getJob() {
        return this.job;
    }

    public String getName() {
        return this.name;
    }

    public int getHP() {
        return this.hp;
    }

    public int getMP() {
        return this.mp;
    }

    public int getSTR() {
        return this.str;
    }

    public int getDEF() {
        return this.def;
    }

    public int getLUCK() {
        return this.luck;
    }

    public int getAGI() {
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

    public Strategy getStrategy() {
        return this.strategy;
    }

    public boolean getIsDeath() {
        return this.isDeath;
    }

    public boolean getInaction() {
        return this.inaction;
    }

    public boolean getCounter() {
        return this.counter;
    }

    public List<SkillBase> getUseSkill() {
        return this.useSkill;
    }

    public String getAllAbnormalStateChar() {
        String str = "";
        for (StateEffect abnormalState : turnAbnormalState) {
            str += abnormalState.getStateChar();
        }
        return str;
    }

    /*============
     *Setメソッド
     ============*/
    public void setParty(Party party) {
        this.party = party;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void setMP(int mp) {
        this.mp = mp;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setIsDeath(boolean isDeath) {
        this.isDeath = isDeath;
    }

    public void setInaction(boolean inaction) {
        this.inaction = inaction;
    }

    public void setAbnormalState(StateEffect skill) {
        this.turnAbnormalState.add(skill);
    }

    public void setCounter(boolean counter) {
        this.counter = counter;
    }

    public void setBeforHP(int beforeHp) {
        this.beforeHp = beforeHp;
    }

    protected void setUseSkill(SkillBase skill) {
        this.useSkill.add(skill);
    }


    /**
     * 通常攻撃の流れ
     * @param target 攻撃されるプレイヤー
     */
    public void normalAttack(Player target) {
        this.readyCounter(target);

        //通常のダメージ計算
        normalDamage(target);
        //戦闘不能判定
        deathJudge(target.getParty().getmenbers());
        //カウンター攻撃確認
        target.checkCounter(this);
    }

    /**
     * 通常攻撃ダメージを与える
     * @param target 攻撃されるプレイヤー
     */
    protected void normalDamage(Player target) {
        int damage = calcDamage(target);
        if (isLuckyHit()) {
            damage = this.getSTR();
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
     * @return true 会心の一撃
     *          false 会心の一撃ではない
     */
    protected boolean isLuckyHit() {
        if (this.getLUCK() > rand.nextInt(1000)) {
            BattleLog.addLog("会心の一撃！！");
            return true;
        }
        return false;
    }

    /**
     * スキルを使用する
     * @param skill  使用スキル
     * @param target ターゲットプレイヤー
     */
    public void useSkill(SkillBase skill, Player target) {
        //スキルを使用する
        skill.use(this, target);
        //戦闘不能判定
        deathJudge(target.getParty().getmenbers());
        //カウンター攻撃
        target.checkCounter(this);
    }

    /**
     * スキルをランダムで選ぶ
     * @param useSkill 使用出来るスキル
     * @return 使用するスキル
     */
    public SkillBase randomSelectSkill(ArrayList<SkillBase> useSkill) {
        SkillBase skill;
        while (true) {
            //スキルをランダムで選ぶ
            skill = useSkill.get(rand.nextInt(useSkill.size()));
            //MPの確認
            if (skill.getUseMp() <= this.getMP()) {
                return skill;
            }
        }
    }

    /**
     * スキルが使用可能か
     * @return true:	使える
     * false:	使えない
     */
    public boolean checkUseSkill() {
        if (getUseSkillOnly().size() == 0) {
            return false;
        } else {
            return true;
        }
    }


    public ArrayList<SkillBase> getUseSkillOnly() {
        ArrayList<SkillBase> useSkill = new ArrayList<>();
        for (SkillBase skill : this.getUseSkill()) {
            if (skill.getUseMp() <= this.getMP()) {
                if (skill instanceof IHeal && checkDicreasePlayerHp(this.getParty())
                        || skill instanceof IHeal == false
                ) {
                    useSkill.add(skill);
                }
            }
        }
        return useSkill;
    }


    /**
     * HPが減少しているパーティーメンバーを探す
     * @param party 　調べるパーティー
     * @return true:	減少している
     * false: 	減少していない
     */
    public boolean checkDicreasePlayerHp(Party party) {
        heelSkill = false;
        for (Player player : party.getmenbers()) {
            //HPが減っている場合
            if (player.getMaxHp() - player.getHP() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * HPの割合が一番少ないプレイヤーを選ぶ
     * @param party 調べるパーティー
     * @return 回復されるプレイヤー
     */
    protected Player heelTargetHP(List<Player> party) {
        double percent;
        double minPercent = party.get(0).getHP() * party.get(0).getMaxHp();//HPの割合

        Player target = party.get(0);
        //HPの割合が一番少ないプレイヤーにする
        for (Player player : party) {
            percent = (double) player.getHP() / (double) player.getMaxHp() * 100;
            if (percent < minPercent) {
                target = player;
                minPercent = percent;
            }
        }
        return target;
    }

    /**
     * カウンター攻撃の確認
     * @param target 攻撃したプレイヤー
     */
    protected void checkCounter(Player target) {
        //HPが減っていて、カウンターができる状態で、戦闘不能ではなく、相手が同じパーティーではない場合
        if (this.getBeforeHP() != this.getHP() &&
                this.getCounter() &&
                this.getHP() != 0 && this.getParty() != target.getParty()) {
            //カウンター攻撃
            this.counterAttack(target);
        }
    }

    /**
     * カウンター攻撃
     * @param target　ターゲットプレイヤー
     */
    protected void counterAttack(Player target) {
        BattleLog.addLog(this.getName() + "は反撃した！！");
        normalDamage(target);
    }

    /**
     * 通常ダメージの計算
     * @param target 攻撃されるプレイヤー
     * @return 与えるダメージ
     */
    public int calcDamage(Player target) {
        int damage = getSTR() - target.getDEF();
        if (damage < 0) {
            damage = 0;
        }
        return damage;
    }

    /**
     * ダメージを与える
     * @param damage 与えるダメージ
     */
    public void damage(int damage) {
        // ダメージ値分、HPを減少させる
        this.hp = Math.max(this.getHP() - damage, 0);
    }

    /**
     * 死亡判定
     * @param party 攻撃を受けたパーティー
     */
    public void deathJudge(List<Player> party) {
        for (int i = party.size() - 1; 0 <= i; i--) {
            Player player = party.get(i);
            //HPが０以下
            if (player.getHP() <= 0 && !player.getIsDeath()) {
                BattleLog.addLog(player.getName() + "は力尽きた...\n");
                player.setIsDeath(true);
            }
        }
    }

    /**
     * 行動選択
     * @param target 攻撃されるプレイヤー
     */
    public void action(Player target) {
        this.readyCounter(target);
        if (checkDicreasePlayerHp(this.getParty())) {
            if (checkUseSkill()) {
                //ランダムでスキルを使用する
                useSkill(randomSelectSkill(this.getUseSkillOnly()), target);
            } else {
                //通常攻撃
                normalAttack(target);
            }
        }
    }

    /**
     * 状態異常の効果
     * @param attacker 攻撃するプレイヤー
     */
    public void abnormalEffect(Player attacker) {
        //すべての状態異常を動かす
        for (int i = turnAbnormalState.size() - 1; 0 <= i; i--) {
            StateEffect abnormal = turnAbnormalState.get(i);
            //効果ターン経過
            abnormal.setTurn(abnormal.getTurn() - 1);
            //状態異常の効果
            abnormal.getSkill().effect(attacker, abnormal.getTurn());
            //効果ターン経過すれば削除する
            if (abnormal.getTurn() < 0) {
                turnAbnormalState.remove(i);
            }
            attacker.deathJudge(attacker.getParty().getmenbers());
            if (attacker.getHP() == 0) {
                break;
            }
        }
    }

    /**
     * 同じ状態効果があるか確認
     * @param skill 使用するスキル
     * @return true : あり	false : なし
     */
    public boolean checkSameAbnormal(AbnormalState skill) {
        for (StateEffect abnormal : turnAbnormalState) {
            if (abnormal.getSkill().getClass().equals(skill.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * ステータス文字列をまとめて返す
     * @return ステータスをまとめてた文字列
     */
    public String getstatus() {
        return "HP:" + getHP() +
                " MP:" + getMP() +
                " STR:" + getSTR() +
                " DEF:" + getDEF() +
                " LUCK:" + getLUCK() +
                " AGI:" + getAGI();
    }

    /**
     * 能力値を返す
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
     * @param target　プレイヤー
     */
    private void readyCounter(Player target) {
        for (Player player : target.getParty().getmenbers()) {
            player.setBeforHP(player.getHP());
        }
    }

    /**
     * スキルの最小消費MPの数値を返す
     * @return スキルの最小消費MP
     */
    private int skillMinUseMp() {
        int minMp = useSkill.get(0).getUseMp();
        for (SkillBase skill : useSkill) {
            if (skill.getUseMp() < minMp) {
                minMp = skill.getUseMp();
            }
        }
        return minMp;
    }

}