package com.sekarre.bowlinggame.bowling.services.impl;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.bowling.exceptions.NotFoundException;
import com.sekarre.bowlinggame.bowling.mappers.GameMapper;
import com.sekarre.bowlinggame.bowling.repositories.GameRepository;
import com.sekarre.bowlinggame.bowling.services.GameService;
import com.sekarre.bowlinggame.bowling.services.PlayerService;
import com.sekarre.bowlinggame.bowling.util.GameEngine;
import com.sekarre.bowlinggame.domain.Game;
import com.sekarre.bowlinggame.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final GameMapper gameMapper;
    private final GameEngine gameEngine;

    @Override
    public String getNewGameId(Integer playersCount) {
        Game game = gameEngine.generateNewGame(playersCount);

        return gameRepository.save(game).getId().toString();
    }

    @Override
    public GameDto getUpdatedGame(UUID gameId, UUID playerId) {
        Game game = getGameById(gameId);

        if (game.isGameEnded()) {
            throw new IllegalStateException("Game ended");
        }

        Player player = playerService.getPlayerById(playerId);

        player = gameEngine.setPlayerScore(gameEngine.generateRandomPinsHit(), player);
        game = gameEngine.setNextTurn(player, game);

        return gameMapper.mapGameToGameDto(game);
    }

    private Game getGameById(UUID id) {
        return gameRepository.findById(id).orElseThrow(() -> new NotFoundException("Game not found"));
    }
}
