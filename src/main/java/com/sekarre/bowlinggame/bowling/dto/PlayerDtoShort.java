package com.sekarre.bowlinggame.bowling.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDtoShort {

    private String name;

    private Integer turnOfRound;

    private Integer thisRoundHitPinsCount;
}
