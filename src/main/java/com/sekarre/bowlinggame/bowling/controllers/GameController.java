package com.sekarre.bowlinggame.bowling.controllers;

import com.sekarre.bowlinggame.bowling.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/bowling-game")
public class GameController {

    private final GameService gameService;

    @GetMapping("/new-game")
    public ResponseEntity<String> getNewGameId(@RequestParam Integer playersCount) {
        return ResponseEntity.ok(gameService.getNewGameId(playersCount));
    }

    @PostMapping("/score-update/{gameId}/{playerId}")
    public ResponseEntity<?> getGamePlayerScore(@PathVariable UUID gameId, @PathVariable UUID playerId) {
        gameService.getUpdatedGame(gameId, playerId);

        return ResponseEntity.ok().build();
    }

}
