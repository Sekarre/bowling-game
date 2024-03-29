package com.sekarre.bowlinggame.bowling.services.impl;

import com.sekarre.bowlinggame.bowling.exceptions.NotFoundException;
import com.sekarre.bowlinggame.bowling.repositories.PlayerRepository;
import com.sekarre.bowlinggame.bowling.services.PlayerService;
import com.sekarre.bowlinggame.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId).orElseThrow(() -> new NotFoundException("Player not found"));
    }
}
