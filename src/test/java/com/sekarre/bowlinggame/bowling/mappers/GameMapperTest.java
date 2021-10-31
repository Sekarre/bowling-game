package com.sekarre.bowlinggame.bowling.mappers;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.factories.GameMockFactory;
import com.sekarre.bowlinggame.factories.PlayerMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GameMapperTest {

    @InjectMocks
    GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

    @Mock
    PlayerMapper playerMapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_map_game_to_game_dto() {
        //given
        Game source = GameMockFactory.buildGameMock();

        //when
        GameDto result = gameMapper.mapGameToGameDto(source);

        //then
        assertEquals(source.getId(), result.getId());
        assertEquals(source.getPlayersCount(), result.getPlayersCount());
        assertEquals(source.getCurrentRound(), result.getCurrentRound());
        assertEquals(source.getTotalRound(), result.getTotalRound());
        assertEquals(source.getLastHitPins(), result.getLastHitPins());
        assertEquals(source.isGameEnded(), result.isGameEnded());
        assertEquals(source.getWinner(), result.getWinner());
    }
}