package com.sekarre.bowlinggame.bowling.engine;

import com.sekarre.bowlinggame.bowling.repositories.GameRepository;
import com.sekarre.bowlinggame.bowling.repositories.PlayerRepository;
import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.domain.enums.ScoreType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

        if (player.getScoreType() != null && player.getScoreType().equals(ScoreType.SPARE)) {
            setSpareScore(hitPins, player);
        }

        if (player.getScoreType() != null && player.getScoreType().equals(ScoreType.STRIKE)) {
            setStrikeScore(hitPins, player);
        }

        //if spare or strike
        if (hitPins.equals(MAX_PINS)) {
            return setScoreSpareOrStrike(player);
        }

        //default
        player.setScore(player.getScore() + hitPins);

        return playerRepository.save(player);
    }

    private void setSpareScore(Integer hitPins, Player player) {
        player.setScore(getSpareScore(hitPins, player));
        player.setScoreType(null);
    }

    private void setStrikeScore(Integer hitPins, Player player) {
        if (player.getTurnOfRound().equals(FIRST_TURN)) {
            player.setBonusStrikeScore(hitPins);
        } else {
            player.setScore(getStrikeScore(hitPins, player));
            player.setScoreType(null);
        }
    }

    private Player setScoreSpareOrStrike(Player player) {
        if (player.getTurnOfRound().equals(LAST_TURN)) {
            setScore(player, ScoreType.SPARE);
        } else {
            setScore(player, ScoreType.STRIKE);
            player.setTurnOfRound(LAST_TURN);
        }

        return playerRepository.save(player);
    }

    private Player setScore(Player player, ScoreType scoreType) {
        player.setScoreType(scoreType);
        player.setScore(player.getScore() + scoreType.getScore());

        return player;
    }

    private Integer getStrikeScore(Integer hitPins, Player player) {
        return player.getScore() + hitPins + ScoreType.STRIKE.getScore() + player.getBonusStrikeScore();
    }

    private Integer getSpareScore(Integer hitPins, Player player) {
        return (player.getScore() + hitPins + ScoreType.SPARE.getScore());
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

    public boolean isEndGame(Game game) {
        return game.getCurrentRound() > MAX_ROUND;
    }

    public Game closeGame(Game game) {
        game.setGameEnded(true);
        game.setWinner(determineWinner(game).getName());

        return game;
    }

    private Player determineWinner(Game game) {
        Optional<Player> winner = game.getPlayers().stream().max(Comparator.comparingInt(Player::getScore));

        return winner.orElse(Player.builder().name("Draw").build());
    }

    private boolean isNextRound(List<Player> players, Player currentPlayer) {
        return players.stream().noneMatch(p -> p.getNumberInQueue().equals((currentPlayer.getNumberInQueue() + 1)));
    }
}
