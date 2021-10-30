package com.sekarre.bowlinggame.bowling.services;

import com.sekarre.bowlinggame.domain.Player;

import java.util.List;
import java.util.UUID;

public interface PlayerService {

    void deletePlayer(Player player);

    Player getPlayerById(UUID playerId);
}
