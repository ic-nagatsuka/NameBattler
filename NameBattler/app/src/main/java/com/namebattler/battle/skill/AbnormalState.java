package com.namebattler.battle.skill;

import com.namebattler.battle.player.Player;

public interface AbnormalState {

    void effect(Player target, int turn);
}
