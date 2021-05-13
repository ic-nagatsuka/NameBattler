package com.namebattler.battle.strategy;

public class AllStrategy {

    public enum EStrategy {
        DAMAGE_PRIORITY("ダメージ優先"),
        LEAST_HP("体力少ない"),
        NO_SKILL("スキル使うな"),
        PLEASE_FREE("自由にやって"),
        LEAST_DEF("柔らか狙い"),
        PRIORITY_ABNORMAL_STATE_SKILL("状態異常優先"),
        ;

        private String name;

        EStrategy(String name) {
            this.name = name;
        }

        public String getName(){
            return this.name;
        }
    }

    public static BaseStrategy getStrategyInstance(EStrategy name) {
        switch (name) {
            case DAMAGE_PRIORITY:
                return new DamagePriority();
            case LEAST_HP:
                return new LeastHP();
            case NO_SKILL:
                return new NoSkill();
            case PLEASE_FREE:
                return new PleaseFree();
            case LEAST_DEF:
                return new LeastDef();
            default :
                return new AbnormalStateSkillPriority();
        }
    }
}
