package com.sekarre.bowlinggame.bowling.services;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.bowling.dto.PlayerDto;

import java.util.UUID;

public interface GameService {

    String getNewGameId(Integer playersCount);

    GameDto getUpdatedGame(UUID gameId, UUID playerId);
}
