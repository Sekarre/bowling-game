package com.sekarre.bowlinggame.factories;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.domain.Game;

import java.util.List;
import java.util.UUID;

public class GameMockFactory {

    public static Game buildGameMock() {
        return Game.builder()
                .id(UUID.randomUUID())
                .playersCount(1)
                .currentRound(1)
                .totalRound(1)
                .lastHitPins(1)
                .winner("Winner")
                .gameEnded(false)
                .currentMovingPlayer(PlayerMockFactory.buildPlayerMock())
                .players(List.of(PlayerMockFactory.buildPlayerMock()))
                .build();
    }

    public static GameDto buildGameDtoMock() {
        return GameDto.builder()
                .id(UUID.randomUUID())
                .playersCount(1)
                .currentRound(1)
                .totalRound(1)
                .lastHitPins(1)
                .gameEnded(false)
                .winner("Winner")
                .currentMovingPlayer(PlayerMockFactory.buildPlayerShortDto())
                .build();
    }
}
