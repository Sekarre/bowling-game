package com.sekarre.bowlinggame.bowling.util;

import org.springframework.stereotype.Component;

@Component
class PlayerGenerator {

    private List<Player> createPlayers(Integer playersCount) {
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playersCount; i++) {
            Player player = createNewPlayer();
            player.setName("Player " + i);
            player.setNumberInQueue(i);
            players.add(player);
        }

        return players;
    }

    private Player createNewPlayer() {
        return Player.builder()
                .name("Player 1")
                .score(0)
                .build();
    }
}
