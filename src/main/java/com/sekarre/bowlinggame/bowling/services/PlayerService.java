package com.sekarre.bowlinggame.bowling.services;

import com.sekarre.bowlinggame.domain.Player;

public interface PlayerService {

    Player getPlayerById(Long playerId);
}
