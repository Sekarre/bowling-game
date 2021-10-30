package com.sekarre.bowlinggame.bowling.util;

import com.sekarre.bowlinggame.bowling.repositories.GameRepository;
import com.sekarre.bowlinggame.bowling.repositories.PlayerRepository;
import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.domain.enums.SpecialScoreType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.sekarre.bowlinggame.domain.enums.GameProperties.*;

@RequiredArgsConstructor
@Component
public class GameEngine {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final PlayerGenerator playerGenerator;

    public Game generateNewGame(Integer playersCount) {
        List<Player> players = playerGenerator.createPlayers(playersCount);
        players = playerRepository.saveAll(players);

        Game newGame = Game.builder()
                .playersCount(playersCount)
                .currentRound(1)
                .totalRound(10)
                .gameEnded(false)
                .winner("")
                .players(players)
                .build();

        newGame.setCurrentPlayerMoving(newGame.getPlayers().get(0));

        return newGame;
    }

    public Integer generateRandomPinsHit() {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        return randomDataGenerator.nextInt(0, 10);
    }

    public Player setPlayerScore(Integer hitPins, Player player) {

        if (player.getSpecialScoreType() != null) {
            player.setScore(getStrikeScore(hitPins, player));

            return playerRepository.save(player);
        }

        if (hitPins.equals(MAX_PINS) && player.getTurnOfRound().equals(FIRST_TURN)) {
            player.setSpecialScoreType(SpecialScoreType.STRIKE);
            player.setScore(player.getScore() + SpecialScoreType.STRIKE.getScore());

            return playerRepository.save(player);
        }

        if (hitPins.equals(MAX_PINS) && player.getTurnOfRound().equals(LAST_TURN)) {
            player.setSpecialScoreType(SpecialScoreType.SPARE);
            player.setScore(player.getScore() + SpecialScoreType.SPARE.getScore());

            return playerRepository.save(player);
        }

        player.setScore(player.getScore() + hitPins);

        return playerRepository.save(player);
    }

    public Game setNextTurn(Player player, Game game) {
        if (player.getTurnOfRound().equals(LAST_TURN)) {
            game.setCurrentRound(game.getCurrentRound() + 1);
            game.setCurrentPlayerMoving(game.getPlayers().stream()
                    .filter(p -> p.getNumberInQueue().equals((player.getNumberInQueue() + 1) % game.getPlayersCount()))
                    .findFirst()
                    .orElse(null));
            player.setTurnOfRound(FIRST_TURN);

            return gameRepository.save(game);
        } else {
            player.setTurnOfRound(LAST_TURN);

            return game;
        }
    }

    private Integer getStrikeScore(Integer hitPins, Player player) {
        return player.getTurnOfRound().equals(LAST_TURN) ?
                hitPins + SpecialScoreType.STRIKE.getScore() :
                hitPins;
    }
}
