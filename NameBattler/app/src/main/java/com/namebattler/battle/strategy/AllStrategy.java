package com.namebattler.battle.strategy;

public enum AllStrategy {
    DAMAGE_PRIORITY(new StrategyOfDamagePriority()),
    LEAST_HP(new StrategyOfLeastHP()),
    NO_SKILL(new StrategyOfNoSkill()),
    ;

    Strategy strategy;

    AllStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

}
