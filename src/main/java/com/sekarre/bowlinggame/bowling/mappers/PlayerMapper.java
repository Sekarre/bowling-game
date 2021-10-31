package com.sekarre.bowlinggame.bowling.mappers;

import com.sekarre.bowlinggame.bowling.dto.PlayerDto;
import com.sekarre.bowlinggame.bowling.dto.PlayerDtoShort;
import com.sekarre.bowlinggame.domain.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class PlayerMapper {

    @Mapping(target = "playerScore", source = "score")
    public abstract PlayerDto mapPlayerToPlayerDto(Player player);

    public abstract PlayerDtoShort mapPlayerToPlayerDtoShort(Player player);
}
