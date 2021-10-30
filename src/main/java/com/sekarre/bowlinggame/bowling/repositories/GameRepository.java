package com.sekarre.bowlinggame.bowling.repositories;

import com.sekarre.bowlinggame.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
}
