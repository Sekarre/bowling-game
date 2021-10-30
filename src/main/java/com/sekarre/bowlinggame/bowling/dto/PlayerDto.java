package com.sekarre.bowlinggame.bowling.dto;

import com.sekarre.bowlinggame.domain.enums.SpecialScoreType;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {
    private UUID id;
    private Integer playerScore;
    private Integer hitPins;
    private SpecialScoreType specialScoreType;
    private Integer turnOfRound;
}
