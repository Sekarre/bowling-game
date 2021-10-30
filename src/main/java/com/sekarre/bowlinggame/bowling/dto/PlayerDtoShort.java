package com.sekarre.bowlinggame.bowling.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDtoShort {

    private Long id;
    private Integer turnOfRound;
}
