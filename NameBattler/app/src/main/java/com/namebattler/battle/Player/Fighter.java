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
        this.hp = getNumber(0, super.jobData.getMaxHp() - super.jobData.getMinHp()) + super.jobData.getMinHp();
        this.mp = getNumber(1, super.jobData.getMaxMp() - super.jobData.getMinMp()) + super.jobData.getMinMp();
        this.str = getNumber(2, super.jobData.getMaxStr() - super.jobData.getMinStr()) + super.jobData.getMinStr();
        this.def = getNumber(3, super.jobData.getMaxDef() - super.jobData.getMinDef()) + super.jobData.getMinDef();
        this.luck = getNumber(4, super.jobData.getMaxLuck() - super.jobData.getMinLuck()) + super.jobData.getMinLuck();
        this.agi = getNumber(5, super.jobData.getMaxAgi() - super.jobData.getMinAgi()) + super.jobData.getMinAgi();
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
