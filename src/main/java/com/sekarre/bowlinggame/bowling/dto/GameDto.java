package com.sekarre.bowlinggame.bowling.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDto {

    private UUID id;

    private Integer playersCount;

    private Integer currentRound;

    private Integer totalRound;

    private Integer lastHitPins;

    private boolean gameEnded;

    private String winner;

    private PlayerDtoShort currentMovingPlayer;

    private List<PlayerDto> players;
}
