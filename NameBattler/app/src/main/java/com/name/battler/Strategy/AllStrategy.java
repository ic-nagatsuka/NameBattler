package com.name.battler.Strategy;

public class AllStrategy {

    AllStrategy(){

    }



    public enum Strategies{
        DAMAGE_PRIORITY(new StrategyOfDamagePriority()),
        LEAST_HP(new StrategyOfLeastHP()),
        NO_SKILL(new StrategyOfNoSkill());

        Strategies(Strategy strategy){

        }

    }

}
