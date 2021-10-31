package com.sekarre.bowlinggame.bowling.mappers;

import com.sekarre.bowlinggame.bowling.dto.PlayerDto;
import com.sekarre.bowlinggame.bowling.dto.PlayerDtoShort;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.factories.PlayerMockFactory;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class PlayerMapperTest {

    PlayerMapper playerMapper = Mappers.getMapper(PlayerMapper.class);

    @Test
    void should_map_player_to_player_dto() {
        //given
        Player source = PlayerMockFactory.buildPlayerMock();

        //when
        PlayerDto result = playerMapper.mapPlayerToPlayerDto(source);

        //then
        assertEquals(source.getId(), result.getId());
        assertEquals(source.getName(), result.getName());
        assertEquals(source.getNumberInQueue(), result.getNumberInQueue());
        assertEquals(source.getScore(), result.getPlayerScore());
        assertEquals(source.getThisRoundHitPinsCount(), result.getThisRoundHitPinsCount());
        assertEquals(source.getScoreType(), result.getScoreType());
        assertEquals(source.getTurnOfRound(), result.getTurnOfRound());
    }

    @Test
    void should_player_to_player_dto_short() {
        //given
        Player source = PlayerMockFactory.buildPlayerMock();

        //when
        PlayerDtoShort result = playerMapper.mapPlayerToPlayerDtoShort(source);

        //then
        assertEquals(source.getName(), result.getName());
        assertEquals(source.getTurnOfRound(), result.getTurnOfRound());
        assertEquals(source.getThisRoundHitPinsCount(), result.getThisRoundHitPinsCount());
    }
}