package com.sekarre.bowlinggame.bowling.engine;

import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.factories.GameMockFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerGeneratorTest {

    PlayerGenerator playerGenerator = new PlayerGenerator();

    @Test
    void should_create_players() {
        //given
        Integer playersCount = 2;
        Game game = GameMockFactory.buildGameMock();

        //when
        List<Player> players = playerGenerator.createPlayers(playersCount, game);

        //then
        assertEquals(players.size(), playersCount);
        assertEquals(players.get(0).getGame(), game);
    }
}