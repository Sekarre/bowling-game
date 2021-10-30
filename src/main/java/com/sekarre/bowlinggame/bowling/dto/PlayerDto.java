package com.sekarre.bowlinggame.bowling.dto;

import com.sekarre.bowlinggame.domain.enums.ScoreType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {

    private Long id;

    private String name;

    private Integer numberInQueue;

    private Integer playerScore;

    private Integer lastHitPinsCount;

    private ScoreType scoreType;

    private Integer turnOfRound;
}
