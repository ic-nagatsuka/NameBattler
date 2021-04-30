package com.namebattler.battle.strategy;

public enum AllStrategy {
    DAMAGE_PRIORITY(new DamagePriority()),
    LEAST_HP(new LeastHP()),
    NO_SKILL(new NoSkill()),
    PLEASE_FREE(new PleaseFree()),
    LEAST_DEF(new LeastDef()),
    ;

    Strategy strategy;

    AllStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

}
