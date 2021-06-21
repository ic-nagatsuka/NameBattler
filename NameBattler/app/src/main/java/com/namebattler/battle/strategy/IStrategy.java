package com.namebattler.battle.strategy;

import com.namebattler.battle.party.Party;
import com.namebattler.battle.player.Player;

public interface IStrategy {

    void action(Player attacker, Party defenseParty);
}
