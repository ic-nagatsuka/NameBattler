package com.namebattler.battle.Strategy;

public class AllStrategy {

    AllStrategy(){

    }



    public enum Strategies{
        DAMAGE_PRIORITY(new StrategyOfDamagePriority()),
        LEAST_HP(new StrategyOfLeastHP()),
        NO_SKILL(new StrategyOfNoSkill());

        Strategy strategy;

        Strategies(Strategy strategy){
            this.strategy = strategy;
        }

        public Strategy getStrategy(){
            return this.strategy;
        }


    }

}
