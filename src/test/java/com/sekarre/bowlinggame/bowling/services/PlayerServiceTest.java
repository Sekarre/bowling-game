package com.sekarre.bowlinggame.bowling.services;

import com.sekarre.bowlinggame.bowling.repositories.PlayerRepository;
import com.sekarre.bowlinggame.bowling.services.impl.PlayerServiceImpl;
import com.sekarre.bowlinggame.domain.Player;
import com.sekarre.bowlinggame.factories.PlayerMockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    @Mock
    PlayerRepository playerRepository;

    private PlayerService playerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        playerService = new PlayerServiceImpl(playerRepository);
    }

    @Test
    void should_get_player_by_id() {
        //given
        Player player = PlayerMockFactory.buildPlayerMock();
        when(playerRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(player));

        //when
        Player result = playerService.getPlayerById(1L);

        //then
        assertEquals(player, result);
        verify(playerRepository, times(1)).findById(1L);
    }
}