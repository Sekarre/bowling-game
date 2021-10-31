package com.sekarre.bowlinggame.bowling.engine;

import com.sekarre.bowlinggame.bowling.repositories.GameRepository;
import com.sekarre.bowlinggame.bowling.repositories.PlayerRepository;
import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.domain.enums.GameProperties;
import com.sekarre.bowlinggame.factories.GameMockFactory;
import com.sekarre.bowlinggame.factories.PlayerMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GameEngineTest {

    @Mock
    GameRepository gameRepository;

    @Mock
    PlayerRepository playerRepository;

    @Mock
    PlayerGenerator playerGenerator;

    @Mock
    ScoreGenerator scoreGenerator;

    GameEngine gameEngine;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        gameEngine = new GameEngine(gameRepository, playerRepository, playerGenerator, scoreGenerator);
    }

    @Test
    void should_generate_new_game() {
        //given
        Integer playersCount = 2;
        Player player = PlayerMockFactory.buildPlayerMock();
        Game game = GameMockFactory.buildGameMock();
        List<Player> players = List.of(player);
        when(playerGenerator.createPlayers(any(), any())).thenReturn(players);
        when(gameRepository.save(any())).thenReturn(game);

        //when
        Game result = gameEngine.generateNewGame(playersCount);

        //then
        assertEquals(game, result);
        verify(playerGenerator, times(1)).createPlayers(any(), any());
        verify(playerRepository, times(1)).saveAll(players);
        verify(gameRepository, times(1)).save(any());
    }

    @Test
    void should_generate_random_pins_hit() {
        //given
        Integer pinsHit = 2;
        Integer lastPinHits = 4;
        when(scoreGenerator.generateRandomPinsHit(any())).thenReturn(pinsHit);

        //when
        Integer result = gameEngine.generateRandomPinsHit(lastPinHits);

        //then
        assertEquals(pinsHit, result);
        verify(scoreGenerator, times(1)).generateRandomPinsHit(lastPinHits);
    }

    @Test
    void should_set_normal_player_score() {
        //given
        Integer pinsHit = 2;
        Player player = PlayerMockFactory.buildPlayerMock();
        when(playerRepository.save(any())).thenReturn(player);

        //when
        Player result = gameEngine.setPlayerScore(pinsHit, player);

        //then
        assertEquals(player, result);
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    void should_set_next_turn() {
        //given
        Player player = PlayerMockFactory.buildPlayerMock();
        player.setTurnOfRound(GameProperties.LAST_TURN);
        Game game = GameMockFactory.buildGameMock();
        when(gameRepository.save(any())).thenReturn(game);

        //when
        Game result = gameEngine.setNextTurn(player, game);

        //then
        assertEquals(game, result);
        verify(gameRepository, times(1)).save(any());
    }

    @Test
    void should_check_if_is_end_game_and_return_false() {
        //given
        Game game = GameMockFactory.buildGameMock();

        //when
        boolean result = gameEngine.isEndGame(game);

        //then
        assertFalse(result);
    }

    @Test
    void should_close_game() {
        //given
        Game game = GameMockFactory.buildGameMock();
        when(gameRepository.save(any())).thenReturn(game);

        //when
        Game result = gameEngine.closeGame(game);

        //then
        assertEquals(game, result);
        verify(gameRepository, times(1)).save(any());
    }
}