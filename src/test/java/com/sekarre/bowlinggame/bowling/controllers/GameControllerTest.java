package com.sekarre.bowlinggame.bowling.controllers;

import com.sekarre.bowlinggame.bowling.dto.GameDto;
import com.sekarre.bowlinggame.bowling.services.GameService;
import com.sekarre.bowlinggame.domain.enums.GameProperties;
import com.sekarre.bowlinggame.factories.GameMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GameControllerTest {

    @Mock
    GameService gameService;

    MockMvc mockMvc;

    GameController gameController;

    private static final String BASE_URL = "/api/bowling-game/";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController(gameService);

        mockMvc = MockMvcBuilders.standaloneSetup(gameController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void should_get_max_number_players() throws Exception {
        //given
        Integer maxPlayers = GameProperties.MAX_PLAYERS;
        when(gameService.getMaxNumberOfPlayers()).thenReturn(maxPlayers);

        //when + then
        mockMvc.perform(get(BASE_URL + "number-of-players"))
                .andExpect(status().isOk());

        verify(gameService, times(1)).getMaxNumberOfPlayers();
    }

    @Test
    void should_get_new_game() throws Exception {
        //given
        Integer playersCount = 2;
        GameDto gameDto = GameMockFactory.buildGameDtoMock();
        when(gameService.getNewGame(playersCount)).thenReturn(gameDto);

        //when + then
        mockMvc.perform(get(BASE_URL + "new-game")
                .param("playersCount", playersCount.toString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(gameDto.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playersCount").value(gameDto.getPlayersCount()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentRound").value(gameDto.getCurrentRound()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalRound").value(gameDto.getTotalRound()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastHitPins").value(gameDto.getLastHitPins()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameEnded").value(gameDto.isGameEnded()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner").value(gameDto.getWinner()));

        verify(gameService, times(1)).getNewGame(playersCount);
    }

    @Test
    void should_get_existing_game() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        GameDto gameDto = GameMockFactory.buildGameDtoMock();
        when(gameService.getGame(any())).thenReturn(gameDto);

        //when + then
        mockMvc.perform(get(BASE_URL + "game/" + id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(gameDto.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playersCount").value(gameDto.getPlayersCount()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentRound").value(gameDto.getCurrentRound()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalRound").value(gameDto.getTotalRound()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastHitPins").value(gameDto.getLastHitPins()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameEnded").value(gameDto.isGameEnded()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner").value(gameDto.getWinner()));

        verify(gameService, times(1)).getGame(id);
    }

    @Test
    void should_get_updated_game() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        GameDto gameDto = GameMockFactory.buildGameDtoMock();
        when(gameService.getUpdatedGame(any())).thenReturn(gameDto);

        //when + then
        mockMvc.perform(get(BASE_URL + "game-update/" + id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(gameDto.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playersCount").value(gameDto.getPlayersCount()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentRound").value(gameDto.getCurrentRound()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalRound").value(gameDto.getTotalRound()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastHitPins").value(gameDto.getLastHitPins()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameEnded").value(gameDto.isGameEnded()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winner").value(gameDto.getWinner()));

        verify(gameService, times(1)).getUpdatedGame(id);
    }
}