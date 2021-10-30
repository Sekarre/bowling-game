package com.sekarre.bowlinggame.bowling.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDto {
    private Integer playersCount;
    private Integer currentRound;
    private Integer totalRound;
    private boolean gameEnded;
    private String winner;
    private PlayerDto currentPlayerMoving;
    private List<PlayerDto> players;
}
