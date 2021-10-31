package com.sekarre.bowlinggame.bowling.services;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.bowling.dto.NewGameDto;

import java.util.UUID;

public interface GameService {

    GameDto getNewGame(Integer playersCount);

    GameDto getUpdatedGame(UUID gameId);

    GameDto getGame(UUID gameId);

    Integer getMaxNumberOfPlayers();
}
