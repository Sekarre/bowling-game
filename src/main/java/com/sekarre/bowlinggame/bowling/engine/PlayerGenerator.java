package com.sekarre.bowlinggame.bowling.engine;

import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.domain.enums.GameProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class PlayerGenerator {

    public List<Player> createPlayers(Integer playersCount, Game newGame) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playersCount; i++) {
            Player player = createNewPlayer();
            player.setName("Player " + i);
            player.setNumberInQueue(i);
            player.setGame(newGame);
            players.add(player);
        }

        return players;
    }

    private Player createNewPlayer() {
        return Player.builder()
                .name("Player 1")
                .turnOfRound(GameProperties.FIRST_TURN)
                .score(0)
                .build();
    }
}
