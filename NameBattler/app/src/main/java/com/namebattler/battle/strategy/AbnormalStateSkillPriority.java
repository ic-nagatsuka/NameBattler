package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;
import com.namebattler.battle.skill.AbnormalState;
import com.namebattler.battle.skill.SkillBase;

import java.util.ArrayList;
import java.util.List;

public class AbnormalStateSkillPriority extends BaseStrategy {
    /*=============
     * コンストラクタ
     =============*/
    public AbnormalStateSkillPriority() {
    }

    @Override
    public void action(Player attacker, Party defenceParty) {
        Player target = randomSelectDefender(defenceParty);
        ArrayList<SkillBase> prioritySkill =
                getPrioritySkillList(attacker.getNowUseSkillOnly());

        if (attacker.canSkill()) {
            SkillBase skill = attacker.randomSelectSkill(prioritySkill);
            if (prioritySkill.size() != 0) {
                skill = attacker.randomSelectSkill(
                        prioritySkill);
            }
            attacker.useSkill(skill, target);
        } else {
            attacker.normalAttack(target);
        }
    }

    /**
     * 優先スキルを探す
     *
     * @param useSkill プレイヤーの使用スキル
     */
    protected ArrayList<SkillBase> getPrioritySkillList(List<SkillBase> useSkill) {
        ArrayList<SkillBase> prioritySkill = new ArrayList<>();
        for (SkillBase skill : useSkill) {
            if (skill instanceof AbnormalState) {
                prioritySkill.add(skill);
            }
        }
        return prioritySkill;
    }

    @Override
    public void initStrategy() {
        super.strategy = AllStrategy.EStrategy.PRIORITY_ABNORMAL_STATE_SKILL;
    }
}
