package com.sekarre.bowlinggame.bowling.dto;

import com.sekarre.bowlinggame.domain.enums.SpecialScoreType;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {

    private Long id;

    private Integer numberInQueue;

    private Integer playerScore;

    private Integer lastHitPinsCount;

    private SpecialScoreType specialScoreType;

    private Integer turnOfRound;
}
