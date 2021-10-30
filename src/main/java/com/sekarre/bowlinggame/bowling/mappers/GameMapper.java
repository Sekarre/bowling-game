package com.sekarre.bowlinggame.bowling.mappers;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.bowling.dto.NewGameDto;
import com.sekarre.bowlinggame.domain.Game;
import org.mapstruct.Mapper;

@Mapper(uses = PlayerMapper.class)
public abstract class GameMapper {

    public abstract GameDto mapGameToGameDto(Game game);

    public abstract NewGameDto mapGameToNewGameDto(Game game);
}
