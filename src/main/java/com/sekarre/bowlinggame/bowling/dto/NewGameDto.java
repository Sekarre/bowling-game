package com.sekarre.bowlinggame.bowling.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewGameDto {

    private UUID id;

    private List<PlayerDto> players;

    private PlayerDtoShort currentMovingPlayer;
}
