package com.sekarre.bowlinggame.bowling.mappers;

import com.sekarre.bowlinggame.bowling.dto.PlayerDto;
import com.sekarre.bowlinggame.domain.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class PlayerMapper {

    @Mapping(source = "playerScore", target = "score")
    public abstract Player mapPlayerScoreDtoToPlayer(PlayerDto playerDto);
}
