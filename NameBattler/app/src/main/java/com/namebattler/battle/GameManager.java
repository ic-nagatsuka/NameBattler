package com.namebattler.battle;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.strategy.AllStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameManager {
    /*============
     *フィールド変数
     ============*/
    public static Party myParty = new Party("味方");    //自パーティー
    public static Party enemyParty = new Party("敵");    //敵パーティー

    //すべてのプレイヤーを入れる
    private List<Player> allPlayer = new ArrayList<>();

    //全てのパーティーを入れる
    private List<Party> allParty = new ArrayList<>();

    public static Party win;    //勝利パーティー
    private static int turnCount = 1;    //ターン数

    /*=============
     * コンストラクタ
     =============*/
    public GameManager() {
        prepare();
    }

    /**
     * 戦闘準備
     */
    public void prepare() {
        this.allParty.add(myParty);
        this.allParty.add(enemyParty);

        addAllPlayer();

        //素早さが高い順に並べる
        highSpeedSort(this.allPlayer);
    }

    /**
     * 全てのパーティーのキャラクターをまとめる
     */
    private void addAllPlayer() {
        for (Party party : this.allParty) {
            for (Player player : party.getAllMenbers()) {
                this.allPlayer.add(player);
            }
        }
    }


    /**
     * 素早さが大きい順に並べる
     *
     * @param playerList すべてのプレイヤー情報
     */
    private void highSpeedSort(List<Player> playerList) {
        for (int i = 0; i < playerList.size() - 1; i++) {
            for (int j = 0; j < playerList.size() - 1; j++) {
                Player player1 = playerList.get(j);
                Player player2 = playerList.get(j + 1);
                if (player1.getAgi() > player2.getAgi()) {
                    //場所を入れ替える
                    Player saveValue = player1;
                    playerList.set(j, player2);
                    playerList.set(j + 1, saveValue);
                }
            }
        }
    }

    /**
     * バトル
     */
    public void battle() {
        //ターン数の表示
        BattleLog.addLog("=====ターン" + turnCount + "=====");

        //行動
        for (Player attacker : this.allPlayer) {
            //状態異常の確認
            attacker.abnormalEffect();
            //行動不能ではなく、状態異常で倒れていない
            if (!attacker.getIsInaction() && !attacker.getIsDeath()) {
                //攻撃されるパーティー
                Party defenseParty = selectDefenseParty(attacker);
                //作戦に沿って行動をする
                AllStrategy.getStrategyInstance(
                        AllStrategy.EStrategy.values()[attacker.getParty().getStrategyKey()])
                        .action(attacker, defenseParty);
            }

            if (battleEnd()) {
                turnCount = 1;
                break;
            }
        }
        if (!battleEnd()) {
            turnCount++;    //ターン経過
        }

    }

    /**
     * バトル終了判定
     *
     * @return true 終了
     * false　継続
     */
    public boolean battleEnd() {
        boolean isEnd = true;
        for (Party party : this.allParty) {
            isEnd = true;

            for (Player player : party.getAllMenbers()) {
                if (player.getHp() != 0) {
                    win = party;
                    isEnd = false;
                }
            }
            if (isEnd == true) {
                return isEnd;
            }
        }

        return isEnd;
    }

    /**
     * 防衛側パーティーを返す
     *
     * @param attacker 攻撃プレイヤー
     * @return 防衛側パーティー
     */
    private Party selectDefenseParty(Player attacker) {
        Random rand = new Random();
        Party defenseParty;
        while (true) {
            //パーティーをランダムで選ぶ
            defenseParty = this.allParty.get(rand.nextInt(this.allParty.size()));
            //同じパーティーでなければ
            if (attacker.getParty() != defenseParty) {
                return defenseParty;
            }
        }
    }


}
