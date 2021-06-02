package com.namebattler.battle.player;

import com.namebattler.battle.battlelog.BattleLog;
import com.namebattler.battle.skill.Fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Fighter extends Player {


    List<String> attText = new ArrayList<>();    //攻撃テキスト

    /*=============
     * コンストラクタ
     =============*/
    public Fighter(String name) {
        super(name);
        makeAttackText();
    }

    @Override
    protected void makeSkill() {
        setUseSkill(new Fire());
    }

    //攻撃テキスト
    protected void makeAttackText() {
        this.attText.add("パンチをした！");
        this.attText.add("キックをした！");
        this.attText.add("タックルをした！");
        this.attText.add("そこら辺の石を投げつけた!");
    }

    @Override
    public void normalAttack(Player target) {
        Random rand = new Random();
        BattleLog.addLog(this.name + "は" + this.attText.get(rand.nextInt(this.attText.size())));

        super.normalAttack(target);
    }

    @Override
    public void initJobData() {
        super.jobData = AllJob.JobData.FIGHTER;
    }
}
