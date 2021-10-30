package com.sekarre.bowlinggame.bowling.controllers;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.bowling.dto.NewGameDto;
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
    public ResponseEntity<NewGameDto> getNewGame(@RequestParam Integer playersCount) {
        return ResponseEntity.ok(gameService.getNewGame(playersCount));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<GameDto> getGamePlayerScore(@PathVariable UUID gameId) {
        return ResponseEntity.ok(gameService.getUpdatedGame(gameId));
    }
}
