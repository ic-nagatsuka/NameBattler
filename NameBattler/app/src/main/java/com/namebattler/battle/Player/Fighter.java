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
    public void makeCharacterStatus() {
        this.hp = getNumber(0, jobData.getMaxHp() - jobData.getMinHp()) + jobData.getMinHp();
        this.mp = getNumber(1, jobData.getMaxMp() - jobData.getMinMp()) + jobData.getMinMp();
        this.str = getNumber(2, jobData.getMaxStr() - jobData.getMinStr()) + jobData.getMinStr();
        this.def = getNumber(3, jobData.getMaxDef() - jobData.getMinDef()) + jobData.getMinDef();
        this.luck = getNumber(4, jobData.getMaxLuck() - jobData.getMinLuck()) + jobData.getMinLuck();
        this.agi = getNumber(5, jobData.getMaxAgi() - jobData.getMinAgi()) + jobData.getMinAgi();
    }

    @Override
    protected void makeSkill() {
        setUseSkill(new Fire());
    }

    //攻撃テキスト
    protected void makeAttackText() {
        attText.add("パンチをした！");
        attText.add("キックをした！");
        attText.add("タックルをした！");
        attText.add("そこら辺の石を投げつけた!");
    }

    @Override
    public void normalAttack(Player target) {
        Random rand = new Random();
        BattleLog.addLog(this.name + "は" + attText.get(rand.nextInt(attText.size())));

        super.normalAttack(target);
    }

    @Override
    public void initJobData() {
        jobData = AllJob.JobData.FIGHTER;
    }
}
