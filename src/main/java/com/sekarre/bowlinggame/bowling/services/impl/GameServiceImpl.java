package com.sekarre.bowlinggame.bowling.services.impl;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.bowling.exceptions.NotFoundException;
import com.sekarre.bowlinggame.bowling.mappers.GameMapper;
import com.sekarre.bowlinggame.bowling.repositories.GameRepository;
import com.sekarre.bowlinggame.bowling.services.GameService;
import com.sekarre.bowlinggame.bowling.services.PlayerService;
import com.sekarre.bowlinggame.bowling.engine.GameEngine;
import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.domain.enums.GameProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final GameMapper gameMapper;
    private final GameEngine gameEngine;

    @Override
    public GameDto getNewGame(Integer playersCount) {
        Game game = gameEngine.generateNewGame(playersCount);

        return gameMapper.mapGameToGameDto(gameRepository.save(game));
    }

    @Override
    public GameDto getUpdatedGame(UUID gameId) {
        Game game = getGameById(gameId);

        if (gameEngine.isEndGame(game)) {
            sortPlayersByScore(game);
            return gameMapper.mapGameToGameDto(gameEngine.closeGame(game));
        }

        Player player = playerService.getPlayerById(game.getCurrentMovingPlayer().getId());

        player = gameEngine.setPlayerScore(gameEngine.generateRandomPinsHit(player.getThisRoundHitPinsCount()), player);
        game = gameEngine.setNextTurn(player, game);

        return gameMapper.mapGameToGameDto(game);
    }

    @Override
    public GameDto getGame(UUID gameId) {
        return gameMapper.mapGameToGameDto(getGameById(gameId));
    }

    @Override
    public Integer getMaxNumberOfPlayers() {
        return GameProperties.MAX_PLAYERS;
    }

    private void sortPlayersByScore(Game game) {
        List<Player> sortedPlayers = game.getPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getScore).reversed())
                .collect(Collectors.toList());

        game.setPlayers(sortedPlayers);
    }

    private Game getGameById(UUID id) {
        return gameRepository.findById(id).orElseThrow(() -> new NotFoundException("Game not found"));
    }
}