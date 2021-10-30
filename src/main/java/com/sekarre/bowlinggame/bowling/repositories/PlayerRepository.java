package com.sekarre.bowlinggame.bowling.repositories;

import com.sekarre.bowlinggame.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
