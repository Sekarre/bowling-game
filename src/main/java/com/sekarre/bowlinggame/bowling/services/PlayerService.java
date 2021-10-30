package com.sekarre.bowlinggame.bowling.services;

import com.sekarre.bowlinggame.domain.Player;

public interface PlayerService {

    void deletePlayer(Player player);

    Player getPlayerById(Long playerId);
}
