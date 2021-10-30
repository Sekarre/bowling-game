package com.sekarre.bowlinggame.bowling.repositories;

import com.sekarre.bowlinggame.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {

    @Query("select g from Game g join fetch g.players where g.id = :id")
    Optional<Game> findByIdFetch(UUID id);
}
