package com.sekarre.bowlinggame.factories;

import com.sekarre.bowlinggame.bowling.dto.PlayerDtoShort;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.domain.enums.ScoreType;

import java.util.UUID;

public class PlayerMockFactory {

    public static Player buildPlayerMock() {
        return Player.builder()
                .id(1L)
                .numberInQueue(1)
                .name("name")
                .thisRoundHitPinsCount(1)
                .score(10)
                .bonusStrikeScore(10)
                .turnOfRound(1)
                .build();
    }

    public static PlayerDtoShort buildPlayerShortDto() {
        return PlayerDtoShort.builder()
                .name("name")
                .thisRoundHitPinsCount(1)
                .turnOfRound(1)
                .build();
    }
}
