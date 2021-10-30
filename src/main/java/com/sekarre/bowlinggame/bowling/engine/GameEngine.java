package com.sekarre.bowlinggame.bowling.engine;

import com.sekarre.bowlinggame.bowling.repositories.GameRepository;
import com.sekarre.bowlinggame.bowling.repositories.PlayerRepository;
import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.domain.enums.SpecialScoreType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.sekarre.bowlinggame.domain.enums.GameProperties.*;

@RequiredArgsConstructor
@Component
public class GameEngine {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final PlayerGenerator playerGenerator;

    public Game generateNewGame(Integer playersCount) {
        Game newGame = Game.builder()
                .playersCount(playersCount)
                .currentRound(1)
                .totalRound(10)
                .gameEnded(false)
                .winner("")
                .build();

        List<Player> players = playerGenerator.createPlayers(playersCount, newGame);
        playerRepository.saveAll(players);

        newGame.setPlayers(players);
        newGame.setCurrentMovingPlayer(newGame.getPlayers().get(0));

        return newGame;
    }

    public Integer generateRandomPinsHit() {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        return randomDataGenerator.nextInt(0, 10);
    }

    public Player setPlayerScore(Integer hitPins, Player player) {

        player.setLastHitPinsCount(hitPins);

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
            if (isNextRound(game.getPlayers(), player)) {
                game.setCurrentRound(game.getCurrentRound() + 1);
            }
            game.setCurrentMovingPlayer(game.getPlayers().stream()
                    .filter(p -> p.getNumberInQueue().equals((player.getNumberInQueue() + 1) % game.getPlayersCount()))
                    .findFirst()
                    .orElse(null));

            player.setTurnOfRound(FIRST_TURN);
        } else {
            player.setTurnOfRound(LAST_TURN);
        }

        return gameRepository.save(game);
    }

    private boolean isNextRound(List<Player> players, Player currentPlayer) {
        return players.stream().noneMatch(p -> p.getNumberInQueue().equals((currentPlayer.getNumberInQueue() + 1)));
    }

    private Integer getStrikeScore(Integer hitPins, Player player) {
        return player.getTurnOfRound().equals(LAST_TURN) ?
                player.getScore() + hitPins + SpecialScoreType.STRIKE.getScore() :
                player.getScore() + hitPins;
    }
}
