package com.namebattler.battle.strategy;

public enum AllStrategy {
    DAMAGE_PRIORITY(new DamagePriority()),
    LEAST_HP(new LeastHP()),
    NO_SKILL(new NoSkill()),
    ;

    Strategy strategy;

    AllStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

}
