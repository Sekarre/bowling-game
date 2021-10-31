package com.sekarre.bowlinggame.bowling.services;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.bowling.engine.GameEngine;
import com.sekarre.bowlinggame.bowling.mappers.GameMapper;
import com.sekarre.bowlinggame.bowling.repositories.GameRepository;
import com.sekarre.bowlinggame.bowling.services.impl.GameServiceImpl;
import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.domain.enums.GameProperties;
import com.sekarre.bowlinggame.factories.GameMockFactory;
import com.sekarre.bowlinggame.factories.PlayerMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @Mock
    GameRepository gameRepository;

    @Mock
    PlayerService playerService;

    @Mock
    GameMapper gameMapper;

    @Mock
    GameEngine gameEngine;

    GameService gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        gameService = new GameServiceImpl(gameRepository, playerService, gameMapper, gameEngine);
    }

    @Test
    void should_get_new_game() {
        //given
        Game game = GameMockFactory.buildGameMock();
        GameDto gameDto = GameMockFactory.buildGameDtoMock();
        when(gameEngine.generateNewGame(any())).thenReturn(game);
        when(gameMapper.mapGameToGameDto(any())).thenReturn(gameDto);
        when(gameRepository.save(any())).thenReturn(game);

        //when
        GameDto result = gameService.getNewGame(1);

        //then
        assertEquals(gameDto, result);
        verify(gameEngine, times(1)).generateNewGame(1);
    }

    @Test
    void should_get_updated_game() {
        //given
        Integer pinsHit = 1;
        UUID id = UUID.randomUUID();
        Game game = GameMockFactory.buildGameMock();
        Player player = PlayerMockFactory.buildPlayerMock();
        GameDto gameDto = GameMockFactory.buildGameDtoMock();
        when(gameRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(game));
        when(gameEngine.isEndGame(any())).thenReturn(false);
        when(playerService.getPlayerById(any())).thenReturn(player);
        when(gameEngine.setPlayerScore(any(), any())).thenReturn(player);
        when(gameEngine.generateRandomPinsHit(any())).thenReturn(pinsHit);
        when(gameEngine.setNextTurn(any(), any())).thenReturn(game);
        when(gameMapper.mapGameToGameDto(any())).thenReturn(gameDto);

        //when
        GameDto result = gameService.getUpdatedGame(id);

        //then
        assertEquals(gameDto, result);
        verify(gameRepository, times(1)).findById(id);
        verify(playerService, times(1)).getPlayerById(Objects.requireNonNull(game).getCurrentMovingPlayer().getId());
        verify(gameEngine, times(1)).isEndGame(game);
        verify(gameEngine, times(1)).setPlayerScore(pinsHit, player);
        verify(gameEngine, times(1)).setNextTurn(player, game);
        verify(gameMapper, times(1)).mapGameToGameDto(game);
    }

    @Test
    void should_get_updated_game_with_winner_and_game_closed() {
        //given
        Integer pinsHit = 1;
        UUID id = UUID.randomUUID();
        Game game = GameMockFactory.buildGameMock();
        Player player = PlayerMockFactory.buildPlayerMock();
        GameDto gameDto = GameMockFactory.buildGameDtoMock();
        when(gameRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(game));
        when(gameEngine.isEndGame(any())).thenReturn(true);
        when(gameEngine.closeGame(any())).thenReturn(game);
        when(gameMapper.mapGameToGameDto(any())).thenReturn(gameDto);

        //when
        GameDto result = gameService.getUpdatedGame(id);

        //then
        assertEquals(gameDto, result);
        verify(gameRepository, times(1)).findById(id);
        verify(playerService, times(0)).getPlayerById(Objects.requireNonNull(game).getCurrentMovingPlayer().getId());
        verify(gameEngine, times(1)).isEndGame(game);
        verify(gameEngine, times(0)).setPlayerScore(pinsHit, player);
        verify(gameEngine, times(0)).setNextTurn(player, game);
        verify(gameMapper, times(1)).mapGameToGameDto(game);
    }

    @Test
    void should_get_game() {
        //given
        UUID id = UUID.randomUUID();
        Game game = GameMockFactory.buildGameMock();
        GameDto gameDto = GameMockFactory.buildGameDtoMock();
        when(gameRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(game));
        when(gameMapper.mapGameToGameDto(any())).thenReturn(gameDto);

        //when
        GameDto result = gameService.getGame(id);

        //then
        assertEquals(gameDto, result);
        verify(gameRepository, times(1)).findById(id);
        verify(gameMapper, times(1)).mapGameToGameDto(game);
    }

    @Test
    void should_get_max_number_of_players() {
        //given
        Integer maxPlayers = GameProperties.MAX_PLAYERS;

        //when
        Integer result = gameService.getMaxNumberOfPlayers();

        //then
        assertEquals(maxPlayers, result);
    }
}